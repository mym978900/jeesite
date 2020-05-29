package com.jeesite.modules.test.vo;

import com.github.pagehelper.PageInfo;
import com.jeesite.modules.test.entity.JsSysApply;
import com.jeesite.modules.test.entity.JsSysMember;

public class MemberVo {

	private String organName;
	private String memberGrade;
	private String startTime;
	private String endTime;
	private Integer pageNum;//页码
	private PageInfo<JsSysMember> page;
	private String[] startEndTime;
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getMemberGrade() {
		return memberGrade;
	}
	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
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
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public PageInfo<JsSysMember> getPage() {
		return page;
	}
	public void setPage(PageInfo<JsSysMember> page) {
		this.page = page;
	}
	public MemberVo(String organName, String memberGrade, String startTime, String endTime, Integer pageNum,
			PageInfo<JsSysMember> page) {
		super();
		this.organName = organName;
		this.memberGrade = memberGrade;
		this.startTime = startTime;
		this.endTime = endTime;
		this.pageNum = pageNum;
		this.page = page;
	}
	public String[] getStartEndTime() {
		return startEndTime;
	}
	public void setStartEndTime(String[] startEndTime) {
		this.startEndTime = startEndTime;
	}
	
}
