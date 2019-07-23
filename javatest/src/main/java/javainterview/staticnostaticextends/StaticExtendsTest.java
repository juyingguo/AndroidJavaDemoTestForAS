package javainterview.staticnostaticextends;
 
/**
 * （类的静态变量和静态方法能否被子类继承？）
结论：java中静态属性和静态方法可以被继承，但是没有被重写(overwrite)而是被隐藏.
https://www.cnblogs.com/twoheads/p/10244017.html
 * @author username
 *
 */
public class StaticExtendsTest {
 
    public static void main(String[] args) {
        C c = new C();
        System.out.println(c.nonStaticStr);
        System.out.println(c.staticStr);
        c.staticMethod();//输出的结果都是父类中的非静态属性、静态属性和静态方法,推出静态属性和静态方法可以被继承
        c.nonStaticMethod();
        
        System.out.println("-------------------------------");
        
        A c1 = new C();
        System.out.println(c1.nonStaticStr);
        System.out.println(c1.staticStr);
        c1.staticMethod();//结果同上，输出的结果都是父类中的非静态属性、静态属性和静态方法,推出静态属性和静态方法可以被继承
        c1.nonStaticMethod();
    
        System.out.println("-------------------------------");
        B b = new B();
        System.out.println(b.nonStaticStr);
        System.out.println(b.staticStr);
        b.staticMethod();
        b.nonStaticMethod();
        
        System.out.println("-------------------------------");
        /**
         * A非静态属性
			A静态属性
			A静态方法
			B改写后的非静态方法
         */
        A b1 = new B();
        System.out.println(b1.nonStaticStr);
        System.out.println(b1.staticStr);
        b1.staticMethod();
        b1.nonStaticMethod();//结果都是父类的静态方法，说明静态方法不可以被重写，不能实现多态
    }
 
}