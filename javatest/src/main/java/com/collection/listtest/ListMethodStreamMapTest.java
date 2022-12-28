package com.collection.listtest;

import com.bean.Strings;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ListMethodStreamMapTest {
    public static void main(String[] args) {
        test();
    }

    /**
     * 将集合元素，以逗号拼接为字符串
     */
    private static void test() {
        List<Data> list = new ArrayList<>();
        list.add(new Data(Long.valueOf(1),Long.valueOf(5)));
        list.add(new Data(Long.valueOf(1),Long.valueOf(8)));
        list.add(new Data(Long.valueOf(1),Long.valueOf(10)));

        String collect = list.stream().map(data -> String.valueOf(data.getDeptId())).collect(Collectors.joining(Strings.COMMA));
        List<Long> collect2 = list.stream().map(Data::getDeptId).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(collect2);
    }
    @Test
    public void test2(){
        List<Data> list = new ArrayList<>();
        list.add(new Data(Long.valueOf(1),Long.valueOf(5)));
        list.add(new Data(Long.valueOf(1),Long.valueOf(8)));
        list.add(new Data(Long.valueOf(2),Long.valueOf(10)));
        list.add(new Data(Long.valueOf(2),Long.valueOf(12)));

        String collect = list.stream().map(data -> String.valueOf(data.getDeptId())).collect(Collectors.joining(Strings.COMMA));
        List<Long> collect2 = list.stream().map(Data::getDeptId).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(collect2);
    }

    @Test
    public void listToMapByObjectValue(){
        List<Data> list = new ArrayList<>();
        list.add(new Data(Long.valueOf(1),Long.valueOf(5)));
        list.add(new Data(Long.valueOf(2),Long.valueOf(10)));
        list.add(new Data(Long.valueOf(3),Long.valueOf(12)));
        // value 为对象 data -> data jdk1.8返回当前对象
        Map<Long, Data> map = list.stream().collect(Collectors.toMap(Data::getUserId, data -> data));
        // 遍历打印结果
        map.forEach((key, value) -> {
            System.out.println("key: " + key + "    value: " + value);
        });
    }
    @Test
    public void listToMapByPropertyValue(){
        List<Data> list = new ArrayList<>();
        list.add(new Data(Long.valueOf(1),Long.valueOf(5)));
        list.add(new Data(Long.valueOf(2),Long.valueOf(10)));
        list.add(new Data(Long.valueOf(3),Long.valueOf(12)));
        // value 为对象 data -> data jdk1.8返回当前对象
        Map<Long, Long> map = list.stream().collect(Collectors.toMap(Data::getUserId, Data::getDeptId));
        // 遍历打印结果
        map.forEach((key, value) -> {
            System.out.println("key: " + key + "    value: " + value);
        });
    }
    static class Data{
        private Long userId;
        private Long deptId;

        public Data(Long userId, Long deptId) {
            this.userId = userId;
            this.deptId = deptId;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getDeptId() {
            return deptId;
        }

        public void setDeptId(Long deptId) {
            this.deptId = deptId;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "userId=" + userId +
                    ", deptId=" + deptId +
                    '}';
        }
    }
}
