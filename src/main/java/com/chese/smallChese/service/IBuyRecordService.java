package com.chese.smallChese.service;

import java.util.Map;

public interface IBuyRecordService {

	/**
	 * 查询用户是否已购买音频
	 * @param map {user_id,audio_id}
	 * @return
	 */
	Map<String,Object> isBought(Map<String,Object> map);
	
	/**
	 * 新增用户购买的音频记录
	 * @param map
	 */
	void addBuyRecord(Map<String,Object> map);
	
	
	
}
