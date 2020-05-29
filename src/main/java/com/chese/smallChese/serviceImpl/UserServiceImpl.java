package com.chese.smallChese.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chese.smallChese.dao.BaseDao;
import com.chese.smallChese.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private BaseDao baseDao;
	
	private String nameSpace = "UserMapper";
	
	@Override
	public String selectOpenIdBySessionId(String sessionId){
		
		String openId = null;
		
		try {
			openId = (String)baseDao.selectOne(nameSpace + ".selectOpenIdBySessionId", sessionId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return openId;
	}
	
	@Override
	public void addUser(Map<String,Object> map) {
		
		try {
			baseDao.insert("UserMapper.addUser", map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> selectUserByOpenId(String openId) {

		Map<String,Object> userResult = new HashMap<>();
		try {
			 userResult = (Map<String, Object>)baseDao.selectOne(nameSpace + ".selectUserByOpenId", openId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userResult;
	}

	@Override
	public void updateSession(Map<String,Object> map){
		System.out.println("service执行updateSession");
		try {
			baseDao.update(nameSpace + ".updateSession", map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public int selectFreeCoinByOpenId(String openId){
		
		int freeCoin = 0;
		
		try {
			freeCoin = (int)baseDao.selectOne(nameSpace + ".selectFreeCoinByOpenId",openId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return freeCoin;
		
	}
	
	@Override
	public int selectQuestionCoinByOpenId(String openId){
		int questionCoin = 0;
		
		try {
			questionCoin = (int)baseDao.selectOne(nameSpace + ".SelectQuestionCoinByOpenId", openId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return questionCoin;
	}
	
	@Override
	public void updateCoinCountByOpenId(String openId){
		
		try {
			baseDao.update(nameSpace + ".updateCoinCountByOpenId", openId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void updateFreeCoinByOpenId(String openId){
		try {
			baseDao.update(nameSpace+".updateFreeCoinByOpenId", openId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateQuestionByOpenId(String openId){
		try {
			baseDao.update(nameSpace+".updateQuestionByOpenId", openId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Override
	public void addUserQuestion(Map<String,Object> map){
		try {
			baseDao.insert("UserQuestionMapper.addUserQuestion", map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public int selectCoinCount(String openId){
		int coinCount = 0;
		try {
			Object selectOne = baseDao.selectOne(nameSpace + ".selectCoinCount", openId);
			if(selectOne != null){
				coinCount = (int)selectOne;				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return coinCount;
	}
	
	@Override
	public void reduceCoinByOpenId(String openId){
		try {
			baseDao.update(nameSpace + ".reduceCoinByOpenId", openId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void clearFreeCoin(){
		try {
			baseDao.update(nameSpace + ".clearFreeCoin", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void clearQuestionCoin(){
		try {
			baseDao.update(nameSpace + ".clearQuestionCoin", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean checkSessionId(String sessionId){
		
		String session = null;
		boolean result = false;
		try {
			session = (String)baseDao.selectOne(nameSpace + ".checkSessionId", sessionId);
			
			if(session != null && session != ""){
				result = true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
}