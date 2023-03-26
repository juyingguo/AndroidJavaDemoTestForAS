package com.sp.spmultipleapp.datastruct;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;

import com.sp.spmultipleapp.R;

public class SparseArrayActivityTest extends AppCompatActivity {
    SparseArray mSparseArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sparse_array_test);
    }
    public void add(){
        mSparseArray = new SparseArray<String>();

        mSparseArray.put(1,"Red");
        mSparseArray.put(2,"Green");
        mSparseArray.put(3,"Blue");
        mSparseArray.put(4,"Yellow");
        mSparseArray.put(4,"Gray");

        System.out.println("peter size is->" + mSparseArray.size() + " mSparseArray:" + mSparseArray);
    }

    public void onAddClick(View view) {
        add();
    }

    public void onDeleteClick(View view) {
        //删除操作，当删除的键不存在时，不执行删除操作，不会报错
        if (mSparseArray.size() == 0) return;
        mSparseArray.delete(0);// 根据键删除
        mSparseArray.delete(1);// 根据键删除
        mSparseArray.removeAt(2);// 删除索引为2的元素
        System.out.println("peter size is->" + mSparseArray.size() + " mSparseArray:" + mSparseArray);
    }

    public void onModifyClick(View view) {
        mSparseArray.setValueAt(0,"modifyColor");
        System.out.println("peter size is->" + mSparseArray.size() + " mSparseArray:" + mSparseArray);
    }
}
