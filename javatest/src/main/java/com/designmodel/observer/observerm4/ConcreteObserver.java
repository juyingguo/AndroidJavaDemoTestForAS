package com.designmodel.observer.observerm4;
/**
 * 具体的观察者
 */
public class ConcreteObserver implements Observer {
    public void update(Object object) {
        System.out.println("收到了通知"+object.toString());
    }
}

