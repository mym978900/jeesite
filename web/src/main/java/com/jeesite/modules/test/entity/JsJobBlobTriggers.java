package com.jeesite.modules.test.entity;

public class JsJobBlobTriggers extends JsJobBlobTriggersKey {
    private byte[] blobData;

    public byte[] getBlobData() {
        return blobData;
    }

    public void setBlobData(byte[] blobData) {
        this.blobData = blobData;
    }
}