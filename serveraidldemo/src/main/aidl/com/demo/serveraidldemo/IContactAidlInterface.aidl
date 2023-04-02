// IContactAidlInterface.aidl
package com.demo.serveraidldemo;
import com.demo.serveraidldemo.IContactAidlInterfaceCallback;
// Declare any non-default types here with import statements

interface IContactAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     oneway void getContactByName(String contactName,IContactAidlInterfaceCallback callback);
}
