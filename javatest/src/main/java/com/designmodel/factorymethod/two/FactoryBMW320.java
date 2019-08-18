package com.designmodel.factorymethod.two;

 
public class FactoryBMW320 implements FactoryBMW{
 
	@Override
	public BMW320 createBMW() {
 
		return new BMW320();
	}
 
}
