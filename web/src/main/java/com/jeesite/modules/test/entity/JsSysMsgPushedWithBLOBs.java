package com.jeesite.modules.test.entity;

public class JsSysMsgPushedWithBLOBs extends JsSysMsgPushed {
    private String msgContent;

    private String pushReturnContent;

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
    }

    public String getPushReturnContent() {
        return pushReturnContent;
    }

    public void setPushReturnContent(String pushReturnContent) {
        this.pushReturnContent = pushReturnContent == null ? null : pushReturnContent.trim();
    }
}