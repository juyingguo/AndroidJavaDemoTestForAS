package com.javareflect.two;

 class Student {
	private String sid;
 
	private String sname;
 
	public Integer age;

	static{
		System.out.println("加载进jvm中！");
	}
 
	public Student() {
		super();
		System.out.println("调用无参构造方法【public 类型】创建了一个学生对象");
	}
//	private Student() {
//		super();
//		System.out.println("调用无参构造方法【private 类型】创建了一个学生对象");
//		/**
//		 * Exception in thread "main" java.lang.IllegalAccessException: Class com.javareflect.two.Demo1 can not access a member of class com.javareflect.two.Student with modifiers "private"
//		 * 	at sun.reflect.Reflection.ensureMemberAccess(Reflection.java:102)
//		 * 	at java.lang.Class.newInstance(Class.java:436)
//		 * 	at com.javareflect.two.Demo1.main(Demo1.java:30)
//		 */
//	}
 
	public Student(String sid) {
		super();
		this.sid = sid;
		System.out.println("调用带一个参数的构造方法创建了一个学生对象。sid：" + sid);
	}
 
	public Student(String sid, String sname) {
		super();
		this.sid = sid;
		this.sname = sname;
		System.out.println("调用带二个参数的构造方法创建了一个学生对象。sid：" + sid + ";sname:" + sname);
	}
 
	@SuppressWarnings("unused")
	private Student(Integer age) {
		System.out.println("调用Student类私有的构造方法创建一个学生对象。age：" + age);
		this.age = age;
	}
 
	public String getSid() {
		return sid;
	}
 
	public void setSid(String sid) {
		this.sid = sid;
	}
 
	public String getSname() {
		return sname;
	}
 
	public void setSname(String sname) {
		this.sname = sname;
	}
 
	public void hello() {
		System.out.println("hello(),你好！我是" + this.sname);
	}
 
	public void hello(String name) {
		System.out.println("hello(),"  + name + "你好！我是" + this.sname);
	}
 
	@SuppressWarnings("unused")
	private Integer add(Integer a, Integer b) {
		return new Integer(a.intValue() + b.intValue());
	}
}
 