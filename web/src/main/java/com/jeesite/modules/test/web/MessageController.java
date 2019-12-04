package com.jeesite.modules.test.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.modules.test.service.TestMessageService;
import com.jeesite.modules.test.vo.UpdatePhoneVo;

@Controller
@RequestMapping(value = "${adminPath}/sms/tosms")
public class MessageController {

	@Autowired
	private TestMessageService messageService;
	
	//发送短信验证码
	@RequestMapping(value = "getmeg")
	@ResponseBody
	public String getMessage(@RequestParam("phone")String phone) {
		
		return messageService.toGetMessage(phone);
	}
	
	//验证message
	@RequestMapping(value = "checkmeg")
	@ResponseBody
	public Object register(HttpServletRequest request,@RequestParam("megnum")String megnum,
			@RequestBody UpdatePhoneVo vo
			) {
	        JSONObject json = (JSONObject)request.getSession().getAttribute("password");
	        if(!json.getString("password").equals(megnum)){
	            return "验证码错误";
	        }
	        if((System.currentTimeMillis() - json.getLong("createTime")) > 1000 * 60 * 2){
	            return "验证码过期";
	        }
	        //将用户信息存入数据库
	        //这里省略
	        
	        return messageService.toUpdatePhone(vo);
	    }
}
