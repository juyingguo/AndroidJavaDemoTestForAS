package com.designmodel.singleton;//for androidstudio
//package main.java.com.designmodel.singleton;//for myeclipse ,now modidy .classpath file xml src node with [kind="src" path="src/main/java"]

/**
 * 单例模式一
 */
public class Singleton {
    
    private static Singleton sing;

    private Singleton() {
        
    }
    
    public static Singleton getInstance() {
        if (sing == null) {
            sing = new Singleton();
        }
        return sing;
    }

    public static void main(String[] args) {
        Singleton sing = Singleton.getInstance();
        Singleton sing2 = Singleton.getInstance();

        System.out.println(sing);
        System.out.println(sing2);
    }

}
