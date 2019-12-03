package com.jeesite.modules.test.entity;

public class JsJobSchedulerState extends JsJobSchedulerStateKey {
    private Long lastCheckinTime;

    private Long checkinInterval;

    public Long getLastCheckinTime() {
        return lastCheckinTime;
    }

    public void setLastCheckinTime(Long lastCheckinTime) {
        this.lastCheckinTime = lastCheckinTime;
    }

    public Long getCheckinInterval() {
        return checkinInterval;
    }

    public void setCheckinInterval(Long checkinInterval) {
        this.checkinInterval = checkinInterval;
    }
}