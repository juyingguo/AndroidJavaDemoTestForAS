package com.designmodel.observer.third;

import java.util.Observable;

/**
 * Date:2019/8/7,10:09
 * author:jy
 */
public class ApiObseverTest {

    class MyObservable extends Observable{
        private void doSomething(){
            notifyObservers();
        }
    }
}
