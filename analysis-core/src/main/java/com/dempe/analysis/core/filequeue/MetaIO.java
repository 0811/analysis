package com.dempe.analysis.core.filequeue;

import java.io.File;
import java.io.IOException;


/**
 * 队列元数据读写
 */
class MetaIO {
    public static final String FILE_SUFFIX = ".meta";
    public static final int META_FILE_LENGTH = 32;

    public static String getMetaFileName(String dir, String name) {
        dir = FilenameUtils.normalizeNoEndSeparator(dir);
        return (dir + File.separator + name + FILE_SUFFIX);
    }

    public static File getMetaFile(String dir, String name) {
        return new File(getMetaFileName(dir, name));
    }

    public static void deleteMetaFile(String dir, String name) {
        File file = getMetaFile(dir, name);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    private FileBuffer file = null;

    private final byte[] buffer = new byte[META_FILE_LENGTH];

    private int readIndex = 0; //当前读取页
    private int readIndexOffset = 0;//当前读取位置
    private int readStore = 0;
    private int readStoreOffset = 0;

    private int writeIndex = 0;//当前写页
    private int writeIndexOffset = 0;//当前写位置
    private int writeStore = 0; //最后写入Store 号
    private int writeStoreOffset = 0;//最后写入数据位置

    public MetaIO(File file) throws IOException {
        this.file = new FileBuffer(file, META_FILE_LENGTH);
        reload();
    }

    private void reload() throws IOException {
        this.readIndex = file.getInt(0);
        this.readIndexOffset = file.getInt(4);
        this.readStore = file.getInt(8);
        this.readStoreOffset = file.getInt(12);

        this.writeIndex = file.getInt(16);
        this.writeIndexOffset = file.getInt(20);
        this.writeStore = file.getInt(24);
        this.writeStoreOffset = file.getInt(28);
    }

    public void flush() throws IOException {

        Bytes.putInt(buffer, 0, readIndex);
        Bytes.putInt(buffer, 4, readIndexOffset);
        Bytes.putInt(buffer, 8, readStore);
        Bytes.putInt(buffer, 12, readStoreOffset);

        Bytes.putInt(buffer, 16, writeIndex);
        Bytes.putInt(buffer, 20, writeIndexOffset);
        Bytes.putInt(buffer, 24, writeStore);
        Bytes.putInt(buffer, 28, writeStoreOffset);
        file.put(0, buffer);
    }

    public void clear() throws IOException {
        this.readIndex = 0;
        this.readIndexOffset = 0;
        this.readStore = 0;
        this.readStoreOffset = 0;

        this.writeIndex = 0;
        this.writeIndexOffset = 0;
        this.writeStore = 0;
        this.writeStoreOffset = 0;
        flush();
    }

    public void close() {
        if (this.file == null) {
            return;
        }
        this.file.close();
        this.file = null;
    }

    public void closeAndDeleteFile() {
        if (this.file == null) {
            return;
        }
        this.file.closeAndDeleteFile();
        this.file = null;
    }

    public long getReadedCount() {
        return (1l * FileBuffer.MAX_FILE_LENGTH * readIndex + readIndexOffset) / Element.ELEMENT_LENGTH;
    }


    public long getWritedCount() {
        return (1l * FileBuffer.MAX_FILE_LENGTH * writeIndex + writeIndexOffset) / Element.ELEMENT_LENGTH;
    }

    public int getReadIndex() {
        return readIndex;
    }

    public void setReadIndex(int readIndex) {
        this.readIndex = readIndex;
    }

    public int getReadIndexOffset() {
        return readIndexOffset;
    }

    public void setReadIndexOffset(int readIndexOffset) {
        this.readIndexOffset = readIndexOffset;
    }

    public int getReadStore() {
        return readStore;
    }

    public void setReadStore(int readStore) {
        this.readStore = readStore;
    }

    public int getReadStoreOffset() {
        return readStoreOffset;
    }

    public void setReadStoreOffset(int readStoreOffset) {
        this.readStoreOffset = readStoreOffset;
    }

    public int getWriteIndex() {
        return writeIndex;
    }

    public void setWriteIndex(int writeIndex) {
        this.writeIndex = writeIndex;
    }

    public int getWriteIndexOffset() {
        return writeIndexOffset;
    }

    public void setWriteIndexOffset(int writeIndexOffset) {
        this.writeIndexOffset = writeIndexOffset;
    }

    public int getWriteStore() {
        return writeStore;
    }

    public void setWriteStore(int writeStore) {
        this.writeStore = writeStore;
    }

    public int getWriteStoreOffset() {
        return writeStoreOffset;
    }

    public void setWriteStoreOffset(int writeStoreOffset) {
        this.writeStoreOffset = writeStoreOffset;
    }

    @Override
    public String toString() {
        return "MetaIO{" +
                "writeCount=" + getWritedCount() +
                ", readCount=" + getReadedCount() +
                ", writeStoreOffset=" + writeStoreOffset +
                ", writeStore=" + writeStore +
                ", writeOffset=" + writeIndexOffset +
                ", writeIndex=" + writeIndex +
                ", readIndexOffset=" + readIndexOffset +
                ", readIndex=" + readIndex +
                ", readStore=" + readStore +
                ", readStoreOffset=" + readStoreOffset +
                '}';
    }
}
