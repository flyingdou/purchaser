package com.purchaser.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.purchaser.dao.ExperienceMapper;
import com.purchaser.pojo.Experience;
import com.purchaser.service.ExperienceService;
import com.purchaser.util.CommentUtils;

/*
 * 作者: dou
 * 时间: 2018-07-23 15:01:42
 * desc: 工作履历业务逻辑实现
 * */
@Service
@Transactional
public class ExperienceServiceImpl implements ExperienceService {
	
	
	/**
	 * 注入experienceDao层对象
	 */
	@Autowired
	private ExperienceMapper experienceMapper;
	

	/**
	 * 查询用户工作履历
	 */
	@Override
	public List<Map<String, Object>> getExperiencesByUser(String userId) {
		List<Map<String, Object>> experienceList = experienceMapper.getExperiences(userId);
		return experienceList;
	}


	/**
	 * 新增工作履历
	 */
	@Override
	public JSONObject saveExperience(JSONObject param) {
		JSONObject ret = new JSONObject();
		// 初始化对象
		Experience experience = new Experience();
		experience.setUser(param.getLong("user"));
		experience.setStarttime(CommentUtils.formatString2Date(param.getString("startTime"), "yyyy-MM"));
		experience.setEndtime(CommentUtils.formatString2Date(param.getString("endTime"), "yyyy-MM"));
		experience.setPost(param.getString("post"));
		experience.setPostDetail(param.getString("post_detail"));
		experience.setCompanyName(param.getString("company_name"));
		
		// 持久化数据
		experienceMapper.insertSelective(experience);
		
		ret.fluentPut("success", true)
		   .fluentPut("message", "OK")
		   ;
		return ret;
	}

}
