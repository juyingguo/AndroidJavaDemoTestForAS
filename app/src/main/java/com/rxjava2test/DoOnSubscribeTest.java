package com.rxjava2test;

import android.util.Log;

import java.util.concurrent.Future;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import util.LogUtil;

public class DoOnSubscribeTest {
    private static String TAG = DoOnSubscribeTest.class.getSimpleName();
    public static void  doSomeWork(){

//        test();
    }
//    public static void  test(){
//
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                LogUtil.d(TAG,"subscribe>>b>>thread:" + Thread.currentThread().getName());
//                emitter.onNext(2);
//                emitter.onComplete();
//            }
//        })
////                .subscribeOn(Schedulers.computation())
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        LogUtil.d(TAG,"doOnSubscribe>>a1>>thread:" + Thread.currentThread().getName());
//                    }
//                })
////                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.newThread())
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        LogUtil.d(TAG,"doOnSubscribe>>a2>>thread:" + Thread.currentThread().getName());
//                    }
//                })
////                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        LogUtil.d(TAG,"accept>>b>>integer:" + integer);
//                        LogUtil.d(TAG,"accept>>b>>thread:" + Thread.currentThread().getName());
//                    }
//                });
//    }
//    public static void  test2(){
//
//        Flowable.fromFuture(new Future<Integer>() {
//        })
//                .subscribeOn(Schedulers.computation())
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        LogUtil.d(TAG,"doOnSubscribe>>a1>>thread:" + Thread.currentThread().getName());
//                    }
//                })
////                .subscribeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        LogUtil.d(TAG,"doOnSubscribe>>a2>>thread:" + Thread.currentThread().getName());
//                    }
//                })
////                .subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        LogUtil.d(TAG,"accept>>b>>integer:" + integer);
//                        LogUtil.d(TAG,"accept>>b>>thread:" + Thread.currentThread().getName());
//                    }
//                });
//    }


}
