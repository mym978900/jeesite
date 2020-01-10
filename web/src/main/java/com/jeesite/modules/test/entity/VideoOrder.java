package com.jeesite.modules.test.entity;

import java.util.Date;

public class VideoOrder {
    private Integer id;

    private String openid;

    private String outTradeNo;

    private Integer state;

    private Date createTime;

    private Date notifyTime;

    private String totalFee;

    private String nickname;

    private String headImg;

    private Integer videoId;

    private String videoTitle;

    private String videoImg;

    private Integer userId;

    private String ip;

    private Integer del;

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
        this.totalFee = totalFee;
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

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

	public VideoOrder(String openid, String outTradeNo, Integer state, Date createTime, Date notifyTime,
			String totalFee, String nickname, String headImg, Integer videoId, String videoTitle, String videoImg,
			Integer userId, String ip, Integer del) {
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

	
    
}