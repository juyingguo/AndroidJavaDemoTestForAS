package com.algorithm.stack;

import org.junit.Test;

public class StackOfArrayTest {
    @Test
    public void baseOperationTest(){
        StackOfArray arrayStack = new StackOfArray();
        arrayStack.push("a");
//        arrayStack.push("f");

        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
    }
}
