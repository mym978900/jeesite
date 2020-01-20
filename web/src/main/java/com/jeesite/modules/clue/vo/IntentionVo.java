package com.jeesite.modules.clue.vo;

import com.github.pagehelper.PageInfo;
import com.jeesite.modules.clue.entity.UpAiinfo;

public class IntentionVo {

	private String beginTime;
	private String endTime;
	private String userCode;
	private Integer pageNum;
	private PageInfo<UpAiinfo> pageInfo;
	private boolean result;
	
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
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
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public PageInfo<UpAiinfo> getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo<UpAiinfo> pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	public IntentionVo(String beginTime, String endTime, String userCode, Integer pageNum, PageInfo<UpAiinfo> pageInfo,
			boolean result) {
		super();
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.userCode = userCode;
		this.pageNum = pageNum;
		this.pageInfo = pageInfo;
		this.result = result;
	}
	public IntentionVo() {
		super();
	}
	
}
