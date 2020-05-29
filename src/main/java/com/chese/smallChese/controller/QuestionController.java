package com.chese.smallChese.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chese.smallChese.service.IQuestionService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/question/")
public class QuestionController {
	
	@Autowired
	private IQuestionService questionService;

	@RequestMapping("getQuestion")
	@ResponseBody
	public JSONObject getQuestion(){
		System.out.println("调用getQuestion");
		/**查出没有被用过的问题*/
		Map<String,Object> map = questionService.getQuestion();
		if(map.size() != 0){
			map.put("resultCode", 1);
		}else{
			map.put("resultCode", 0);
		}
		
		JSONObject result = JSONObject.fromObject(map);
		return result;
	}
	
}
