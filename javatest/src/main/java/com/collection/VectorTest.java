package com.collection;

import java.util.Vector;

public class VectorTest {

    static class Action{
        int time ;

        public Action(int i) {
            this.time = i;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "Action{" +
                    "time=" + time +
                    '}';
        }
    }
    public static void main(String[] args) {
//        test01();
        test02();
        test03();
    }

    private static void test01() {

        Vector<Integer> vectorOne = new Vector<Integer>();
        Vector<Integer> vectorTwo = new Vector<Integer>();
        for (int i = 10;i<=15 ; i++) {

            vectorOne.add(i);
        }
        for (Integer i:vectorOne){
            vectorTwo.add(i + 1000);
            System.out.println("i:" + i);
        }

        /*Integer aa = 0;
        for (Integer i:vectorTwo){
            i = i + 1000;
        }*/
        vectorOne.addAll(vectorTwo);
        for (Integer i:vectorOne){
            System.out.println("合并后的vectorOne :" + i);
        }


    }

    /**
     * 这种方式，vectorOne也被小改了
     */
    private static void test02() {
        //请问集合一种的元素内容是多少。
        Vector<Action> vectorOne = new Vector<Action>();
        Vector<Action> vectorTwo = new Vector<Action>();
        Vector<Action> vectorThird = new Vector<Action>();
        for (int i = 10;i<=15 ; i++) {
            vectorOne.add(new Action(i) );
        }
        for (Action bean:vectorOne){
            vectorTwo.add(bean);
            System.out.println("bean:" + bean);
        }

        /*Integer aa = 0;
        for (Integer i:vectorTwo){
            i = i + 1000;
        }*/
        for (Action bean: vectorTwo) {
            bean.setTime(bean.getTime() + 1000);

        }
//        vectorOne.addAll(vectorTwo);
        for (Action bean:vectorOne){
            System.out.println("合并后的vectorOne :" + bean);
        }

        /**
         * bean:Action{time=10}
         bean:Action{time=11}
         bean:Action{time=12}
         bean:Action{time=13}
         bean:Action{time=14}
         bean:Action{time=15}
         合并后的vectorOne :Action{time=1010}
         合并后的vectorOne :Action{time=1011}
         合并后的vectorOne :Action{time=1012}
         合并后的vectorOne :Action{time=1013}
         合并后的vectorOne :Action{time=1014}
         合并后的vectorOne :Action{time=1015}
         */

    }
    private static void test03() {
        //请问集合一种的元素内容是多少。
        Vector<Action> vectorOne = new Vector<Action>();
        Vector<Action> vectorTwo = new Vector<Action>();
        for (int i = 10;i<=15 ; i++) {
            vectorOne.add(new Action(i) );
        }

        for (Action bean:vectorOne){
            Action actionBean = new Action(bean.getTime() + 80000);
            vectorTwo.add(actionBean);
            System.out.println("bean:" + bean);
        }

        /*Integer aa = 0;
        for (Integer i:vectorTwo){
            i = i + 1000;
        }*/
       /* for (Action bean: vectorTwo) {
            bean.setTime(bean.getTime() + 1000);

        }*/
        vectorOne.addAll(vectorTwo);
        for (Action bean:vectorOne){
            System.out.println("合并后的vectorOne :" + bean);
        }


    }
}
