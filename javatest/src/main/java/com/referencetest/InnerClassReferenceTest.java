package com.referencetest;

import org.junit.Test;

public class InnerClassReferenceTest {
    interface CallBack{
        void call();
    }
    private CallBack mAnonymousInnerClass = new CallBack() {
        @Override
        public void call() {
            System.out.println("mAnonymousInnerClass call():" + this);
        }
    };
    private static CallBack mStaticAnonymousInnerClass = new CallBack() {
        @Override
        public void call() {
            System.out.println("mStaticAnonymousInnerClass call():" + this);
        }
    };
    class InnerClass{
        public void call(){
            System.out.println("InnerClass call():" + this);
        }
    }
    static class StaticInnerClass{
        void call(){
            System.out.println("StaticInnerClass call():" + this);
        }
    }
    @Test
    public void test(){
        mAnonymousInnerClass.call();
        mStaticAnonymousInnerClass.call();

        new InnerClass().call();
        new StaticInnerClass().call();
    }
}
