package com.jeesite.modules.test.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.modules.test.service.TestMessageService;
import com.jeesite.modules.test.vo.UpdatePhoneVo;

@Controller
@RequestMapping(value = "${adminPath}/tosms")
public class MessageController {

	@Autowired
	private TestMessageService messageService;

	// 发送短信验证码
	@RequestMapping(value = "getmeg")
	@ResponseBody
	public Integer getMessage(HttpServletRequest request,String phone) {

		return messageService.toGetMessage(request,phone);
	}

	// 验证message
	@RequestMapping(value = "checkmeg")
	@ResponseBody
	public Integer register(HttpServletRequest request, UpdatePhoneVo vo, HttpServletResponse response, Model model) {
		JSONObject json = (JSONObject) request.getSession().getAttribute("password");
		if (!json.getString("password").equals(vo.getMegNum())) {
			return 1;// 验证码错误
		}
		if ((System.currentTimeMillis() - json.getLong("createTime")) > 1000 * 60 * 2) {
			return 2;// 验证码超时
		}
		// 将用户信息存入数据库
		// 这里省略

		return messageService.toUpdatePhone(response, model, vo);
	}

	// 修改密码
	@RequestMapping(value = "updatePass")
	@ResponseBody
	public Integer updatePass(UpdatePhoneVo vo, HttpServletResponse response, Model model) {

		return messageService.toUpdatePass(response, model, vo);
	}

}
