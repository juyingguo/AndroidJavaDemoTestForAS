package com.strinterception;

public class StringTest {

    public static void main(String[] args) {



        test05();

    }

    private static void test05() {

        final String appendStr = "..";
        String string = "解放..abc...test" + appendStr;
        System.out.println(string.lastIndexOf(appendStr));
        if (string.endsWith(appendStr)){
            string = string.substring(0,string.lastIndexOf(appendStr));
        }
        System.out.println(string);
        System.out.println(String.format("温度: %.1f",36.02));
        System.out.println(String.format("温度: %5.1f",35.555));
        System.out.println(String.format("温度: %-5.5f",35.555));
        System.out.println(String.format("温度: %-5.5f",35.555));

    }


}
