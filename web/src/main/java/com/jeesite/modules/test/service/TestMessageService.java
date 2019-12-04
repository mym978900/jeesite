package com.jeesite.modules.test.service;

import com.jeesite.modules.test.vo.UpdatePhoneVo;

public interface TestMessageService {

	String toGetMessage(String phone);

	String toUpdatePhone(UpdatePhoneVo vo);
}
