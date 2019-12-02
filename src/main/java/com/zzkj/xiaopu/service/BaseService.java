package com.zzkj.xiaopu.service;


import com.zzkj.xiaopu.dao.BaseDao;

public interface BaseService extends BaseDao {
	public String getMixId(String table) throws Exception;
	public String getMixCode(String adc_id, String ai_type, String as_date) throws Exception;
}