package com.test.textprint;

/**
 * Created by dgabrove on 05/31/2016.
 */
public class TextPrintException extends Exception {
    public TextPrintException() {
    }

    public TextPrintException(String message) {
        super(message);
    }

    public TextPrintException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextPrintException(Throwable cause) {
        super(cause);
    }
}
