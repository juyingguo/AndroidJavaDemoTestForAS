package com.sp.spmultipleapp.groupdance;

/**
 * Created by jack_zou on 2017/3/21.
 */

public class ActionBean {
    private long timeStamp;
    private int action;
    private String name;
    private boolean isDone = false;

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "ActionBean{" +
                "timeStamp=" + timeStamp +
                ", action=" + action +
                ", name='" + name + '\'' +
                ", isDone=" + isDone +
                '}';
    }
}
