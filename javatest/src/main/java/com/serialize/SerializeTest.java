package com.serialize;

import java.io.Serializable;
import java.util.UUID;

public class SerializeTest implements Serializable{
    private final long serialVersionUID = UUID.randomUUID().hashCode();

    public static void main(String[] args) {
        test1();

    }

    private static void test1() {
        for (int i=0; i<100;i++){
            System.out.println("test1()>>>UUID.randomUUID().hashCode():" + UUID.randomUUID().hashCode());
        }
    }
}
