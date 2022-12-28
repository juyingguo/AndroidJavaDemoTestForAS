package com.collection.listtest;

import java.util.ArrayList;
import java.util.List;

public class ListForeachTest {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        List<Integer> list = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        for(int i=0 ;i<10;i++){
            list.add(i);
        }

        list.forEach(integer -> {

            System.out.println("integer:" + integer);
            if (integer >= 5) {
                return;
            }
            result.add(integer);
        });

        System.out.println(result.size());
    }
}
