package com.jeesite.modules.test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeesite.modules.test.service.TestMessageService;

@Controller
@RequestMapping(value = "${adminPath}/sms/tosms")
public class MessageController {

	@Autowired
	private TestMessageService messageService;
	
	@RequestMapping(value = "getmeg")
	public void getMessage(String phone) {
		
		messageService.toGetMessage(phone);
	}	
}
