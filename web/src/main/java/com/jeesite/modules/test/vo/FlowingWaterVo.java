package com.jeesite.modules.test.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.github.pagehelper.PageInfo;
import com.jeesite.modules.test.entity.JsSysOrder;
import com.jeesite.modules.test.entity.VideoOrder;

public class FlowingWaterVo{

	private String organName;
	private String orderNum;
	private String startTime;
	private String endTime;
	private PageInfo<VideoOrder> page;
	private BigDecimal moneyCensus;
	private String[] startEndTime;
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public FlowingWaterVo(String organName, String orderNum, String startTime, String endTime,
			PageInfo<VideoOrder> page, BigDecimal moneyCensus) {
		super();
		this.organName = organName;
		this.orderNum = orderNum;
		this.startTime = startTime;
		this.endTime = endTime;
		this.page = page;
		this.moneyCensus = moneyCensus;
	}
	public FlowingWaterVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageInfo<VideoOrder> getPage() {
		return page;
	}
	public void setPage(PageInfo<VideoOrder> page) {
		this.page = page;
	}
	public BigDecimal getMoneyCensus() {
		return moneyCensus;
	}
	public void setMoneyCensus(BigDecimal moneyCensus) {
		this.moneyCensus = moneyCensus;
	}
	public String[] getStartEndTime() {
		return startEndTime;
	}
	public void setStartEndTime(String[] startEndTime) {
		this.startEndTime = startEndTime;
	}
	
}
