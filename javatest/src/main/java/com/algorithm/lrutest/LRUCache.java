package com.algorithm.lrutest;

import java.util.HashMap;

/**
 * Date:2021/11/8,11:46
 * author:jy
 */
public class LRUCache<K,V> {
    public static boolean mLogSwitch = false;
    private String TAG = "LRUCache";
    /**数据结构*/
    class CacheNode {
        Object key; // 键
        Object value; // 值
        CacheNode next; // 后继节点
        CacheNode pre; // 前继节点
    }
    /**缓存容器*/
    HashMap<K, CacheNode> caches;

    /**容量大小*/
    private int capacity;
    /**头结点*/
    private CacheNode head;
    /**尾节点*/
    private CacheNode tail;
    /**实例化*/
    public LRUCache(int size) {
        // 容器大小
        this.capacity = size;
        // 实例化容器
        caches = new HashMap<K, CacheNode>(size);
    }
    /**
     *  添加k-v
     * @param k 键
     * @param v 值
     * @return 值
     */
    public V put(K k, V v) {
        // 1. 从容器中查找是否存在
        CacheNode cacheNode = caches.get(k);
        // 2. 若存在于容器中 将CacheNode节点移到容器队首
        // 3. 若不存在与容器中
        if (cacheNode == null) {
            // 3.1 容器实际大小 是否大于 所允许存放的最大数量
            if (caches.size() >= capacity) {
                if(mLogSwitch){
                    System.out.println(TAG + " put caches.size():" + caches.size() + ">= capacity size [" + capacity + "] to remove tail.");
                }
                // 3.1.1 若容器实际大小大于所允许存放的最大数量 将容器尾部的CacheNode节点(活跃度不高的节点)删除
                caches.remove(tail.key);
                // 将tail节点指向它前一个节点  更新tail节点的指向
                removeLast();
            }
            // 3.1.2 若容器实际大小小于所允许存放的最大数量
            // 3.1.2.1 将其封装成CacheNode节点 投入到容器中
            cacheNode = new CacheNode();
            cacheNode.key = k;
        }
        cacheNode.value = v;
        // 将节点 移到 容器头部 保证 容器中的节点是按活跃度排序的
        moveToFirst(cacheNode);
        // 将当前节点封装成CacheNode 填充到容器中
        caches.put(k ,cacheNode);
        return v;
    }
    /**
     *  获取该节点
     * @param k
     * @return
     */
    public V get(K k) {
        // 查询该k 是否存在于容器中
        CacheNode node = caches.get(k);
        if (node == null) {
            return null;
        }
        // 将该节点移到容器头部
        moveToFirst(node);
        return (V)node.value;
    }
    /**
     *  将CacheNode移到容器头部 & 更新head和tail节点的指向
     *  0. 若当前节点等于head节点 无需移到 直接return
     *  1. 若当前节点的next节点不为空 将当前节点的后继节点的前继节点指向当前节点的前继节点
     *  2. 若当前节点的pre节点不为空 将当前节点的前继节点的后继节点指向当前节点的后继节点
     *  3. 将tail节点等于当前节点 更新tail节点指向 将tail节点指向当前节点的前继节点
     *  4. 若head和tail节点都为空 直接将当前节点赋值给head和tail节点 -- 表示第一次添加k-v键值对
     * @param cacheNode
     */
    private void moveToFirst(CacheNode cacheNode) {
        if(mLogSwitch){
            System.out.println(TAG + " moveToFirst enter cacheNode:" + cacheNode.key + ":" + cacheNode.value);
        }
        // 若当前节点等于head节点 无需移到 直接return
        if (head == cacheNode) return;
        // 若当前节点的next节点不为空 将当前节点的后继节点的前继节点指向当前节点的前继节点
        if (cacheNode.next != null) cacheNode.next.pre = cacheNode.pre;
        // 若当前节点的pre节点不为空 将当前节点的前继节点的后继节点指向当前节点的后继节点
        if (cacheNode.pre != null) cacheNode.pre.next = cacheNode.next;
        // 将tail节点等于当前节点 更新tail节点指向 将tail节点指向当前节点的前继节点
        if (tail == cacheNode) tail = cacheNode.pre;
        // 若head和tail节点都为空 直接将当前节点赋值给head和tail节点 -- 表示第一次添加k-v键值对
        if (head == null || tail == null) {
            head = tail = cacheNode;
            return;
        }
        // 将当前节点的next指向head节点
        cacheNode.next = head;
        // head节点的前继节点指向当前节点
        head.pre = cacheNode;
        // 将当前节点赋值给head节点
        head = cacheNode;
        // 将当前节点的前继节点置为空
        head.pre = null;
        if(mLogSwitch){
            System.out.println(TAG + " moveToFirst head:" + head.key + ":" + head.value);
            System.out.println(TAG + " moveToFirst head.next:" + head.next.key + ":" + head.next.value);
            System.out.println(TAG + " moveToFirst cacheNode.next:" + cacheNode.next.key + ":" + cacheNode.next.value);
        }
    }
    /**
     * 将tail节点指向tail.pre
     */
    private void removeLast() {
        if (tail != null) {
            // 将tail节点更新为它的前继节点
            tail = tail.pre;
            // 若tail节点为空 表示容器中无节点 将head置为空
            if (tail == null) head = null;
                // 否则 将tail节点的next置为空
            else {
                if(mLogSwitch){
                    System.out.println(TAG + " removeLast tail.next:" + tail.next.key + ":" + tail.next.value);
                }
                tail.next = null;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TAG);
        sb.append(" ");
        CacheNode node = head;
        while (node != null) {
            sb.append(String.format("%s:%s ", node.key, node.value));
            node = node.next;
        }

        return sb.toString();
    }

}
