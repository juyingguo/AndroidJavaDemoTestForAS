// IContactAidlInterface.aidl
package com.demo.serveraidldemo;
import com.demo.serveraidldemo.IContactAidlInterfaceCallback;
// Declare any non-default types here with import statements

interface IContactAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     * 获取联系人方法，该方法是异步的。参数使用回调接口
     */
     oneway void getContactByName(String contactName,IContactAidlInterfaceCallback callback);
}
