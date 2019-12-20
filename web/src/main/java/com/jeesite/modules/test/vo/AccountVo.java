package com.jeesite.modules.test.vo;

import java.io.Serializable;

import com.github.pagehelper.PageInfo;
import com.jeesite.modules.test.entity.JsSysApply;
import com.jeesite.modules.test.entity.JsSysProblem;
import com.jeesite.modules.test.entity.JsSysProposal;

public class AccountVo implements Serializable{

	private String startTime;//开始时间
	private String endTime;//结束时间
	private String followState;//跟进状态
	private Integer pageNum;//页码
	private PageInfo<JsSysApply> page;//page分页数据
	private PageInfo<JsSysProposal> pagePro;
	private PageInfo<JsSysProblem> pagePb;
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
	public String getFollowState() {
		return followState;
	}
	public void setFollowState(String followState) {
		this.followState = followState;
	}
	public PageInfo<JsSysApply> getPage() {
		return page;
	}
	public void setPage(PageInfo<JsSysApply> page) {
		this.page = page;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	@Override
	public String toString() {
		return "AccountVo [startTime=" + startTime + ", endTime=" + endTime + ", followState=" + followState
				+ ", pageNum=" + pageNum + ", page=" + page + "]";
	}
	public AccountVo(String startTime, String endTime, String followState, Integer pageNum, PageInfo<JsSysApply> page,
			PageInfo<JsSysProposal> pagePro, PageInfo<JsSysProblem> pagePb) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.followState = followState;
		this.pageNum = pageNum;
		this.page = page;
		this.pagePro = pagePro;
		this.pagePb = pagePb;
	}
	public AccountVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageInfo<JsSysProposal> getPagePro() {
		return pagePro;
	}
	public void setPagePro(PageInfo<JsSysProposal> pagePro) {
		this.pagePro = pagePro;
	}
	public AccountVo(String startTime, String endTime, String followState, Integer pageNum, PageInfo<JsSysApply> page) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.followState = followState;
		this.pageNum = pageNum;
		this.page = page;
	}
	public PageInfo<JsSysProblem> getPagePb() {
		return pagePb;
	}
	public void setPagePb(PageInfo<JsSysProblem> pagePb) {
		this.pagePb = pagePb;
	}
	
}
