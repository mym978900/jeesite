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
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.JsSysUser;
import com.jeesite.modules.test.service.TestMessageService;
import com.jeesite.modules.test.util.DailyUtil;
import com.jeesite.modules.test.vo.GetUserVo;
import com.jeesite.modules.test.vo.UpdatePhoneVo;

@Controller
@RequestMapping(value = "${adminPath}/tosms")
public class MessageController {

	@Autowired
	private TestMessageService messageService;

	// 发送短信验证码
	@RequestMapping(value = "getmeg")
	@ResponseBody
	public Integer getMessage(HttpServletRequest request, String phone) {

		return messageService.toGetMessage(request, phone);
	}

	// 验证message
	@RequestMapping(value = "checkmeg")
	@ResponseBody
	public Integer register(HttpServletRequest request, UpdatePhoneVo vo, HttpServletResponse response, Model model) {
		JSONObject json = (JSONObject) request.getSession().getAttribute(vo.getNewphone());
		if (!json.getString(vo.getNewphone()).equals(vo.getMegNum())) {
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

	// 新用户登录后修改密码
	@RequestMapping(value = "updatep")
	@ResponseBody
	public Integer toRegister(HttpServletRequest request, UpdatePhoneVo vo, HttpServletResponse response, Model model) {
		GetUserVo userVo = DailyUtil.getLoginUser(response, model);
		if (!vo.getNewphone().equals(userVo.getUser().getLoginCode())) {
			return 0;// 手机号（账号）错误
		}
		JSONObject json = (JSONObject) request.getSession().getAttribute(vo.getNewphone());
		if (!json.getString(vo.getNewphone()).equals(vo.getMegNum())) {
			return 1;// 验证码错误
		}
		if ((System.currentTimeMillis() - json.getLong("createTime")) > 1000 * 60 * 2) {
			return 2;// 验证码超时
		}
		// 将用户信息存入数据库
		// 这里省略

		return messageService.toUpdatePassByLogin(userVo, vo);
	}

	// 忘记密码
	@RequestMapping(value = "forgetp")
	@ResponseBody
	public Integer forgetPass(HttpServletRequest request, UpdatePhoneVo vo, HttpServletResponse response, Model model) {
		JsSysUser jsSysUser=messageService.findUserByLoginCode(vo.getNewphone());
		    if (jsSysUser==null||"".equals(jsSysUser)) {
		    	return 0;// 手机号（账号）错误
		    }
		    User user=new User();
		    user.setUserCode(jsSysUser.getUserCode());
		    GetUserVo userVo = new GetUserVo(null, user);
		JSONObject json = (JSONObject) request.getSession().getAttribute(vo.getNewphone());
		if (!json.getString(vo.getNewphone()).equals(vo.getMegNum())) {
			return 1;// 验证码错误
		}
		if ((System.currentTimeMillis() - json.getLong("createTime")) > 1000 * 60 * 2) {
			return 2;// 验证码超时
		}
		// 将用户信息存入数据库
		// 这里省略

		return messageService.toUpdatePassByForget(userVo, vo);
	}

	/**
	 * @author 验证登录用户是否是新用户
	 *
	 */
	@RequestMapping(value = "checkUser")
	@ResponseBody
	public Integer checkUserIsOld(HttpServletResponse response, Model model) {

		return messageService.checkUserIsOld(response, model);
	}

	/**
	 * @author 验证登录用户是否是新用户
	 *
	 */
	@RequestMapping(value = "getMem")
	@ResponseBody
	public JsSysMember getMemberByLoginCode(HttpServletResponse response, Model model) {

		return messageService.getMemberByLoginCode(response, model);
	}
}
