package com.jeesite.modules.clue.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UpAitask {
    private String upTaskid;

    private String upSource;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date upCreatetime;

    private Date upStartaitime;

    private Date upEndtime;

    private String upCreateusercode;

    private String upStatus;

    private int upTalktime;
    
    private String upCluecode;

    private Date upAipersontime;

    private Date upAipersonendtime;

    private String upDeductionmark;

    private Double upDeductionprice;
    
    private String upId;
    
    private String upCallinstancestatus;

    private String upFinishstatus;
    
    private String upAiappraise;
    
    private String upCluename;
    
    private String upCluetel;

    public String getUpTaskid() {
        return upTaskid;
    }

    public void setUpTaskid(String upTaskid) {
        this.upTaskid = upTaskid == null ? null : upTaskid.trim();
    }

    public String getUpSource() {
        return upSource;
    }

    public void setUpSource(String upSource) {
        this.upSource = upSource == null ? null : upSource.trim();
    }

    public Date getUpCreatetime() {
        return upCreatetime;
    }

    public void setUpCreatetime(Date upCreatetime) {
        this.upCreatetime = upCreatetime;
    }

    public Date getUpStartaitime() {
        return upStartaitime;
    }

    public void setUpStartaitime(Date upStartaitime) {
        this.upStartaitime = upStartaitime;
    }

    public Date getUpEndtime() {
        return upEndtime;
    }

    public void setUpEndtime(Date upEndtime) {
        this.upEndtime = upEndtime;
    }

    public String getUpCreateusercode() {
        return upCreateusercode;
    }

    public void setUpCreateusercode(String upCreateusercode) {
        this.upCreateusercode = upCreateusercode == null ? null : upCreateusercode.trim();
    }

    public String getUpStatus() {
        return upStatus;
    }

    public void setUpStatus(String upStatus) {
        this.upStatus = upStatus == null ? null : upStatus.trim();
    }

    public int getUpTalktime() {
        return upTalktime;
    }

    public void setUpTalktime(int upTalktime) {
        this.upTalktime = upTalktime;
    }
    
    public String getUpCluecode() {
        return upCluecode;
    }

    public void setUpCluecode(String upCluecode) {
        this.upCluecode = upCluecode == null ? null : upCluecode.trim();
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

    public String getUpDeductionmark() {
        return upDeductionmark;
    }

    public void setUpDeductionmark(String upDeductionmark) {
        this.upDeductionmark = upDeductionmark == null ? null : upDeductionmark.trim();
    }

    public Double getUpDeductionprice() {
        return upDeductionprice;
    }

    public void setUpDeductionprice(Double upDeductionprice) {
        this.upDeductionprice = upDeductionprice;
    }
    
    public String getUpId() {
        return upId;
    }

    public void setUpId(String upId) {
        this.upId = upId == null ? null : upId.trim();
    }
    
    public String getUpCallinstancestatus() {
        return upCallinstancestatus;
    }

    public void setUpCallinstancestatus(String upCallinstancestatus) {
        this.upCallinstancestatus = upCallinstancestatus == null ? null : upCallinstancestatus.trim();
    }

    public String getUpFinishstatus() {
        return upFinishstatus;
    }

    public void setUpFinishstatus(String upFinishstatus) {
        this.upFinishstatus = upFinishstatus == null ? null : upFinishstatus.trim();
    }
    
    public String getUpAiappraise() {
        return upAiappraise;
    }

    public void setUpAiappraise(String upAiappraise) {
        this.upAiappraise = upAiappraise == null ? null : upAiappraise.trim();
    }
    
    public String getUpCluename() {
        return upCluename;
    }

    public void setUpCluename(String upCluename) {
        this.upCluename = upCluename == null ? null : upCluename.trim();
    }
    
    public String getUpCluetel() {
        return upCluetel;
    }

    public void setUpCluetel(String upCluetel) {
        this.upCluetel = upCluetel == null ? null : upCluetel.trim();
    }
}