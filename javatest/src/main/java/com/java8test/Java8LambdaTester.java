package com.java8test;

public class Java8LambdaTester {
   public static void main(String args[]){
      Java8LambdaTester tester = new Java8LambdaTester();
        
      // 类型声明
      MathOperation addition = (int a, int b) -> a + b;
        
      // 不用类型声明
      MathOperation subtraction = (a, b) -> a - b;
        
      // 大括号中的返回语句
      MathOperation multiplication = (int a, int b) -> { return a * b; };
        
      // 没有大括号及返回语句
      MathOperation division = (int a, int b) -> a / b;
        
      System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
      System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
      System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
      System.out.println("10 / 5 = " + tester.operate(10, 5, division));
        
      // 不用括号
      GreetingService greetService1 = message ->
      System.out.println("Hello " + message);
        
      // 用括号
      GreetingService greetService2 = (message) ->
      System.out.println("Hello " + message);
        
      greetService1.sayMessage("Runoob");
      greetService2.sayMessage("Google");
      
      useCommonWay();
      accessVariable();
      accessLocalVariable2();
   }

   final static String salutation = "Hello! ";

   final static int number = 1;
   /**
    * 测试访问变量
    */
   private static void accessVariable() {
      GreetingService greetService1 = message ->
              System.out.println("accessVariable():" + (salutation) + message);
      GreetingService greetService2 = message ->{System.out.println("accessVariable():" + (number) + message);};
      greetService1.sayMessage(" Runoob");
      greetService2.sayMessage(" number");
   }

   /**
    * lambda 表达式的局部变量可以不用声明为 final，但是必须不可被后面的代码修改（即隐性的具有 final 的语义）
    */
   private static void accessLocalVariable2() {
      /*final*/ int num = 1;
      Converter<Integer, String> s = (param) -> System.out.println("accessLocalVariable2():" + String.valueOf(param + num));
      s.convert(2);  // 输出结果为 3
      ///num = 4;
   }
   public interface Converter<T1, T2> {
      void convert(int i);
   }

   /**
    * 1.Lambda 表达式免去了使用匿名方法的麻烦，并且给予Java简单但是强大的函数化的编程能力。
    * 2.不使用Lambda,就得使用匿名方法
    */
   private static void useCommonWay() {
       MathOperation add = new MathOperation(){
           @Override
           public int operation(int a, int b) {
               return a+b;
           }
       };
      System.out.println("useCommonWay 10 + 5 = " + add.operation(10,5));
   }
   private static void useCommonWay2() {
      MathOperation mathOperationAdd = new MathOperation(){
         @Override
         public int operation(int a, int b) {
            return a+b;
         }
      };
      System.out.println("useCommonWay 10 + 5 = " + mathOperationAdd.operation(10,5));
   }

   interface MathOperation {
      int operation(int a, int b);
   }
    
   interface GreetingService {
      void sayMessage(String message);
   }
    
   private int operate(int a, int b, MathOperation mathOperation){
      return mathOperation.operation(a, b);
   }
   
   
}