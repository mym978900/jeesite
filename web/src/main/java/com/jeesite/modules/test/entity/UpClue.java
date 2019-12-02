package com.jeesite.modules.test.entity;

import java.util.Date;

public class UpClue {
    private String upClueCode;

    private Date upClueTime;

    private String upUserCode;

    private String upClueName;

    private String upClueTel;

    private String upClueSex;

    private String upClueAddre;

    private String upClueStatus;

    private String upClueAppraise;

    private Integer upClueAge;

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
}