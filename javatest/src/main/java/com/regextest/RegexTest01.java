package com.regextest;

import com.utils.RegExpUtils;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest01 {
    public static void main(String[] args) {
//        test01();
//        test02();
//        test03();
//        test04();
//        test05();
//        test06();
//        test07();
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

    /**
     * 匹配中文和字母。
     */
    @Test
    public void testPatternWithFindAndGroup() {

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
    /**
     * 匹配中文和字母。
     */
    @Test
    public void testPatternWithStartAndEnd() {

        String fileName = "唱歌一首?*小跳蛙-a-[0]*哈哈";

        Matcher matcher = Pattern.compile("[\\u4e00-\\u9fa5a-zA-Z]+").matcher(fileName);
        StringBuilder nameBuilder = new StringBuilder();
        while (matcher.find()){
            String group = fileName.substring(matcher.start(),matcher.end());
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

    /**
     * 过滤 1-1,1、1等前缀。通过正则表达式来替换字符
     */
    @Test
    public void test06(){
        String s = "11-01小跳蛙".toLowerCase().replaceAll("^\\d+[-、]?\\d?", "");
        String s2 = "小苹果11".toLowerCase().replaceAll("$\\d+[-、]?\\d?", "");
        String s3 = "1]1小苹果".toLowerCase().replaceAll("^\\d+[-、\\]]?\\d?", "");
        System.out.println("test06,s:" + s);
        System.out.println("test06,s2:" + s2);
        System.out.println("test06,s3:" + s3);
    }
    @Test
    public void testPatternForMatches(){
        String content = "I am noob " +
                "from runoob.com.";

        String pattern = ".*runoob.*";

        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);
    }
    @Test
    public void testPatternForCompileAndMatches(){
        String content = "I am noob " +
                "from runoob.com.";

        String pattern = ".*runoob.*";

        boolean isMatch = Pattern.compile(pattern).matcher(content).matches();
        System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);
    }
    @Test
    public void test07(){
        String s = "好开心，好幸福，好吃饭，好，小爱好开心".replaceAll("好(开心|幸福)", "好[hao3]开心");
        String s2 = "好开心，好幸福，好吃饭，好，小爱好开心".replaceAll("好[开心]", "好[hao3]开心");///只能一个一个字符的匹配
        String s3 = "小爱，小爱，小i".replaceAll("小[愛|爱|i|I]", "小爱");
        System.out.println("test06,s:" + s);
        System.out.println("test06,s2:" + s2);
        System.out.println("test06,s3:" + s3);
    }
}
