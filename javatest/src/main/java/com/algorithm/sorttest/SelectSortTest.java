package com.algorithm.sorttest;

/**
 * 选择排序。
 * https://www.cnblogs.com/10158wsj/p/6782124.html?utm_source=tuicool&utm_medium=referral
 */
public class SelectSortTest {

    public static void main(String[] args) {
        int[] a  = new int[]{32,43,23,13,5};
        /**
         * 32,43,23,13,5
         *
         * 5,43,23,13,32  //0
         * 5,13,23,43,32  //1
         * 5,13,23,43,32  //2
         * 5,13,23,32,43  //2
         *
         * 总共分4组比较,。4 = sum -1;
         * 每一组中记录最小的数的索引，与当前所比较的数的第一个数交换。
         *
         * 首先确定循环次数，并且记住当前数字和当前位置。
         * 将当前位置后面所有的数与当前数字进行对比，小数赋值给key，并记住小数的位置。
         * 比对完成后，将最小的值与第一个数的值交换。
         *
         * 重复2、3步。
         *
         */
        selectSort(a);


    }
    public static void selectSort(int[]a){
        int len=a.length;
        for(int i=0;i<len;i++){//循环次数
            int value=a[i];//当前最小数字，先指向每次循环的第一个数字。
            int position=i;//当前最小数字的位置索引。先指向每次循环的第一个数字的位置索引。
            for(int j=i+1;j<len;j++){//找到最小的值和位置
                if(a[j]<value){
                    value=a[j];
                    position=j;
                }
            }
            a[position]=a[i];//进行交换
            a[i]=value;
        }
    }
}
