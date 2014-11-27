package com.dempe.analysis.core.filequeue;


import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class IndexManager {
    private static final Logger LOGGER = Logger.getLogger(IndexManager.class);
    private MetaIO metaIO;
    private String dir;
    private String name;
    private int bufferSize = FileBuffer.DEFAULT_BUFFER_SIZE;

    private final Map<Integer, IndexIO> indexs = new HashMap<Integer, IndexIO>();

    public IndexManager(MetaIO metaIO, String dir, String name) {
        this.metaIO = metaIO;
        this.dir = dir;
        this.name = name;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public boolean expired(int index) {
        return index < metaIO.getReadIndex();
    }

    public synchronized IndexIO getIndexIO(int index) throws IOException {
        if (expired(index)) {
            throw new ExpiredException("The index file was expired : " + IndexIO.getIndexFileName(dir, name, index));
        }

        if (index > metaIO.getWriteIndex()) {
            throw new FileNotFoundException("The index file out of size : " + IndexIO.getIndexFileName(dir, name, index));
        }

        IndexIO io = indexs.get(index);
        if (io == null) {
            File pageFile = IndexIO.getIndexFile(dir, name, index);
            io = new IndexIO(pageFile, index, bufferSize);
            indexs.put(index, io);
        }
        return io;
    }

    public void deleteIndexFile(int index) {
        IndexIO io = indexs.remove(index);
        if (io != null) {
            LOGGER.info("deleting index file : " + IndexIO.getIndexFileName(dir, name, index));
            io.closeAndDeleteFile();
            return;
        }

        if (IndexIO.deleteIndexFile(dir, name, index)) {
            LOGGER.info("deleting index file : " + IndexIO.getIndexFileName(dir, name, index));
        }
    }

    public synchronized IndexIO createNextIndexFile() throws IOException {
        int index = metaIO.getWriteIndex() + 1;
        File pageFile = IndexIO.getIndexFile(dir, name, index);
        IndexIO io = new IndexIO(pageFile, index, bufferSize);
        indexs.put(index, io);
        metaIO.setWriteIndex(index);
        return io;
    }

    public synchronized void close() {
        if (indexs.isEmpty()) {
            return;
        }
        for (IndexIO io : indexs.values()) {
            io.close();
        }
        indexs.clear();
    }
}
