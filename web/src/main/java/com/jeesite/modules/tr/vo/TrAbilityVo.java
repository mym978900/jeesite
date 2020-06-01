package com.jeesite.modules.tr.vo;

import com.github.pagehelper.PageInfo;
import com.jeesite.modules.tr.entity.TrAbility;

public class TrAbilityVo {
	private String beginTime;
	private String endTime;
	private String status;
	private Double minlat;
	private Double maxlat;
	private Double minlng;
	private Double maxlng;
	private String userCode;
	private Integer pageNum;
	private PageInfo<TrAbility> pageInfo;
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
	public PageInfo<TrAbility> getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo<TrAbility> pageInfo) {
		this.pageInfo = pageInfo;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public Double getMinlat() {
		return minlat;
	}
	public void setMinlat(Double minlat) {
		this.minlat = minlat;
	}
	public Double getMaxlat() {
		return maxlat;
	}
	public void setMaxlat(Double maxlat) {
		this.maxlat = maxlat;
	}
	public Double getMinlng() {
		return minlng;
	}
	public void setMinlng(Double minlng) {
		this.minlng = minlng;
	}
	public Double getMaxlng() {
		return maxlng;
	}
	public void setMaxlng(Double maxlng) {
		this.maxlng = maxlng;
	}
	public TrAbilityVo(String beginTime, String endTime, String status, Double minlat, Double maxlat, Double minlng,
			Double maxlng, String userCode, Integer pageNum, PageInfo<TrAbility> pageInfo, boolean result) {
		super();
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.status = status;
		this.minlat = minlat;
		this.maxlat = maxlat;
		this.minlng = minlng;
		this.maxlng = maxlng;
		this.userCode = userCode;
		this.pageNum = pageNum;
		this.pageInfo = pageInfo;
		this.result = result;
	}
	public TrAbilityVo() {
		super();
	}
	
	
	
}
