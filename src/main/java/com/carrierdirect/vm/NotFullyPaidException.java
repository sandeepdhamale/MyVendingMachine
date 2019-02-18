package com.carrierdirect.vm;

public class NotFullyPaidException extends RuntimeException {
    private String message;
    private long remaining;

    public NotFullyPaidException(String message, long remaining) {
        this.message = message;
        this.remaining = remaining;
    }

    public long getRemaining() {
        return remaining;
    }

    @Override
    public String getMessage() {
        return message + remaining;
    }

}