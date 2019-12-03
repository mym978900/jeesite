package com.jeesite.modules.test.entity;

import java.util.Date;

public class JsSysMsgInnerRecord {
    private String id;

    private String msgInnerId;

    private String receiveUserCode;

    private String receiveUserName;

    private String readStatus;

    private Date readDate;

    private String isStar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMsgInnerId() {
        return msgInnerId;
    }

    public void setMsgInnerId(String msgInnerId) {
        this.msgInnerId = msgInnerId == null ? null : msgInnerId.trim();
    }

    public String getReceiveUserCode() {
        return receiveUserCode;
    }

    public void setReceiveUserCode(String receiveUserCode) {
        this.receiveUserCode = receiveUserCode == null ? null : receiveUserCode.trim();
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName == null ? null : receiveUserName.trim();
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus == null ? null : readStatus.trim();
    }

    public Date getReadDate() {
        return readDate;
    }

    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }

    public String getIsStar() {
        return isStar;
    }

    public void setIsStar(String isStar) {
        this.isStar = isStar == null ? null : isStar.trim();
    }
}