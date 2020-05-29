package com.chese.smallChese.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chese.smallChese.dao.BaseDao;
import com.chese.smallChese.service.IAudioService;

@Service
public class AudioServiceImpl implements IAudioService {

	@Autowired
	private BaseDao baseDao;
	
	private String namespace = "AudioMapper";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map<String, Object>> findAudios() {
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		try {
			list = (List)baseDao.selectList(namespace + ".findAudiosIndex", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> findAudioById(String id){
		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			map = (Map<String,Object>)baseDao.selectOne(namespace + ".findAudioById", id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
	
	

}
