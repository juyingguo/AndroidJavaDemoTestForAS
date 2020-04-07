package com.detect;

import com.simpledatatype.ByteUtil;

/**
 * Date:2020/4/3,18:27
 * author:jy
 */
public class BodyTemperatureDetect {
    private static long body_byte_one = 0;
    private static long body_byte_two = 0;
    private static int real_environmental_temperature = 0;
    private static float curr_body_temperature;

    /**
     * 体温检测，uart串口返回数据处理。
     * @param buffer byte[]
     * @param size int size
     */
    private static void dealBodyTemperatureDetect(int[] buffer, int size) {
        System.out.println("dealBodyTemperatureDetect,size:" + size);
        if (buffer != null){
            if (buffer.length >=7){
                System.out.println("dealBodyTemperatureDetect,buffer[0]:" + buffer[0]);
                body_byte_one = buffer[2];
                body_byte_two = buffer[3];

                curr_body_temperature = ((body_byte_one<<8)  + body_byte_two) /10.0f;

                real_environmental_temperature = buffer[6] - 40;

                System.out.println("dealBodyTemperatureDetect,curr_body_temperature:" + curr_body_temperature);
                System.out.println("dealBodyTemperatureDetect,real_environmental_temperature:" + real_environmental_temperature);
            }
        }
    }

    public static void main(String[] args) {
        //0XF1 0XF2 0X01 0X69 0XF3 0XF4 0X44
        int[] bt = new int[]{0xF1,0XF2,0X01,0X69, 0XF3, 0XF4,0X44,};
        System.out.println(" 0xF1:" +  0xF1);//241
        System.out.println("(byte)0xF1:" +  (byte)0xF1);//  -15
        System.out.println("(byte)0xF2:" +  (byte)0xF2);//  -15
        System.out.println("(byte)0xF3:" +  (byte)0xF3);//  -15
        System.out.println("(1<<2):" + (1<<2));//4
        System.out.println("0X01:" + 0X01);//1
        System.out.println("0X01 <<8:" + (0X01 <<8));//256
        int a = 0X01 <<8;
        System.out.println("(a + 0X69):" + (a + 0X69));// 256 + 105 = 361
        System.out.println("(a + 0X69):" + (a + 0X69));// 256 + 105 = 361

        System.out.println("************************************");
        byte[] byteTest = new byte[]{1};
        for (int i = 0;i<byteTest.length;i++) {
            System.out.println("(byteTest[" + i + "]:" + (byteTest[i]));
        }
        System.out.println("ByteUtil.byteArrToHex(byteTest):" + ByteUtil.byteArrToHex(byteTest));
        System.out.println("ByteUtil.bytesToHexString(byteTest):" + ByteUtil.bytesToHexString(byteTest));
        System.out.println("******************test02******************");

        System.out.println("Byte.parseByte(\"127\"):" + Byte.parseByte("127"));

        System.out.println("************************************");

        dealBodyTemperatureDetect(bt,7);
    }
}
