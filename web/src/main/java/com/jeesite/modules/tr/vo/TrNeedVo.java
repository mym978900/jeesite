package com.jeesite.modules.tr.vo;

import com.github.pagehelper.PageInfo;
import com.jeesite.modules.tr.entity.TrNeed;

public class TrNeedVo {

	private String beginTime;
	private String endTime;
	private String status;
	private String userCode;
	private Integer pageNum;
	private PageInfo<TrNeed> pageInfo;
	private boolean result;
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public PageInfo<TrNeed> getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo<TrNeed> pageInfo) {
		this.pageInfo = pageInfo;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public TrNeedVo(String beginTime, String endTime, String status, String userCode, PageInfo<TrNeed> pageInfo,
			boolean result) {
		super();
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.status = status;
		this.userCode = userCode;
		this.pageInfo = pageInfo;
		this.result = result;
	}
	public TrNeedVo() {
		super();
	}
	
	
}
