package com.chese.smallChese.serviceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chese.smallChese.dao.BaseDao;
import com.chese.smallChese.service.IShareInfoService;

@Service
public class ShareInfoImpl implements IShareInfoService {

	@Autowired
	private BaseDao baseDao;
	
	private String namespace = "ShareInfoMapper";
	
	@Override
	public void addShareInfo(Map<String, Object> map) {
		
		try {
			baseDao.insert(namespace + ".addShareInfo", map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
