package com.jeesite.modules.test.vo;

import com.jeesite.modules.sys.entity.User;

public class GetUserVo {

	private String information;
	private User user;
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public GetUserVo(String information, User user) {
		super();
		this.information = information;
		this.user = user;
	}
	public GetUserVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
