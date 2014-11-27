package com.dempe.analysis.core.filequeue;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 基于内存映射的文件读写Buffer 由于采用了内存映射方式,所以约定单文件长度不超过1GB
 */
public class FileBuffer {
    private static final Logger LOGGER = Logger.getLogger(FileBuffer.class);
    /**
     * 默认的缓冲区大小 : 4MB
     */
    public static final int DEFAULT_BUFFER_SIZE = 4 * 1024 * 1024; // 4m

    /**
     * 文件的最大SIZE:1GB
     */
    public static final int MAX_FILE_LENGTH = 1024 * 1024 * 1024; // 1G

    private final Lock lock = new ReentrantLock();

    private MappedByteBuffer readBuffer = null;
    private int readPostion = 0;
    private int readMapOffset = 0;

    private MappedByteBuffer writeBuffer = null;
    private int writePostion = 0;
    private int writeMapOffset = 0;

    private File file;
    private RandomAccessFile raf = null;
    private FileChannel channel;

    private final int bufferSize;

    public FileBuffer(File file) throws IOException {
        this(file, DEFAULT_BUFFER_SIZE);
    }

    public FileBuffer(File file, int bufferSize) throws IOException {
        this.bufferSize = bufferSize;
        this.file = file;
        initialize();
    }

    protected synchronized FileChannel getFileChannel() {
        if (this.channel == null) {
            this.channel = getRandomAccessFile().getChannel();
        }
        return this.channel;
    }

    protected synchronized RandomAccessFile getRandomAccessFile() {
        if (this.raf == null) {
            try {
                this.raf = new RandomAccessFile(file, "rwd");
            } catch (IOException e) {
                throw new IOError(e);
            }
        }

        return this.raf;
    }

    protected MappedByteBuffer mmap(int postion) {
        try {
            lock.lock();
            int mapLength = bufferSize;
            int remaining = MAX_FILE_LENGTH - postion;
            if (remaining < bufferSize) {
                mapLength = remaining;
            }
            return getFileChannel().map(FileChannel.MapMode.READ_WRITE, postion, mapLength);
        } catch (IOException e) {
            throw new IOError(e);
        } finally {
            lock.unlock();
        }
    }

    protected void unmap(MappedByteBuffer buffer) {
        if (buffer == null) {
            return;
        }
        try {
            buffer.force();
            Method cleanerMethod = buffer.getClass().getMethod("cleaner", new Class[0]);
            if (cleanerMethod != null) {
                cleanerMethod.setAccessible(true);
                Object cleaner = cleanerMethod.invoke(buffer, new Object[0]);
                if (cleaner != null) {
                    Method clearMethod = cleaner.getClass().getMethod("clean", new Class[0]);
                    if (cleanerMethod != null) {
                        clearMethod.invoke(cleaner, new Object[0]);
                    }
                }
            }

        } catch (Exception e) {

        }
    }

    private synchronized void initialize() throws IOException {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (raf == null) {
            raf = new RandomAccessFile(file, "rwd");
            channel = raf.getChannel();
        }
    }

