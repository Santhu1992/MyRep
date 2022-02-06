package com.snatch.exception;

public class UserException extends Exception {
    private static final long serialVersionId = 1L;
    private String message;
    public UserException(String message){
        super();
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
