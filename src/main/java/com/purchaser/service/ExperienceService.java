package com.purchaser.service;
/*
 * 作者: dou
 * 时间: 2018-07-23 14:59:10
 * desc: 工作履历业务逻辑
 * */

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface ExperienceService {
	
	/**
	 * 通过userId查询当前用户的工作履历
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getExperiencesByUser (String userId);
	
	/**
	 * 新增工作履历
	 * @param param
	 * @return
	 */
	public JSONObject saveExperience (JSONObject param) throws Exception;
	
	/**
	 * 查询用户的工作年限
	 * @param userId
	 * @return
	 */
	public Integer getWorkYears(Long userId); 

}
