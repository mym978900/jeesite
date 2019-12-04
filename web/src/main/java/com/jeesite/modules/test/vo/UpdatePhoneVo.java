package com.jeesite.modules.test.vo;

import java.io.Serializable;

public class UpdatePhoneVo implements Serializable{

	private String newphone;
	private String password;
	public String getNewphone() {
		return newphone;
	}
	public void setNewphone(String newphone) {
		this.newphone = newphone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
