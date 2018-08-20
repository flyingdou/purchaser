package com.purchaser.controller;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.purchaser.constants.Constant;
import com.purchaser.pojo.Active;
import com.purchaser.pojo.User;
import com.purchaser.service.ActiveService;
import com.purchaser.util.CommentUtils;

@Controller
@RequestMapping("/active")
public class ActiveController {

	@Autowired
	private ActiveService activeService;

	/**
	 * 发起活动
	 * 
	 * @param json
	 * @param response
	 * @param session
	 */
	@RequestMapping("/release")
	public void release(String json, HttpServletResponse response, HttpSession session) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			// 生成一条活动数据
			Active active = JSONObject.toJavaObject(param, Active.class);
			User user = (User) session.getAttribute("user");
			active.setCreator(user.getId());
			activeService.release(active);
			// 生成活动对应的邀请码
			int distinguishedCodeCount = param.getIntValue("distinguishedCodeCount");
			int manufacturerCodeCount = param.getIntValue("manufacturerCodeCount");
			activeService.createActiveCode(active, distinguishedCodeCount, manufacturerCodeCount);
			JSONObject result = new JSONObject();
			result.fluentPut("success", true);
			CommentUtils.response(response, JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm"));
		} catch (Exception e) {
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(e));
		}
	}
	
	/**
	 * 查询挑战列表
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/getActiveList")
	public void getActiveList(String json, HttpServletResponse response) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			param.fluentPut("status", Constant.ACTIVE_STATUS_OPEN);
			List<Active> activeList = activeService.getActiveList(param);
			CommentUtils.response(response, JSON.toJSONStringWithDateFormat(activeList, "yyyy-MM-dd HH:mm"));
		} catch (Exception e) {
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(e));
		}
	}

	/**
	 * 根据id查询挑战信息
	 * 
	 * @param json
	 * @param response
	 */
	@RequestMapping("/getActiveById")
	public void getActiveById(String json, HttpServletResponse response) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			Map<String, Object> result = activeService.getActiveById(param);
			CommentUtils.response(response, JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm"));
		} catch (Exception e) {
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(e));
		}
	}

	/**
	 * 修改活动数据
	 * 
	 * @param json
	 * @param response
	 */
	@RequestMapping("/updateActive")
	public void updateActive(String json, HttpServletResponse response) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			activeService.updateActive(param);
			JSONObject result = new JSONObject();
			result.fluentPut("success", true);
			CommentUtils.response(response, JSON.toJSONString(result));
		} catch (Exception e) {
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(e));
		}
	}

	/**
	 * 活动签到
	 * 
	 * @param json
	 * @param response
	 * @param session
	 */
	@RequestMapping("/sign")
	public void sign(String json, HttpServletResponse response, HttpSession session) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			User user = (User) session.getAttribute("user");
			param.fluentPut("userId", user.getId());
			JSONObject result = activeService.sign(param);
			CommentUtils.response(response, JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm"));
		} catch (Exception e) {
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(e));
		}
	}

	/**
	 * 验证活动邀请码
	 * 
	 * @param json
	 * @param response
	 */
	@RequestMapping("/checkActiveCode")
	public void checkActiveCode(String json, HttpServletResponse response) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			Map<String, Object> resultMap = activeService.checkActiveCode(param);
			CommentUtils.response(response, JSON.toJSONString(resultMap));
		} catch (Exception e) {
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(e));
		}
	}
}
