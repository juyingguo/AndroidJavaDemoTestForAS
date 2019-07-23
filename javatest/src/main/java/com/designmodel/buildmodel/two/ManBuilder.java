package com.designmodel.buildmodel.two;
public class ManBuilder implements PersonBuilder {

    Person person;
    
    public ManBuilder() {
        person = new Man();
    }
    
    /**
     * 注意直接返回该当前对象，便于链式调用
     */
    public ManBuilder buildBody() {
        person.setBody("建造男人的身体");
        return this;
    }

    public ManBuilder buildFoot() {
        person.setFoot("建造男人的脚");
        return this;
    }

    public ManBuilder buildHead() {
        person.setHead("建造男人的头");
        return this;
    }

    public Person buildPerson() {
        return person;
    }
}
