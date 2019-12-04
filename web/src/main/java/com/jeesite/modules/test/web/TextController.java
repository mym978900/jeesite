package com.jeesite.modules.test.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "${adminPath}/redis")
public class TextController {
	/*
	 * @Autowired private RedisTemplate<String, Object> jedis;
	 * 
	 * @RequestMapping(value = "test") public String getOfficeByUser() {
	 * 
	 * jedis.opsForValue().set("mm", "李白"); return (String)
	 * jedis.opsForValue().get("mm");
	 * 
	 * }
	 */
}
