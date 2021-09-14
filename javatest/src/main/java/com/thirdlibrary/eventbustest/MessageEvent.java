package com.thirdlibrary.eventbustest;

public class MessageEvent {
    String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

}