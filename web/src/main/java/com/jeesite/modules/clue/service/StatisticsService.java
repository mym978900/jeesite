package com.jeesite.modules.clue.service;

/**
 * 统计管理Service
 * @author xf
 * @version 2019.01.13
 */
public interface StatisticsService {
	
	//统计登陆机构拨打进度
	public int loginOrganDialStatistics(String userCode,String callInstanceStatus);

	//统计登陆机构接听总量
	public int loginOrganRecivedCount(String userCode);

	//统计登陆机构接通率
	public int loginOrganConnectRate(String userCode, String finishStatus);

	//统计登陆机构通话总时长
	public int loginOrganTotalCallTime(String userCode, int day);

	//统计登录机构昨日、累计AI呼叫客户数量
	public int loginOrganAICallCustomCount(String userCode, int day);

	//统计登陆机构昨日、累计用户意向用户总量
	public int loginOrganTotalIntentionCount(String userCode, int day);

	//统计登录机构昨日、累计客户接听数量
	public int loginOrganAnswerdCount(String userCode, int day);

	//统计登录机构通话话费
	public double loginOrganUsingPhoneBill(String userCode, int day);

}
