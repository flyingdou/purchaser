package com.purchaser.controller;

import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.purchaser.pojo.User;
import com.purchaser.service.OrderService;
import com.purchaser.util.CommentUtils;

/**
 * 
 * @author 华文
 * @Date 2018-07-18
 * @Time 14:10
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * 获取活动商品信息
	 * 
	 * @param json
	 * @param response
	 */
	@RequestMapping("/getActiveProductInfo")
	public void getActiveProductInfo(HttpServletResponse response, HttpSession session) {
		try {
			User user = (User) session.getAttribute("user");
			Map<String, Object> resultMap = orderService.getActiveProductInfo(user);
			CommentUtils.response(response, JSON.toJSONStringWithDateFormat(resultMap, "yyyy-MM-dd HH:mm"));
		} catch (Exception e) {
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(e));
		}
	}

	@RequestMapping("/createOrder")
	public void createOrder(String json, HttpServletResponse response, HttpServletRequest request) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			String result = orderService.createOrder(param, request);
			CommentUtils.response(response, JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm"));
		} catch (Exception e) {
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(e));
		}
	}
}
