package com.designmodel.proxymodel.staticproxy.one;

/**
 * 被代理类
 * @author username
 *
 */
class people implements buy_car {
 
   private int cash;
   private String vip;
   private String username;
  
 @Override
 public void buy_mycar() {
  // TODO Auto-generated method stub
  System.out.print(username+"是vip 客户，可以直接购买新车！");
 }
 
public int getCash(){
      return cash;
   }
   public void setCash(int cash){
       this.cash = cash;
    }
   public String getUsername(){
       return username;
    }
   public void setUsername(String username){
       this.username = username;
    }
   public String getVip(){
       return vip;
    }
   public void setVip(String vip){
        this.vip = vip;
   }
}
