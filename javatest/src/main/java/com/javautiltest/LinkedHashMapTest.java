package com.javautiltest;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LinkedHashMapTest {
    public static void main(String[] args) {
        initDataSynchronizedMap();
        initDataHashMap();
        initDataLinkedHashMap();
        initDataConcurrentHashMap();

    }

    private static void initDataSynchronizedMap() {
        System.out.println("---------initDataSynchronizedMap--------");
        ConcurrentLinkedQueue<String> concurrentLinkedQueue1 = new ConcurrentLinkedQueue<>();
            concurrentLinkedQueue1.add("a1");
            concurrentLinkedQueue1.add("a2");
        ConcurrentLinkedQueue<String> concurrentLinkedQueue2 = new ConcurrentLinkedQueue<>();
            concurrentLinkedQueue2.add("b1");
            concurrentLinkedQueue2.add("b2");
        ConcurrentLinkedQueue<String> concurrentLinkedQueue3 = new ConcurrentLinkedQueue<>();
            concurrentLinkedQueue3.add("c1");
            concurrentLinkedQueue3.add("c2");
        Map<String,ConcurrentLinkedQueue<String>> linkedHashMap =  Collections.synchronizedMap(new LinkedHashMap());
        linkedHashMap.put("k1",concurrentLinkedQueue1);
        linkedHashMap.put("k2",concurrentLinkedQueue2);
        linkedHashMap.put("k3",concurrentLinkedQueue3);
        linkedHashMap.put("k4",null);
        linkedHashMap.put("k5",null);
        linkedHashMap.put("k6",null);

        Set<String> strings = linkedHashMap.keySet();
        for (String s :strings){
            System.out.println(s);
            ConcurrentLinkedQueue<String> v = linkedHashMap.get(s);
            if (v != null){
                for (String qs : v){
                    System.out.println(qs);

                }

            }
            System.out.println("-----------------");
        }


    }
    private static void initDataHashMap() {
        System.out.println("---------initDataHashMap--------");
        ConcurrentLinkedQueue<String> concurrentLinkedQueue1 = new ConcurrentLinkedQueue<>();
        concurrentLinkedQueue1.add("a1");
        concurrentLinkedQueue1.add("a2");
        ConcurrentLinkedQueue<String> concurrentLinkedQueue2 = new ConcurrentLinkedQueue<>();
        concurrentLinkedQueue2.add("b1");
        concurrentLinkedQueue2.add("b2");
        ConcurrentLinkedQueue<String> concurrentLinkedQueue3 = new ConcurrentLinkedQueue<>();
        concurrentLinkedQueue3.add("c1");
        concurrentLinkedQueue3.add("c2");
        HashMap<String,ConcurrentLinkedQueue<String>> linkedHashMap =  new HashMap<>();
        linkedHashMap.put("k1",concurrentLinkedQueue1);
        linkedHashMap.put("k2",concurrentLinkedQueue2);
        linkedHashMap.put("k3",concurrentLinkedQueue3);
        linkedHashMap.put("k4",null);
        linkedHashMap.put("k5",null);
        linkedHashMap.put("k6",null);

        Set<String> strings = linkedHashMap.keySet();
        for (String s :strings){
            System.out.println(s);
            ConcurrentLinkedQueue<String> v = linkedHashMap.get(s);
            if (v != null){
                for (String qs : v){
                    System.out.println(qs);

                }

            }
            System.out.println("-----------------");
        }


    }
    private static void initDataLinkedHashMap() {
        System.out.println("---------initDataLinkedHashMap--------");
        ConcurrentLinkedQueue<String> concurrentLinkedQueue1 = new ConcurrentLinkedQueue<>();
        concurrentLinkedQueue1.add("a1");
        concurrentLinkedQueue1.add("a2");
        ConcurrentLinkedQueue<String> concurrentLinkedQueue2 = new ConcurrentLinkedQueue<>();
        concurrentLinkedQueue2.add("b1");
        concurrentLinkedQueue2.add("b2");
        ConcurrentLinkedQueue<String> concurrentLinkedQueue3 = new ConcurrentLinkedQueue<>();
        concurrentLinkedQueue3.add("c1");
        concurrentLinkedQueue3.add("c2");
        LinkedHashMap<String,ConcurrentLinkedQueue<String>> linkedHashMap =  new LinkedHashMap<>();
        linkedHashMap.put("k1",concurrentLinkedQueue1);
        linkedHashMap.put("k2",concurrentLinkedQueue2);
        linkedHashMap.put("k3",concurrentLinkedQueue3);

        Set<String> strings = linkedHashMap.keySet();
        for (String s :strings){
            System.out.println(s);
            ConcurrentLinkedQueue<String> v = linkedHashMap.get(s);
            for (String qs : v){
                System.out.println(qs);

            }
            System.out.println("-----------------");
        }


    }
    private static void initDataConcurrentHashMap() {
        System.out.println("---------initDataConcurrentHashMap--------");
        ConcurrentLinkedQueue<String> concurrentLinkedQueue1 = new ConcurrentLinkedQueue<>();
        concurrentLinkedQueue1.add("a1");
        concurrentLinkedQueue1.add("a2");
        ConcurrentLinkedQueue<String> concurrentLinkedQueue2 = new ConcurrentLinkedQueue<>();
        concurrentLinkedQueue2.add("b1");
        concurrentLinkedQueue2.add("b2");
        ConcurrentLinkedQueue<String> concurrentLinkedQueue3 = new ConcurrentLinkedQueue<>();
        concurrentLinkedQueue3.add("c1");
        concurrentLinkedQueue3.add("c2");
        ConcurrentHashMap<String,ConcurrentLinkedQueue<String>> linkedHashMap =  new ConcurrentHashMap<>();
        linkedHashMap.put("k1",concurrentLinkedQueue1);
        linkedHashMap.put("k2",concurrentLinkedQueue2);
        linkedHashMap.put("k3",concurrentLinkedQueue3);

        Set<String> strings = linkedHashMap.keySet();
        for (String s :strings){
            System.out.println(s);
            ConcurrentLinkedQueue<String> v = linkedHashMap.get(s);
            for (String qs : v){
                System.out.println(qs);

            }
            System.out.println("-----------------");
        }


    }
}
