package com.chese.smallChese.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chese.smallChese.dao.BaseDao;
import com.chese.smallChese.service.IQuestionService;

@Service
public class QuestionServiceImpl implements IQuestionService {

	@Autowired
	private BaseDao baseDao;
	
	private String namespace = "QuestionMapper";
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> getQuestion() {
		
		Map<String,Object> map = new HashMap<>();
		try {
			map = (Map<String,Object>)baseDao.selectOne(namespace + ".getQuestion", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
	
	

}
