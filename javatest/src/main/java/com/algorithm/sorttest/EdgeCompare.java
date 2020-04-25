package com.algorithm.sorttest;

import java.util.Comparator;

public class EdgeCompare implements Comparator<Edge> {


@Override
public int compare(Edge o1, Edge o2) {
if(o2.w<o1.w){
return 1;
}
if(o2.w>o1.w){
return -1;
}
return 0;
}


}