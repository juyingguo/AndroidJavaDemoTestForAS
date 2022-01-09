package com.datastructtest;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

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
    @Test
    public void hashMapHashAndKeyAllSameTest(){
       HashMap<String, String> map = new HashMap<String, String>(16,0.75f);
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("3", "e");
        for (Iterator<String> iterator = map.values().iterator(); iterator.hasNext();) {
            String name = (String) iterator.next();
            System.out.println(name);
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    /**
     * 测试hash相同，key不相同的情况。为了便于测试，可以自定义Bean来故意保证hash相同，key不同。
     */
    @Test
    public void hashMapHashSameKeyNotSameTest(){
        HashMap<MyBean, String> map = new HashMap<MyBean, String>(16,0.75f);
        MyBean beanA = new MyBean("a");
        MyBean beanB = new MyBean("b");
        MyBean beanC = new MyBean("c");
        map.put(beanA, "a1");
        map.put(beanB, "b1");
        map.put(beanC, "c1");
        System.out.println("map.size():" + map.size());
        System.out.println("(beanA == beanB):" + (beanA == beanB));
        System.out.println("(beanA.equals(beanB)):" + (beanA.equals(beanB)));
        System.out.println("(beanB.equals(new MyBean(\"b\"))):" + (beanB.equals(new MyBean("b"))));
        for (Iterator<String> iterator = map.values().iterator(); iterator.hasNext();) {
            String name = (String) iterator.next();
            System.out.println(name);
        }
        for (Map.Entry<MyBean, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
    class  MyBean{
        int sameHashCode = 1;
        String name;
        public MyBean(String name) {
            this.name = name;
        }

        @Override
        public int hashCode() {
            return sameHashCode;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyBean myBean = (MyBean) o;
            return name.equals(myBean.name);
        }

        @Override
        public String toString() {
            return "MyBean{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
