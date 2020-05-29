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
	public int addShareInfo(String sessionId,Integer audioId){
		System.out.println("addShareInfo is be used");
		if(sessionId != null && sessionId != ""){
			
			/**根据sessionId查询openId*/
			String openId = userService.selectOpenIdBySessionId(sessionId);
			if(openId != null && openId != ""){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("openId", openId);
				map.put("audioId", audioId);
				map.put("creatTime", FormatTimeUtil.getTimestamp());
				userService.updateCoinCountByOpenId(openId);
				shareInfoService.addShareInfo(map);
				System.out.println("share succussful");
				return 1;				
			}else{
				System.out.println("sessionId : " + sessionId + "不存在或已过期，请重新申请");
				return 0;
			}
			
		}else{
			System.out.println("sessionId 为空，请检查");
			return 0;
		}
	}
	
	
}
