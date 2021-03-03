// IContactAidlInterfaceCallback.aidl
package com.demo.serveraidldemo;

// Declare any non-default types here with import statements

interface IContactAidlInterfaceCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     * 如果存在就contactName联系人实际姓名；否则为null
     */
    void handleResult(String contactName);
}
