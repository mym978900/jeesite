package com.jeesite.modules.test.entity;

public class JsSysSetmeal {
    private String serialNumber;

    private String setMealTitle;

    private String setMealContent;

    private Integer halfPrice;

    private Integer wholePrice;

    private String reserve1;

    private String reserve2;

    private String reserve3;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public String getSetMealTitle() {
        return setMealTitle;
    }

    public void setSetMealTitle(String setMealTitle) {
        this.setMealTitle = setMealTitle == null ? null : setMealTitle.trim();
    }

    public String getSetMealContent() {
        return setMealContent;
    }

    public void setSetMealContent(String setMealContent) {
        this.setMealContent = setMealContent == null ? null : setMealContent.trim();
    }

    public Integer getHalfPrice() {
        return halfPrice;
    }

    public void setHalfPrice(Integer halfPrice) {
        this.halfPrice = halfPrice;
    }

    public Integer getWholePrice() {
        return wholePrice;
    }

    public void setWholePrice(Integer wholePrice) {
        this.wholePrice = wholePrice;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1 == null ? null : reserve1.trim();
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2 == null ? null : reserve2.trim();
    }

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3 == null ? null : reserve3.trim();
    }
}