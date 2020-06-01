package com.jeesite.modules.test.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.modules.tr.entity.TrAbility;
import com.jeesite.modules.tr.entity.TrNeed;
import com.jeesite.modules.tr.entity.TrOrder;

public class VideoOrder {
    private Integer id;

    private String openid;

    private String outTradeNo;

    private Integer state;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date notifyTime;

    private String totalFee;

    private String nickname;

    private String headImg;

    private String videoId;

    private String videoTitle;

    private String videoImg;

    private String userId;

    private String ip;

    private Integer del;
    
    private String reserve1;

    private String reserve2;

    private String reserve3;
    
    private String trPaycommodity;

    private String trPartbusercode;
    
    private TrOrder trOrder;

    private TrAbility trAbility;
    private TrNeed trNeed;
    
    public TrOrder getTrOrder() {
		return trOrder;
	}

	public void setTrOrder(TrOrder trOrder) {
		this.trOrder = trOrder;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee == null ? null : totalFee.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId == null ? null : videoId.trim();
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle == null ? null : videoTitle.trim();
    }

    public String getVideoImg() {
        return videoImg;
    }

    public void setVideoImg(String videoImg) {
        this.videoImg = videoImg == null ? null : videoImg.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();

    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
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
    
    public String getTrPaycommodity() {
        return trPaycommodity;
    }

    public void setTrPaycommodity(String trPaycommodity) {
        this.trPaycommodity = trPaycommodity == null ? null : trPaycommodity.trim();
    }

    public String getTrPartbusercode() {
        return trPartbusercode;
    }

    public void setTrPartbusercode(String trPartbusercode) {
        this.trPartbusercode = trPartbusercode == null ? null : trPartbusercode.trim();
    }
    
    public TrAbility getTrAbility() {
		return trAbility;
	}

	public void setTrAbility(TrAbility trAbility) {
		this.trAbility = trAbility;
	}

	public TrNeed getTrNeed() {
		return trNeed;
	}

	public void setTrNeed(TrNeed trNeed) {
		this.trNeed = trNeed;
	}

	public VideoOrder(String openid, String outTradeNo, Integer state, Date createTime, Date notifyTime,
			String totalFee, String nickname, String headImg, String videoId, String videoTitle, String videoImg,
			String userId, String ip, Integer del) {
		super();
		this.openid = openid;
		this.outTradeNo = outTradeNo;
		this.state = state;
		this.createTime = createTime;
		this.notifyTime = notifyTime;
		this.totalFee = totalFee;
		this.nickname = nickname;
		this.headImg = headImg;
		this.videoId = videoId;
		this.videoTitle = videoTitle;
		this.videoImg = videoImg;
		this.userId = userId;
		this.ip = ip;
		this.del = del;
	}

	public VideoOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VideoOrder(String openid, Integer state, Date notifyTime) {
		super();
		this.openid = openid;
		this.state = state;
		this.notifyTime = notifyTime;
	}

	public VideoOrder(String openid, Integer state, Date notifyTime, String videoTitle) {
		super();
		this.openid = openid;
		this.state = state;
		this.notifyTime = notifyTime;
		this.videoTitle = videoTitle;
	}

	public VideoOrder(String openid, String outTradeNo, Integer state, Date createTime, Date notifyTime,
			String totalFee, String nickname, String headImg, String videoId, String videoTitle, String videoImg,
			String userId, String ip, Integer del, String reserve1, String reserve2, String reserve3,
			String trPaycommodity, String trPartbusercode) {
		super();
		this.openid = openid;
		this.outTradeNo = outTradeNo;
		this.state = state;
		this.createTime = createTime;
		this.notifyTime = notifyTime;
		this.totalFee = totalFee;
		this.nickname = nickname;
		this.headImg = headImg;
		this.videoId = videoId;
		this.videoTitle = videoTitle;
		this.videoImg = videoImg;
		this.userId = userId;
		this.ip = ip;
		this.del = del;
		this.reserve1 = reserve1;
		this.reserve2 = reserve2;
		this.reserve3 = reserve3;
		this.trPaycommodity = trPaycommodity;
		this.trPartbusercode = trPartbusercode;
	}

}