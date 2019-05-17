package com.chese.smallChese.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.NullArgumentException;
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
	public int addArticle(String userId,String title,String content){
		System.out.println("addArticle is used");
		if(userId == null || userId == ""){
			//程序异常
			return -2;
		}
		
		Map<String, Object> map = userService.selectUserByOpenId(userId);
		if(map == null || map.size() == 0){
			//该用户不存在
			return -1;
		}
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("userId", userId);
		paramMap.put("title", title);
		paramMap.put("content", content);
		paramMap.put("creatTime", FormatTimeUtil.getTimestamp());
		
		try {
			articleService.addArticle(paramMap);
			userService.updateCoinCountByOpenId(userId);
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
}
