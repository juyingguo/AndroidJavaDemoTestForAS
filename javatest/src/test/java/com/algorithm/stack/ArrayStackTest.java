package com.algorithm.stack;

import org.junit.Test;

public class ArrayStackTest {
    @Test
    public void baseOperationTest(){
        ArrayStack arrayStack = new ArrayStack(5);
        arrayStack.push("a");
        arrayStack.push("b");

        System.out.println(arrayStack.pop());;
    }
}
