package com.jeesite.modules.test.entity;

public class JsSysEmployeeOffice extends JsSysEmployeeOfficeKey {
    private String id;

    private String postCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }
}