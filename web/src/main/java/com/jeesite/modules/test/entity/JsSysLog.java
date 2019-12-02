package com.jeesite.modules.test.entity;

import java.math.BigDecimal;
import java.util.Date;

public class JsSysLog {
    private String id;

    private String logType;

    private String logTitle;

    private String createBy;

    private String createByName;

    private Date createDate;

    private String requestUri;

    private String requestMethod;

    private String bizKey;

    private String bizType;

    private String remoteAddr;

    private String serverAddr;

    private String isException;

    private String userAgent;

    private String deviceName;

    private String browserName;

    private BigDecimal executeTime;

    private String corpCode;

    private String corpName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType == null ? null : logType.trim();
    }

    public String getLogTitle() {
        return logTitle;
    }

    public void setLogTitle(String logTitle) {
        this.logTitle = logTitle == null ? null : logTitle.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName == null ? null : createByName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri == null ? null : requestUri.trim();
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod == null ? null : requestMethod.trim();
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

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr == null ? null : remoteAddr.trim();
    }

    public String getServerAddr() {
        return serverAddr;
    }

    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr == null ? null : serverAddr.trim();
    }

    public String getIsException() {
        return isException;
    }

    public void setIsException(String isException) {
        this.isException = isException == null ? null : isException.trim();
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName == null ? null : browserName.trim();
    }

    public BigDecimal getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(BigDecimal executeTime) {
        this.executeTime = executeTime;
    }

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode == null ? null : corpCode.trim();
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName == null ? null : corpName.trim();
    }
}