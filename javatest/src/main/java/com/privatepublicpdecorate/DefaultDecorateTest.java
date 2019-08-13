package com.privatepublicpdecorate;


import com.privatepublicpdecorate.one.AClass;

/**
 * Date:2019/8/7,10:58
 * author:jy
 * https://blog.csdn.net/dz77dz/article/details/81635250
 */
public class DefaultDecorateTest {

}


class B extends AClass {
    void TestB(){
//        testA();//默认的(default/friendly)
        testA02();
    }
}