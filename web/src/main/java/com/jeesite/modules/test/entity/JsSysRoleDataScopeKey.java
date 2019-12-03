package com.jeesite.modules.test.entity;

public class JsSysRoleDataScopeKey {
    private String roleCode;

    private String ctrlType;

    private String ctrlData;

    private String ctrlPermi;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getCtrlType() {
        return ctrlType;
    }

    public void setCtrlType(String ctrlType) {
        this.ctrlType = ctrlType == null ? null : ctrlType.trim();
    }

    public String getCtrlData() {
        return ctrlData;
    }

    public void setCtrlData(String ctrlData) {
        this.ctrlData = ctrlData == null ? null : ctrlData.trim();
    }

    public String getCtrlPermi() {
        return ctrlPermi;
    }

    public void setCtrlPermi(String ctrlPermi) {
        this.ctrlPermi = ctrlPermi == null ? null : ctrlPermi.trim();
    }
}