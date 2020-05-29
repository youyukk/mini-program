package com.chese.smallChese.serviceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chese.smallChese.dao.BaseDao;
import com.chese.smallChese.service.IArticleService;

@Service
public class ArticleServiceImpl implements IArticleService {

	@Autowired
	private BaseDao baseDao;
	
	private String namespace = "ArticleMapper";
	
	public void addArticle(Map<String,Object> map) throws Exception{
		
		baseDao.insert(namespace + ".addArticle", map);
		
	}
	
}
