package com.chese.smallChese.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chese.smallChese.service.IUserService;
import com.chese.smallChese.utils.FormatTimeUtil;

@Controller
@RequestMapping("/coin/")
public class CoinController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping("checkFreeCoin")
	@ResponseBody
	public int checkFreeCoin(String sessionId){
		System.out.println("调用checkFreeCoin,sessionId = " + sessionId);
		String openId = userService.selectOpenIdBySessionId(sessionId);
		int freeCoin = 0;
		
		if(openId != null && openId != ""){
			freeCoin = userService.selectFreeCoinByOpenId(openId);
			System.out.println("freeCoin:" + freeCoin);
			
		}else{
			System.out.println("sessionId : " + sessionId + "已过期，请重新申请");			
		}
		
		return freeCoin;
	}
	
	
	@RequestMapping("getFreeCoin")
	@ResponseBody
	public int getFreeCoin(String sessionId){
		System.out.println("调用getFreeCoin");
		String openId = userService.selectOpenIdBySessionId(sessionId);
		if(openId != null && openId != ""){
			try {
				/**更改免费币的状态值*/
				userService.updateFreeCoinByOpenId(openId);
				/**更改虚拟币总值*/
				userService.updateCoinCountByOpenId(openId);
				
				return 1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("sessionId : " + sessionId + "已过期，请重新申请");						
		}
		
		return 0;
	}
	
	/**
	 * 检查是否有答题
	 * @param openId
	 * @return 返回 0 表示没有，返回 1 表示有
	 */
	@RequestMapping("checkQuestionCoin")
	@ResponseBody
	public int checkQuestionCoin(String sessionId){
		System.out.println("调用checkQuestionCoin");
		String openId = userService.selectOpenIdBySessionId(sessionId);
		int questionCoin = 0;
		if(openId != null && openId != ""){
			questionCoin = userService.selectQuestionCoinByOpenId(sessionId);			
		}else{
			System.out.println("sessionId : " + sessionId + "已过期，请重新申请");			
		}
		
		return questionCoin;
	}

	@RequestMapping("getQuestionCoin")
	@ResponseBody
	public int getQuestionCoin(String sessionId,int questionId,int answer){
		System.out.println("调用getQuestionCoin");

		try {
			
			/**新增用户回答的问题及答案*/
			Map<String,Object> paramMap = new HashMap<String,Object>();
			String openId = userService.selectOpenIdBySessionId(sessionId);
			if(openId != null && openId != ""){
				
				/**修改问题币的状态值*/
				userService.updateQuestionByOpenId(openId);
				/**修改虚拟币的总值*/
				userService.updateCoinCountByOpenId(openId);
				
				paramMap.put("openId",openId);
				paramMap.put("questionId",questionId);
				paramMap.put("answer",answer);
				String createTime = FormatTimeUtil.getTimestamp();
				paramMap.put("createTime", createTime);
				userService.addUserQuestion(paramMap);
				
				return 1;				
			}else{
				System.out.println("sessionId : " + sessionId + "已过期，请重新申请");
				return 0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return 0;
		}
	}
	
	@RequestMapping("getCoinCount")
	@ResponseBody
	public int getCoinCount(String sessionId){
		System.out.println("调用getCoinCount");
		
		String openId = userService.selectOpenIdBySessionId(sessionId);
		int coinCount = 0;
		if(openId != null && openId != ""){
			coinCount = userService.selectCoinCount(openId);			
		}else{
			System.out.println("sessionId : " + sessionId + "已过期，请重新申请");
		}
		
		return coinCount;
	}
	
	@RequestMapping("reduceCoinCount")
	@ResponseBody
	public int reduceCoinCount(String sessionId){
		
		String openId = userService.selectOpenIdBySessionId(sessionId);
		if(openId != null && openId != ""){
			
			userService.reduceCoinByOpenId(openId);
			
		}else{
			System.out.println("sessionId : " + sessionId + "已过期，请重新申请");
		}
		
		return 1;
	}
	
	
}
