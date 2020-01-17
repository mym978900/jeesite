package com.jeesite.modules.clue.mapper;

public interface StatisticsMapper {

	public int loginOrganDialStatistics(String userCode,String callInstanceStatus);

	public int loginOrganRecivedCount(String userCode);

	public int loginOrganConnectRate(String userCode, String finishStatus);

	public int loginOrganTotalCallTime(String userCode, int day);

	public int loginOrganAICallCustomCount(String userCode, int day);

	public int loginOrganTotalIntentionCount(String userCode, int day);

	public int loginOrganAnswerdCount(String userCode, int day);

	public double loginOrganUsingPhoneBill(String userCode, int day);

}
