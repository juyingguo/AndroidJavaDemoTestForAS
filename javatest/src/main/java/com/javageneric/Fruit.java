package com.javageneric;

//public class Fruit {
//}
//class Apple extends Fruit {}

class Plate<T>{
    private T item;
    public Plate(T t){item=t;}
    public void set(T t){item=t;}
    public T get(){return item;}
}
//Lev 1
class Food{}
//Lev 2
class Fruit extends Food{}
class Meat extends Food{}
//Lev 3
class Apple extends Fruit{}
class Banana extends Fruit{}
class Pork extends Meat{}
class Beef extends Meat{}
//Lev 4
class RedApple extends Apple{}
class GreenApple extends Apple{}

class Test{

    @org.junit.Test
    public void test(){
//        Plate<Fruit> p=new Plate<Apple>(new Apple());
    }
    @org.junit.Test
    public void testExtends(){
        Plate<? extends Fruit> p=new Plate<Apple>(new Apple());  //不能存入任何元素
//        p.set(new Fruit()); //Error
//        p.set(new Apple()); //Error
        //读职出来的东西R能存放在Fruit或它的基类里。
        Fruit newFruit1=p.get();
        Object newFruit2=p.get();
//        Apple newFruit3=p.get(); //Error

    }
    @org.junit.Test
    public void testSuper(){
        Plate<? super Fruit> p=new Plate<Fruit>(new Fruit()); //存入元者正常
        p.set(new Fruit());
        p.set(new Apple()); //读职出来的东西R能存放在0bject类里。
//        Apple newFruit3=p.get(); //Error
//        Fruit newFruit1=p.get(); //Error
        Object newFruit2=p.get();


    }
    @org.junit.Test
    public void testGenericCompareSuper(){
        Plate<Fruit> p=new Plate<Fruit>(new Fruit()); //存入元者正常
        p.set(new Fruit());
        p.set(new Apple()); //读职出来的东西R能存放在0bject类里。
//        Apple newFruit3=p.get(); //Error
        Fruit newFruit1=p.get();
        Object newFruit2=p.get();


    }
}