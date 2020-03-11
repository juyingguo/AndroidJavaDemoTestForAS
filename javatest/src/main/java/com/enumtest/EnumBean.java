package com.enumtest;

/**
 * Date:2019/12/24,11:41
 * author:jy
 */
public class EnumBean {
    /**
     * AlarmClock Type
     */
    public enum AlarmClockType{
        TEST_DEF(2),
        /** default message reminder */
        DEFALT_MESSAGE_REMINDER(0),
        /** routine reminder */
        ROUTINE_REMINDER(1);

        private final int value;
        AlarmClockType(int value){
            this.value = value;
        }

        public int getValue() {
            return value;
        }
        public AlarmClockType valueOf(int value){
            switch (value){
                case 0:
                    return DEFALT_MESSAGE_REMINDER;
                case 1:
                    return ROUTINE_REMINDER;
                default:
                    return null;
            }
        }
    }
}
