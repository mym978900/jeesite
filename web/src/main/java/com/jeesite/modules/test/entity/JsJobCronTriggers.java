package com.jeesite.modules.test.entity;

public class JsJobCronTriggers extends JsJobCronTriggersKey {
    private String cronExpression;

    private String timeZoneId;

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression == null ? null : cronExpression.trim();
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId == null ? null : timeZoneId.trim();
    }
}