package com.chese.smallChese.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chese.smallChese.service.IBuyRecordService;
import com.chese.smallChese.utils.FormatTimeUtil;

@Controller
@RequestMapping("/buyRecord/")
public class BuyRecordController {

	@Autowired
	private IBuyRecordService buyRecordService;
	
	/**
	 * 
	 * @param userId
	 * @param audioId
	 * @return 返回 0 ：没购买；返回 1 ：已购买
	 */
	@RequestMapping("isBought")
	@ResponseBody
	public int isBought(String userId,String audioId) {
		if(userId == null){
			throw new NullArgumentException(userId);
		}else if(audioId == null){
			throw new NullArgumentException(audioId);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("audioId", audioId);
		
		Map<String, Object> result = buyRecordService.isBought(map);
		if(result == null){
			return 0;
		}else{
			return 1;
		}
	}
	
	@RequestMapping("addBuyRecord")
	@ResponseBody
	public int addBuyRecord(String userId,String audioId){
		if(userId == null){
			throw new NullArgumentException(userId);
		}else if(audioId == null){
			throw new NullArgumentException(audioId);
		}
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("audioId", audioId);
		map.put("creatTime", FormatTimeUtil.getTimestamp());
		
		buyRecordService.addBuyRecord(map);
		
		return 1;
	}
}
