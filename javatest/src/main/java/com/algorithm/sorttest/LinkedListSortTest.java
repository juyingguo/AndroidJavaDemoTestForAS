package com.algorithm.sorttest;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Date:2020/4/14,15:45
 * author:jy
 */
public class LinkedListSortTest {
    public static void main(String[] args) {
        List<Edge> list = new LinkedList<Edge>();

        Edge e3 = new Edge(4);
        Edge e1 = new Edge(1);
        Edge e2 = new Edge(3);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        Collections.sort(list, new EdgeCompare());
        System.out.println(list);
    }
}
