package com.dempe.analysis.core.filequeue;

import java.io.IOException;

interface QueueWriter {

    public void write(byte[] bytes) throws IOException;

}
