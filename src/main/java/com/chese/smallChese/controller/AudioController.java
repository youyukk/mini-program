package com.chese.smallChese.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chese.smallChese.service.IAudioService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/audio/")
public class AudioController {

	@Autowired
	private IAudioService audioService;
	
	@RequestMapping("findAudios")
	@ResponseBody
	public JSONArray findAudios(){
		System.out.println("findAudios be used");
		List<Map<String,Object>> list = audioService.findAudios();
		if(list != null){
			JSONArray jsonArray = JSONArray.fromObject(list);
			return jsonArray;			
		}else{
			return null;
		}
	}
	
	@RequestMapping("findAudioById")
	@ResponseBody
	public JSONObject findAudioById(String id){
		
		Map<String, Object> map = audioService.findAudioById(id);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		
		return jsonObject;
	}
	
	
}
