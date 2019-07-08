package com.javautiltest;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MapTest01 {
    public static void main(String[] args) {
        initDataSynchronizedMap();
        initDataHashMap();
        initDataLinkedHashMap();
        initDataConcurrentHashMap();

    }

    private static void initDataSynchronizedMap() {
        System.out.println("---------initDataSynchronizedMap--------");
        Map<String,Integer> synchronizedMap =  Collections.synchronizedMap(new LinkedHashMap());
        testMap(synchronizedMap);

    }
    private static void initDataHashMap() {
        System.out.println("---------initDataHashMap--------");
        HashMap<String,Integer> hashMap =  new HashMap<>();
        testMap(hashMap);

    }
    private static void initDataLinkedHashMap() {
        System.out.println("---------initDataLinkedHashMap--------");
        LinkedHashMap<String,Integer> linkedHashMap =  new LinkedHashMap<>();
        testMap(linkedHashMap);

    }
    private static void initDataConcurrentHashMap() {
        System.out.println("---------initDataConcurrentHashMap--------");
        ConcurrentHashMap<String,Integer> concurrentHashMap =  new ConcurrentHashMap<>();
        testMap(concurrentHashMap);

    }
    private static void testMap(Map<String, Integer> map) {
        map.put("1das", 1);
        map.put("2das", 2);
        map.put("3das", 3);
        map.put("4das", 4);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

}
