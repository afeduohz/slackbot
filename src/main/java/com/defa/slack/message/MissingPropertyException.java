package com.defa.slack.message;

public class MissingPropertyException extends RuntimeException {
    public MissingPropertyException(final String message) {
        super(message);
    }
}
