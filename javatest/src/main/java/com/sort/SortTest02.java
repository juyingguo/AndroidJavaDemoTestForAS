package com.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author puyf
 * 12.1、【java】Comparator的用法
 * 		https://blog.csdn.net/u012250875/article/details/55126531
 */
public class SortTest02 {
    public static void main(String[] args) {
        List<Integer> re = new ArrayList<>();

        re.add(1);
        re.add(2);
        re.add(6);
        re.add(5);
        re.add(8);
        re.add(8);
        re.add(4);

        Collections.sort(re, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                //下面这么写，结果是降序
                if(o1 < o2){
                    return 1;
                }else if(o1 > o2){
                    return -1;
                }
                return 0;
            }

        });

        System.out.println(re);
    }
}
