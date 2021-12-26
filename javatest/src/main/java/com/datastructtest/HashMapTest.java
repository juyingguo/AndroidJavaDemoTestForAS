package com.datastructtest;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapTest {
    @Test
    public void hashMapBaseTest(){
       HashMap<String, String> map = new HashMap<String, String>(16,0.75f);
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "e");

        map.get("1");
        map.get("2");
        map.get("1");
        for (Iterator<String> iterator = map.values().iterator(); iterator.hasNext();) {
            String name = (String) iterator.next();
            System.out.println(name);
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
