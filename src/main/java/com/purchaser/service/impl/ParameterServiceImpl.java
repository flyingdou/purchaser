package com.purchaser.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.purchaser.dao.ParameterMapper;
import com.purchaser.service.ParameterService;

/*
 * 作者: dou
 * 时间: 2018-06-19 17:06:29
 * */
@Service
@Transactional
public class ParameterServiceImpl implements ParameterService {
	
	@Autowired
	private ParameterMapper parameterMap;

	/**
	 * 查询参数
	 */
	@Override
	public JSONObject findParameters(JSONObject param) {
		JSONObject ret = new JSONObject();
		Map<String, Object> mapDou = new HashMap<String, Object>();
		// 行业类型
		if (param.containsKey("business") && StringUtils.isNotEmpty(param.getString("business"))) {
			mapDou.clear();
			mapDou.put("parent", param.getInteger("business"));
			List<Map<String, Object>> business = parameterMap.findParameterByParent(mapDou);
			ret.fluentPut("business", JSON.parseArray(JSON.toJSONString(business)));
		}
		
		// 公司类型
		if (param.containsKey("company_type") && StringUtils.isNotEmpty(param.getString("company_type"))) {
			mapDou.clear();
			mapDou.put("parent", param.getInteger("company_type"));
			List<Map<String, Object>> company_type = parameterMap.findParameterByParent(mapDou);
			ret.fluentPut("company_type", JSON.parseArray(JSON.toJSONString(company_type)));
		}
		
		// 会员类型
		if (param.containsKey("type") && StringUtils.isNotEmpty(param.getString("type"))) {
			mapDou.clear();
			mapDou.put("parent", param.getInteger("type"));
			List<Map<String, Object>> type = parameterMap.findParameterByParent(mapDou);
			ret.fluentPut("type", JSON.parseArray(JSON.toJSONString(type)));
		}
		
		// 学历类型
		if (param.containsKey("study_type") && StringUtils.isNotEmpty(param.getString("study_type"))) {
			mapDou.clear();
			mapDou.put("parent", param.getString("study_type"));
			List<Map<String, Object>> studyType = parameterMap.findParameterByParent(mapDou);
			ret.fluentPut("studyType", studyType);
		}
		
		ret.fluentPut("success", true).fluentPut("message", "OK");
		return ret;
	}

}
