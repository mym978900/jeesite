package com.jeesite.modules.clue.vo;

import com.github.pagehelper.PageInfo;
import com.jeesite.modules.clue.entity.UpAitask;

public class AiTaskVo {

	private String beginTime;
	private String endTime;
	private String userCode;
	private Integer pageNum;
	private String memberGrade;
	private PageInfo<UpAitask> pageInfo;
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
	public String getMemberGrade() {
		return memberGrade;
	}
	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}
	public PageInfo<UpAitask> getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo<UpAitask> pageInfo) {
		this.pageInfo = pageInfo;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public AiTaskVo(String beginTime, String endTime, String userCode, Integer pageNum, String memberGrade,
			PageInfo<UpAitask> pageInfo, boolean result) {
		super();
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.userCode = userCode;
		this.pageNum = pageNum;
		this.memberGrade = memberGrade;
		this.pageInfo = pageInfo;
		this.result = result;
	}
	public AiTaskVo() {
		super();
	}
	
	
	
}
