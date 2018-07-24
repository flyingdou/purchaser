package com.purchaser.controller;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.purchaser.pojo.User;
import com.purchaser.util.CommentUtils;

/*
 * 作者: dou
 * 时间: 2018-07-19 15:40:18
 * desc: 采购师控制器
 * */
@Controller
@RequestMapping("/admirer")
public class AdmirerController {

	
	@RequestMapping("/saveAdmirer")
	@ResponseBody
	public void saveAdmirer (HttpServletResponse response, HttpSession session, String json) {
		JSONObject ret = new JSONObject();
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			// 用户登录信息
			User user = (User) session.getAttribute("user");
			
			param.fluentPut("user", user.getId());
			
			
		} catch (Exception e) {
			ret.fluentPut("success", false)
			   .fluentPut("message", e.toString())
			   ;
		}
		
		// 返回数据
		CommentUtils.response(response, JSON.toJSONString(ret));
	}
	
	
}
