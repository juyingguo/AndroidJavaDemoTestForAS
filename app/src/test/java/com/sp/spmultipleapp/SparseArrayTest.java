package com.sp.spmultipleapp;

import android.util.SparseArray;

import org.junit.Test;

public class SparseArrayTest {
    @Test
    @SuppressWarnings("unchecked")
    public void test(){
        SparseArray mSparseArray = new SparseArray<String>();

        mSparseArray.append(1,"Red");
//        mSparseArray.put(2,"Green");
//        mSparseArray.put(3,"Blue");
//        mSparseArray.put(4,"Yellow");
//        mSparseArray.put(4,"Gray");

        System.out.println("peter size is->" + mSparseArray.size() + " mSparseArray:" + mSparseArray);
    }
}
