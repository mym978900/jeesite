package com.jeesite.modules.clue.web;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.shiro.realm.LoginInfo;
import com.jeesite.common.shiro.session.SessionManager;
import com.jeesite.common.web.http.ServletUtils;
import com.jeesite.modules.clue.entity.UpClue;
import com.jeesite.modules.clue.service.UpClueService;
import com.jeesite.modules.config.MySessionManager;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/clue")
public class UpClueController{
	
	@Autowired
	private UpClueService iUpClueService;
	
	
	//用户新增单条线索 by xf 2019.12.04
	@RequestMapping(value="addUpClue", method = RequestMethod.POST)
	@ResponseBody
	public String addUpClue(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		System.out.println("收到了前端请求");
		
		String name = WebUtils.getCleanParam(request,"name");
		String tel = WebUtils.getCleanParam(request,"tel");
		String sex = WebUtils.getCleanParam(request,"sex");
		String age = WebUtils.getCleanParam(request,"age");
		String site = WebUtils.getCleanParam(request,"site");
		
		if(StringUtils.isBlank(name)) {
			model.addAttribute("result", Global.FALSE);
			model.addAttribute("resdata","用户姓名为空，保存失败！请填写后保存");
			return ServletUtils.renderObject(response, model);
			
		}
		if(StringUtils.isBlank(tel)) {
			model.addAttribute("result", Global.FALSE);
			return "用户电话为空，保存失败！请填写后保存";
		}
		if(StringUtils.isBlank(sex)) {
			model.addAttribute("result", Global.FALSE);
			return "用户性别为空，保存失败！请填写后保存";
		}
		if(StringUtils.isBlank(age)) {
			model.addAttribute("result", Global.FALSE);
			return "用户年龄为空，保存失败！请填写后保存";
		}
		if(StringUtils.isBlank(site)) {
			model.addAttribute("result", Global.FALSE);
			return "用户地址为空，保存失败！请填写后保存";
		}
		
		UpClue uc = new UpClue();
		
		String upClueCode = UUID.randomUUID().toString().replace("-", "");
		
		uc.setUpClueCode(upClueCode);
		uc.setUpClueName(name);
		uc.setUpClueTel(tel);
		uc.setUpClueSex(sex);
		uc.setUpClueAge(Integer.parseInt(age));
		uc.setUpClueTime(new Date());
		uc.setUpClueAddre(site);
		User user = UserUtils.getUser();
//		uc.setUpUserCode();
		iUpClueService.addUpClue(uc);
		
		model.addAttribute("result", Global.TRUE);
		
		return ServletUtils.renderObject(response, model);
	}
	
	//用户上传线索列表
	@RequestMapping(value = "listData")
	@ResponseBody
	public List<UpClue> listData(UpClue upClue) {
		List<UpClue> list = iUpClueService.getUpClueList(upClue);
		return list;
	}
	
}
