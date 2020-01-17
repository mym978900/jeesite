package com.jeesite.modules.test.vo;

import java.util.List;

public class DataStatisticsVo {

	private Integer memberNum;//会员总量
	private Integer memberNumM;//新增会员量月
	private Integer clueNum;//线索总量
	private Integer clueNumM;//新增线索量月
	private List<AitaskBackVo> dayList;//日折线
	private List<AitaskBackVo> monthList;//月折线
	public Integer getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(Integer memberNum) {
		this.memberNum = memberNum;
	}
	public Integer getMemberNumM() {
		return memberNumM;
	}
	public void setMemberNumM(Integer memberNumM) {
		this.memberNumM = memberNumM;
	}
	public Integer getClueNum() {
		return clueNum;
	}
	public void setClueNum(Integer clueNum) {
		this.clueNum = clueNum;
	}
	public Integer getClueNumM() {
		return clueNumM;
	}
	public void setClueNumM(Integer clueNumM) {
		this.clueNumM = clueNumM;
	}
	public List<AitaskBackVo> getDayList() {
		return dayList;
	}
	public void setDayList(List<AitaskBackVo> dayList) {
		this.dayList = dayList;
	}
	public List<AitaskBackVo> getMonthList() {
		return monthList;
	}
	public void setMonthList(List<AitaskBackVo> monthList) {
		this.monthList = monthList;
	}
	public DataStatisticsVo(Integer memberNum, Integer memberNumM, Integer clueNum, Integer clueNumM,
			List<AitaskBackVo> dayList, List<AitaskBackVo> monthList) {
		super();
		this.memberNum = memberNum;
		this.memberNumM = memberNumM;
		this.clueNum = clueNum;
		this.clueNumM = clueNumM;
		this.dayList = dayList;
		this.monthList = monthList;
	}
	public DataStatisticsVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
