package com.threadtest.synchronizedtest;
/**
 * @date 2019/8/2
 * @author jy
 * @description
 * @Version
 */
public class Demo00{

    /*
    ---------------------
    作者：真快啊夏天
    来源：CSDN
    原文：https://blog.csdn.net/sinat_32588261/article/details/72880159
    版权声明：本文为博主原创文章，转载请附上博文链接！
    */


    public static final Object signal = new Object(); // 线程间通信变量
	//将account改为Demo00.signal也能实现线程同步
	public static void main(String args[]){
		Account account = new Account("zhang san", 10000.0f);
		AccountOperator accountOperator = new AccountOperator(account);
 
		final int THREAD_NUM = 5;
		Thread threads[] = new Thread[THREAD_NUM];
		for (int i = 0; i < THREAD_NUM; i ++) {
		   threads[i] = new Thread(accountOperator, "Thread" + i);
		   threads[i].start();
		}
	}
}
/**
 * 银行账户类
 */
class Account {
	String name;
	float amount;

	public Account(String name, float amount) {
		this.name = name;
		this.amount = amount;
	}
	//存钱
	public  void deposit(float amt) {
		amount += amt;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//取钱
	public  void withdraw(float amt) {
		amount -= amt;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public float getBalance() {
		return amount;
	}
}

/**
 * 账户操作类
 */
class AccountOperator implements Runnable{
	private Account account;
	public AccountOperator(Account account) {
		this.account = account;
	}

	public void run() {
	    //对象锁1
		/*synchronized (account) {
			account.deposit(500);
			account.withdraw(500);
			System.out.println(Thread.currentThread().getName() + ":" + account.getBalance());
		}*/
        //对象锁2
		/*synchronized (Demo00.signal) {
			account.deposit(500);
			account.withdraw(500);
			System.out.println(Thread.currentThread().getName() + ":" + account.getBalance());
		}*/
		//类锁3
		synchronized (AccountOperator.class) {
			account.deposit(500);
			account.withdraw(500);
			System.out.println(Thread.currentThread().getName() + ":" + account.getBalance());
		}
	}
}