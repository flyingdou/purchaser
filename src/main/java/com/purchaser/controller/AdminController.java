package com.purchaser.controller;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.purchaser.pojo.PageInfo;
import com.purchaser.pojo.User;
import com.purchaser.service.ActiveService;
import com.purchaser.service.AdminService;
import com.purchaser.service.ContentService;
import com.purchaser.service.SupplierService;
import com.purchaser.util.CommentUtils;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private ActiveService activeService;

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private ContentService contentService;

	/**
	 * 管理员登录
	 * 
	 * @param json
	 * @param response
	 * @param session
	 */
	@RequestMapping("/login")
	public void Login(String json, HttpServletResponse response, HttpSession session) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			String account = param.getString("account");
			String password = CommentUtils.MD5(param.getString("password"));
			User user = adminService.login(account, password);
			JSONObject result = new JSONObject();
			// 用户是否存在
			if (user == null || user.getId() == null) {
				result.fluentPut("success", false).fluentPut("message", "账号或密码错误!");
				CommentUtils.response(response, JSON.toJSONString(result));
				return;
			}
			// 是否拥有权限
			if (user.getPower() < 1) {
				result.fluentPut("success", false).fluentPut("message", "抱歉, 您没有管理员权限, 无法登录!");
				CommentUtils.response(response, JSON.toJSONString(result));
				return;
			}
			// 校验通过
			session.setAttribute("user", user);
			result.fluentPut("success", true);
			CommentUtils.response(response, JSON.toJSONString(result));
		} catch (Exception e) {
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(e));
		}
	}

	/**
	 * 跳转页面
	 * 
	 * @param url
	 * @param request
	 * @param response
	 */
	@RequestMapping("/toPage")
	public void toAdmin(String url, HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
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
			PageInfo pageInfo = activeService.getActiveListForAdmin(param);
			CommentUtils.response(response, JSON.toJSONStringWithDateFormat(pageInfo, "yyyy-MM-dd HH:mm"));
		} catch (Exception e) {
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(e));
		}
	}

	/**
	 * 改变挑战状态
	 * 
	 * @param json
	 * @param response
	 */
	@RequestMapping("/changeActiveStatus")
	public void changeActiveStatus(String json, HttpServletResponse response) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			activeService.changeActiveStatus(param);
			JSONObject result = new JSONObject();
			result.fluentPut("success", true);
			CommentUtils.response(response, JSON.toJSONString(result));
		} catch (Exception e) {
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(e));
		}
	}

	/**
	 * 查询供应商列表(后台管理系统)
	 * 
	 * @param json
	 * @param response
	 */
	@RequestMapping("/getSupplierList")
	public void getSupplierList(String json, HttpServletResponse response) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			PageInfo pageInfo = supplierService.getSupplierListForAdmin(param);
			CommentUtils.response(response, JSON.toJSONStringWithDateFormat(pageInfo, "yyyy-MM-dd HH:mm"));
		} catch (Exception e) {
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(e));
		}
	}

	/**
	 * 审核供应商
	 * 
	 * @param json
	 * @param response
	 */
	@RequestMapping("/supplierAudit")
	public void supplierAudit(String json, HttpServletResponse response) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			JSONObject result = new JSONObject();
			supplierService.supplierAudit(param);
			result.fluentPut("success", true);
			CommentUtils.response(response, JSON.toJSONString(result));
		} catch (Exception e) {
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(e));
		}
	}

	/**
	 * 查询内容列表(后台管理系统)
	 * 
	 * @param json
	 * @param response
	 */
	@RequestMapping("/getContentList")
	public void getContentList(String json, HttpServletResponse response) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			PageInfo pageInfo = contentService.getContentListForAdmin(param);
			CommentUtils.response(response, JSON.toJSONStringWithDateFormat(pageInfo, "yyyy-MM-dd HH:mm"));
		} catch (Exception e) {
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(e));
		}
	}

	/**
	 * 内容置顶
	 * 
	 * @param json
	 * @param response
	 */
	@RequestMapping("/contentSetTop")
	public void contentSetTop(String json, HttpServletResponse response) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			JSONObject result = new JSONObject();
			contentService.contentSetTop(param);
			result.fluentPut("success", true);
			CommentUtils.response(response, JSON.toJSONString(result));
		} catch (Exception e) {
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(e));
		}
	}

	/**
	 * 修改内容
	 */
	@RequestMapping("/updateContent")
	public void updateContent(String json, HttpServletResponse response) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			JSONObject result = new JSONObject();
			contentService.updateContent(param);
			result.fluentPut("success", true);
			CommentUtils.response(response, JSON.toJSONString(result));
		} catch (Exception e) {
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(e));
		}
	}


    



}
