package com.dempe.analysis.core.filequeue;


import java.io.File;
import java.io.IOException;

/**
 * 队列索引文件读写
 *
 * @author lamfire
 */
class IndexIO {
    public static final String FILE_SUFFIX = ".idx";
    public static final int ELEMENT_LENGTH = Element.ELEMENT_LENGTH;
    public static final int MAX_AVAILABLE_FILE_SPACE = FileBuffer.MAX_FILE_LENGTH - FileBuffer.MAX_FILE_LENGTH % Element.ELEMENT_LENGTH;

    public static String getIndexFileName(String dir, String name, int index) {
        dir = FilenameUtils.normalizeNoEndSeparator(dir);
        if (index == 0) {
            return (dir + File.separator + name + FILE_SUFFIX);
        }
        return (dir + File.separator + name + FILE_SUFFIX + "." + index);
    }

    public static File getIndexFile(String dir, String name, int index) {
        return new File(getIndexFileName(dir, name, index));
    }

    public static boolean deleteIndexFile(String dir, String name, int index) {
        File file = getIndexFile(dir, name, index);
        if (file.exists() && file.isFile()) {
            return file.getAbsoluteFile().delete();
        }
        return false;
    }

    FileBuffer buffer;
    final int index;

    public IndexIO(File file, int page) throws IOException {
        this.buffer = new FileBuffer(file);
        this.index = page;
    }

    public IndexIO(File file, int page, int bufferSize) throws IOException {
        this.buffer = new FileBuffer(file, bufferSize);
        this.index = page;
    }


    public void setWriteOffset(int offset) {
        this.buffer.setWritePostion(offset);
    }

    public void setReadOffset(int offset) {
        this.buffer.setReadPostion(offset);
    }

    public void add(Element element) throws IOException {
        this.buffer.put(element.asBytes());
    }

    public synchronized Element take() throws IOException {
        byte[] bytes = new byte[ELEMENT_LENGTH];
        this.buffer.get(bytes);
        return Element.fromBytes(bytes);
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

    public int getFreeElementSize() {
        int freeSpace = this.buffer.getFreeWriteSpace();
        return (freeSpace - freeSpace % ELEMENT_LENGTH) / ELEMENT_LENGTH;
    }


    public int getUnreadElementSize() {
        int unreadSpace = (FileBuffer.MAX_FILE_LENGTH - this.buffer.getReadPostion());
        return (unreadSpace - unreadSpace % ELEMENT_LENGTH) / ELEMENT_LENGTH;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "IndexIO{" +
                "index=" + index +
                ",unreadElementSize=" + getUnreadElementSize() +
                ",freeElementSize=" + getFreeElementSize() +
                '}';
    }
}
