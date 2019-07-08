package com.sockettest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadInterruptTest01 {
    private final static  String TAG = ThreadInterruptTest01.class.getSimpleName();
    public static void main(String[] args) {
        test1();


    }

    private static void test1() {

        oneThread.start();
//        twoThread.start();


        try {
            Thread.sleep(5000);
//            oneThread.isRun = false;
            oneThread.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static OneThread oneThread = new OneThread();
    static class  OneThread extends Thread{
        boolean isRun = true;
        ServerSocket serverSocket = null;
        void close(){
            isRun = false;
            if (serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        @Override
        public void run() {
            super.run();
            try {
                InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
                serverSocket = new ServerSocket(5566,50, inetAddress);
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (isRun) {
                try {
                    System.out.println("OneThread>>>>socket>>waiting client connect ....");
                    Socket socket = serverSocket.accept();
                    System.out.println("OneThread>>>>new client connect >>socket:" + socket );
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    static  TwoThread twoThread = new TwoThread();
    static class  TwoThread extends Thread{
        boolean isRun = true;
        @Override
        public void run() {
            super.run();
            while (isRun) {
                Socket socket = new Socket();
                try {
                    socket.bind(null);
                    socket.connect((new InetSocketAddress("127.0.0.1", 5566)), 5000);
                    System.out.println("TwoThread>>>socket:" + socket);

                    Thread.sleep(5000);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
