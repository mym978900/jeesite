package com.jeesite.modules.test.entity;

import java.util.Date;

public class JsSysMsgInner {
    private String id;

    private String msgTitle;

    private String contentLevel;

    private String contentType;

    private String receiveType;

    private String sendUserCode;

    private String sendUserName;

    private Date sendDate;

    private String isAttac;

    private String notifyTypes;

    private String status;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle == null ? null : msgTitle.trim();
    }

    public String getContentLevel() {
        return contentLevel;
    }

    public void setContentLevel(String contentLevel) {
        this.contentLevel = contentLevel == null ? null : contentLevel.trim();
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType == null ? null : contentType.trim();
    }

    public String getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType == null ? null : receiveType.trim();
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

    public String getIsAttac() {
        return isAttac;
    }

    public void setIsAttac(String isAttac) {
        this.isAttac = isAttac == null ? null : isAttac.trim();
    }

    public String getNotifyTypes() {
        return notifyTypes;
    }

    public void setNotifyTypes(String notifyTypes) {
        this.notifyTypes = notifyTypes == null ? null : notifyTypes.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}