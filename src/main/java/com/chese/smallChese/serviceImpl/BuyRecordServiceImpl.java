package com.chese.smallChese.serviceImpl;

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
	public Integer isBought(Map<String,Object> map){
		
		Integer result = null;
		
		try {
			result = (Integer)baseDao.selectOne(namespace + ".isBought", map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public int addBuyRecord(Map<String,Object> map){
		
		try {
			baseDao.insert(namespace + ".addBuyRecord", map);
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
}
