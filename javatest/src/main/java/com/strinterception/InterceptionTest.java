package com.strinterception;

import com.utils.StringUtils;

public class InterceptionTest {
    public static void main(String[] args) {
//        test01();
        test02();
        test03();
        test04();
        test05();
    }

    private static void test02() {



        String string = "解[=jie4]甲归田。。";
        Object[] objects = polyphonicInterception(string);
        if (objects[0] instanceof  Boolean){
             System.out.println(objects[1]);
        }


    }

    private static void test03() {



        String string = "解[=jie4]甲归田,着[=zhuo2]手[]。。";
        Object[] objects = polyphonicInterception(string);
        if (objects[0] instanceof  Boolean){
            System.out.println(objects[1]);
        }

        string = StringUtils.polyphonicInterception(string);
        System.out.println("string:" + string);


    }
    private static void test04() {



        String string = "解[=jie4]甲归田,着[=zhuo2]手[=]。。";
        Object[] objects = polyphonicInterception(string);
        if (objects[0] instanceof  Boolean){
            System.out.println(objects[1]);
        }


    }

    private static void test05() {



        String string = "解[=jie4]甲归田,着[=zhuo2]手[]。。";

        string = StringUtils.polyphonicInterception(string);
        System.out.println(">>test05()>>string:" + string);


    }

    /**
     * 多音字拦截。
     * @param str 字符串
     * @return Object[] 长度为2，第一位是否拦截标志，true,表示被拦截处理过，第二位为处理过的字符串；
     */
    private static Object[] polyphonicInterception(String str){
        ////解[=jie4]甲归田。。
        Object[] resultArray = new Object[]{false,str};
        String customStr = (String) resultArray[1];
        if (customStr != null){
            int indexStart = customStr.indexOf("[=");
            int indexEnd = customStr.indexOf("]");
            if (indexStart >=0 && indexEnd > indexStart){
                final String dealedStr = customStr.substring(0,indexStart) + customStr.substring(indexEnd + 1);
                resultArray[0] = true;
                resultArray[1] = dealedStr;

                //如果截取后的字符串，还包含多音字，就递归过滤
                if (dealedStr != null && dealedStr.indexOf("[=") >=0 && dealedStr.indexOf("]") > dealedStr.indexOf("[=")){
                    return polyphonicInterception(dealedStr);
                }

            }
        }

        return resultArray;
    }

}
