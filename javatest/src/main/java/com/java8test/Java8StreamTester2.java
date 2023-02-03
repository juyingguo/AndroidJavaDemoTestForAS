package com.java8test;

import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Java8StreamTester2 {

   @Test
   public void streamGenerateTest(){
      List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
      List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
      System.out.println(filtered);
   }
   @Test
   public void forEachTest(){
//       Stream 提供了新的方法 'forEach' 来迭代流中的每个数据。以下代码片段使用 forEach 输出了10个随机数：
       Random random = new Random();
       random.ints().limit(3).forEach(System.out::println);
   }
   @Test
   public void sortedTest(){
//       sorted 方法用于对流进行排序。以下代码片段使用 sorted 方法对输出的 10 个随机数进行排序：
        Random random = new Random();
        random.ints().limit(5).sorted().forEach(System.out::println);
   }
   @Test
   public void mapTest(){
//       map 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数：
       List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
// 获取对应的平方数
       List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
       System.out.println(squaresList);
   }
   @Test
   public void filterTest(){
//      filter 方法用于通过设置的条件过滤出元素。以下代码片段使用 filter 方法过滤出空字符串：
//
        List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        // 获取空字符串的数量
        long count = strings.stream().filter(String::isEmpty).count();
         System.out.println("filterTest,count:" + count);
        long count2 = strings.stream().filter(String::isEmpty).mapToLong(e -> 1L).sum();
        System.out.println("filterTest,count2:" + count2);
   }
   @Test
   public void testCollectors(){
//       Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：

       List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
       List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

       System.out.println("筛选列表: " + filtered);
       String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
       System.out.println("合并字符串: " + mergedString);
   }
   @Test
   public void testIntSummaryStatistics(){
//       另外，一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，
//       它们可以用来产生类似如下的统计结果。
       List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

       IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

       System.out.println("列表中最大的数 : " + stats.getMax());
       System.out.println("列表中最小的数 : " + stats.getMin());
       System.out.println("所有数之和 : " + stats.getSum());
       System.out.println("平均数 : " + stats.getAverage());
   }
}