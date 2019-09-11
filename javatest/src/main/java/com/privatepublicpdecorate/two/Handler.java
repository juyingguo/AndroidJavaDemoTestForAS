package com.privatepublicpdecorate.two;

/**
 * Date:2019/9/11,15:27
 * author:jy
 *
 *
 * 默认的(default/friendly)
 * 		如是该类型的变量。
 * 		不同的包中有继承关系，不可见，是否代表不能使用？
 * 		不能直接访问，但通过反射可以使用使用。
 */
public class Handler {


    /**
     * Callback interface you can use when instantiating a Handler to avoid
     * having to implement your own subclass of Handler.
     */
    public interface Callback {

        public boolean handleMessage();
    }

    final Callback mCallback = null;
/*
    public Handler(Callback callback){
        mCallback = callback;
    }*/
}
