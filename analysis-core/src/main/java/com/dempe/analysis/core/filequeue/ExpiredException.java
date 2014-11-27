package com.dempe.analysis.core.filequeue;

import java.io.IOException;


public class ExpiredException extends IOException {
    public ExpiredException() {
    }

    public ExpiredException(String message) {
        super(message);
    }

    public ExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpiredException(Throwable cause) {
        super(cause);
    }
}
