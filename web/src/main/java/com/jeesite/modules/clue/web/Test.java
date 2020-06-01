package com.jeesite.modules.clue.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {

	public static void main(String[] args){
	    List<String>list = new ArrayList<String>();
	    list.add("保护环境");  //向列表中添加数据
	    list.add("爱护地球");  //向列表中添加数据
	    list.add("从我做起");  //向列表中添加数据
	    List<String>list1 = new ArrayList<String>();
	    list1.add("保护环境");  //向列表中添加数据
	    list1.add("爱护地球");  //向列表中添加数据
	    list1.add("人人有责");  //向列表中添加数据
	    boolean ret = list.removeAll(list1);  //从list中移除与list1相同的元素
	    Iterator<String>it = list.iterator();  //创建迭代器
	    while(it.hasNext()){  //循环遍历迭代器
	    System.out.println(it.next());  //输出集合中元素
	  }
	}

}
