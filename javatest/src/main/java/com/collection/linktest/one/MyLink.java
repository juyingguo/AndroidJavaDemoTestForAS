package com.collection.linktest.one;

/**
 * 自定义链表设计
 * 
 * @author zjn
 * 【数据结构】链表的原理及java实现
 * 		https://blog.csdn.net/jianyuerensheng/article/details/51200274
 */
public class MyLink {
    Node head = null; // 头节点

    /**
     * 链表中的节点，data代表节点的值，next是指向下一个节点的引用
     * 
     * @author zjn
     *
     */
    static class Node {
        Node next = null;// 节点的引用，指向下一个节点
        int data;// 节点的对象，即内容

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 向链表中插入数据
     * 
     * @param d
     */
    public void addNode(int d) {
        Node newNode = new Node(d);// 实例化一个节点
        if (head == null) {
            head = newNode;
            return;
        }
        Node tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = newNode;
    }

    /**
     * 
     * @param index:删除第index个节点
     * @return
     */
    public boolean deleteNode(int index) {
        if (index < 1 || index > length()) {
            return false;
        }
        if (index == 1) {
            head = head.next;
            return true;
        }
        int i = 1;
        Node preNode = head;
        Node curNode = preNode.next;
        while (curNode != null) {
            if (i == index) {
                preNode.next = curNode.next;
                return true;
            }
            preNode = curNode;
            curNode = curNode.next;
            i++;
        }
        return false;
    }
    /**
     *
     * @param index:删除第index个节点,0,,,
     * @return
     */
    public boolean deleteNodeForFix02(int index) {
        if (index < 0 || index >= length()) {
            checkElementIndex(index);
            return false;
        }
        if (index == 0) {
            head = head.next;
            return true;
        }

        if (index == 1) {
            head.next = head.next.next;
            return true;
        }

        int i = 1;
        Node preNode = head;
        Node curNode = preNode.next;
        while (curNode != null) {
            if (i == index) {
                preNode.next = curNode.next;
                return true;
            }
            preNode = curNode;
            curNode = curNode.next;
            i++;
        }
        return false;
    }
    /**
     *
     * @param index:删除第index个节点,0,,,
     * @return
     */
    public boolean deleteNodeForFix03(int index) {
        if (index < 0 || index >= length()) {
            checkElementIndex(index);
            return false;
        }
        if (index == 0) {
            head = head.next;
            return true;
        }

        /*if (index == 1) {
            head = head.next.next;
            return true;
        }*/

        int i = 1;
        Node preNode = head;
        Node curNode = preNode.next;
        while (curNode != null) {
            if (i == index) {
                preNode.next = curNode.next;
                return true;
            }
            preNode = curNode;
            curNode = curNode.next;
            i++;
        }
        return false;
    }
    /**
     * 
     * @return 返回节点长度
     */
    public int length() {
        int length = 0;
        Node tmp = head;
        while (tmp != null) {
            length++;
            tmp = tmp.next;
        }
        return length;
    }

    /**
     * 在不知道头指针的情况下删除指定节点
     * 
     * @param n
     * @return
     */
    public boolean deleteNode11(Node n) {
        if (n == null || n.next == null)
            return false;
        int tmp = n.data;
        n.data = n.next.data;
        n.next.data = tmp;
        n.next = n.next.next;
        System.out.println("删除成功！");
        return true;
    }

    public void printList() {
        Node tmp = head;
        while (tmp != null) {
            System.out.println(tmp.data);
            tmp = tmp.next;
        }
    }
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    /**
     * Tells if the argument is the index of an existing element.
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < length();
    }
    /**
     * Constructs an IndexOutOfBoundsException detail message.
     * Of the many possible refactorings of the error handling code,
     * this "outlining" performs best with both server and client VMs.
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+length();
    }
    public static void main(String[] args) {


        deleteNoteTest();
//        deleteNoteTest2();
    }

    private static void deleteNoteTest() {
        MyLink list = new MyLink();
        list.addNode(1);
        list.addNode(2);
        list.addNode(3);
        list.addNode(4);
        list.addNode(5);
        list.addNode(6);
        System.out.println("linkLength:" + list.length());
        System.out.println("head.data:" + list.head.data);
        list.printList();
        list.deleteNodeForFix03(1);
//        list.deleteNode(1);
        System.out.println("After deleteNode(4):");
        list.printList();

    }


    private static void deleteNoteTest2() {
        MyLink list = new MyLink();
        list.addNode(1);
        list.addNode(2);
        list.addNode(3);
        list.addNode(4);
        list.addNode(5);
        list.addNode(6);
        System.out.println("linkLength:" + list.length());
        System.out.println("head.data:" + list.head.data);
        list.printList();
//        list.deleteNodeForFix03(1);
//        list.deleteNode(5);

        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node2.next = node3;
        list.deleteNode11(node2);

        System.out.println("After deleteNode(4):");
        list.printList();
    }
}
