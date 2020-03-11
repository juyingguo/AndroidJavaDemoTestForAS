package com.algorithm.sorttest;

import com.utils.ArrayUtils;

public class BubbleSortVerify {
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
        ArrayUtils.printArray(a);

        bubbleSort(a);
        System.out.println("******** select sort after ...");
        ArrayUtils.printArray(a);

    }

    /**
     * 将序列中所有元素两两比较，将最大的放在最后面。
     *
     * 将剩余序列中所有元素两两比较，将最大的放在最后面。
     *
     * 重复第二步，直到只剩下一个数。
     * @param a array
     */
    public static void bubbleSort(int []a){
        int len=a.length;
        for(int i=0;i<len;i++){//i为拍好序的元素个数
            for(int j=0;j<len-i-1;j++){//注意第二重循环的条件
                if(a[j]>a[j+1]){
                    int temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }
    }
}
