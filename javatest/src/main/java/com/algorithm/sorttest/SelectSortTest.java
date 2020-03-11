package com.algorithm.sorttest;

/**
 * 选择排序。
 * <br/>
 *  * 1.1、
 * https://www.cnblogs.com/10158wsj/p/6782124.html?utm_source=tuicool&utm_medium=referral
 *
 * <br/>
 * 1.2、
 *  https://blog.csdn.net/u012373815/article/details/77939896
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
         * 5,13,23,32,43  //3
         *
         * 外层循环总共分4组比较,。4 = sum -1;
         * 每一组中记录最小的数的索引，与当前所比较的数的第一个数交换。
         *
         * 首先确定循环次数，并且记住当前数字和当前位置。
         * 将当前位置后面所有的数与当前数字进行对比，小数赋值给key，并记住小数的位置。
         * 比对完成后，将最小的值与第一个数的值交换。
         *
         * 重复2、3步。
         *
         */
        System.out.println("******** raw array ...");
        printArray(a);

        selectSort(a);
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

    public static void selectSort(int[]a){
        int len=a.length;
        for(int i=0;i<len;i++){//循环次数
            int value=a[i];//当前最小数字，先指向每次循环的第一个数字。
            int position=i;//当前最小数字的位置索引。先指向每次循环的第一个数字的位置索引。
            for(int j=i+1;j<len;j++){// //遍历后面的数据比较最小
                if(a[j]<value){//如果当前数据不是最小的，则交换
                    value=a[j];//记录最小的数据
                    position=j;//记录最小的数据的索引
                }
            }
            a[position]=a[i];//进行交换
            a[i]=value;
        }
    }


}
