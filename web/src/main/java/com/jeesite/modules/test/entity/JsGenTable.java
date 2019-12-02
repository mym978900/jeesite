package com.jeesite.modules.test.entity;

import java.util.Date;

public class JsGenTable {
    private String tableName;

    private String className;

    private String comments;

    private String parentTableName;

    private String parentTableFkName;

    private String dataSourceName;

    private String tplCategory;

    private String packageName;

    private String moduleName;

    private String subModuleName;

    private String functionName;

    private String functionNameSimple;

    private String functionAuthor;

    private String genBaseDir;

    private String options;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String remarks;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public String getParentTableName() {
        return parentTableName;
    }

    public void setParentTableName(String parentTableName) {
        this.parentTableName = parentTableName == null ? null : parentTableName.trim();
    }

    public String getParentTableFkName() {
        return parentTableFkName;
    }

    public void setParentTableFkName(String parentTableFkName) {
        this.parentTableFkName = parentTableFkName == null ? null : parentTableFkName.trim();
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName == null ? null : dataSourceName.trim();
    }

    public String getTplCategory() {
        return tplCategory;
    }

    public void setTplCategory(String tplCategory) {
        this.tplCategory = tplCategory == null ? null : tplCategory.trim();
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName == null ? null : packageName.trim();
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getSubModuleName() {
        return subModuleName;
    }

    public void setSubModuleName(String subModuleName) {
        this.subModuleName = subModuleName == null ? null : subModuleName.trim();
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName == null ? null : functionName.trim();
    }

    public String getFunctionNameSimple() {
        return functionNameSimple;
    }

    public void setFunctionNameSimple(String functionNameSimple) {
        this.functionNameSimple = functionNameSimple == null ? null : functionNameSimple.trim();
    }

    public String getFunctionAuthor() {
        return functionAuthor;
    }

    public void setFunctionAuthor(String functionAuthor) {
        this.functionAuthor = functionAuthor == null ? null : functionAuthor.trim();
    }

    public String getGenBaseDir() {
        return genBaseDir;
    }

    public void setGenBaseDir(String genBaseDir) {
        this.genBaseDir = genBaseDir == null ? null : genBaseDir.trim();
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options == null ? null : options.trim();
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