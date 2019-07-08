package com.sp.spmultipleapp.bean;

public class MessageEvent {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String message;

    public MessageEvent(String message) {
        this.message = message;
    }

}