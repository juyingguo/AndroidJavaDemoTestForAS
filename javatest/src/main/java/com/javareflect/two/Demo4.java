package com.javareflect.two;
import java.lang.reflect.Field;
/**
*
* @ClassName: Demo4
* @Description: 读写属性
* @author LQ
* @date 2019年2月22日 下午9:14:27
*
*/
public class Demo4 {
        public static void main(String[] args) throws  NoSuchFieldException, SecurityException,  IllegalArgumentException, IllegalAccessException {
                // 一切反射相关的代码都从获得类对象开始
                Class clz = Student.class;
                Student stu =new Student();
                
 
//             stu.age=22;
//             Field field = clz.getField("age");
//          第一个参数：具体的类的类实例
//          第二个参数：就是给field对象需要赋的值
//          field.set(stu, 18);
//             System.out.println("反射取值："+field.get(stu));
//             System.out.println("OOP取值："+stu.age);
//             输出：
//             加载进jvm中！
//             调用无参构造方法创建了一个学生对象
//             反射取值：22
//             OOP取值：22
                
                
//             stu.setSname("有点意思");
//             Field field = clz.getDeclaredField("sname");
//             field.setAccessible(true);
//             field.set(stu, "确实有点意思");
//             System.out.println("反射赋值："+field.get(stu));
//             System.out.println("OOP赋值："+stu.getSname());
//             输出：
//             加载进jvm中！
//             调用无参构造方法创建了一个学生对象
//             反射赋值：确实有点意思
//             OOP赋值：确实有点意思
                
                
                stu.age=18;
                stu.setSid("s001");
                stu.setSname("张三");
////             反射取值   VS   oop取值
                System.out.println(stu.age);
                System.out.println(stu.getSid());
                System.out.println(stu.getSname());
////             Dao层插入操作
                Field[] declaredFields = clz.getDeclaredFields();
                for (Field field : declaredFields) {
                        field.setAccessible(true);
                        System.out.println(field.getName()+","+field.get(stu));
                }
//             输出：
//             加载进jvm中！
//             调用无参构造方法创建了一个学生对象
//             18
//             s001
//             张三
//             sid,s001
//             sname,张三
//             age,18
                
        }
}