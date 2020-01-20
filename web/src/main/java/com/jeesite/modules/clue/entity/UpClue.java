package com.jeesite.modules.clue.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UpClue {
    private String upClueCode;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date upClueTime;

    private String upUserCode;

    private String upClueName;

    private String upClueTel;

    private String upClueSex;

    private String upClueAddre;

    private String upClueStatus;

    private String upClueAppraise;

    private Integer upClueAge;

    private Double upClueLongitude;

    private Double upClueLatitude;

    private Date upClueMatchtime;

    private String extra1;

    private String extra2;

    private String extra3;

    private String extra4;

    private String extra5;
    
    private String upClueDepttype;
    
    private String upClueTaskid;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date upAiexecutetime;

    private Date upAipersontime;
    
    private Date upAipersonendtime;

    private Date upAiendtime;

    private Integer upTalktime;
    
    private String upIseffective;

	public String getUpClueCode() {
		return upClueCode;
	}

	public void setUpClueCode(String upClueCode) {
		this.upClueCode = upClueCode;
	}

	public Date getUpClueTime() {
		return upClueTime;
	}

	public void setUpClueTime(Date upClueTime) {
		this.upClueTime = upClueTime;
	}

	public String getUpUserCode() {
		return upUserCode;
	}

	public void setUpUserCode(String upUserCode) {
		this.upUserCode = upUserCode;
	}

	public String getUpClueName() {
		return upClueName;
	}

	public void setUpClueName(String upClueName) {
		this.upClueName = upClueName;
	}

	public String getUpClueTel() {
		return upClueTel;
	}

	public void setUpClueTel(String upClueTel) {
		this.upClueTel = upClueTel;
	}

	public String getUpClueSex() {
		return upClueSex;
	}

	public void setUpClueSex(String upClueSex) {
		this.upClueSex = upClueSex;
	}

	public String getUpClueAddre() {
		return upClueAddre;
	}

	public void setUpClueAddre(String upClueAddre) {
		this.upClueAddre = upClueAddre;
	}

	public String getUpClueStatus() {
		return upClueStatus;
	}

	public void setUpClueStatus(String upClueStatus) {
		this.upClueStatus = upClueStatus;
	}

	public String getUpClueAppraise() {
		return upClueAppraise;
	}

	public void setUpClueAppraise(String upClueAppraise) {
		this.upClueAppraise = upClueAppraise;
	}

	public Integer getUpClueAge() {
		return upClueAge;
	}

	public void setUpClueAge(Integer upClueAge) {
		this.upClueAge = upClueAge;
	}

	public Double getUpClueLongitude() {
		return upClueLongitude;
	}

	public void setUpClueLongitude(Double upClueLongitude) {
		this.upClueLongitude = upClueLongitude;
	}

	public Double getUpClueLatitude() {
		return upClueLatitude;
	}

	public void setUpClueLatitude(Double upClueLatitude) {
		this.upClueLatitude = upClueLatitude;
	}

	public Date getUpClueMatchtime() {
		return upClueMatchtime;
	}

	public void setUpClueMatchtime(Date upClueMatchtime) {
		this.upClueMatchtime = upClueMatchtime;
	}

	public String getExtra1() {
		return extra1;
	}

	public void setExtra1(String extra1) {
		this.extra1 = extra1;
	}

	public String getExtra2() {
		return extra2;
	}

	public void setExtra2(String extra2) {
		this.extra2 = extra2;
	}

	public String getExtra3() {
		return extra3;
	}

	public void setExtra3(String extra3) {
		this.extra3 = extra3;
	}

	public String getExtra4() {
		return extra4;
	}

	public void setExtra4(String extra4) {
		this.extra4 = extra4;
	}

	public String getExtra5() {
		return extra5;
	}

	public void setExtra5(String extra5) {
		this.extra5 = extra5;
	}

	public String getUpClueDepttype() {
		return upClueDepttype;
	}

	public void setUpClueDepttype(String upClueDepttype) {
		this.upClueDepttype = upClueDepttype;
	}

	public String getUpClueTaskid() {
		return upClueTaskid;
	}

	public void setUpClueTaskid(String upClueTaskid) {
		this.upClueTaskid = upClueTaskid;
	}

	public Date getUpAiexecutetime() {
		return upAiexecutetime;
	}

	public void setUpAiexecutetime(Date upAiexecutetime) {
		this.upAiexecutetime = upAiexecutetime;
	}

	public Date getUpAipersontime() {
		return upAipersontime;
	}

	public void setUpAipersontime(Date upAipersontime) {
		this.upAipersontime = upAipersontime;
	}

	public Date getUpAipersonendtime() {
		return upAipersonendtime;
	}

	public void setUpAipersonendtime(Date upAipersonendtime) {
		this.upAipersonendtime = upAipersonendtime;
	}

	public Date getUpAiendtime() {
		return upAiendtime;
	}

	public void setUpAiendtime(Date upAiendtime) {
		this.upAiendtime = upAiendtime;
	}

	public Integer getUpTalktime() {
		return upTalktime;
	}

	public void setUpTalktime(Integer upTalktime) {
		this.upTalktime = upTalktime;
	}

	public String getUpIseffective() {
		return upIseffective;
	}

	public void setUpIseffective(String upIseffective) {
		this.upIseffective = upIseffective;
	}
   
}