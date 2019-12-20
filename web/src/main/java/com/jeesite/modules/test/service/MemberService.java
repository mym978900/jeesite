package com.jeesite.modules.test.service;

import java.math.BigDecimal;
import java.util.List;

import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.test.entity.JsSysOffice;
import com.jeesite.modules.test.entity.JsSysOrder;
import com.jeesite.modules.test.entity.JsSysUser;
import com.jeesite.modules.test.vo.FlowingWaterVo;

public interface MemberService {
	//获取机构
	JsSysOffice getOffice(User user);
  
	int updateOffice(JsSysOffice office);

	List<JsSysOrder> findOrderByLimit(FlowingWaterVo vo);

	BigDecimal selectMoneyByTime(FlowingWaterVo vo);
}
