package com.designmodel.buildmodel.two;
public class PersonDirector {

    public Person constructPerson(PersonBuilder pb) {
        
        return pb.buildHead()
        		.buildBody()
        		.buildFoot()
        		.buildPerson();
    }
}
