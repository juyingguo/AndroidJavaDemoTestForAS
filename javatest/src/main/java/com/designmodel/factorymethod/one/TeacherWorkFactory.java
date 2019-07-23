package com.designmodel.factorymethod.one;
public class TeacherWorkFactory implements IWorkFactory {

    public Work getWork() {
        return new TeacherWork();
    }

}
