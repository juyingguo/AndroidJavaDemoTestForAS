package com.algorithm.queue;

/**
 * Date:2021/12/14,14:19
 * author:jy
 */
public class LinkedQueue {
    //定义一个节点类
    private class Node{
        String value;
        Node next;
    }
    private int size = 0;
    //head指向队头结点， tail指向队尾节点
    private Node head;
    private Node tail;
    //申请一个队列
    public LinkedQueue(){}
    //入队
    public boolean enqueue(String item){
        Node newNode = new Node();
        newNode.value = item;
        if (size == 0) head = newNode;
        else tail.next = newNode;
        tail = newNode;
        size++;
        return true;
    }
    //出队
    public String dequeue(){
        String res = null;
        if(size == 0) return res;
        if(size == 1) tail = null;
        res = head.value;
        head = head.next;
        size--;
        return res;
    }
}
