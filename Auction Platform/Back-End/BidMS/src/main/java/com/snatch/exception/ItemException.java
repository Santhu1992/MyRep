package com.snatch.exception;

public class ItemException extends Exception{

    private String message;

    public ItemException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
