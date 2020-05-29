package com.jeesite.modules.test.vo;

import java.util.Date;

import com.jeesite.modules.test.entity.NeedTime;
import com.jeesite.modules.test.entity.TrNeed;
import com.jeesite.modules.test.entity.TrOrder;
import com.jeesite.modules.test.entity.VideoOrder;

public class OrderNotHaveAbilityVo {

	private String openid ;
	private Date createTime;
	private String totalFee;
	private String mationTime;
	
	private String trPartAname;
	private String trPartAphone;
	private String trPartAaddress;
	private String trPartBname;
	private String trPartBphone;
	private String trPartBaddress;
	
	private String trType;
	private Integer trCount;
	private Double trUnitprice;
	private Double trTotaltime;
	private String trNeeddatelist;
	private String trNeedtimelist;
	private NeedTime[] needTime;
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getTrPartAname() {
		return trPartAname;
	}
	public void setTrPartAname(String trPartAname) {
		this.trPartAname = trPartAname;
	}
	public String getTrPartAphone() {
		return trPartAphone;
	}
	public void setTrPartAphone(String trPartAphone) {
		this.trPartAphone = trPartAphone;
	}
	public String getTrPartAaddress() {
		return trPartAaddress;
	}
	public void setTrPartAaddress(String trPartAaddress) {
		this.trPartAaddress = trPartAaddress;
	}
	public String getTrPartBname() {
		return trPartBname;
	}
	public void setTrPartBname(String trPartBname) {
		this.trPartBname = trPartBname;
	}
	public String getTrPartBphone() {
		return trPartBphone;
	}
	public void setTrPartBphone(String trPartBphone) {
		this.trPartBphone = trPartBphone;
	}
	public String getTrPartBaddress() {
		return trPartBaddress;
	}
	public void setTrPartBaddress(String trPartBaddress) {
		this.trPartBaddress = trPartBaddress;
	}
	public String getTrType() {
		return trType;
	}
	public void setTrType(String trType) {
		this.trType = trType;
	}
	public Integer getTrCount() {
		return trCount;
	}
	public void setTrCount(Integer trCount) {
		this.trCount = trCount;
	}
	public Double getTrUnitprice() {
		return trUnitprice;
	}
	public void setTrUnitprice(Double trUnitprice) {
		this.trUnitprice = trUnitprice;
	}
	public Double getTrTotaltime() {
		return trTotaltime;
	}
	public void setTrTotaltime(Double trTotaltime) {
		this.trTotaltime = trTotaltime;
	}
	public String getTrNeeddatelist() {
		return trNeeddatelist;
	}
	public void setTrNeeddatelist(String trNeeddatelist) {
		this.trNeeddatelist = trNeeddatelist;
	}
	public String getTrNeedtimelist() {
		return trNeedtimelist;
	}
	public void setTrNeedtimelist(String trNeedtimelist) {
		this.trNeedtimelist = trNeedtimelist;
	}
	public String getMationTime() {
		return mationTime;
	}
	public void setMationTime(String mationTime) {
		this.mationTime = mationTime;
	}
	public NeedTime[] getNeedTime() {
		return needTime;
	}
	public void setNeedTime(NeedTime[] needTime) {
		this.needTime = needTime;
	}
	
	
	
}
