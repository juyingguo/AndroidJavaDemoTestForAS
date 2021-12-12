package com.algorithm.lrutest;

import org.junit.Test;

/**
 * Date:2021/11/8,11:53
 * author:jy
 */
public class LRUCacheTest {
    @Test
    public void test() {
        LRUCache<Integer, String> lru = new LRUCache<>(3);
        LRUCache.mLogSwitch = false;
        lru.put(1, "a");
        System.out.println(lru.toString());
        lru.put(2, "b");    // 2:b 1:a
        System.out.println(lru.toString());
        lru.put(3, "c");    // 3:c 2:b 1:a
        System.out.println(lru.toString());
        lru.put(4, "d");    // 4:d 3:c 2:b
        System.out.println(lru.toString());
//        lru.put(1, "aa");   // 1:aa 4:d 3:c
//        System.out.println(lru.toString());
//        lru.put(2, "bb");   // 2:bb 1:aa 4:d
//        System.out.println(lru.toString());
//        lru.put(5, "e");    // 5:e 2:bb 1:aa
//        System.out.println(lru.toString());
//        lru.get(1);         // 1:aa 5:e 2:bb
//        System.out.println(lru.toString());
    }

}
