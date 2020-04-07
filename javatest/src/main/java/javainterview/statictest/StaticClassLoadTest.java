package javainterview.statictest;

/**
 * Date:2020/3/31,17:33
 * author:jy
 */
public class StaticClassLoadTest {

    public static void main(String[] args) {
        System.out.println("Student.name:" + Student.name);
    }

    static class Student{
        private  static String name = null;
        static {
            name = "eee";
        }
    }
}
