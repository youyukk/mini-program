package com.chese.smallChese.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chese.smallChese.service.IUserService;
import com.chese.smallChese.utils.FormatTimeUtil;
import com.chese.smallChese.utils.HttpClientUtil;
import com.chese.smallChese.utils.MD5Util;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/login/")
public class LoginController {	
	
	private String url = "https://api.weixin.qq.com/sns/jscode2session?";
	private String grant_type = "authorization_code";           
	//private String appid = "wxe414ac7f64ebfd82";				  ---个人appid
	//private String secret = "e4392af08a5e6b8e959c28dd1c7cf36e"; ---个人秘钥
	
	private String appid = "wx98029e1fb23c1dbe";  				//企业appid
	private String secret = "5f49d9b7bbacff0f214119a88491a21b"; //企业秘钥
	
	@Autowired
	private HttpClientUtil httpClient;
	
	@Autowired
	private IUserService userService;
	
	
	/**
	 * 通过code获取openID和session_key
	 * @return
	 */
	@RequestMapping("/")
	@ResponseBody
	public String hello(){
		
		return "hello";
	}
	
	@RequestMapping("getSessionKey")
	@ResponseBody
	public JSONObject login(String code,String nickName,Integer gender,String province,String city,String country){
		System.out.println("===login被调用===");
		if(code == null || code == ""){
			HashMap<String,Object> resultMap = new HashMap<>();
			resultMap.put("errcode", -2);
			resultMap.put("errmsg", "code不能为空");
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			return jsonObject; 
		}
		
		url = url + "appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=" + grant_type;
		String result = httpClient.sendGetHttp(url);
		System.out.println("httpClint result is : " + result);
		JSONObject json = JSONObject.fromObject(result);
		Map<String,Object> map = (Map<String,Object>)json;
		if(map != null){
			if(map.containsKey("openid")){
				
				String openId = (String)map.get("openid");
				String sessionKey = (String)map.get("session_key");
				
				Map<String, Object> userResult = userService.selectUserByOpenId(openId);
				//如果userResult为空，说明用户是第一次登陆
				if(userResult == null){
					HashMap<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("openId", openId);
					paramMap.put("sessionKey", sessionKey);
					paramMap.put("nickName", nickName);
					paramMap.put("gender", gender);
					paramMap.put("province", province);
					paramMap.put("city", city);
					paramMap.put("country", country);
					paramMap.put("creatTime", FormatTimeUtil.getTimestamp());
					userService.addUser(paramMap);
					
					HashMap<String, Object> resultMap = new HashMap<>();
					resultMap.put("md5SessionKey", MD5Util.getMD5(sessionKey));
					resultMap.put("coinCount", 0);
					resultMap.put("openId", openId);
					resultMap.put("errcode", 0);
					JSONObject jsonObject = JSONObject.fromObject(resultMap);
					
					return jsonObject;
				}else{
					//如果userResult不为空，说明用户已经登录过，现在是更新session_key
					HashMap<Object,Object> updateMap = new HashMap<>();
					updateMap.put("sessionKey", sessionKey);
					updateMap.put("openId", openId);
					userService.updateSessionKey(updateMap);
					
					HashMap<String, Object> resultMap = new HashMap<>();
					resultMap.put("md5SessionKey", MD5Util.getMD5(sessionKey));
					resultMap.put("coinCount", userResult.get("coin_count").toString());
					resultMap.put("openId", openId);
					resultMap.put("errcode", 0);
					JSONObject jsonObject = JSONObject.fromObject(resultMap);
					
					return jsonObject;
				}
				
			}else{
				System.out.println("errcode=" + map.get("errcode").toString() + ",errmsg=" + map.get("errmsg"));
				HashMap<String, Object> resultMap = new HashMap<>();
				resultMap.put("errcode", -1);
				JSONObject jsonObject = JSONObject.fromObject(resultMap);
				
				return jsonObject;
			}
		}else{
			System.out.println("获取openID和session_key失败");
			HashMap<String, Object> resultMap = new HashMap<>();
			resultMap.put("errcode", -1);
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			
			return jsonObject;
		}
	}
	
	
}