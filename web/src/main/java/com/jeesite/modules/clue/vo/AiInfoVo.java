package com.jeesite.modules.clue.vo;

import com.github.pagehelper.PageInfo;
import com.jeesite.modules.clue.entity.UpAiinfo;

public class AiInfoVo {

	private String beginTime;
	private String endTime;
	private String status;
	private Integer times;
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
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
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
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
	public AiInfoVo(String beginTime, String endTime, String status, int times, String userCode, Integer pageNum, PageInfo<UpAiinfo> pageInfo,
			boolean result) {
		super();
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.status = status;
		this.times = times;
		this.userCode = userCode;
		this.pageNum = pageNum;
		this.pageInfo = pageInfo;
		this.result = result;
	}
	
	public AiInfoVo() {
		super();
	}
	
}
