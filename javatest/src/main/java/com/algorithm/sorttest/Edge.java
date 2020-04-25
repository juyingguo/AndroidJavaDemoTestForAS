package com.algorithm.sorttest;
//https://blog.csdn.net/u014230945/article/details/68065594
public class Edge {
public int w;


public Edge() {
// TODO 自动生成的构造函数存根
}

public Edge(int w){
this.w=w;
}

public int getW() {
return w;
}


public void setW(int w) {
this.w = w;
}


@Override
public String toString() {
return "Edge [w=" + w + "]";
}

}