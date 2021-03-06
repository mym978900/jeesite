package com.jeesite.modules.clue.vo;

import com.github.pagehelper.PageInfo;
import com.jeesite.modules.clue.entity.UpClue;

public class ClueVo {

	
	private String beginTime;
	private String endTime;
	private String status;
	private String userCode;
	private String memberGrade;
	private Integer pageNum;
	private PageInfo<UpClue> pageInfo;
	private boolean result;
	
	
	public String getMemberGrade() {
		return memberGrade;
	}
	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public PageInfo<UpClue> getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo<UpClue> pageInfo) {
		this.pageInfo = pageInfo;
	}
	public ClueVo(String beginTime, String endTime, String status, String userCode, Integer pageNum, PageInfo<UpClue> pageInfo,boolean result) {
		super();
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.status = status;
		this.userCode = userCode;
		this.pageNum = pageNum;
		this.pageInfo = pageInfo;
		this.result = result;
	}
	public ClueVo() {
		super();
	}
	
	
	
	
}
