package com.jeesite.modules.test.vo;

import java.util.Date;

public class AitaskVo {

	private Date day;//开始时间
	private Integer time;//结束时间
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public AitaskVo(Date day, Integer time) {
		super();
		this.day = day;
		this.time = time;
	}
	public AitaskVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
