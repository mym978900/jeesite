package com.jeesite.modules.test.vo;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.JsSysSeat;
import com.jeesite.modules.test.entity.JsSysSetmeal;
import com.jeesite.modules.test.entity.VideoOrder;

public class CostVo {
	private Integer pageNum;//页码
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String costType;//交易类型
	private List<JsSysSetmeal> mealList;//套餐列表
	private JsSysMember member;//会员信息
	private String loginCode;
	private PageInfo<VideoOrder> page;//订单列表
	private JsSysSeat seat;//坐席信息
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
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
	public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
	}
	public List<JsSysSetmeal> getMealList() {
		return mealList;
	}
	public void setMealList(List<JsSysSetmeal> mealList) {
		this.mealList = mealList;
	}
	public JsSysMember getMember() {
		return member;
	}
	public void setMember(JsSysMember member) {
		this.member = member;
	}
	
	public PageInfo<VideoOrder> getPage() {
		return page;
	}
	public void setPage(PageInfo<VideoOrder> page) {
		this.page = page;
	}
	public JsSysSeat getSeat() {
		return seat;
	}
	public void setSeat(JsSysSeat seat) {
		this.seat = seat;
	}
	
	public CostVo(Integer pageNum, String startTime, String endTime, String costType, List<JsSysSetmeal> mealList,
			JsSysMember member, PageInfo<VideoOrder> page, JsSysSeat seat) {
		super();
		this.pageNum = pageNum;
		this.startTime = startTime;
		this.endTime = endTime;
		this.costType = costType;
		this.mealList = mealList;
		this.member = member;
		this.page = page;
		this.seat = seat;
	}
	public CostVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getLoginCode() {
		return loginCode;
	}
	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}
	
	

}
