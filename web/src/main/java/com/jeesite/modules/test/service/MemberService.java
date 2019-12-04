package com.jeesite.modules.test.service;

import com.jeesite.modules.test.entity.JsSysOffice;
import com.jeesite.modules.test.entity.JsSysUser;

public interface MemberService {
	//获取机构
	JsSysOffice getOffice(JsSysUser user);

	int updateOffice(JsSysOffice office);
}
