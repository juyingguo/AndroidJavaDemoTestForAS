package com.designmodel.observer.observerm4;

import org.junit.Test;

/**
 * 测试类
 */
public class ObserverTest {
    @Test
    public void test() throws InterruptedException {
        Observer observer = new ConcreteObserver();
        ConcreteSubject subject = new ConcreteSubject();
        subject.addObserver(observer);
        Thread.sleep(1000);
        subject.doSomething();
    }
}