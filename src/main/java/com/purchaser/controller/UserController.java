package com.purchaser.controller;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.purchaser.pojo.PageInfo;
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



}
