package com.snatch.exception;

public enum ExceptionConstants {
    USER_ALREADY_EXISTS("user.already.exists"),
    USER_NOT_FOUND("user.not.found");
    private final String type;
    private ExceptionConstants(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
