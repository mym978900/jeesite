package com.jeesite.modules.test.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.modules.test.entity.JsSysOffice;
import com.jeesite.modules.test.entity.JsSysUser;
import com.jeesite.modules.test.service.MemberService;

@Controller
@RequestMapping(value = "${adminPath}/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	// 获取机构信息
	@RequestMapping(value = "list")
	@ResponseBody
	public JsSysOffice getOfficeByUser(HttpServletRequest request) {

		HttpSession session = request.getSession();

		JsSysUser user1 = new JsSysUser();
		user1.setLoginCode("system");
		session.setAttribute("loginUser", user1);

		if (session.getAttribute("loginUser") != null) {
			JsSysUser user = (JsSysUser) session.getAttribute("loginUser");
			return memberService.getOffice(user);
		}

		return null;
	}

	// 编辑机构信息
	@RequestMapping(value = "update")
	@ResponseBody
	public String updateOffice() {

		JsSysOffice office = new JsSysOffice();
		office.setTreeNames("奥力格科技");
		office.setAddress("新华科技大厦A2008");
		office.setOfficeCode("SD");
		int affectNum = memberService.updateOffice(office);
		if (affectNum == 1) {
			return "编辑成功";
		}
		return "失败";

	}

}
