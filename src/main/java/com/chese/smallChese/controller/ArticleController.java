package com.chese.smallChese.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chese.smallChese.service.IArticleService;
import com.chese.smallChese.service.IUserService;
import com.chese.smallChese.utils.FormatTimeUtil;

@Controller
@RequestMapping("article")
public class ArticleController {

	@Autowired
	private IArticleService articleService;
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("addArticle")
	@ResponseBody
	public int addArticle(String sessionId,String title,String content){
		System.out.println("addArticle is used");
		if(sessionId == null || sessionId == ""){
			//程序异常
			return -2;
		}
		
		String openId = userService.selectOpenIdBySessionId(sessionId);
		if(openId != null && openId != ""){
			Map<String, Object> map = userService.selectUserByOpenId(openId);
			if(map == null || map.size() == 0){
				//该用户不存在
				return -1;
			}	
				
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("openId", openId);
				paramMap.put("title", title);
				paramMap.put("content", content);
				paramMap.put("creatTime", FormatTimeUtil.getTimestamp());
				
				try {
					articleService.addArticle(paramMap);
					userService.updateCoinCountByOpenId(openId);
					return 1;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}
			
		}else{
			System.out.println("sessionId : " + sessionId + "已过期，请重新申请");
			return 0;
		}
		
		
	}
	
}
