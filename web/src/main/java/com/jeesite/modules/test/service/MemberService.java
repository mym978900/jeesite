package com.jeesite.modules.test.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.JsSysOffice;
import com.jeesite.modules.test.entity.JsSysOrder;
import com.jeesite.modules.test.vo.FlowingWaterVo;

public interface MemberService {
	//获取机构
	JsSysOffice getOffice(User user);

	int updateOffice(JsSysOffice office);
	
	//获取需要匹配线索的会员
	//xf
	//2019.12.11
	List<JsSysMember> getClueMatchUser();
	
	//获取机构品类
	//xf
	//2019.12.12
	String getDeptType(String loginCode);
	
	//更新初次匹配时间
	//xf
	//2019.12.13
	void updateOnedate(Date date,String userCode);
	
	//更新用户最新匹配批次
	//xf
	//2019.12.13
	void updateAiTimes(String userCode, int times);

	//获取未标注经纬度的会员
	//xf
	//2019.12.13
	List<JsSysMember> getNoConfigAddress();

	void updateByPrimaryKey(JsSysMember jsm);

	JsSysMember getMemberByAccountCode(String code);

	List<JsSysOrder> findOrderByLimit(FlowingWaterVo vo);

	BigDecimal selectMoneyByTime(FlowingWaterVo vo);

	Integer toGetMessage(HttpServletRequest request, String phone);
}
