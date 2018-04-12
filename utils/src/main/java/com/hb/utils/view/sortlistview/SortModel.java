package com.hb.utils.view.sortlistview;

import java.io.Serializable;

public class SortModel implements Serializable{

	private String name;   //显示的数据
	private String sortLetters;  //显示数据拼音的首字母
	private Object obj;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
	
	public synchronized Object getObj() {
		return obj;
	}
	public synchronized void setObj(Object obj) {
		this.obj = obj;
	}
	@Override
	public String toString() {
		return "SortModel [name=" + name + ", sortLetters=" + sortLetters + ", obj=" + obj + "]";
	}
}
