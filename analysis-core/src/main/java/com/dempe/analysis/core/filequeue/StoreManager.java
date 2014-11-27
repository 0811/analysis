package com.dempe.analysis.core.filequeue;


import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class StoreManager {
    private static final Logger LOGGER = Logger.getLogger(StoreManager.class);
    private MetaIO metaIO;
    private String dir;
    private String name;
    private int bufferSize = FileBuffer.DEFAULT_BUFFER_SIZE;

    private final Map<Integer, StoreIO> stores = new HashMap<Integer, StoreIO>();

    public StoreManager(MetaIO metaIO, String dir, String name) {
        this.metaIO = metaIO;
        this.dir = dir;
        this.name = name;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public boolean expired(int index) {
        return index < metaIO.getReadStore();
    }


    private synchronized StoreIO getOrNewStoreIO(int index) throws IOException {
        StoreIO io = stores.get(index);
        if (io == null) {
            File file = StoreIO.getStoreFile(dir, name, index);
            io = new StoreIO(file, index, bufferSize);
            stores.put(index, io);
        }
        return io;
    }

    public synchronized StoreIO getStoreIO(int index) throws IOException {
        if (expired(index)) {
            throw new ExpiredException("The index file was expired : " + IndexIO.getIndexFileName(dir, name, index));
        }

        if (index > metaIO.getWriteStore()) {
            throw new FileNotFoundException("The index file out of size : " + IndexIO.getIndexFileName(dir, name, index));
        }

        StoreIO io = getOrNewStoreIO(index);
        return io;
    }

    public void deleteStoreFile(int index) {

        StoreIO io = stores.remove(index);
        if (io != null) {
            LOGGER.info("deleting store file : " + StoreIO.getStoreFileName(dir, name, index));
            io.closeAndDeleteFile();
            return;
        }

        if (StoreIO.deleteStoreFile(dir, name, index)) {
            LOGGER.info("deleting store file : " + StoreIO.getStoreFileName(dir, name, index));
        }

    }

    public synchronized StoreIO createNextStoreFile() throws IOException {
        int index = metaIO.getWriteStore() + 1;
        File file = StoreIO.getStoreFile(dir, name, index);
        StoreIO io = new StoreIO(file, index, bufferSize);
        stores.put(index, io);
        metaIO.setWriteStore(index);
        metaIO.setWriteStoreOffset(0);
        return io;
    }

    public synchronized void close() {
        if (stores.isEmpty()) {
            return;
        }
        for (StoreIO io : stores.values()) {
            io.close();
        }
        stores.clear();
    }
}
