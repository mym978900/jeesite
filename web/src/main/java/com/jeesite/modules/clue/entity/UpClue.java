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

    private String upClueAddressCity;

    private String upClueAddressAera;
    
    private String upCluePersonstatus;

    public String getUpClueCode() {
        return upClueCode;
    }

    public void setUpClueCode(String upClueCode) {
        this.upClueCode = upClueCode == null ? null : upClueCode.trim();
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
        this.upUserCode = upUserCode == null ? null : upUserCode.trim();
    }

    public String getUpClueName() {
        return upClueName;
    }

    public void setUpClueName(String upClueName) {
        this.upClueName = upClueName == null ? null : upClueName.trim();
    }

    public String getUpClueTel() {
        return upClueTel;
    }

    public void setUpClueTel(String upClueTel) {
        this.upClueTel = upClueTel == null ? null : upClueTel.trim();
    }

    public String getUpClueSex() {
        return upClueSex;
    }

    public void setUpClueSex(String upClueSex) {
        this.upClueSex = upClueSex == null ? null : upClueSex.trim();
    }

    public String getUpClueAddre() {
        return upClueAddre;
    }

    public void setUpClueAddre(String upClueAddre) {
        this.upClueAddre = upClueAddre == null ? null : upClueAddre.trim();
    }

    public String getUpClueStatus() {
        return upClueStatus;
    }

    public void setUpClueStatus(String upClueStatus) {
        this.upClueStatus = upClueStatus == null ? null : upClueStatus.trim();
    }

    public String getUpClueAppraise() {
        return upClueAppraise;
    }

    public void setUpClueAppraise(String upClueAppraise) {
        this.upClueAppraise = upClueAppraise == null ? null : upClueAppraise.trim();
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
        this.extra1 = extra1 == null ? null : extra1.trim();
    }

    public String getExtra2() {
        return extra2;
    }

    public void setExtra2(String extra2) {
        this.extra2 = extra2 == null ? null : extra2.trim();
    }

    public String getExtra3() {
        return extra3;
    }

    public void setExtra3(String extra3) {
        this.extra3 = extra3 == null ? null : extra3.trim();
    }

    public String getExtra4() {
        return extra4;
    }

    public void setExtra4(String extra4) {
        this.extra4 = extra4 == null ? null : extra4.trim();
    }

    public String getExtra5() {
        return extra5;
    }

    public void setExtra5(String extra5) {
        this.extra5 = extra5 == null ? null : extra5.trim();
    }

    public String getUpClueDepttype() {
        return upClueDepttype;
    }

    public void setUpClueDepttype(String upClueDepttype) {
        this.upClueDepttype = upClueDepttype == null ? null : upClueDepttype.trim();
    }

    public String getUpClueTaskid() {
        return upClueTaskid;
    }

    public void setUpClueTaskid(String upClueTaskid) {
        this.upClueTaskid = upClueTaskid == null ? null : upClueTaskid.trim();
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
        this.upIseffective = upIseffective == null ? null : upIseffective.trim();
    }

    public String getUpClueAddressCity() {
        return upClueAddressCity;
    }

    public void setUpClueAddressCity(String upClueAddressCity) {
        this.upClueAddressCity = upClueAddressCity == null ? null : upClueAddressCity.trim();
    }

    public String getUpClueAddressAera() {
        return upClueAddressAera;
    }

    public void setUpClueAddressAera(String upClueAddressAera) {
        this.upClueAddressAera = upClueAddressAera == null ? null : upClueAddressAera.trim();
    }
    
    public String getUpCluePersonstatus() {
        return upCluePersonstatus;
    }

    public void setUpCluePersonstatus(String upCluePersonstatus) {
        this.upCluePersonstatus = upCluePersonstatus == null ? null : upCluePersonstatus.trim();
    }
}