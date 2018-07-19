package com.purchaser.controller;

import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.purchaser.pojo.User;
import com.purchaser.service.MemberService;
import com.purchaser.service.ParameterService;
import com.purchaser.service.UserService;
import com.purchaser.util.CommentUtils;

/*
 * 作者: dou
 * 时间: 2018-06-19 16:54:31
 * des: 会员业务控制器
 * */
@Controller
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	private ParameterService paramService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MemberService memberService;
	
	
	/**
	 * 跳转到 入会申请 页面
	 * @return
	 */
	@RequestMapping("/joinApply")
	public String joinApply() {
		return "join_apply";
	}
	

	/**
	 * 查询参数列表中的参数
	 */
	@RequestMapping("/findParameters")
	public void findParameters (HttpServletResponse response, String json) {
		JSONObject ret = new JSONObject();
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			ret = paramService.findParameters(param);
		} catch (Exception e) {
			ret.fluentPut("success", false).fluentPut("message", e.toString());
			e.printStackTrace();
		}
		// 返回结果
		CommentUtils.response(response, ret.toJSONString());
	}
	
	/**
	 * 根据用户id查询用户的基本信息
	 */
	@RequestMapping("/findUser")
	public void findUser(HttpServletRequest request, HttpServletResponse response) {
		JSONObject ret = new JSONObject();
		try {
		    User user = (User) request.getSession().getAttribute("user");
		    JSONObject userInfo = userService.findUser(user.getId());
		    ret.fluentPut("success", true).fluentPut("message", "OK").fluentPut("userInfo", userInfo);
		} catch (Exception e) {
			ret.fluentPut("success", false).fluentPut("message", e.toString());
			e.printStackTrace();
		}
		// 返回结果
		CommentUtils.response(response, ret.toJSONString());
	}
	
	/**
	 * 查询采购师的基本信息
	 */
	@RequestMapping("/findPurchaserInfo")
	public void findPurchaserInfo (HttpServletRequest request, HttpServletResponse response) {
		JSONObject ret = new JSONObject();
		try {
			// 从session中取出当前登录用户
			User user = (User) request.getSession().getAttribute("user");
			
			// 查询采购师的基本信息
			JSONObject memberInfo = memberService.findMemberInfo(user.getId());
			
			// 判断当前用户是否已经是会员了
			Boolean isMember = userService.userIsMember(user.getId());
			ret.fluentPut("success", true)
			   .fluentPut("message", "OK")
			   .fluentPut("memberInfo", memberInfo)
			   .fluentPut("isMember", isMember)
			   ;
		} catch (Exception e) {
			ret.fluentPut("success", false).fluentPut("message", e.toString());
			e.printStackTrace();
		}
		
		// 返回结果
		CommentUtils.response(response, ret.toJSONString());
	}
	
	
	/**
	 * 保存用户数据
	 * @param request
	 * @param response
	 * @param json
	 */
	@RequestMapping("/saveMember")
	public void saveMember (HttpServletRequest request, HttpServletResponse response, String json) {
		JSONObject ret = new JSONObject();
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			ret = memberService.saveMember(param, request);
		} catch (Exception e) {
			ret.fluentPut("success", false)
			   .fluentPut("message", e.toString());
			e.printStackTrace();
		}
		
		// 返回数据
		CommentUtils.response(response, ret.toJSONString());
	}
	
	
	/**
	 * 获取当前登录用户的会员价格
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getMemberPrice")
	public void getMemberPrice (HttpServletRequest request, HttpServletResponse response) {
		JSONObject ret = new JSONObject();
		try {
			
			User user = (User) request.getSession().getAttribute("user");
			ret = memberService.getMemberPrice(user.getId());
			ret.fluentPut("success", true)
			   .fluentPut("message", "OK")
			   .fluentPut("orderDate", CommentUtils.format(new Date(), "yyyy-MM-dd"))
			   ;
		} catch (Exception e) {
			ret.fluentPut("success", false)
			   .fluentPut("message", e.toString())
			   ;
		}
		
		// 返回数据
		CommentUtils.response(response, ret.toJSONString());
	}
	
}
