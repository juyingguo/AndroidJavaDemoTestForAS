package com.niotest;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadFileTest01 {
    public static void main(String[] args) {

        write01();
        read01();


    }

    private static void read01() {

        try {
            RandomAccessFile aFile = new RandomAccessFile("D:\\juying\\test-file\\niotest\\nio.txt", "rw");
            FileChannel inChannel = aFile.getChannel();

//create buffer with capacity of 48 bytes
            ByteBuffer buf = ByteBuffer.allocate(48);

            int bytesRead = inChannel.read(buf); //read into buffer.
            while (bytesRead != -1) {

                buf.flip();  //make buffer ready for read

                while(buf.hasRemaining()){
                    System.out.print((char) buf.get()); // read 1 byte at a time
                }

                buf.clear(); //make buffer ready for writing
                bytesRead = inChannel.read(buf);
            }
            aFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void write01() {

        try {
            RandomAccessFile aFile = new RandomAccessFile("D:\\juying\\test-file\\niotest\\nio.txt", "rw");
            FileChannel inChannel = aFile.getChannel();

            //create buffer with capacity of 48 bytes
            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.put("write".getBytes());

            int bytesRead = inChannel.write(buf); //read into buffer.
            inChannel.close();
            aFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
