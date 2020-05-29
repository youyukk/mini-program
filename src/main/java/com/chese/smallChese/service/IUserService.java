package com.chese.smallChese.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface IUserService {

	/**
	 * 根据sessionId查询openId
	 * @param sessionId
	 * @return
	 */
	String selectOpenIdBySessionId(String sessionId);
	
	/**
	 * 新增用户
	 * @param map
	 */
	void addUser(@Param("map") Map<String,Object> map);
	
	/**
	 * 根据openID查询用户信息
	 * @param openId
	 * @return
	 */
	Map<String,Object> selectUserByOpenId(@Param("openId") String openId);
	
	/**
	 * 更改session_key和session_id
	 * @param map
	 */
	void updateSession(Map<String,Object> map);
	
	/**
	 * 根据openID查领取免费虚拟币的状态值
	 * @param openId
	 * @return
	 */
	int selectFreeCoinByOpenId(String openId);
	
	/**
	 * 根据openID查领取答题虚拟币的状态值
	 * @param openId
	 * @return
	 */
	int selectQuestionCoinByOpenId(String openId);
	
	/**
	 * 更近openID更新用户虚拟币的总数
	 * @param openId
	 */
	void updateCoinCountByOpenId(String openId);
	
	/**
	 * 将获取免费虚拟币的状态值改为1
	 * @param openId
	 */
	void updateFreeCoinByOpenId(String openId);
	
	/**
	 * 将答题获取虚拟币的状态值改为1
	 * @param openId
	 */
	void updateQuestionByOpenId(String openId);
	
	/**
	 * 新增用户答题的信息
	 * @param map
	 */
	void addUserQuestion(Map<String,Object> map);
	
	/**
	 * 查询用户虚拟币的总数
	 * @param openId
	 * @return
	 */
	int selectCoinCount(String openId);
	
	/**
	 * 减去用户芝士币数量
	 * @param userId
	 */
	void reduceCoinByOpenId(String openId);
	
	/**
	 * 清除所有用户免费币的数量
	 */
	void clearFreeCoin();
	
	/**
	 * 清除所有用户问题币的数量
	 */
	void clearQuestionCoin();
	
	/**
	 * 检查是否存在sessionId
	 * @param sessionId
	 * @return
	 */
	boolean checkSessionId(String sessionId);
}
