package com.designmodel.observer.one;
/**
 * ConcreteSubject（具体目标）
      将有关状态存入各ConcreteObserver对象。
      当它的状态发生改变时,向它的各个观察者发出通知。
 * @author username
 *
 */
public class HuangPuCitizen extends Citizen {

    public HuangPuCitizen(Policeman pol) {
        setPolicemen();
        register(pol);
    }
    
    public void sendMessage(String help) {
        setHelp(help);
        for(int i = 0; i < pols.size(); i++) {
            Policeman pol = pols.get(i);
            //通知警察行动
            pol.action(this);
        }
    }
}
