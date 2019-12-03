package com.jeesite.modules.test.entity;

public class JsSysLogWithBLOBs extends JsSysLog {
    private String requestParams;

    private String diffModifyData;

    private String exceptionInfo;

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams == null ? null : requestParams.trim();
    }

    public String getDiffModifyData() {
        return diffModifyData;
    }

    public void setDiffModifyData(String diffModifyData) {
        this.diffModifyData = diffModifyData == null ? null : diffModifyData.trim();
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo == null ? null : exceptionInfo.trim();
    }
}