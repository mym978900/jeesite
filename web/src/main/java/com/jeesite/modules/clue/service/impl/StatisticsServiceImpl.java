package com.jeesite.modules.clue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeesite.modules.clue.mapper.StatisticsMapper;
import com.jeesite.modules.clue.service.StatisticsService;

@Service
public class StatisticsServiceImpl implements StatisticsService {

	@Autowired
	private StatisticsMapper iStatisticsMapper;
	
	//统计登陆机构拨打进度
	@Override
	public int loginOrganDialStatistics(String userCode,String callInstanceStatus) {
		int count = 0;
		count = iStatisticsMapper.loginOrganDialStatistics(userCode,callInstanceStatus);
		return count;
	}

	//统计登陆机构接听总量
	@Override
	public int loginOrganRecivedCount(String userCode) {
		int count = 0;
		count = iStatisticsMapper.loginOrganRecivedCount(userCode);
		return count;
	}

	//统计登陆机构接通率
	@Override
	public int loginOrganConnectRate(String userCode,String finishStatus) {
		int count = 0;
		count = iStatisticsMapper.loginOrganConnectRate(userCode,finishStatus);
		return count;
	}

	//统计登陆机构通话总时长
	@Override
	public int loginOrganTotalCallTime(String userCode,int day) {
		int count = 0;
		count = iStatisticsMapper.loginOrganTotalCallTime(userCode,day);
		return count;
	}

	//统计登录机构昨日、累计AI呼叫客户数量
	@Override
	public int loginOrganAICallCustomCount(String userCode, int day) {
		int count = 0;
		count = iStatisticsMapper.loginOrganAICallCustomCount(userCode,day);
		return count;
	}

	//统计登陆机构昨日、累计用户意向用户总量
	@Override
	public int loginOrganTotalIntentionCount(String userCode, int day) {
		int count = 0;
		count = iStatisticsMapper.loginOrganTotalIntentionCount(userCode,day);
		return count;
	}

	//统计登录机构昨日、累计客户接听数量
	@Override
	public int loginOrganAnswerdCount(String userCode, int day) {
		int count = 0;
		count = iStatisticsMapper.loginOrganAnswerdCount(userCode,day);
		return count;
	}

	//统计登录机构通话话费
	@Override
	public double loginOrganUsingPhoneBill(String userCode, int day) {
		double count;
		count = iStatisticsMapper.loginOrganUsingPhoneBill(userCode,day);
		return count;
	}

}
