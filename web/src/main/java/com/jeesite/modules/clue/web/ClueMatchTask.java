package com.jeesite.modules.clue.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClueMatchTask {
	
	private static final Logger logger = LoggerFactory.getLogger(ClueMatchTask.class);
	
	@Scheduled(cron="0 0 0 * * ?")
	public void tesk() {
		
	}

}
