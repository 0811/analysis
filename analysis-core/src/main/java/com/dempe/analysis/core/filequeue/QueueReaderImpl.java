package com.dempe.analysis.core.filequeue;


import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class QueueReaderImpl implements QueueReader {
    private Lock lock = new ReentrantLock();
    private MetaIO meta;
    private IndexManager indexMgr;
    private StoreManager storeMgr;


    public QueueReaderImpl(MetaIO meta, IndexManager indexMgr, StoreManager storeMgr) throws IOException {
        this.meta = meta;
        this.indexMgr = indexMgr;
        this.storeMgr = storeMgr;
    }

    @Override
    public boolean hashMore() {
        return meta.getWritedCount() > meta.getReadedCount();
    }

    private byte[] read(int index, int indexOffset) throws IOException {
        try {
            lock.lock();
            IndexIO indexIO = indexMgr.getIndexIO(index);
            indexIO.setReadOffset(indexOffset);

            Element element = indexIO.take();
            StoreIO storeIO = storeMgr.getStoreIO(element.getStore());
            storeIO.setReadOffset(element.getPosition());
            byte[] bytes = new byte[element.getLength()];
            storeIO.read(bytes);
            return bytes;
        } finally {
            lock.unlock();
        }
    }

    public byte[] poll() throws IOException {
        if (!hashMore()) {
            return null;
        }
        try {
            lock.lock();

            IndexIO indexIO = indexMgr.getIndexIO(meta.getReadIndex());
            indexIO.setReadOffset(meta.getReadIndexOffset());
            if (indexIO.getUnreadElementSize() <= 0) {
                meta.setReadIndex(meta.getReadIndex() + 1);
                meta.setReadIndexOffset(0);
                indexIO = indexMgr.getIndexIO(meta.getReadIndex());
                indexIO.setReadOffset(0);
            }

            Element element = indexIO.take();
            StoreIO storeIO = storeMgr.getStoreIO(element.getStore());
            storeIO.setReadOffset(element.getPosition());
            byte[] bytes = new byte[element.getLength()];
            storeIO.read(bytes);

            meta.setReadStore(element.getStore());
            meta.setReadStoreOffset(element.getPosition());
            meta.setReadIndex(indexIO.getIndex());
            meta.setReadIndexOffset(meta.getReadIndexOffset() + Element.ELEMENT_LENGTH);
            meta.flush();

            return bytes;
        } finally {
            lock.unlock();
        }
    }

    public byte[] peek() throws IOException {
        if (!hashMore()) {
            return null;
        }
        try {
            lock.lock();
            int index = meta.getReadIndex();
            int indexOffset = meta.getReadIndexOffset();

            if ((indexOffset + Element.ELEMENT_LENGTH) > FileBuffer.MAX_FILE_LENGTH) {
                index++;
                indexOffset = 0;
            }
            return read(index, indexOffset);
        } finally {
            lock.unlock();
        }
    }

    public byte[] peek(int i) throws IOException {
        if (!hashMore()) {
            return null;
        }
        try {
            lock.lock();
            int index = meta.getReadIndex();
            int indexOffset = meta.getReadIndexOffset();

            int skipOffset = i * Element.ELEMENT_LENGTH;
            int maxAvailableSpace = IndexIO.MAX_AVAILABLE_FILE_SPACE;
            int skipIdx = skipOffset / maxAvailableSpace;
            skipOffset = skipOffset % maxAvailableSpace;
            index += skipIdx;
            indexOffset += skipOffset;

            if (indexOffset > maxAvailableSpace) {
                index++;
                indexOffset = indexOffset - maxAvailableSpace;
            }
            return read(index, indexOffset);
        } finally {
            lock.unlock();
        }
    }

}
