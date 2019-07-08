package com.regextest;

import com.utils.RegExpUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest01 {
    public static void main(String[] args) {
//        test01();
//        test02();
        test03();
//        test04();
        test05();
        test06();
        test07();
    }

    private static void test01() {


        String text = "开启状态。";
        System.out.println("text:" + text + ",text.length():" + text.length());
        String str = RegExpUtils.replaceToSpace(RegExpUtils.CUSTOM_REGEX_2,text);
        System.out.println("str:" + str + "，str.length():" + str.length());


    }


    private static void test02() {
//        String regex = "[给|我们]跳舞";
//        String regex = "给?我?们?跳{1}个?舞{1}";
//        String regex = "给?我|们跳{1}[一]?[个支]?舞{1}吧?";
//        String regex = "给?[我|我们]?跳{1}[一]?[个支]?舞{1}吧?";
//        String regex = "给?[我]?[们]?跳{1}[一]?[个支]?舞{1}吧?";
        String regex = "想?给?[我]?[们]?跳{1}[一]?[个支]?舞{1}吧?|不跳舞";
//        String str = "跳舞";
        String[] array = new String[]{"想给我们跳支舞","不跳舞","跳舞","跳个舞","跳一个舞","跳一支舞","我跳舞","我跳个舞","我们跳舞","们跳舞","给我们跳舞","跳舞吧"};

        String regEx = regex;
        Pattern p = Pattern.compile(regEx);

        for (String str : array){
            System.out.println("str:" + str);
            Matcher m = p.matcher(str);
            boolean matches = m.matches();
            System.out.println("matches:" + matches);

        }

    }
    private static void test03() {

        String fileName = "唱歌一首?*小跳蛙-a-[0]";

        Matcher matcher = Pattern.compile("[\\u4e00-\\u9fa5a-zA-Z]+").matcher(fileName);
        StringBuilder nameBuilder = new StringBuilder();
        while (matcher.find()){
            String group = matcher.group();
            System.out.println("group:" + group);
            nameBuilder.append(group);
        }
        fileName = nameBuilder.toString();
        System.out.println("fileName:" + fileName);
    }
    private static void test04() {
        String regex = "^\\d.*x$";
        String regEx = regex;
        String string = "5678abc_x";
        Pattern p = Pattern.compile(regEx);

        Matcher m = p.matcher(string);
        boolean matches = m.matches();
        System.out.println("matches:" + matches);
    }
    private static void test05(){
        String s = RegExpUtils.filterSpecicalChars("   你好啊  --*是吗？  ");
        System.out.println("test05,s:" + s);
    }
    private static void test06(){
        String s = "11-01小跳蛙".toLowerCase().replaceAll("^\\d+[-、]?\\d?", "");
        String s2 = "小苹果11".toLowerCase().replaceAll("^\\d+[-、]?\\d?", "");
        System.out.println("test06,s:" + s);
        System.out.println("test06,s2:" + s2);
    }
    private static void test07(){
        String s = "好开心，好幸福，好吃饭，好，小爱好开心".replaceAll("好(开心|幸福)", "好[hao3]开心");
        String s2 = "好开心，好幸福，好吃饭，好，小爱好开心".replaceAll("好[开心]", "好[hao3]开心");///只能一个一个字符的匹配
        String s3 = "小爱，小爱，小i".replaceAll("小[愛|爱|i|I]", "小爱");
        System.out.println("test06,s:" + s);
        System.out.println("test06,s2:" + s2);
        System.out.println("test06,s3:" + s3);
    }
}
