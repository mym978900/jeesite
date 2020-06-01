package com.jeesite.modules.tr.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TrNeed {
    private String trId;

    private String trType;

    private Integer trCount;

    private Double trUnitprice;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date trNeedbegindate;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date trNeedenddate;

    private Integer trBegintime;

    private Integer trEndtime;

    private String trAddress;

    private Double trLongitude;

    private Double trLatitude;

    private Double trMinlng;

    private Double trMaxlng;

    private Double trMinlat;

    private Double trMaxlat;

    private String trIseffective;

    private String trRemark;

    private String trUsercode;

    private String extra1;

    private String extra2;

    private String extra3;

    private String extra4;

    private String extra5;

    private String trAddressCity;

    private String trAddressArea;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date trCreatetime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date trUpdatetime;
    
    private Boolean result;
    
    private String trAllneedtime;
    
    private String[] timeArr;
    
    private String[] addressArr;
    
    private Double trTotaltime;
    
    private NeedTime[] needTime;
    
    private String trNeeddatelist;

    private String trNeedtimelist;
    
    private String trNeeddatelistasc;

    private String trNeedtimelistasc;
    
	public String getTrNeeddatelistasc() {
        return trNeeddatelistasc;
    }

    public void setTrNeeddatelistasc(String trNeeddatelistasc) {
        this.trNeeddatelistasc = trNeeddatelistasc == null ? null : trNeeddatelistasc.trim();
    }
    
    public String getTrNeedtimelistasc() {
        return trNeedtimelistasc;
    }

    public void setTrNeedtimelistasc(String trNeedtimelistasc) {
        this.trNeedtimelistasc = trNeedtimelistasc == null ? null : trNeedtimelistasc.trim();
    }
    
    public NeedTime[] getNeedTime() {
		return needTime;
	}

	public void setNeedTime(NeedTime[] needTime) {
		this.needTime = needTime;
	}

	public Double getTrTotaltime() {
        return trTotaltime;
    }

    public void setTrTotaltime(Double trTotaltime) {
        this.trTotaltime = trTotaltime;
    }
    
    public String[] getTimeArr() {
		return timeArr;
	}

	public void setTimeArr(String[] timeArr) {
		this.timeArr = timeArr;
	}

	public String[] getAddressArr() {
		return addressArr;
	}

	public void setAddressArr(String[] addressArr) {
		this.addressArr = addressArr;
	}

	public String getTrAllneedtime() {
        return trAllneedtime;
    }

    public void setTrAllneedtime(String trAllneedtime) {
        this.trAllneedtime = trAllneedtime;
    }

    public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getTrId() {
        return trId;
    }

    public void setTrId(String trId) {
        this.trId = trId == null ? null : trId.trim();
    }

    public String getTrType() {
        return trType;
    }

    public void setTrType(String trType) {
        this.trType = trType == null ? null : trType.trim();
    }

    public Integer getTrCount() {
        return trCount;
    }

    public void setTrCount(Integer trCount) {
        this.trCount = trCount;
    }

    public Double getTrUnitprice() {
        return trUnitprice;
    }

    public void setTrUnitprice(Double trUnitprice) {
        this.trUnitprice = trUnitprice;
    }

    public Date getTrNeedbegindate() {
        return trNeedbegindate;
    }

    public void setTrNeedbegindate(Date trNeedbegindate) {
        this.trNeedbegindate = trNeedbegindate;
    }

    public Date getTrNeedenddate() {
        return trNeedenddate;
    }

    public void setTrNeedenddate(Date trNeedenddate) {
        this.trNeedenddate = trNeedenddate;
    }

    public Integer getTrBegintime() {
        return trBegintime;
    }

    public void setTrBegintime(Integer trBegintime) {
        this.trBegintime = trBegintime;
    }

    public Integer getTrEndtime() {
        return trEndtime;
    }

    public void setTrEndtime(Integer trEndtime) {
        this.trEndtime = trEndtime;
    }

    public String getTrAddress() {
        return trAddress;
    }

    public void setTrAddress(String trAddress) {
        this.trAddress = trAddress == null ? null : trAddress.trim();
    }

    public Double getTrLongitude() {
        return trLongitude;
    }

    public void setTrLongitude(Double trLongitude) {
        this.trLongitude = trLongitude;
    }

    public Double getTrLatitude() {
        return trLatitude;
    }

    public void setTrLatitude(Double trLatitude) {
        this.trLatitude = trLatitude;
    }

    public Double getTrMinlng() {
        return trMinlng;
    }

    public void setTrMinlng(Double trMinlng) {
        this.trMinlng = trMinlng;
    }

    public Double getTrMaxlng() {
        return trMaxlng;
    }

    public void setTrMaxlng(Double trMaxlng) {
        this.trMaxlng = trMaxlng;
    }

    public Double getTrMinlat() {
        return trMinlat;
    }

    public void setTrMinlat(Double trMinlat) {
        this.trMinlat = trMinlat;
    }

    public Double getTrMaxlat() {
        return trMaxlat;
    }

    public void setTrMaxlat(Double trMaxlat) {
        this.trMaxlat = trMaxlat;
    }

    public String getTrIseffective() {
        return trIseffective;
    }

    public void setTrIseffective(String trIseffective) {
        this.trIseffective = trIseffective == null ? null : trIseffective.trim();
    }

    public String getTrRemark() {
        return trRemark;
    }

    public void setTrRemark(String trRemark) {
        this.trRemark = trRemark == null ? null : trRemark.trim();
    }

    public String getTrUsercode() {
        return trUsercode;
    }

    public void setTrUsercode(String trUsercode) {
        this.trUsercode = trUsercode == null ? null : trUsercode.trim();
    }

    public String getExtra1() {
        return extra1;
    }

    public void setExtra1(String extra1) {
        this.extra1 = extra1 == null ? null : extra1.trim();
    }

    public String getExtra2() {
        return extra2;
    }

    public void setExtra2(String extra2) {
        this.extra2 = extra2 == null ? null : extra2.trim();
    }

    public String getExtra3() {
        return extra3;
    }

    public void setExtra3(String extra3) {
        this.extra3 = extra3 == null ? null : extra3.trim();
    }

    public String getExtra4() {
        return extra4;
    }

    public void setExtra4(String extra4) {
        this.extra4 = extra4 == null ? null : extra4.trim();
    }

    public String getExtra5() {
        return extra5;
    }

    public void setExtra5(String extra5) {
        this.extra5 = extra5 == null ? null : extra5.trim();
    }

    public String getTrAddressCity() {
        return trAddressCity;
    }

    public void setTrAddressCity(String trAddressCity) {
        this.trAddressCity = trAddressCity == null ? null : trAddressCity.trim();
    }

    public String getTrAddressArea() {
        return trAddressArea;
    }

    public void setTrAddressArea(String trAddressArea) {
        this.trAddressArea = trAddressArea == null ? null : trAddressArea.trim();
    }

    public Date getTrCreatetime() {
        return trCreatetime;
    }

    public void setTrCreatetime(Date trCreatetime) {
        this.trCreatetime = trCreatetime;
    }

    public Date getTrUpdatetime() {
        return trUpdatetime;
    }

    public void setTrUpdatetime(Date trUpdatetime) {
        this.trUpdatetime = trUpdatetime;
    }
    
    public String getTrNeeddatelist() {
        return trNeeddatelist;
    }

    public void setTrNeeddatelist(String trNeeddatelist) {
        this.trNeeddatelist = trNeeddatelist == null ? null : trNeeddatelist.trim();
    }

    public String getTrNeedtimelist() {
        return trNeedtimelist;
    }

    public void setTrNeedtimelist(String trNeedtimelist) {
        this.trNeedtimelist = trNeedtimelist == null ? null : trNeedtimelist.trim();
    }
    
}