    public void closeAndDeleteFile() {
        try {
            lock.lock();
            close();
            file.getAbsoluteFile().delete();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 调整读内存映射地址
     *
     * @param necessary 需要的内存空间
     * @throws java.io.IOException
     */
    void adjustReadMapper(int necessary) throws IOException {
        try {
            lock.lock();
            if ((this.readPostion + necessary) > MAX_FILE_LENGTH) {
                throw new IOException("Read length out of disk space");
            }

            if (this.readBuffer == null) {
                this.readBuffer = mmap(this.readPostion);
                this.readMapOffset = this.readPostion;
                return;
            }

            if (this.readMapOffset + bufferSize < this.readPostion + necessary || this.readPostion < this.readMapOffset) {
                unmap(this.readBuffer);
                this.readBuffer = mmap(this.readPostion);
                this.readMapOffset = this.readPostion;
                return;
            }

            if (this.readMapOffset + this.readBuffer.position() != this.readPostion) {
                int postion = this.readPostion - this.readMapOffset;
                this.readBuffer.position(postion);
            }
        } finally {
            lock.unlock();
        }
    }

    public int getFreeWriteSpace() {
        try {
            this.lock.lock();
            return MAX_FILE_LENGTH - this.writePostion;
        } finally {
            lock.unlock();
        }
    }

    public void commit() {
        try {
            this.lock.lock();
            if (this.writeBuffer != null) {
                this.writeBuffer.force();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 调整写内存映射地址
     *
     * @param necessary 需要的内存空间
     * @throws java.io.IOException
     */
    void adjustWriteMapper(int necessary) throws IOException {
        try {
            lock.lock();
            if ((this.writePostion + necessary) > MAX_FILE_LENGTH) {
                throw new IOException("No more disk space");
            }

            if (this.writeBuffer == null) {
                this.writeBuffer = mmap(this.writePostion);
                this.writeMapOffset = this.writePostion;
                return;
            }

            if (this.writeMapOffset + bufferSize < this.writePostion + necessary || this.writePostion < this.writeMapOffset) {
                unmap(this.writeBuffer);
                this.writeBuffer = mmap(this.writePostion);
                this.writeMapOffset = this.writePostion;
                return;
            }

            if (this.writeMapOffset + this.writeBuffer.position() != this.writePostion) {
                int position = this.writePostion - this.writeMapOffset;
                this.writeBuffer.position(position);
            }
        } finally {
            lock.unlock();
        }
    }

    public void close() {
        try {
            this.lock.lock();
            if (this.writeBuffer != null) {
                unmap(this.writeBuffer);
                this.writeBuffer = null;
            }

            if (this.readBuffer != null) {
                unmap(this.readBuffer);
                this.readBuffer = null;
            }

            if (this.channel != null) {
                this.channel.close();
                this.channel = null;
            }

            if (this.raf != null) {
                this.raf.close();
                this.raf = null;
            }
            if (shutdownCloseThread != null) {
                Runtime.getRuntime().removeShutdownHook(shutdownCloseThread);
                shutdownCloseThread = null;
            }

        } catch (IOException e) {
            throw new IOError(e);
        } finally {
            lock.unlock();
        }
    }

    public File getFile() {
        return file;
    }

    public int getReadPostion() {
        return readPostion;
    }

    public int getWritePostion() {
        return writePostion;
    }

    /**
     * 设置读的起始位置
     *
     * @param postion
     */
    public void setReadPostion(int postion) {
        try {
            lock.lock();
            this.readPostion = postion;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 设置写的起始位置
     *
     * @param postion
     */
    public void setWritePostion(int postion) {
        try {
            lock.lock();
            this.writePostion = postion;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 写入数据
     *
     * @param bytes
     * @throws java.io.IOException
     */
    public void put(byte[] bytes) throws IOException {
        try {
            lock.lock();
            adjustWriteMapper(bytes.length);
            this.writeBuffer.put(bytes);
            this.writePostion += bytes.length;
        } finally {
            lock.unlock();
        }
    }

    public void put(int postion, byte[] bytes) throws IOException {
        try {
            lock.lock();
            this.setWritePostion(postion);
            this.put(bytes);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 读取数据
     *
     * @param bytes
     * @throws java.io.IOException
     */
    public void get(byte[] bytes) throws IOException {
        try {
            lock.lock();
            adjustReadMapper(bytes.length);
            this.readBuffer.get(bytes);
            this.readPostion += bytes.length;
        } finally {
            lock.unlock();
        }
    }

    public void get(int postion, byte[] bytes) throws IOException {
        try {
            lock.lock();
            this.setReadPostion(postion);
            this.get(bytes);
        } finally {
            lock.unlock();
        }
    }

    public void put(byte b) throws IOException {
        try {
            lock.lock();
            adjustWriteMapper(1);
            this.writeBuffer.put(b);
            this.writePostion++;
        } finally {
            lock.unlock();
        }
    }

    public byte get() throws IOException {
        try {
            lock.lock();
            adjustReadMapper(1);
            byte value = this.readBuffer.get();
            this.readPostion++;
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void putInt(int value) throws IOException {
        try {
            lock.lock();
            adjustWriteMapper(4);
            this.writeBuffer.putInt(value);
            this.writePostion += 4;
        } finally {
            lock.unlock();
        }
    }

    public void putInt(int postion, int value) throws IOException {
        try {
            lock.lock();
            setWritePostion(postion);
            putInt(value);
        } finally {
            lock.unlock();
        }
    }

    public int getInt() throws IOException {
        try {
            lock.lock();
            adjustReadMapper(4);
            int value = this.readBuffer.getInt();
            this.readPostion += 4;
            return value;
        } finally {
            lock.unlock();
        }
    }

    public int getInt(int postion) throws IOException {
        try {
            lock.lock();
            setReadPostion(postion);
            return getInt();
        } finally {
            lock.unlock();
        }
    }

    public void putShort(short value) throws IOException {
        try {
            lock.lock();
            adjustWriteMapper(2);
            this.writeBuffer.putShort(value);
            this.writePostion += 2;
        } finally {
            lock.unlock();
        }
    }

    public void putShort(int postion, short value) throws IOException {
        try {
            lock.lock();
            setWritePostion(postion);
            putShort(value);
        } finally {
            lock.unlock();
        }
    }

    public short getShort() throws IOException {
        try {
            lock.lock();
            adjustReadMapper(2);
            short value = this.readBuffer.getShort();
            this.readPostion += 2;
            return value;
        } finally {
            lock.unlock();
        }
    }

    public short getShort(int postion) throws IOException {
        try {
            lock.lock();
            setReadPostion(postion);
            return getShort();
        } finally {
            lock.unlock();
        }
    }

    public void putLong(long value) throws IOException {
        try {
            lock.lock();
            adjustWriteMapper(8);
            this.writeBuffer.putLong(value);
            this.writePostion += 8;
        } finally {
            lock.unlock();
        }
    }

    public void putLong(int postion, long value) throws IOException {
        try {
            lock.lock();
            setWritePostion(postion);
            putLong(value);
        } finally {
            lock.unlock();
        }
    }

    public long getLong() throws IOException {
        try {
            lock.lock();
            adjustReadMapper(8);
            long value = this.readBuffer.getLong();
            this.readPostion += 8;
            return value;
        } finally {
            lock.unlock();
        }
    }

    public long getLong(int postion) throws IOException {
        try {
            lock.lock();
            setReadPostion(postion);
            return getLong();
        } finally {
            lock.unlock();
        }
    }

    public void putFloat(float value) throws IOException {
        try {
            lock.lock();
            adjustWriteMapper(4);
            this.writeBuffer.putFloat(value);
            this.writePostion += 4;
        } finally {
            lock.unlock();
        }
    }

    public void putFloat(int postion, float value) throws IOException {
        try {
            lock.lock();
            setWritePostion(postion);
            putFloat(value);
        } finally {
            lock.unlock();
        }
    }

    public float getFloat() throws IOException {
        try {
            lock.lock();
            adjustReadMapper(4);
            float value = this.readBuffer.getFloat();
            this.readPostion += 4;
            return value;
        } finally {
            lock.unlock();
        }
    }

    public float getFloat(int postion) throws IOException {
        try {
            lock.lock();
            setReadPostion(postion);
            return getFloat();
        } finally {
            lock.unlock();
        }
    }

    public void putDouble(double value) throws IOException {
        try {
            lock.lock();
            adjustWriteMapper(8);
            this.writeBuffer.putDouble(value);
            this.writePostion += 8;
        } finally {
            lock.unlock();
        }
    }

    public void putDouble(int postion, double value) throws IOException {
        try {
            lock.lock();
            setWritePostion(postion);
            putDouble(value);
        } finally {
            lock.unlock();
        }
    }

    public double getDouble() throws IOException {
        try {
            lock.lock();
            adjustReadMapper(8);
            double value = this.readBuffer.getDouble();
            this.readPostion += 8;
            return value;
        } finally {
            lock.unlock();
        }
    }

    public double getDouble(int postion) throws IOException {
        try {
            lock.lock();
            setReadPostion(postion);
            return getDouble();
        } finally {
            lock.unlock();
        }
    }


    void addShutdownHook() {
        if (shutdownCloseThread == null) {
            shutdownCloseThread = new ShutdownCloseThread(this);
            Runtime.getRuntime().addShutdownHook(shutdownCloseThread);
        }
    }


    ShutdownCloseThread shutdownCloseThread = null;

    private static class ShutdownCloseThread extends Thread {

        FileBuffer fileBuffer = null;

        ShutdownCloseThread(FileBuffer fileBuffer) {
            super("FileBuffer Shutdown");
            this.fileBuffer = fileBuffer;
        }

        public void run() {
            if (fileBuffer != null && fileBuffer.raf != null) {
                fileBuffer.shutdownCloseThread = null;
                fileBuffer.commit();
                fileBuffer.close();
                LOGGER.info("[JVM SHUTDOWN] commited and closed.");
            }
        }

    }
}
