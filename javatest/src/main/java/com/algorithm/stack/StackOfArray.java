package com.algorithm.stack;

import java.util.Iterator;

/**
 * 栈的数组实现,支持动态扩容
 * @param <Item>
 */
public class StackOfArray<Item> implements Iterable<Item> {
    /** 存储数据的数组 */
    private Item[] a = (Item[]) new Object[1];
    /** 记录元素个数N */
    private int N = 0;

    /** 构造器*/
    public StackOfArray() {
    }

    /** 添加元素*/
    public void push(Item item) {
        //自动扩容
        if (N == a.length) resize(2 * a.length);
        a[N++] = item;
    }

    /** 删除元素*/
    public Item pop() {
        if (isEmpty()) return null;
        Item item = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length / 4) resize(a.length / 2);
        return item;
    }

    /** 是否为空*/
    public boolean isEmpty() {
        return N == 0;
    }

    /** 元素个数*/
    public int size() {
        return N;
    }

    /** 改变数组容量*/
    private void resize(int length) {
        Item[] temp = (Item[]) new Object[length];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    /** 返回栈中最近添加的元素而不删除它*/
    public Item peek() {
        if (isEmpty()) return null;
        return a[N - 1];
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    /** 内部类*/
    class ArrayIterator implements Iterator {
        /** 控制迭代数量*/
        int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            if (isEmpty()) return null;
            return a[--i];
        }
    }
}