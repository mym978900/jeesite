package com.jeesite.modules.test.entity;

import java.util.Date;

public class JsSysMsgPushed {
    private String id;

    private String msgType;

    private String msgTitle;

    private String bizKey;

    private String bizType;

    private String receiveCode;

    private String receiveUserCode;

    private String receiveUserName;

    private String sendUserCode;

    private String sendUserName;

    private Date sendDate;

    private String isMergePush;

    private Date planPushDate;

    private Integer pushNumber;

    private String pushReturnCode;

    private String pushReturnMsgId;

    private String pushStatus;

    private Date pushDate;

    private String readStatus;

    private Date readDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle == null ? null : msgTitle.trim();
    }

    public String getBizKey() {
        return bizKey;
    }

    public void setBizKey(String bizKey) {
        this.bizKey = bizKey == null ? null : bizKey.trim();
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType == null ? null : bizType.trim();
    }

    public String getReceiveCode() {
        return receiveCode;
    }

    public void setReceiveCode(String receiveCode) {
        this.receiveCode = receiveCode == null ? null : receiveCode.trim();
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

    public String getSendUserCode() {
        return sendUserCode;
    }

    public void setSendUserCode(String sendUserCode) {
        this.sendUserCode = sendUserCode == null ? null : sendUserCode.trim();
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName == null ? null : sendUserName.trim();
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getIsMergePush() {
        return isMergePush;
    }

    public void setIsMergePush(String isMergePush) {
        this.isMergePush = isMergePush == null ? null : isMergePush.trim();
    }

    public Date getPlanPushDate() {
        return planPushDate;
    }

    public void setPlanPushDate(Date planPushDate) {
        this.planPushDate = planPushDate;
    }

    public Integer getPushNumber() {
        return pushNumber;
    }

    public void setPushNumber(Integer pushNumber) {
        this.pushNumber = pushNumber;
    }

    public String getPushReturnCode() {
        return pushReturnCode;
    }

    public void setPushReturnCode(String pushReturnCode) {
        this.pushReturnCode = pushReturnCode == null ? null : pushReturnCode.trim();
    }

    public String getPushReturnMsgId() {
        return pushReturnMsgId;
    }

    public void setPushReturnMsgId(String pushReturnMsgId) {
        this.pushReturnMsgId = pushReturnMsgId == null ? null : pushReturnMsgId.trim();
    }

    public String getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(String pushStatus) {
        this.pushStatus = pushStatus == null ? null : pushStatus.trim();
    }

    public Date getPushDate() {
        return pushDate;
    }

    public void setPushDate(Date pushDate) {
        this.pushDate = pushDate;
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
}