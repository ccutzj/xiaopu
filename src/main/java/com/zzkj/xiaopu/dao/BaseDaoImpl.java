package com.zzkj.xiaopu.dao;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

@Component
public class BaseDaoImpl extends SqlSessionDaoSupport implements BaseDao {
	@Resource  
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){  
        super.setSqlSessionFactory(sqlSessionFactory);  
    }  
	
	public <E> List<E> selectList(String statementName) throws Exception {
		// TODO Auto-generated method stub
		return   getSqlSession().selectList(statementName);
	}

	public int update(String statementName, Object obj) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().update(statementName, obj);
	}
	

	public int insert(String statementName, Object obj) throws Exception {
		// TODO Auto-generated method stub
		return  getSqlSession().insert(statementName, obj);
	}


	public int delete(String statementName, Object obj) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().delete(statementName, obj);
	}


	public int count(String statementName, Object obj) throws Exception {
		// TODO Auto-generated method stub
		return  getSqlSession().selectOne(statementName, obj);
	}

	public <T> T selectOne(String statementName, Object parameter) throws Exception {
		// TODO Auto-generated method stub
		return  getSqlSession().selectOne(statementName, parameter);
	}


	public <E> List<E> selectList(String statementName, Object parameter) throws Exception {
		// TODO Auto-generated method stub
		return  getSqlSession().selectList(statementName, parameter);
	}

	public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
		// TODO Auto-generated method stub
		return  getSqlSession().selectList(statement, parameter,rowBounds);
	}


	public <T> T selectOne(String statementName) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(statementName);
	}


	public <K, V> Map<K, V> selectMap(String statement, String mapKey) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().selectMap(statement, mapKey);
	}


	public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().selectMap(statement,parameter, mapKey);
	}


	public Connection getConnection() {
		// TODO Auto-generated method stub
		return getSqlSession().getConnection();
	}

	
   

	
}