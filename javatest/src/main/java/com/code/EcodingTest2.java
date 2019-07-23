package com.code;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;


public class EcodingTest2 {

	/**
	 * file.encoding的值保存的是每个程序的main入口的那个java文件的保存编码，是.java文件的编码。
	 * Charset.defaultCharset()
	 * 1、如果使用了eclipse，由java文件的编码决定
	 * 2、如果没有使用eclipse，则有本地电脑语言环境决定，中国的都是默认GBK编码
	 * System.getProperty("user.language"));
	 * 用户使用的语言,而非编码方式
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//获取系统默认编码
		System.out.println("系统默认编码：    "+System.getProperty("file.encoding"));//查询结果GBK
		//系统默认字符编码
		System.out.println("系统默认字符编码:"+Charset.defaultCharset()); //查询结果GBK
		//操作系统用户使用的语言
		System.out.println("系统默认语言:"+ System.getProperty("user.language")); //查询结果zh
		//定义字符串包含数字和中文
       String t = "1a我";
       //通过getBytes方法获取默认的编码
       System.out.println("默认编码格式:");
       byte[] b = t.getBytes();//ASCII,GBK,UTF-8对数字和英文字母的编码相同,对汉字的编码不同,unicode的编码跟前面三项都不同
       //打印默认编码
       for (byte c : b) {
    	   System.out.print(c+",\t");
       }
       System.out.println();
       //打印GBK编码
       System.out.println("GBK编码格式:");
	}
}