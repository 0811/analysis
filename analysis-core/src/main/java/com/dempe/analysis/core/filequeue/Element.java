package com.dempe.analysis.core.filequeue;


/**
 * IndexIO 元素
 */
class Element {
    public static final int ELEMENT_LENGTH = 12;

    public static final Element NULL = new Element(0, 0, 0);

    private int store;

    private int position;

    private int length;

    public Element() {

    }

    public Element(int store, int position, int length) {
        this.store = store;
        this.position = position;
        this.length = length;
    }

    public byte[] asBytes() {
        byte[] bytes = new byte[12];
        Bytes.putInt(bytes, 0, store);
        Bytes.putInt(bytes, 4, position);
        Bytes.putInt(bytes, 8, length);
        return bytes;
    }

    public static Element fromBytes(byte[] bytes) {
        Element e = new Element();
        e.store = Bytes.toInt(bytes, 0);
        e.position = Bytes.toInt(bytes, 4);
        e.length = Bytes.toInt(bytes, 8);
        return e;
    }

    public int getPosition() {
        return position;
    }


    public int getLength() {
        return length;
    }


    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + "store = " + store + ", position = " + position + ", length = " + length + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + length;
        result = prime * result + store;
        result = prime * result + position;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Element other = (Element) obj;
        if (length != other.length)
            return false;
        if (store != other.store)
            return false;
        if (position != other.position)
            return false;
        return true;
    }


}
