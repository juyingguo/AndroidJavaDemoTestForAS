package com.javareflect.two;
/**
*
* @ClassName: Demo1
* @Description: 获取类对象
* @author LQ
* @date 2019年2月22日 下午5:36:23
*  https://blog.csdn.net/li_2580/article/details/87888513
*/
public class Demo1 {
        public static void main(String[] args)
                        throws ClassNotFoundException,  InstantiationException, IllegalAccessException {
                // 1. Class.forname
                // 通过全限定名进行获取
                // clz 就是类对象（类类的类对象）
                // Class<?> clz =  Class.forName("com.lq.reflect.Student");
 
                // 2. 类名 . Class 通用查询方法
                // Class clz=Student.class;
                // 获取的是student类对象
                // System.out.println(clz.newInstance());
 
                // 3.通过具体的类实例拿到类类的类实例（对象 .  getClass） 通用的增删改方法
                // Student stu=new Student();
                // Class clz=stu.getClass();
                // System.out.println(clz.newInstance());
                // 反射怎么实例化对象 clz.newInstance(); 就是实例化对象
                //通过反射去创建一个学生对象的实例
                Class clz = Student.class;
                Student stu = (Student) clz.newInstance();
        }
}