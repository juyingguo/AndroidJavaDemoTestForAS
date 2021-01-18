LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

#LOCAL_MODULE是将要生成的.so的名字
LOCAL_MODULE := MyJni
#LOCAL_SRC_FILES是需要将哪个.c/.cpp文件生成.so文件
LOCAL_SRC_FILES := myjni_test.cpp

#LOCAL_LDLIBS这里是给.c/.cpp文件添加了一个log库，可以打印log
#LOCAL_LDLIBS +=-L$(SYSROOT)/usr/lib -lm -llog
include $(BUILD_SHARED_LIBRARY)