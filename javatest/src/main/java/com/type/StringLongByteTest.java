package com.type;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2018/6/5.
 */

public class StringLongByteTest {

    public static void main(String[] args) {

        intByte();

        stringByte();

        stringByteUseSystemApi();

        chineseStringToByteUseSystemApi();
    }

    private static void intByte() {
        System.out.println("/*********intByte()*********/");
        int fileLength = 10;
        byte fileLengthToByte = new Integer(fileLength).byteValue();
        System.out.println("fileLength:" + fileLength);
        System.out.println("fileLengthToByte:" + fileLengthToByte);

        byte[] lengthByteArray =  ByteUtil.intToByteArray(fileLength);
        byte[] lengthByteArray2 =  ByteUtil.getBytes(fileLength);

        for (int i= 0;i< lengthByteArray.length; i ++){
            System.out.println("///////////");
            System.out.println("lengthByteArray[" + i + "]:" + lengthByteArray[i]);
            System.out.println("lengthByteArray2[" + i + "]:" + lengthByteArray2[i]);
        }

        System.out.println("lengthByteArray.length:" + lengthByteArray.length);

        int lengthByteArrayToInt = ByteUtil.byteArrayToInt(lengthByteArray);
        System.out.println("lengthByteArrayToInt:" + lengthByteArrayToInt);

    }

    private static void stringByte() {
        byte[] nameLength = new byte[20];
        System.out.println("nameLength.length:" + nameLength.length);
        System.out.println("/******************/");
        final String FILENAME = "record_wav.wav";
        byte[] fileNameToBytes = FILENAME.getBytes(Charset.forName("UTF-8"));
        System.out.println("fileNameToBytes.length:" + fileNameToBytes.length);

        String bytesToString = new String(fileNameToBytes,Charset.forName("UTF-8"));
        System.out.println("bytesToString:" + bytesToString);
        final File file = new File("D:\\juying\\work-test\\ibotnlauncher\\voice-related",bytesToString);
        try {
            new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("bytesToString.length:" + bytesToString.length());

        System.out.println("/******************/");

        for (int i= 0;i< fileNameToBytes.length; i ++){
            nameLength[i] = fileNameToBytes[i];
            System.out.println("fileNameToBytes[" + i + "]:" + fileNameToBytes[i]);
        }
        for (int i= 0;i< nameLength.length; i ++){
            System.out.println("nameLength[" + i + "]:" + nameLength[i]);
        }

        System.out.println("nameLength.length:" + nameLength.length);
        String nameLengthToString = new String(nameLength,Charset.forName("UTF-8"));
        System.out.println("nameLengthToString:" + nameLengthToString);
    }

    private static void stringByteUseSystemApi() {
        System.out.println("/******stringByteUseSystemApi()************/");
        byte[] nameLength = new byte[20];
        System.out.println("nameLength.length:" + nameLength.length);
        final String FILENAME = "record_wav.wav";
        byte[] fileNameToBytes = FILENAME.getBytes(Charset.forName("UTF-8"));
        System.out.println("fileNameToBytes.length:" + fileNameToBytes.length);

        String bytesToString = new String(fileNameToBytes,Charset.forName("UTF-8"));
        System.out.println("bytesToString:" + bytesToString);
        System.out.println("bytesToString.length:" + bytesToString.length());

        System.out.println("/******************/");

        System.arraycopy(fileNameToBytes,0,nameLength,0,fileNameToBytes.length);
        /*for (int i= 0;i< fileNameToBytes.length; i ++){
            nameLength[i] = fileNameToBytes[i];
            System.out.println("fileNameToBytes[" + i + "]:" + fileNameToBytes[i]);
        }*/
        for (int i= 0;i< nameLength.length; i ++){
            System.out.println("nameLength[" + i + "]:" + nameLength[i]);
        }

        System.out.println("nameLength.length:" + nameLength.length);
        String nameLengthToString = new String(nameLength,Charset.forName("UTF-8"));
        System.out.println("nameLengthToString:" + nameLengthToString);

        System.out.println("/******stringByteUseSystemApi()>>>end ************/");
    }

    private static void chineseStringToByteUseSystemApi() {
        System.out.println("/******chineseStringToByteUseSystemApi()************/");
        byte[] nameLength = new byte[150];
        System.out.println("nameLength.length:" + nameLength.length);
//        final String FILENAME = "2018年6月深圳下了好几天大雨(可以在家购物、炒股、读书、健身；如果你有可以的话).wav";
        final String FILENAME = "2018年6月深圳下了好几天大雨.wav";
        byte[] fileNameToBytes = FILENAME.getBytes(Charset.forName("UTF-8"));
        System.out.println("fileNameToBytes.length:" + fileNameToBytes.length);

        String bytesToString = new String(fileNameToBytes,Charset.forName("UTF-8"));
        System.out.println("bytesToString:" + bytesToString);
        System.out.println("bytesToString.length:" + bytesToString.length());

        System.out.println("/******************/");

        System.arraycopy(fileNameToBytes,0,nameLength,0,fileNameToBytes.length);
        /*for (int i= 0;i< fileNameToBytes.length; i ++){
            nameLength[i] = fileNameToBytes[i];
            System.out.println("fileNameToBytes[" + i + "]:" + fileNameToBytes[i]);
        }*/
        for (int i= 0;i< nameLength.length; i ++){
            System.out.println("nameLength[" + i + "]:" + nameLength[i]);
        }

        System.out.println("nameLength.length:" + nameLength.length);
        String nameLengthToString = new String(nameLength,Charset.forName("UTF-8"));
        System.out.println("nameLengthToString:" + nameLengthToString);

        System.out.println("/******chineseStringToByteUseSystemApi()>>>end ************/");
    }
}
