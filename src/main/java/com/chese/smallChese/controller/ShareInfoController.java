package com.chese.smallChese.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chese.smallChese.service.IShareInfoService;
import com.chese.smallChese.service.IUserService;
import com.chese.smallChese.utils.FormatTimeUtil;

@Controller
@RequestMapping("/shareInfo/")
public class ShareInfoController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IShareInfoService shareInfoService;
	
	@RequestMapping("addShareInfo")
	@ResponseBody
	public int addShareInfo(String openId,Integer audioId){
		System.out.println("addShareInfo is be used");
		if(openId != null && openId != ""){
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", openId);
			map.put("audioId", audioId);
			map.put("creatTime", FormatTimeUtil.getTimestamp());
			userService.updateCoinCountByOpenId(openId);
			shareInfoService.addShareInfo(map);
			System.out.println("share succussful");
			return 1;
		}
		
		return 0;
	}
	
	
}
