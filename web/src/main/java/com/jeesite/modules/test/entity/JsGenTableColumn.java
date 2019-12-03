package com.jeesite.modules.test.entity;

public class JsGenTableColumn {
    private String id;

    private String tableName;

    private String columnName;

    private Long columnSort;

    private String columnType;

    private String columnLabel;

    private String comments;

    private String attrName;

    private String attrType;

    private String isPk;

    private String isNull;

    private String isInsert;

    private String isUpdate;

    private String isList;

    private String isQuery;

    private String queryType;

    private String isEdit;

    private String showType;

    private String options;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public Long getColumnSort() {
        return columnSort;
    }

    public void setColumnSort(Long columnSort) {
        this.columnSort = columnSort;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType == null ? null : columnType.trim();
    }

    public String getColumnLabel() {
        return columnLabel;
    }

    public void setColumnLabel(String columnLabel) {
        this.columnLabel = columnLabel == null ? null : columnLabel.trim();
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName == null ? null : attrName.trim();
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType == null ? null : attrType.trim();
    }

    public String getIsPk() {
        return isPk;
    }

    public void setIsPk(String isPk) {
        this.isPk = isPk == null ? null : isPk.trim();
    }

    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull == null ? null : isNull.trim();
    }

    public String getIsInsert() {
        return isInsert;
    }

    public void setIsInsert(String isInsert) {
        this.isInsert = isInsert == null ? null : isInsert.trim();
    }

    public String getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(String isUpdate) {
        this.isUpdate = isUpdate == null ? null : isUpdate.trim();
    }

    public String getIsList() {
        return isList;
    }

    public void setIsList(String isList) {
        this.isList = isList == null ? null : isList.trim();
    }

    public String getIsQuery() {
        return isQuery;
    }

    public void setIsQuery(String isQuery) {
        this.isQuery = isQuery == null ? null : isQuery.trim();
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType == null ? null : queryType.trim();
    }

    public String getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit == null ? null : isEdit.trim();
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType == null ? null : showType.trim();
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options == null ? null : options.trim();
    }
}