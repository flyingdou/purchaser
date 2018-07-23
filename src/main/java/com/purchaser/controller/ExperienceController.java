package com.purchaser.controller;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.purchaser.pojo.User;
import com.purchaser.service.ExperienceService;
import com.purchaser.util.CommentUtils;

/*
 * 作者: dou
 * 时间: 2018-07-23 14:51:09
 * desc: 工作履历控制器
 * */
@Controller
@RequestMapping("/experience")
public class ExperienceController {
	
	/**
	 * 注入experienceService对象
	 */
	@Autowired
	private ExperienceService experienceService;
	
	
	/**
	 * 获取工作履历信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getExperiences")
	@ResponseBody
	public void getExperiences (HttpServletRequest request, HttpServletResponse response) {
		JSONObject ret = new JSONObject();
		try {
			// 用户登录
			User user = (User) request.getSession().getAttribute("user");
			List<Map<String, Object>> experienceList = experienceService.getExperiencesByUser(user.getId().toString());
			JSONArray experienceJA = JSONArray.parseArray(JSON.toJSONString(CommentUtils.listDateAndFormat(experienceList, "yyyy-MM")));
		    ret.fluentPut("success", true)
		       .fluentPut("message", "OK")
		       .fluentPut("experienceList", experienceJA)
		       ;
		} catch (Exception e) {
			ret.fluentPut("success", false)
			   .fluentPut("message", e.toString())
			   ;
			e.printStackTrace();
		}
		
		// 返回数据
		CommentUtils.response(response, JSON.toJSONString(ret));
	}
	
	
	
	/**
	 * 保存工作履历
	 * @param request
	 * @param response
	 * @param json
	 */
	@RequestMapping("/saveExperience")
	@ResponseBody
	public void saveExperience(HttpServletRequest request, HttpServletResponse response, String json) {
		JSONObject ret = new JSONObject();
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			// 用户登录
			User user = (User) request.getSession().getAttribute("user");
			param.fluentPut("user", user.getId());
			ret = experienceService.saveExperience(param);
		} catch (Exception e) {
			ret.fluentPut("success", false)
			   .fluentPut("message", e.toString())
			   ;
		}
		
		// 返回数据
		CommentUtils.response(response, JSON.toJSONString(ret));
	}
	

}
