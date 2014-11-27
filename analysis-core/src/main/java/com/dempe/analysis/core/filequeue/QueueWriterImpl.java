package com.dempe.analysis.core.filequeue;


import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class QueueWriterImpl implements QueueWriter {
    private Lock lock = new ReentrantLock();
    private MetaIO meta;
    private IndexManager indexMgr;
    private StoreManager storeMgr;

    public QueueWriterImpl(MetaIO meta, IndexManager indexMgr, StoreManager storeMgr) {
        this.meta = meta;
        this.indexMgr = indexMgr;
        this.storeMgr = storeMgr;
    }


    @Override
    public void write(byte[] bytes) throws IOException {
        try {
            lock.lock();

            StoreIO io = storeMgr.getStoreIO(meta.getWriteStore());
            io.setWriteOffset(meta.getWriteStoreOffset());

            if (io.getFreeWriteSpace() < bytes.length) {
                io = storeMgr.createNextStoreFile();
            }
            int writeStoreOffset = io.getWriteOffset();
            io.write(bytes);
            int writeStore = io.getStore();

            int writeIndex = meta.getWriteIndex();
            IndexIO indexIO = indexMgr.getIndexIO(writeIndex);
            indexIO.setWriteOffset(meta.getWriteIndexOffset());

            if (indexIO.getFreeElementSize() <= 0) {
                indexIO = indexMgr.createNextIndexFile();
                meta.setWriteIndex(indexIO.getIndex());
                meta.setWriteIndexOffset(0);
            }

            Element element = new Element(writeStore, writeStoreOffset, bytes.length);
            indexIO.add(element);

            meta.setWriteIndex(indexIO.getIndex());
            meta.setWriteIndexOffset(meta.getWriteIndexOffset() + Element.ELEMENT_LENGTH);
            meta.setWriteStoreOffset(meta.getWriteStoreOffset() + bytes.length);
            meta.setWriteStore(io.getStore());
            meta.flush();
        } finally {
            lock.unlock();
        }
    }


}
