package com.zzkj.xiaopu.dao;

import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface BaseDao {
	int insert(String statementName, Object obj) throws Exception;
	int delete(String statementName, Object obj) throws Exception;
	int update(String statementName, Object obj) throws Exception;
	int count(String statementName, Object obj) throws Exception;
	<T> T selectOne(String statementName) throws Exception;
	<T> T selectOne(String statementName, Object parameter) throws Exception;
	<E> List<E> selectList(String statementName) throws Exception;
	<E> List<E> selectList(String statementName, Object parameter) throws Exception;
	<E> List<E> selectList (String statement, Object parameter, RowBounds rowBounds) throws Exception;;
	<K,V> Map<K,V> selectMap(String statement, String mapKey)throws Exception;
	<K,V> Map<K,V> selectMap(String statement, Object parameter, String mapKey)throws Exception;
	public Connection getConnection();
	
	
}