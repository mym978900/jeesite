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
    private String upClueDepttype;
    private Double upClueLatitude;

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

	

	public Double getUpClueLatitude() {
		return upClueLatitude;
	}

	public void setUpClueLatitude(Double upClueLatitude) {
		this.upClueLatitude = upClueLatitude;
	}

	public Double getUpClueLongitude() {
		return upClueLongitude;
	}

	public void setUpClueLongitude(Double upClueLongitude) {
		this.upClueLongitude = upClueLongitude;
	}

	public String getUpClueDepttype() {
		return upClueDepttype;
	}

	public void setUpClueDepttype(String upClueDepttype) {
		this.upClueDepttype = upClueDepttype;
	}
}