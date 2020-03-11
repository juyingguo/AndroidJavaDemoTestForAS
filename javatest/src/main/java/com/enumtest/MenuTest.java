package com.enumtest;

/**
 * Date:2019/12/24,11:32
 * author:jy
 */
public class MenuTest {

    public static void main(String[] args) {
        System.out.println(EnumBean.AlarmClockType.DEFALT_MESSAGE_REMINDER.name());
        System.out.println(EnumBean.AlarmClockType.DEFALT_MESSAGE_REMINDER);
        System.out.println(EnumBean.AlarmClockType.DEFALT_MESSAGE_REMINDER.getValue());
        System.out.println(EnumBean.AlarmClockType.DEFALT_MESSAGE_REMINDER.ordinal());
        System.out.println(EnumBean.AlarmClockType.ROUTINE_REMINDER.ordinal());
        System.out.println(EnumBean.AlarmClockType.ROUTINE_REMINDER.getValue());
    }
}
