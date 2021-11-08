package com.datastructtest;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {
    @Test
    public void paramAccessOrderIsTrueTest(){
        Map<String, String> map = new LinkedHashMap<String, String>(16,0.75f,true);
             map.put("1", "a");
             map.put("2", "b");
             map.put("3", "c");
             map.put("4", "e");

             for (Iterator<String> iterator = map.values().iterator(); iterator.hasNext();) {
                     String name = (String) iterator.next();
                     System.out.print(name);
             }
    }
    @Test
    public void paramAccessOrderIsTrueGetOperateTest(){
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(16,0.75f,true);
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "e");

        map.get("1");
        map.get("2");
        map.get("1");
        for (Iterator<String> iterator = map.values().iterator(); iterator.hasNext();) {
            String name = (String) iterator.next();
            System.out.print(name);
        }
    }
}