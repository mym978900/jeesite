package com.jeesite.modules.test.entity;

import java.util.Date;

public class JsSysJobLog {
    private String id;

    private String jobName;

    private String jobGroup;

    private String jobType;

    private String jobEvent;

    private String jobMessage;

    private String isException;

    private Date createDate;

    private String exceptionInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup == null ? null : jobGroup.trim();
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType == null ? null : jobType.trim();
    }

    public String getJobEvent() {
        return jobEvent;
    }

    public void setJobEvent(String jobEvent) {
        this.jobEvent = jobEvent == null ? null : jobEvent.trim();
    }

    public String getJobMessage() {
        return jobMessage;
    }

    public void setJobMessage(String jobMessage) {
        this.jobMessage = jobMessage == null ? null : jobMessage.trim();
    }

    public String getIsException() {
        return isException;
    }

    public void setIsException(String isException) {
        this.isException = isException == null ? null : isException.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo == null ? null : exceptionInfo.trim();
    }
}