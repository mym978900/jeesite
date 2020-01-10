package com.jeesite.modules.test.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.vo.UpdatePhoneVo;

public interface TestMessageService {

	Integer toGetMessage(HttpServletRequest request,String phone);

	Integer toUpdatePhone(HttpServletResponse response,Model model,UpdatePhoneVo vo);

	Integer toUpdatePass(HttpServletResponse response, Model model, UpdatePhoneVo vo);

	Integer checkUserIsOld(HttpServletResponse response, Model model);

	JsSysMember getMemberByLoginCode(HttpServletResponse response, Model model);
}
