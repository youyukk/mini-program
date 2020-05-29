package com.chese.smallChese.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chese.smallChese.service.IBuyRecordService;
import com.chese.smallChese.service.IUserService;
import com.chese.smallChese.utils.FormatTimeUtil;

@Controller
@RequestMapping("/buyRecord/")
public class BuyRecordController {

	@Autowired
	private IBuyRecordService buyRecordService;
		
	@Autowired
	private IUserService userService;
	
	/**
	 * 
	 * @param userId
	 * @param audioId
	 * @return 返回 0 ：没购买；返回 1 ：已购买
	 */
	@RequestMapping("isBought")
	@ResponseBody
	public int isBought(String sessionId,int audioId) {
		if(sessionId == null || sessionId == ""){
			throw new NullArgumentException(sessionId);
		}
		
		String openId = userService.selectOpenIdBySessionId(sessionId);
		if(openId != null && openId != ""){
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("openId", openId);
			map.put("audioId", audioId);
			
			Integer result = buyRecordService.isBought(map);
			if(result != null){
				//已购买过了
				return 1;
			}else{
				//没购买过
				return 0;
			}
			
		}else{
			System.out.println("sessionId : " + sessionId + "已过期，请重新申请");
			return 0;
		}
		
	}
	
	@RequestMapping("addBuyRecord")
	@ResponseBody
	public int addBuyRecord(String sessionId,int audioId){
		if(sessionId == null){
			throw new NullArgumentException(sessionId);
		}
		
		int result = 0;
		
		String openId = userService.selectOpenIdBySessionId(sessionId);
		if(openId != null && openId != ""){
			
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("openId", openId);
			map.put("audioId", audioId);
			map.put("creatTime", FormatTimeUtil.getTimestamp());
			result = buyRecordService.addBuyRecord(map);
			if(result == 1){
				//减去一个芝士币
				userService.reduceCoinByOpenId(openId);
			}
		}else{
			System.out.println("sessionId : " + sessionId + "已过期");
		}
		
		return result;
	}
}
