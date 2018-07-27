package com.purchaser.controller;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.purchaser.pojo.PageInfo;
import com.purchaser.service.ParameterService;
import com.purchaser.service.UserService;
import com.purchaser.util.CommentUtils;

/**
 * 
 * @author 华文
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	/**
	 * 注入userService
	 */
	@Autowired
	private UserService userService;
	
	
	/**
	 * 注入parameterService
	 */
	@Autowired
	private ParameterService parameterService;

	/**
	 * 检查当前访问是否是已经登录过的
	 * @param redirectURL
	 * @return
	 */
	@RequestMapping("/checkLogin")
	public String checkLogin(String redirectURL) {
		try {
			return URLDecoder.decode(redirectURL, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return "www.ecartoon.com.cn";
		}
	}




	/**
	 * 获取微信登录用户列表
	 * @param response
	 * @param json
	 */
	@RequestMapping("/getUserList4admin")
	@ResponseBody
    public void getUserList4admin (HttpServletResponse response, String json) {
    	try {
    		// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
    		PageInfo pageInfo = userService.userList4admin(param);
			CommentUtils.response(response, JSON.toJSONString(pageInfo, CommentUtils.dateformatValue("yyyy-MM-dd HH:mm:ss")));
		
    	} catch (Exception e) {
			JSONObject ret = new JSONObject();
			ret.fluentPut("success", false)
			   .fluentPut("message", e.toString())
			   ;
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(ret));
		}
    	
    	
    	
    }
	
	/**
	 * 查询会员录入页面初始化数据
	 * @param response
	 * @param json
	 */
	@RequestMapping("/getList4release")
	@ResponseBody
	public void getList4release (HttpServletResponse response, String json) {
		JSONObject ret = new JSONObject();
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			ret = parameterService.findParameters(param);
			List<Map<String, Object>> userListJ = userService.userList4release();
			JSONArray userList = JSONArray.parseArray(JSON.toJSONString(userListJ));
			ret.fluentPut("userList", userList);
		} catch (Exception e) {
			ret.fluentPut("success", false)
			   .fluentPut("message", e.toString())
			   ;
			e.printStackTrace();
		}
		
		// 返回数据
		CommentUtils.response(response, JSON.toJSONString(ret));
		
		
	}



}
