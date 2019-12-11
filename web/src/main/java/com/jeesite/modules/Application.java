/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.jeesite.modules.clue.utils.PropertiesListener;

/**
 * Application
 * @author ThinkGem
 * @version 2018-10-13
 */
@MapperScan(basePackages = "com.jeesite.modules.**.mapper")
@ComponentScan("com.jeesite")
@SpringBootApplication
@EnableScheduling
public class Application extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Application.class);
        // 第四种方式：注册监听器
        application.addListeners(new PropertiesListener("config/sysconfig.properties"));
        application.run(args);
//		SpringApplication.run(Application.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		this.setRegisterErrorPageFilter(false); // 閿欒椤甸潰鏈夊鍣ㄦ潵澶勭悊锛岃�屼笉鏄疭pringBoot
		return builder.sources(Application.class);
	}
	
}