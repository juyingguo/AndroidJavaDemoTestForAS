package com.baseconditiontest;

public class SwitchTest {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        int a = 1;
        switch (a){
            case 0:
                System.out.println("0:");
            case 1:
                System.out.println("1:");
            case 2:
                System.out.println("2:");
                break;
            default:
                System.out.println("default:");
                break;


        }
    }
}
