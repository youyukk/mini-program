package com.chese.smallChese.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.chese.smallChese.service.IUserService;

@Component
public class CoinScheduler {

	@Autowired
	private IUserService userService;
	
	@Scheduled(cron = "59 59 23 * * ?")
	public void clearFreeScheduler(){
		
		userService.clearFreeCoin();
	
	}
	
	@Scheduled(cron = "59 59 23 * * ?")
	public void clearQuestionScheduler(){
		
		userService.clearQuestionCoin();
		
	}
	
	
}
