package com.jeesite.modules.test.entity;

import java.util.Date;

public class JsFilemanagerFolder {
    private String id;

    private String parentCode;

    private String parentCodes;

    private Long treeSort;

    private String treeSorts;

    private String treeLeaf;

    private Short treeLevel;

    private String treeNames;

    private String folderName;

    private String groupType;

    private String officeCode;

    private String status;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String remarks;

    private String corpCode;

    private String corpName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    public String getParentCodes() {
        return parentCodes;
    }

    public void setParentCodes(String parentCodes) {
        this.parentCodes = parentCodes == null ? null : parentCodes.trim();
    }

    public Long getTreeSort() {
        return treeSort;
    }

    public void setTreeSort(Long treeSort) {
        this.treeSort = treeSort;
    }

    public String getTreeSorts() {
        return treeSorts;
    }

    public void setTreeSorts(String treeSorts) {
        this.treeSorts = treeSorts == null ? null : treeSorts.trim();
    }

    public String getTreeLeaf() {
        return treeLeaf;
    }

    public void setTreeLeaf(String treeLeaf) {
        this.treeLeaf = treeLeaf == null ? null : treeLeaf.trim();
    }

    public Short getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(Short treeLevel) {
        this.treeLevel = treeLevel;
    }

    public String getTreeNames() {
        return treeNames;
    }

    public void setTreeNames(String treeNames) {
        this.treeNames = treeNames == null ? null : treeNames.trim();
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName == null ? null : folderName.trim();
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType == null ? null : groupType.trim();
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode == null ? null : officeCode.trim();
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

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode == null ? null : corpCode.trim();
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName == null ? null : corpName.trim();
    }
}