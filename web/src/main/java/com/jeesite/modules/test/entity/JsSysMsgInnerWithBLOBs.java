package com.jeesite.modules.test.entity;

public class JsSysMsgInnerWithBLOBs extends JsSysMsgInner {
    private String msgContent;

    private String receiveCodes;

    private String receiveNames;

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
    }

    public String getReceiveCodes() {
        return receiveCodes;
    }

    public void setReceiveCodes(String receiveCodes) {
        this.receiveCodes = receiveCodes == null ? null : receiveCodes.trim();
    }

    public String getReceiveNames() {
        return receiveNames;
    }

    public void setReceiveNames(String receiveNames) {
        this.receiveNames = receiveNames == null ? null : receiveNames.trim();
    }
}