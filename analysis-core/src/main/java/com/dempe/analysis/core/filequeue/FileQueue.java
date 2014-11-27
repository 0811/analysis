package com.dempe.analysis.core.filequeue;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 一个高性能文件对列,支持先进先出
 */
public class FileQueue {
    private static final Logger LOGGER = Logger.getLogger(FileQueue.class);
    private static final int DEFAULT_BUFFER_SIZE = 4 * 1024 * 1024;
    private static final int AUTO_CLEAR_INTERVAL = 300; //s
    private int indexBufferSize = DEFAULT_BUFFER_SIZE;
    private int storeBufferSize = DEFAULT_BUFFER_SIZE;

    private final Lock lock = new ReentrantLock();

    private String dir;
    private String name;

    private MetaIO meta;
    private IndexManager indexMgr;
    private StoreManager storeMgr;

    private QueueReader reader;
    private QueueWriter writer;

    private int indexOfLastDeleteStoreFile = 0;
    private int indexOfLastDeleteIndexFile = 0;

    public FileQueue(String dirPath, String name) throws IOException {
        this(dirPath, name, DEFAULT_BUFFER_SIZE, DEFAULT_BUFFER_SIZE);
    }

    public FileQueue(String dirPath, String name, int storeBufferSize) throws IOException {
        this(dirPath, name, DEFAULT_BUFFER_SIZE, storeBufferSize);
    }

    public FileQueue(String dirPath, String name, int indexBufferSize, int storeBufferSize) throws IOException {
        this.indexBufferSize = indexBufferSize;
        this.storeBufferSize = storeBufferSize;
        this.dir = dirPath;
        this.name = name;
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        initialize();
        Threads.scheduleWithFixedDelay(autoClearExpiredFileMonitor, AUTO_CLEAR_INTERVAL, AUTO_CLEAR_INTERVAL, TimeUnit.SECONDS);
    }

    void initialize() throws IOException {
        if (meta != null) {
            return;
        }
        try {
            lock.lock();
            meta = new MetaIO(MetaIO.getMetaFile(dir, name));
            indexMgr = new IndexManager(this.meta, dir, name);
            storeMgr = new StoreManager(this.meta, dir, name);
            reader = new QueueReaderImpl(this.meta, indexMgr, storeMgr);
            writer = new QueueWriterImpl(this.meta, indexMgr, storeMgr);

            indexMgr.setBufferSize(indexBufferSize);
            storeMgr.setBufferSize(storeBufferSize);

            indexOfLastDeleteStoreFile = 0;
            indexOfLastDeleteIndexFile = 0;
        } finally {
            lock.unlock();
        }
    }


    public boolean add(byte[] bytes) {
        try {
            lock.lock();
            writer.write(bytes);
            return true;
        } catch (Exception e) {
            throw new IOError(e);
        } finally {
            lock.unlock();
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }


    public byte[] peek() {
        if (isEmpty()) {
            return null;
        }
        try {
            lock.lock();
            return reader.peek();
        } catch (IOException e) {
            throw new IOError(e);
        } finally {
            lock.unlock();
        }
    }

    public byte[] peek(int i) {
        if (isEmpty()) {
            return null;
        }
        try {
            lock.lock();
            return reader.peek(i);
        } catch (IOException e) {
            throw new IOError(e);
        } finally {
            lock.unlock();
        }
    }

    public byte[] poll() {
        if (isEmpty()) {
            return null;
        }
        try {
            lock.lock();
            if (reader.hashMore()) {
                byte[] bytes = reader.poll();
                return bytes;
            }
            return null;
        } catch (IOException e) {
            throw new IOError(e);
        } finally {
            lock.unlock();
        }
    }

    public long size() {
        try {
            lock.lock();
            return meta.getWritedCount() - meta.getReadedCount();
        } finally {
            lock.unlock();
        }
    }

    public void clear() {
        try {
            lock.lock();
            meta.clear();
            deleteAllIndexFiles();
            deleteAllStoreFiles();
            initialize();
        } catch (IOException e) {
            throw new IOError(e);
        } finally {
            lock.unlock();
        }
    }

    public void close() {
        try {
            lock.lock();
            storeMgr.close();
            indexMgr.close();
            meta.close();
        } finally {
            lock.unlock();
        }
    }


    private void deleteExpiredStoreFiles() {
        int store = meta.getReadStore();
        for (int i = indexOfLastDeleteStoreFile; i < store; i++) {
            storeMgr.deleteStoreFile(i);
            indexOfLastDeleteStoreFile = i;
        }
    }

    private void deleteExpiredIndexFiles() {
        int index = meta.getReadIndex();
        for (int i = indexOfLastDeleteIndexFile; i < index; i++) {
            indexMgr.deleteIndexFile(i);
            indexOfLastDeleteIndexFile = i;
        }
    }

    private void deleteAllStoreFiles() {
        int store = meta.getWriteStore();
        for (int i = indexOfLastDeleteStoreFile; i <= store; i++) {
            storeMgr.deleteStoreFile(i);
            indexOfLastDeleteStoreFile = i;
        }
    }

    private void deleteAllIndexFiles() {
        int index = meta.getWriteIndex();
        for (int i = indexOfLastDeleteIndexFile; i <= index; i++) {
            indexMgr.deleteIndexFile(i);
            indexOfLastDeleteIndexFile = i;
        }
    }

    Runnable autoClearExpiredFileMonitor = new Runnable() {
        @Override
        public void run() {
            try {
                lock.lock();
                deleteExpiredStoreFiles();
                deleteExpiredIndexFiles();
                if (size() == 0) {
                    clear();
                }
            } catch (Throwable t) {

            } finally {
                lock.unlock();
            }
        }
    };
}
