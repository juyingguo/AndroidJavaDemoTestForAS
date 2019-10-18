package com.datetest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/6/21.
 */

public class DateFormatTest {
    static String TAG = "DateFormatTest";
    public static void main(String[] args) {
        formatTime(1570840772487L);
    }

    private static void formatTime(Long time) {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date(time);

        String format1 = format.format(date);

        System.out.println(format1);

    }


}
