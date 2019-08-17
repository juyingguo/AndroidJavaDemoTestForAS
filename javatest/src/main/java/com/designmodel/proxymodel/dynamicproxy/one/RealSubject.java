package com.designmodel.proxymodel.dynamicproxy.one;

public class RealSubject implements Subject
{   
  public void doSomething()   
  {   
    System.out.println( "call doSomething()" );   
  }   
}