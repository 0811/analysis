package com.dempe.analysis.core.filequeue;


import java.io.IOException;

interface QueueReader {

    public boolean hashMore();

    public byte[] peek() throws IOException;

    public byte[] peek(int i) throws IOException;

    public byte[] poll() throws IOException;
}
