package com.dempe.analysis.core.filequeue;


import java.io.File;
import java.io.IOException;

/**
 * 队列数据文件读写
 */
class StoreIO {
    public static final String FILE_SUFFIX = ".data";

    public static File getStoreFile(String dir, String name, int index) {
        return new File(getStoreFileName(dir, name, index));
    }

    public static String getStoreFileName(String dir, String name, int index) {
        dir = FilenameUtils.normalizeNoEndSeparator(dir);
        if (index == 0) {
            return (dir + File.separator + name + FILE_SUFFIX);
        }
        return (dir + File.separator + name + FILE_SUFFIX + "." + index);
    }

    public static boolean deleteStoreFile(String dir, String name, int index) {
        File file = getStoreFile(dir, name, index);
        if (file.exists() && file.isFile()) {
            return file.getAbsoluteFile().delete();
        }
        return false;
    }

    FileBuffer buffer;
    final int store;

    public StoreIO(File file, int store) throws IOException {
        this.buffer = new FileBuffer(file);
        this.store = store;
    }

    public StoreIO(File file, int store, int bufferSize) throws IOException {
        this.buffer = new FileBuffer(file, bufferSize);
        this.store = store;
    }

    public void setWriteOffset(int offset) {
        this.buffer.setWritePostion(offset);
    }

    public void setReadOffset(int offset) {
        this.buffer.setReadPostion(offset);
    }

    public synchronized void write(byte[] bytes) throws IOException {
        this.buffer.put(bytes);
    }

    public synchronized void read(byte[] bytes) throws IOException {
        this.buffer.get(bytes);
    }

    public synchronized void read(int postion, byte[] bytes) throws IOException {
        this.buffer.setReadPostion(postion);
        read(bytes);
    }

    public void close() {
        this.buffer.close();
        this.buffer = null;
    }

    public void closeAndDeleteFile() {
        if (this.buffer == null) {
            return;
        }
        this.buffer.closeAndDeleteFile();
        this.buffer = null;
    }


    public int getWriteOffset() {
        return this.buffer.getWritePostion();
    }

    public int getReadOffset() {
        return this.buffer.getReadPostion();
    }

    public int getFreeWriteSpace() {
        return this.buffer.getFreeWriteSpace();
    }

    public int getStore() {
        return store;
    }


}
