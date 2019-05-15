package com.chese.smallChese.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 
	 * @param statement
	 * @param parameter
	 * @return peter
	 * @description:查询一组集合的数据的方法
	 * @throws Exception
	 */
	public List<Object> selectList(String statement,Object parameter) throws Exception{
		List<Object> selectList = sqlSessionTemplate.selectList(statement,parameter);
		return selectList;
	}
	
	/**
	 * 查询单个数据
	 * @param statement
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	public Object selectOne(String statement,Object parameter)throws Exception{
		Object object = sqlSessionTemplate.selectOne(statement,parameter);
		return object;
	}
	
	/**
	 * 新增数据
	 * @param statement
	 * @param parameter
	 * @throws Exception
	 */
	public void insert(String statement,Object parameter)throws Exception{
		sqlSessionTemplate.insert(statement, parameter);
	}
	
	/**
	 * 删除数据
	 * @param statement
	 * @param parameter
	 * @throws Exception
	 */
	public void delete(String statement,Object parameter)throws Exception{
		sqlSessionTemplate.delete(statement, parameter);
	}

	/**
	 * 修改数据
	 * @param statement
	 * @param parameter
	 * @throws Exception
	 */
	public void update(String statement,Object parameter)throws Exception{
		sqlSessionTemplate.update(statement, parameter);
	}
}
