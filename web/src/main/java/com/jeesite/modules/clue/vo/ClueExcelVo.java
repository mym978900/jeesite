package com.jeesite.modules.clue.vo;

import lombok.Data;

/**
 * Author: xf
 * Date: 2019-12-10
 * Time: 14:18
 * Description: 读取Excel时，封装读取的每一行的数据
 */
@Data
public class ClueExcelVo {
	
	//姓名
	private String username;
	
	//手机号
	private String phoneNumber;
	
	//年龄
	private int age;
	
	//性别
	private String sex;
	
	//联系地址
	private String address;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
