package com.purchaser.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.purchaser.constants.Constant;
import com.purchaser.dao.AdmirerMapper;
import com.purchaser.dao.UserMapper;
import com.purchaser.pojo.AdmirerWithBLOBs;
import com.purchaser.pojo.User;
import com.purchaser.service.AdmirerService;

/*
 * 作者: dou
 * 时间: 2018-07-24 10:49:11
 * desc: 采购师业务逻辑实现
 * */
@Service
@Transactional
public class AdmirerServiceImpl implements AdmirerService {

	/**
	 * 注入userMapper对象
	 */
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 注入admirerMapper对象
	 */
	@Autowired
	private AdmirerMapper admirerMapper;
	
	
	/**
	 * 保存采购师
	 */
	@Override
	public JSONObject saveAdmirer(JSONObject param, HttpSession session) {
		JSONObject ret = new JSONObject();
		// 用户基本信息修改保存
		User user = (User) session.getAttribute("user");
		user.setName(param.getString("name"));
		user.setGender(param.getString("gender"));
		user.setIdCardNum(param.getString("id_card_num"));
		user.setConcord(param.getString("province") + "/" + param.getString("city"));
		user.setMobilephone(param.getString("mobilephone"));
		user.setEmail(param.getString("email"));
		user.setImage(param.getString("image"));
		// 持久化数据
		userMapper.updateByPrimaryKeySelective(user);
		
		// 采购师信息
		AdmirerWithBLOBs admirer = new AdmirerWithBLOBs();
		admirer.setUser(user.getId());
		admirer.setAddress(param.getString("address"));
		admirer.setStudy(param.getString("study_code"));
		admirer.setLanguage(param.getString("language"));
		admirer.setQualification(param.getString("qualification"));
		admirer.setLearning(param.getString("learning"));
		admirer.setSelfEvaluation(param.getString("self_evaluation"));
		admirer.setAudit(Constant.AUDIT_STATUS_NOT);
		admirer.setApplyDate(new Date());
		
	    // 持久化数据
		admirerMapper.insertSelective(admirer);
		
		ret.fluentPut("success", true)
		   .fluentPut("message", "OK")
		   .fluentPut("admirer", admirer.getId())
		   ;
		return ret;
	}


	/**
	 * 查询采购师列表
	 */
	@Override
	public List<Map<String, Object>> getAdmirerList(JSONObject param) {
		// 处理请求参数
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (param.containsKey("flag") && StringUtils.isNotBlank(param.getString("flag"))) {
			paramMap.put("flag", param.getString("flag"));
		}
		if (param.containsKey("name") && StringUtils.isNotBlank(param.getString("name"))) {
			paramMap.put("name",param.getString("name"));
		}
		// 查询数据
		paramMap.put("audit", Constant.AUDIT_STATUS_PASS);
		paramMap.put("parent", Constant.PARAMETER_STUDY_PARENT);
		List<Map<String, Object>> admirerList = admirerMapper.getAdmirerList(paramMap);
		
		return admirerList;
	}


	/**
	 * 查询采购师详情
	 */
	@Override
	public JSONObject admirerDetail(JSONObject param) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", param.getString("id"));
		paramMap.put("audit", Constant.AUDIT_STATUS_PASS);
		paramMap.put("parent", Constant.PARAMETER_STUDY_PARENT);
		paramMap.put("user", param.getString("user"));
		Map<String, Object> admirerMap = admirerMapper.admirerDetail(paramMap);
		return JSONObject.parseObject(JSON.toJSONString(admirerMap));
	}


	/**
	 * 通过采购师id查询user
	 */
	@Override
	public JSONObject getUserByAdmirer(String admirerId) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", admirerId);
		paramMap.put("audit", Constant.AUDIT_STATUS_PASS);
		Map<String, Object> userMap = admirerMapper.getUserByAdmirer(paramMap);
		return JSONObject.parseObject(JSON.toJSONString(userMap));
	}

}
