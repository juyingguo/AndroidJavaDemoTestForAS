package com.designmodel.observer.one;
/**
 * Observer（观察者）
      为那些在目标发生改变时需获得通知的对象定义一个更新接口。
 * @author username
 *
 */
public interface Policeman {

    void action(Citizen ci);
}
