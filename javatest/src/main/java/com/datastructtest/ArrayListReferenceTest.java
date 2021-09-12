package com.datastructtest;

import java.util.ArrayList;

/**
 * ArrayList中的数据引用测试
 */
public class ArrayListReferenceTest {
    public static void main(String[] args) {
        ArrayList<SubscriberMethod> list = new ArrayList<>();
        list.add(new SubscriberMethod(10,false));
        SubscriberMethod subscriberMethod = list.get(0);

        System.out.println(subscriberMethod);
        SubscriberMethod subscriberMethodNew = subscriberMethod;
        subscriberMethodNew.sticky = true;

        SubscriberMethod subscriberMethod1 = list.get(0);
        System.out.println(subscriberMethod1);
    }

}
