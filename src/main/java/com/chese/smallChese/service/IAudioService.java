package com.chese.smallChese.service;

import java.util.List;
import java.util.Map;

public interface IAudioService {
	
	public List<Map<String,Object>> findAudios();

	public Map<String,Object> findAudioById(String id);
	
}
