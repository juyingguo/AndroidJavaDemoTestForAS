package com.designmodel.buildmodel.two;
public interface PersonBuilder {

	PersonBuilder buildHead();
    
	PersonBuilder buildBody();
    
	PersonBuilder buildFoot();

    Person buildPerson();
}
