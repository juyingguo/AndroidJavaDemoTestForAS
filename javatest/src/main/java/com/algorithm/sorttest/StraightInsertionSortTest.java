package com.algorithm.sorttest;

/**
 * 直接插入排序（Straight Insertion Sorting）的基本思想：在要排序的一组数中，假设前面(n-1) [n>=2] 个数已经是排好顺序的，
 * 现在要把第n个数插到前面的有序数中，使得这n个数也是排好顺序的。如此反复循环，直到全部排好顺序。
 */
public class StraightInsertionSortTest {
    public static void main(String[] args) {
        int[] a  = new int[]{32,43,23,13,5};
        /**
         * 32,43,23,13,5
         *
         * 32，//i=0 一个数字时无需进行比较。
         *
         * 32,43  //i=1
         *      32,43 //排好序后
         * 32,43,23,  //i=2
         *      23，32,43 //排好序后
         * 23，32,43,13  //i= 3
         *      13,23，32,43,//排好序后
         * 13,23，32,43,5  //i = 4
         *          5，13,23，32,43 //排好序后
         *
         *
         *
         */
        System.out.println("******** raw array ...");
        printArray(a);

        straightInsertionSort(a);
        System.out.println("******** select sort after ...");
        printArray(a);

    }

    private static void printArray(int[] a) {

        if (a != null || a.length>0){
            for (int i = 0; i <a.length; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println();
        }
    }
    public static void straightInsertionSort(int [] a){
        int len=a.length;//单独把数组长度拿出来，提高效率
        int insertNum;//要插入的数
        for(int i=1;i<len;i++){//排序多少次，第一个数不用排序
            insertNum=a[i];
            int j=i-1;//前面已经排好序的序列数组索引最大值。
            while(j>=0 && a[j]>insertNum){//从后往前循环，将大于insertNum的数向后移动
                a[j+1]=a[j];//j 位元素大于insertNum, j 以后元素都往后移动一格
                j--;
            }
            a[j+1]=insertNum;//比较到第j 位时 小于 insertNum ，所以insertNum 应该放在 j+1 位
        }
    }
}
