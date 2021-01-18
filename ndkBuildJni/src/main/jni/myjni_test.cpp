//
// Created by Administrator on 2021/1/18.
//
#include <com_example_ndkbuildjni_MyJni.h>
#include <android/log.h>

#define LOG_TAG "myjni_test"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

JNIEXPORT jstring JNICALL Java_com_example_ndkbuildjni_MyJni_get
  (JNIEnv *env, jclass jz){

        //LOGI("hello，这里是native层");
        return env->NewStringUTF("hello jni , you are so easy!");

 }