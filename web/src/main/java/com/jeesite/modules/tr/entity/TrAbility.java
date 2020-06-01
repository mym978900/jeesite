package com.jeesite.modules.tr.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TrAbility {
    private String trId;

    private String trType;

    private Integer trCount;

    private Double trUnitprice;

    private Date trBegindate;

    private Date trEnddate;

    private Integer trBegintime;

    private Integer trEndtime;

    private String trAddress;

    private Double trLongitude;

    private Double trLatitude;

    private String trIseffective;

    private String trRemark;

    private String trUsercode;

    private String trCardusername;

    private String trCardnum;

    private String trBankname;

    private String extra1;

    private String extra2;

    private String extra3;

    private String extra4;

    private String extra5;

    private String trOrgbriefintroduction;

    private String trAddressCity;

    private String trAddressArea;

    private Date trCreatetime;

    private Date trUpdatetime;
    
    private String[] timeArr;
    
    private Boolean result;
    
    private String[] addressArr;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date trAllbegintime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date trAllendtime;
    //机构名称
    private String orgname;
    
    //返回日期
    private String matchdate;
    
    private String matchtime;
    
    //创建订单
    private String TrNeedId;
    
	public String getTrNeedId() {
		return TrNeedId;
	}

	public void setTrNeedId(String trNeedId) {
		TrNeedId = trNeedId;
	}

	public String getMatchdate() {
		return matchdate;
	}

	public void setMatchdate(String matchdate) {
		this.matchdate = matchdate;
	}

	public String getMatchtime() {
		return matchtime;
	}

	public void setMatchtime(String matchtime) {
		this.matchtime = matchtime;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public Date getTrAllbegintime() {
        return trAllbegintime;
    }

    public void setTrAllbegintime(Date trAllbegintime) {
        this.trAllbegintime = trAllbegintime;
    }
    
    public Date getTrAllendtime() {
        return trAllendtime;
    }

    public void setTrAllendtime(Date trAllendtime) {
        this.trAllendtime = trAllendtime;
    }

    public String[] getAddressArr() {
		return addressArr;
	}

	public void setAddressArr(String[] addressArr) {
		this.addressArr = addressArr;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String[] getTimeArr() {
		return timeArr;
	}

	public void setTimeArr(String[] timeArr) {
		this.timeArr = timeArr;
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

    public Date getTrBegindate() {
        return trBegindate;
    }

    public void setTrBegindate(Date trBegindate) {
        this.trBegindate = trBegindate;
    }

    public Date getTrEnddate() {
        return trEnddate;
    }

    public void setTrEnddate(Date trEnddate) {
        this.trEnddate = trEnddate;
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

    public String getTrCardusername() {
        return trCardusername;
    }

    public void setTrCardusername(String trCardusername) {
        this.trCardusername = trCardusername == null ? null : trCardusername.trim();
    }

    public String getTrCardnum() {
        return trCardnum;
    }

    public void setTrCardnum(String trCardnum) {
        this.trCardnum = trCardnum == null ? null : trCardnum.trim();
    }

    public String getTrBankname() {
        return trBankname;
    }

    public void setTrBankname(String trBankname) {
        this.trBankname = trBankname == null ? null : trBankname.trim();
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

    public String getTrOrgbriefintroduction() {
        return trOrgbriefintroduction;
    }

    public void setTrOrgbriefintroduction(String trOrgbriefintroduction) {
        this.trOrgbriefintroduction = trOrgbriefintroduction == null ? null : trOrgbriefintroduction.trim();
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

}