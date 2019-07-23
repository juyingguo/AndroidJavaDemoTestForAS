package com.designmodel.observer.one;
public class Test{

    public static void main(String[] args) {
        Policeman thPol = new TianHePoliceman();
        Policeman hpPol = new HuangPuPoliceman();
        
        Citizen citizen = new HuangPuCitizen(hpPol);
        citizen.sendMessage("unnormal");
        citizen.sendMessage("normal");
        
        System.out.println("===========");
        
        citizen = new TianHeCitizen(thPol);
        citizen.register(hpPol);//同时注册HuangPuPoliceman
        citizen.sendMessage("normal");
        citizen.sendMessage("unnormal");
    }
}
