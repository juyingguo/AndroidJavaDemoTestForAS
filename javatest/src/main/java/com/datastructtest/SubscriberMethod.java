package com.datastructtest;

/** Used internally by EventBus and generated subscriber indexes. */
class SubscriberMethod {
    final int priority;
    boolean sticky;

    SubscriberMethod(int priority, boolean sticky) {
        this.priority = priority;
        this.sticky = sticky;
    }

    @Override
    public String toString() {
        return "SubscriberMethod{" +
                "priority=" + priority +
                ", sticky=" + sticky +
                '}';
    }
}