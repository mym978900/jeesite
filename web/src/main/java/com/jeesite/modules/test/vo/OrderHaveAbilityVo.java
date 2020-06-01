package com.jeesite.modules.test.vo;

import java.util.Date;

import com.jeesite.modules.tr.entity.NeedTime;
import com.jeesite.modules.tr.entity.TrNeed;
import com.jeesite.modules.tr.entity.TrOrder;
import com.jeesite.modules.test.entity.VideoOrder;

public class OrderHaveAbilityVo {

	private Integer id;
    private String openid;
    private String outTradeNo;
    private Integer state;
    private Date createTime;
    private Date notifyTime;
    private String totalFee;
    private String nickname;
    private String headImg;
    private String videoId;
    private String videoTitle;
    private String videoImg;
    private String userId;
    private String ip;
    private Integer del;
    private String reserve1;
    private String reserve2;
    private String reserve3;
    private String trPaycommodity;
    private String trPartbusercode;
	
	private String trCardusername;
	private String trCardnum;
	private String trBankname;
	
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
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getNotifyTime() {
		return notifyTime;
	}
	public void setNotifyTime(Date notifyTime) {
		this.notifyTime = notifyTime;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getVideoTitle() {
		return videoTitle;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	public String getVideoImg() {
		return videoImg;
	}
	public void setVideoImg(String videoImg) {
		this.videoImg = videoImg;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getDel() {
		return del;
	}
	public void setDel(Integer del) {
		this.del = del;
	}
	public String getReserve1() {
		return reserve1;
	}
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}
	public String getReserve2() {
		return reserve2;
	}
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}
	public String getReserve3() {
		return reserve3;
	}
	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}
	public String getTrPaycommodity() {
		return trPaycommodity;
	}
	public void setTrPaycommodity(String trPaycommodity) {
		this.trPaycommodity = trPaycommodity;
	}
	public String getTrPartbusercode() {
		return trPartbusercode;
	}
	public void setTrPartbusercode(String trPartbusercode) {
		this.trPartbusercode = trPartbusercode;
	}
	public String getTrCardusername() {
		return trCardusername;
	}
	public void setTrCardusername(String trCardusername) {
		this.trCardusername = trCardusername;
	}
	public String getTrCardnum() {
		return trCardnum;
	}
	public void setTrCardnum(String trCardnum) {
		this.trCardnum = trCardnum;
	}
	public String getTrBankname() {
		return trBankname;
	}
	public void setTrBankname(String trBankname) {
		this.trBankname = trBankname;
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
	public NeedTime[] getNeedTime() {
		return needTime;
	}
	public void setNeedTime(NeedTime[] needTime) {
		this.needTime = needTime;
	}
	
	
}
