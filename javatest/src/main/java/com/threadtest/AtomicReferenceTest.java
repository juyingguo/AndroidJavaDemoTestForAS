package com.threadtest;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Date:2019/8/2,14:01
 * author:jy
 */
public class AtomicReferenceTest {
    public static void main(String[] args) {
        test();
        test2();
    }


    private static AtomicReference<Thread> owner = new AtomicReference<Thread>();
    private static void test() {
        Thread current = Thread.currentThread();
        owner.set(null);
        boolean b = owner.compareAndSet(null, current);
        System.out.println("b:" + b);
        System.out.println("owner.get():" + owner.get());
        Thread thread2 = new Thread("thread2");
        owner.set(thread2);
        Thread thread3 = new Thread("thread3");
        b = owner.compareAndSet(thread2, thread3);
        System.out.println("b:" + b);
        System.out.println("owner.get():" + owner.get());

    }


    private static void test2() {
        Person2 p1 = new Person2(101);
        Person2 p2 = new Person2(102);
        // 新建AtomicReference对象，初始化它的值为p1对象
        AtomicReference ar = new AtomicReference(p1);
        //更改p1的id.
        p1.setId(106);
        System.out.println("p1 is="+p1);
        System.out.println("ar.get() is="+ar.get());
        System.out.println("p1.equals(ar.get()="+p1.equals(ar.get()));

        // 通过CAS设置ar。如果ar的值为p1的话，则将其设置为p2。
        ar.compareAndSet(p1, p2);



        Person2 p3 = (Person2)ar.get();
        System.out.println("p3 is "+p3);
        System.out.println("p3.equals(p1)="+p3.equals(p1));
        System.out.println("p3.equals(p2)="+p3.equals(p2));

        Person2 px = new Person2(102);
        Person2 py = new Person2(110);
        System.out.println("p3.equals(px)="+p3.equals(px));

        boolean b = ar.compareAndSet(px, py);
        System.out.println("b:" + b);
        System.out.println("ar.get="+ar.get());

    }

    static class Person2 {
        volatile long id;
        public Person2(long id) {
            this.id = id;
        }
        public String toString() {
            return "id:"+id;
        }
        public void setId(long id){
            this.id=id;
        }

        //方式一：
        //方式二：重写不重写，都一样，ar.compareAndSet(p1, p2)内部还是比较内存地址。
        /*@Override
        public boolean equals(Object o) {
            if (o instanceof Person2){
                return ((Person2) o).id == id;
            }
            return false;
        }*/

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }
/*---------------------
    作者：fxkcsdn
    来源：CSDN
    原文：https://blog.csdn.net/fxkcsdn/article/details/82261972
    版权声明：本文为博主原创文章，转载请附上博文链接！*/
}
