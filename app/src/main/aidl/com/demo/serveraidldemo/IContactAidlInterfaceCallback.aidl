// IContactAidlInterfaceCallback.aidl
package com.demo.serveraidldemo;

// Declare any non-default types here with import statements

interface IContactAidlInterfaceCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.IContactAidlInterfaceCallback
     * has contact contactName != null,or contactName = null;
     */
    void handleResult(String contactName);
}
