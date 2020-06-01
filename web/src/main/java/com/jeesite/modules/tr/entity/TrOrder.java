package com.jeesite.modules.tr.entity;

import java.util.Date;

public class TrOrder {
    private String trCommonditycode;

    private String trPartaname;

    private String trPartaphone;

    private String trPartaaddress;

    private String trPartbname;

    private String trPartbphone;

    private String trPartbaddress;

    private String trType;

    private Integer trCount;

    private Date trNeedbegindate;

    private Date trNeedenddate;

    private Integer trBegintime;

    private Integer trEndtime;

    private String trNeeddatelist;

    private String trNeedtimelist;

    private Double trDuration;

    private Double trUnitprice;

    private Double trTotalprice;

    private String trRemark;

    private String reserveone;

    private String reservetwo;
    
    private Boolean result;
    
    private NeedTime[] needTime;
    
    private String productTitle;
    
    //乙方用户编号
    private String partBCode;
    
    //需求id
    private String needId;
    
    public String getNeedId() {
		return needId;
	}

	public void setNeedId(String needId) {
		this.needId = needId;
	}

	public String getPartBCode() {
		return partBCode;
	}

	public void setPartBCode(String partBCode) {
		this.partBCode = partBCode;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public NeedTime[] getNeedTime() {
		return needTime;
	}

	public void setNeedTime(NeedTime[] needTime) {
		this.needTime = needTime;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getTrCommonditycode() {
        return trCommonditycode;
    }

    public void setTrCommonditycode(String trCommonditycode) {
        this.trCommonditycode = trCommonditycode == null ? null : trCommonditycode.trim();
    }

    public String getTrPartaname() {
        return trPartaname;
    }

    public void setTrPartaname(String trPartaname) {
        this.trPartaname = trPartaname == null ? null : trPartaname.trim();
    }

    public String getTrPartaphone() {
        return trPartaphone;
    }

    public void setTrPartaphone(String trPartaphone) {
        this.trPartaphone = trPartaphone == null ? null : trPartaphone.trim();
    }

    public String getTrPartaaddress() {
        return trPartaaddress;
    }

    public void setTrPartaaddress(String trPartaaddress) {
        this.trPartaaddress = trPartaaddress == null ? null : trPartaaddress.trim();
    }

    public String getTrPartbname() {
        return trPartbname;
    }

    public void setTrPartbname(String trPartbname) {
        this.trPartbname = trPartbname == null ? null : trPartbname.trim();
    }

    public String getTrPartbphone() {
        return trPartbphone;
    }

    public void setTrPartbphone(String trPartbphone) {
        this.trPartbphone = trPartbphone == null ? null : trPartbphone.trim();
    }

    public String getTrPartbaddress() {
        return trPartbaddress;
    }

    public void setTrPartbaddress(String trPartbaddress) {
        this.trPartbaddress = trPartbaddress == null ? null : trPartbaddress.trim();
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

    public Double getTrDuration() {
        return trDuration;
    }

    public void setTrDuration(Double trDuration) {
        this.trDuration = trDuration;
    }

    public Double getTrUnitprice() {
        return trUnitprice;
    }

    public void setTrUnitprice(Double trUnitprice) {
        this.trUnitprice = trUnitprice;
    }

    public Double getTrTotalprice() {
        return trTotalprice;
    }

    public void setTrTotalprice(Double trTotalprice) {
        this.trTotalprice = trTotalprice;
    }

    public String getTrRemark() {
        return trRemark;
    }

    public void setTrRemark(String trRemark) {
        this.trRemark = trRemark == null ? null : trRemark.trim();
    }

    public String getReserveone() {
        return reserveone;
    }

    public void setReserveone(String reserveone) {
        this.reserveone = reserveone == null ? null : reserveone.trim();
    }

    public String getReservetwo() {
        return reservetwo;
    }

    public void setReservetwo(String reservetwo) {
        this.reservetwo = reservetwo == null ? null : reservetwo.trim();
    }
}