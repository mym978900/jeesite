package com.jeesite.modules.test.vo;

import java.util.Date;

public class AitaskBackVo {

	private String day;//开始时间
	private Integer time;//结束时间
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public AitaskBackVo(String day, Integer time) {
		super();
		this.day = day;
		this.time = time;
	}
	public AitaskBackVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
