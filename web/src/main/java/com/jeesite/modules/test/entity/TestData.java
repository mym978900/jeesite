package com.jeesite.modules.test.entity;

import java.util.Date;

public class TestData {
    private String id;

    private String testInput;

    private String testTextarea;

    private String testSelect;

    private String testSelectMultiple;

    private String testRadio;

    private String testCheckbox;

    private Date testDate;

    private Date testDatetime;

    private String testUserCode;

    private String testOfficeCode;

    private String testAreaCode;

    private String testAreaName;

    private String status;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTestInput() {
        return testInput;
    }

    public void setTestInput(String testInput) {
        this.testInput = testInput == null ? null : testInput.trim();
    }

    public String getTestTextarea() {
        return testTextarea;
    }

    public void setTestTextarea(String testTextarea) {
        this.testTextarea = testTextarea == null ? null : testTextarea.trim();
    }

    public String getTestSelect() {
        return testSelect;
    }

    public void setTestSelect(String testSelect) {
        this.testSelect = testSelect == null ? null : testSelect.trim();
    }

    public String getTestSelectMultiple() {
        return testSelectMultiple;
    }

    public void setTestSelectMultiple(String testSelectMultiple) {
        this.testSelectMultiple = testSelectMultiple == null ? null : testSelectMultiple.trim();
    }

    public String getTestRadio() {
        return testRadio;
    }

    public void setTestRadio(String testRadio) {
        this.testRadio = testRadio == null ? null : testRadio.trim();
    }

    public String getTestCheckbox() {
        return testCheckbox;
    }

    public void setTestCheckbox(String testCheckbox) {
        this.testCheckbox = testCheckbox == null ? null : testCheckbox.trim();
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public Date getTestDatetime() {
        return testDatetime;
    }

    public void setTestDatetime(Date testDatetime) {
        this.testDatetime = testDatetime;
    }

    public String getTestUserCode() {
        return testUserCode;
    }

    public void setTestUserCode(String testUserCode) {
        this.testUserCode = testUserCode == null ? null : testUserCode.trim();
    }

    public String getTestOfficeCode() {
        return testOfficeCode;
    }

    public void setTestOfficeCode(String testOfficeCode) {
        this.testOfficeCode = testOfficeCode == null ? null : testOfficeCode.trim();
    }

    public String getTestAreaCode() {
        return testAreaCode;
    }

    public void setTestAreaCode(String testAreaCode) {
        this.testAreaCode = testAreaCode == null ? null : testAreaCode.trim();
    }

    public String getTestAreaName() {
        return testAreaName;
    }

    public void setTestAreaName(String testAreaName) {
        this.testAreaName = testAreaName == null ? null : testAreaName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}