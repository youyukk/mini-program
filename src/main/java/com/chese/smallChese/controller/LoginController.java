package com.chese.smallChese.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chese.smallChese.service.IUserService;
import com.chese.smallChese.utils.FormatTimeUtil;
import com.chese.smallChese.utils.HttpClientUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/login/")
public class LoginController {	
	
	private String url = "https://api.weixin.qq.com/sns/jscode2session?"; 
	private String grant_type = "authorization_code";           		  
	
	private String appid = "wxe414ac7f64ebfd82";				  //---个人appid
	private String secret = "e4392af08a5e6b8e959c28dd1c7cf36e"; //---个人秘钥
	
	//private String appid = "wx98029e1fb23c1dbe";  				//企业appid
	//private String secret = "5f49d9b7bbacff0f214119a88491a21b"; //企业秘钥
	
	@Autowired
	private HttpClientUtil httpClient;
	
	@Autowired
	private IUserService userService;
	
	
	/**
	 * 获取sessionId
	 * @return 
	 */
	@RequestMapping("getSessionId")
	@ResponseBody
	public String getSessionId(){
		
		String sessionId = UUID.randomUUID().toString();
		return sessionId;
	}
	
	/**
	 * 重新申请sessionId
	 * @param oldSessionId 过期的sessionId
	 * @return
	 */
	@RequestMapping("getNewSessionId")
	@ResponseBody
	public String getNewSessionId(String oldSessionId){
		
		//先到Redis里查是否有对应的sessionId
		//先用MySQL存储，后期改为用Redis存sessionId
		boolean sessionId = userService.checkSessionId(oldSessionId);
		
		if(sessionId){
			return getSessionId();
		}else{
			return "0";
		}
		
	}
	
	/**
	 * 检查sessionId是否已过期
	 * @param sessionId
	 * @return 0：没有过期 ， 1：已过期
	 */
	@RequestMapping("checkSessionId")
	@ResponseBody
	public int checkSessionId(String sessionId){
		System.out.println("checkSessionId 被调用");
		System.out.println("sessionId = " + sessionId);
		boolean session = userService.checkSessionId(sessionId);
		if(session){
			return 0;
		}else{
			return 1;
		}
		
	}
	
	/**
	 * 登录
	 * @param code
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody
	public JSONObject login(String code,String nickName,Integer gender,String province,String city,String country){
		
		System.out.println("===login被调用===");
		System.out.println("code === " + code);
		if(code == null || code == ""){
			System.out.println("code 为空");
		}
		
		String newUrl = url + "appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=" + grant_type;
		System.out.println("url = " + newUrl);
		String result = httpClient.sendGetHttp(newUrl);
		System.out.println("httpClint result is : " + result);
		JSONObject json = JSONObject.fromObject(result);
		@SuppressWarnings("unchecked")
		Map<String,Object> map = (Map<String,Object>)json;
		
		if(map != null){
			System.out.println("map不为空");
			if(map.containsKey("openid")){
				
				String openId = (String)map.get("openid");
				String sessionKey = (String)map.get("session_key");
				
				Map<String, Object> userResult = userService.selectUserByOpenId(openId);
				String sessionId = getSessionId();
				//如果userResult为空，说明该用户是第一次登录
				if(userResult == null){
					HashMap<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("openId", openId);
					paramMap.put("sessionKey", sessionKey);
					paramMap.put("sessionId", sessionId);
					paramMap.put("nickName", nickName);
					paramMap.put("gender", gender);
					paramMap.put("province", province);
					paramMap.put("city", city);
					paramMap.put("country", country);
					paramMap.put("creatTime", FormatTimeUtil.getTimestamp());
					userService.addUser(paramMap);
					
					HashMap<String, Object> resultMap = new HashMap<String,Object>();
					resultMap.put("sessionId", sessionId);
					resultMap.put("coinCount", 0);
					resultMap.put("loginStatus", 1);
					JSONObject jsonObject = JSONObject.fromObject(resultMap);
					
					return jsonObject;
				}else{
					System.out.println("修改sessionId和session_key");
					Map<String,Object> updateMap = new HashMap<>();
					updateMap.put("sessionKey", sessionKey);
					updateMap.put("sessionId", sessionId);
					updateMap.put("openId",openId);
					userService.updateSession(updateMap);
					
					HashMap<String, Object> resultMap = new HashMap<String,Object>();
					resultMap.put("coinCount", userResult.get("coin_count").toString());
					resultMap.put("sessionId", sessionId);
					resultMap.put("loginStatus", 1);
					
					JSONObject jsonObject = JSONObject.fromObject(resultMap);
					return jsonObject;
				}
			}else{
				System.out.println("errcode=" + map.get("errcode").toString() + ",errmsg=" + map.get("errmsg"));
				HashMap<String,Object> backMap = new HashMap<String,Object>();
				backMap.put("loginStatus", 0);
				JSONObject jsonObject = JSONObject.fromObject(backMap);
				return jsonObject;
			}
		}else{
			System.out.println("结果为空，获取openID和session_key失败");
			HashMap<String,Object> backMap = new HashMap<String,Object>();
			backMap.put("loginStatus", 0);
			JSONObject jsonObject = JSONObject.fromObject(backMap);
			return jsonObject;
		}
		
	}
	
	
}