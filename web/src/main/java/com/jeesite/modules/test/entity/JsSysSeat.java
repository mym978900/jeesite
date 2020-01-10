package com.jeesite.modules.test.entity;

public class JsSysSeat {
    private String serialNumber;

    private String seatTitle;

    private String seatPrice;

    private String restriction;

    private String reserve1;

    private String reserve2;

    private String reserve3;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public String getSeatTitle() {
        return seatTitle;
    }

    public void setSeatTitle(String seatTitle) {
        this.seatTitle = seatTitle == null ? null : seatTitle.trim();
    }

    public String getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(String seatPrice) {
        this.seatPrice = seatPrice == null ? null : seatPrice.trim();
    }

    public String getRestriction() {
        return restriction;
    }

    public void setRestriction(String restriction) {
        this.restriction = restriction == null ? null : restriction.trim();
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