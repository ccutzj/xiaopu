package com.zzkj.xiaopu.service;

import com.zzkj.xiaopu.dao.BaseDao;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Qualifier("baseService")
public class BaseServiceImpl implements BaseService {

	@Autowired
	BaseDao baseDaoImpl;
	
	
	@Transactional(propagation=Propagation.REQUIRED)

	public int insert(String statementName, Object obj) throws Exception {
		// TODO Auto-generated method stub
		return baseDaoImpl.insert(statementName, obj);
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	
	public int delete(String statementName, Object obj) throws Exception {
		// TODO Auto-generated method stub
		return baseDaoImpl.delete(statementName, obj);
	}

	@Transactional(propagation=Propagation.REQUIRED)

	public int update(String statementName, Object obj) throws Exception {
		// TODO Auto-generated method stub
		return baseDaoImpl.update(statementName, obj);
	}


	public int count(String statementName, Object obj) throws Exception {
		// TODO Auto-generated method stub
		return baseDaoImpl.count(statementName, obj);
	}


	public <T> T selectOne(String statementName) throws Exception {
		// TODO Auto-generated method stub
		return baseDaoImpl.selectOne(statementName);
	}


	public <T> T selectOne(String statementName, Object parameter) throws Exception {
		// TODO Auto-generated method stub
		return baseDaoImpl.selectOne(statementName, parameter);
	}


	public <E> List<E> selectList(String statementName) throws Exception {
		// TODO Auto-generated method stub
		return baseDaoImpl.selectList(statementName);
	}


	public <E> List<E> selectList(String statementName, Object parameter) throws Exception {
		// TODO Auto-generated method stub
		return baseDaoImpl.selectList(statementName, parameter);
	}


	public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) throws Exception {
		// TODO Auto-generated method stub
		return baseDaoImpl.selectList(statement, parameter, rowBounds);
	}


	public <K, V> Map<K, V> selectMap(String statement, String mapKey) throws Exception {
		// TODO Auto-generated method stub
		return baseDaoImpl.selectMap(statement, mapKey);
	}


	public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) throws Exception {
		// TODO Auto-generated method stub
		return baseDaoImpl.selectMap(statement, parameter, mapKey);
	}


	public Connection getConnection() {
		// TODO Auto-generated method stub
		return baseDaoImpl.getConnection();
	}
	
	
	//得到最大单据ID
	public String getMixId(String table) throws Exception {
		// TODO Auto-generated method stub
		String mixId = "";
		Map pmap = new HashMap();
		pmap.put("table", table);
		Map max =baseDaoImpl.selectOne("apiservice.Sys.getmaxid",pmap);
		if(max != null) {
			BigInteger b1 = new BigInteger(max.get("zdid").toString());
			BigInteger b2 = new BigInteger("1");
			BigInteger b3 = b1.add(b2);
			//int i = Integer.parseInt(max.get("zdid").toString()) + 1;
			pmap.put("zdid", b3.toString());
			baseDaoImpl.update("apiservice.Sys.updatemaxid", pmap);
			mixId = b3.toString();
		}
		else {
			pmap.put("zdid","220100000000000001");
			baseDaoImpl.insert("apiservice.Sys.insertmaxid", pmap);
			mixId = "220100000000000001";
		}
		return mixId;
	}
	
	//得到单据编码
	public String getMixCode(String adc_id, String ai_type, String as_date) throws Exception {
		// TODO Auto-generated method stub
		String ls_billcode= "";
		String ls_pyt = "";
		int i_maxcode = 0;
		Map pmap = new HashMap();
		pmap.put("adc_id", adc_id);
		Map c_pyt =baseDaoImpl.selectOne("apiservice.Sys.getc_pyt",pmap);
		if(c_pyt != null) {
			ls_pyt = c_pyt.get("c_pyt").toString();
		}
		else {
			throw new Exception("没有设置销售订单对应的拼音头");
		}
		pmap.put("adc_id", adc_id);
		pmap.put("ai_type", ai_type);
		pmap.put("as_date", as_date);
		List<Map> list  =baseDaoImpl.selectList("apiservice.Sys.getll_count",pmap);
		if(!list.get(0).get("ll_count").toString().equals("0")) {
			Map i_maxcodeMap =baseDaoImpl.selectOne("apiservice.Sys.geti_maxcode",pmap);
			i_maxcode =  Integer.parseInt(i_maxcodeMap.get("i_maxcode").toString());
			i_maxcode ++;
			pmap.put("ll_maxcode", i_maxcode);
			baseDaoImpl.update("apiservice.Sys.updatei_maxcode",pmap);
		}
		else {
			String ldc_maxid = getMixId("T_SYS_BILL_CODE");
			i_maxcode = 1;
			pmap.put("ll_maxcode", i_maxcode);
			pmap.put("ldc_maxid", ldc_maxid);
			pmap.put("ls_pyt", ls_pyt);
			baseDaoImpl.insert("apiservice.Sys.inserti_maxcode",pmap);
		}
		
		String code = "";
		if(i_maxcode < 10)
			code = "0" + Integer.toString(i_maxcode);
		else 
			code = Integer.toString(i_maxcode);
		ls_billcode = ls_pyt + as_date + "_" + code;
		return ls_billcode;
	}
	
	
	
	

}
