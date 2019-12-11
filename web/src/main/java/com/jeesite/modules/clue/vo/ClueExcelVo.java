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
	
}
