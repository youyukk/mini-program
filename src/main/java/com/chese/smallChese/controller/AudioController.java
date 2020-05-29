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
		
		JSONArray jsonArray = null;
		
		if(list != null){
			//循环修改标题长度，如果长度超过15个字符，超出的部分用 ...替代
			for(int i = 0; i < list.size(); i++){
				Map<String,Object> map = list.get(i);
				String name = map.get("name").toString();
				if(name.length() > 17){
					String str = name.substring(0, 17);
					name = str + " . . .";
					map.put("name", name);
				}
			}
			jsonArray = JSONArray.fromObject(list);			
		}
		
		return jsonArray;
	}
	
	@RequestMapping("findAudioById")
	@ResponseBody
	public JSONObject findAudioById(String id){
		
		Map<String, Object> map = audioService.findAudioById(id);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		
		return jsonObject;
	}
	
	
}
