package com.collection.listtest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListTest {
    @Test
    public void testListRemoveCauseSkipElement(){

        ArrayList<String > list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list.size());
        for (int i=0; i< list.size();i++){
            list.remove(i);
        }
        System.out.println("after remove ,list size:" + list.size());
        System.out.println("after remove ,list :" + list);

    }
    @Test
    public void testListRemoveLoopConditionCauseIndexOutOfBoundsException(){

        ArrayList<String > list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list.size());
        int i = 0;
        int maxNum = list.size();
        for (; i< maxNum;i++){
            list.remove(i);
        }
        System.out.println("after remove ,list size:" + list.size());
        System.out.println("after remove ,list :" + list);

    }

    /**
     * cause java.util.ConcurrentModificationException
     */
    @Test
    public void testListRemoveUseForeach(){

        ArrayList<String > list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list.size());
        for (String s:
             list) {
            list.remove(s);
        }
        System.out.println("after remove ,list size:" + list.size());
        System.out.println("after remove ,list :" + list);

    } @Test
    public void testListRemoveUseIterator(){

        ArrayList<String > list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list.size());
        Iterator<String> it =  list.iterator();
        while(it.hasNext()){
            String x = it.next();
            it.remove();
        }
        System.out.println("after remove ,list size:" + list.size());
        System.out.println("after remove ,list :" + list);

    }
}
