package com.chese.smallChese.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chese.smallChese.dao.BaseDao;
import com.chese.smallChese.service.IBuyRecordService;

@Service
public class BuyRecordServiceImpl implements IBuyRecordService {

	@Autowired
	private BaseDao baseDao;
	
	private String namespace = "BuyRecordMapper";
	
	@Override
	public Map<String,Object> isBought(Map<String,Object> map){
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		try {
			resultMap = (Map<String,Object>)baseDao.selectOne(namespace + ".isBought", map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	@Override
	public void addBuyRecord(Map<String,Object> map){
		
		try {
			baseDao.insert(namespace + ".addBuyRecord", map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
