package com.jeesite.modules.test.entity;

import java.math.BigDecimal;

public class JsSysFileEntity {
    private String fileId;

    private String fileMd5;

    private String filePath;

    private String fileContentType;

    private String fileExtension;

    private BigDecimal fileSize;

    private String fileMeta;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5 == null ? null : fileMd5.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType == null ? null : fileContentType.trim();
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension == null ? null : fileExtension.trim();
    }

    public BigDecimal getFileSize() {
        return fileSize;
    }

    public void setFileSize(BigDecimal fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileMeta() {
        return fileMeta;
    }

    public void setFileMeta(String fileMeta) {
        this.fileMeta = fileMeta == null ? null : fileMeta.trim();
    }
}