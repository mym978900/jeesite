package com.jeesite.modules.tr.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class NeedTime {

	private String[] timeArr;
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date needDate;
	public String[] getTimeArr() {
		return timeArr;
	}
	public void setTimeArr(String[] timeArr) {
		this.timeArr = timeArr;
	}
	public Date getNeedDate() {
		return needDate;
	}
	public void setNeedDate(Date needDate) {
		this.needDate = needDate;
	}
		
		
	
}
