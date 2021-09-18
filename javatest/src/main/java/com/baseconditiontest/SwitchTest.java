package com.baseconditiontest;

import org.junit.Test;

/**
 * switch test.<br/>
 * 1.switch with return test<br/>
 * 1.1 when return in one case,the code after switch will not execute.
 */
public class SwitchTest {
    public static void main(String[] args) {
    }
    @Test
    public void test() {
        int a = 1;
        switch (a){
            case 0:
                System.out.println("0:");
                break;
            case 1:
                System.out.println("1:");
                break;
            case 2:
                System.out.println("2:");
                break;
            default:
                System.out.println("default:");
                break;


        }
    }
    @Test
    public void testSwitchWithReturn() {
        int a = 5;
        switch (a){
            case 0:
                System.out.println("0:");
            case 1:
                System.out.println("1:");
                break;
            case 2:
                System.out.println("2:");
                break;
            default:
                System.out.println("default:");
                break;
        }

        System.out.println("SwitchTest.testSwitchWithReturn,a:" + a);
    }
}
