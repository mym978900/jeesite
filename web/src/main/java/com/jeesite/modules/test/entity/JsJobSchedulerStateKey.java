package com.jeesite.modules.test.entity;

public class JsJobSchedulerStateKey {
    private String schedName;

    private String instanceName;

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName == null ? null : schedName.trim();
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName == null ? null : instanceName.trim();
    }
}