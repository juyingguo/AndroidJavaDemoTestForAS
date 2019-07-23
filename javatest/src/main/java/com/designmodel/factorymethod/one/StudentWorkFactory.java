package com.designmodel.factorymethod.one;
public class StudentWorkFactory implements IWorkFactory {

    public Work getWork() {
        return new StudentWork();
    }

}
