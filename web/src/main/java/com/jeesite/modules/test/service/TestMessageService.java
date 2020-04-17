package com.jeesite.modules.test.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.jeesite.modules.test.entity.JsSysApply;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.JsSysUser;
import com.jeesite.modules.test.vo.GetUserVo;
import com.jeesite.modules.test.vo.UpdatePhoneVo;

public interface TestMessageService {

	Integer toGetMessage(HttpServletRequest request,HttpServletResponse response,String phone);

	Integer toUpdatePhone(HttpServletResponse response,Model model,UpdatePhoneVo vo);

	Integer toUpdatePass(HttpServletResponse response, Model model, UpdatePhoneVo vo);

	Integer checkUserIsOld(HttpServletResponse response, Model model);

	JsSysMember getMemberByLoginCode(HttpServletResponse response, Model model);

	Integer toUpdatePassByLogin(GetUserVo vo, UpdatePhoneVo vo2);

	void toGetMessageByApply(HttpServletRequest request, JsSysApply apply, String string);

	Integer toUpdatePassByForget(GetUserVo userVo, UpdatePhoneVo vo);

	JsSysUser findUserByLoginCode(String newphone);
}
