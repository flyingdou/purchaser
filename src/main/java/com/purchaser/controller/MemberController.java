package com.purchaser.controller;

import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.purchaser.pojo.PageInfo;
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
	@ResponseBody
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
	@ResponseBody
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
	@ResponseBody
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
	@ResponseBody
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
	@ResponseBody
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
	

	/**
	 * 查询当前会员的详情
	 * @param response
	 * @param session
	 */
	@RequestMapping("/memberDetail")
	@ResponseBody
    public void memberDetail (HttpServletResponse response, HttpSession session) {
    	JSONObject ret = new JSONObject();
    	try {
			// 用户登录
    		User user = (User) session.getAttribute("user");
    		JSONObject memberDetail = memberService.findMemberInfo(user.getId());
    		
    		ret.fluentPut("success", true)
    		   .fluentPut("message", "OK")
    		   .fluentPut("memberDetail", memberDetail);
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
	 * 查询当前登录用户的简要会员信息
	 * @param response
	 * @param session
	 */
	@RequestMapping("/memberSimple")
	@ResponseBody
	public void memberSimple (HttpServletResponse response, HttpSession session) {
		JSONObject ret = new JSONObject();
		try {
			// 用户登录
			User user = (User) session.getAttribute("user");
			
			// 查询当前用户是否为有效会员
			Boolean isMember = userService.userIsMember(user.getId());
			
			// 查询会员简要信息
			JSONObject memberInfo = memberService.memberSimple(user.getId());
			
			ret.fluentPut("success", true)
			   .fluentPut("message", "OK")
			   .fluentPut("isMember", isMember)
			   .fluentPut("memberInfo", memberInfo)
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
	 * 查询会员列表
	 * @param response
	 * @param json
	 */
	@RequestMapping("/getMemberList")
	@ResponseBody
	public void getMemberList (HttpServletResponse response, String json) {
		JSONObject ret = new JSONObject();
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			
			// 查询数据
			PageInfo pageInfo = memberService.getMemberList(param);
			ret.fluentPut("success", true)
			   .fluentPut("pageInfo", pageInfo)
			   ;
		} catch (Exception e) {
			ret.fluentPut("success", false)
			   .fluentPut("message", e.toString())
			   ;
			e.printStackTrace();
		}
		
		// 返回数据
		CommentUtils.response(response, JSON.toJSONString(ret, CommentUtils.dateformatValue("yyyy-MM-dd")));
		
	}


}
