package com.snatch.exception;

public enum ItemConstants {

    ITEM_NOT_FOUND("item.not.found"),
    USER_NOT_FOUND("user.not.found"),
    ITEM_DELETED("item.deleted");
    private final String type;
    private ItemConstants(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
