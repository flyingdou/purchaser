package com.purchaser.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.purchaser.pojo.Supplier;
import com.purchaser.pojo.User;
import com.purchaser.service.SupplierService;
import com.purchaser.util.CommentUtils;
import com.purchaser.util.Validcode;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	/**
	 * 供应商录入
	 * 
	 * @param json
	 * @param response
	 */
	@RequestMapping("/release")
	public void release(String json, HttpServletResponse response, HttpServletRequest request) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			// 首先校验短信验证码
			Validcode validcode = new Validcode(param.getString("mobilephone"), request);
			if (!validcode.isRightful(param.getString("code"))) {
				Map<String, Object> reslutMap = new HashMap<String, Object>();
				reslutMap.put("success", false);
				reslutMap.put("message", "验证码错误");
				CommentUtils.response(response, JSON.toJSONString(reslutMap));
				return;
			}

			// 生成一条供应商数据
			Supplier supplier = JSON.toJavaObject(param, Supplier.class);
			User user = (User) request.getSession().getAttribute("user");
			Map<String, Object> reslutMap = supplierService.release(supplier, user);
			CommentUtils.response(response, JSON.toJSONString(reslutMap));
		} catch (Exception e) {
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(e));
		}
	}

	/**
	 * 查询供应商列表
	 * 
	 * @param json
	 * @param response
	 */
	@RequestMapping("/getSupplierList")
	public void getSupplierList(String json, HttpServletResponse response) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			List<Supplier> supplierList = supplierService.getSupplierList(param);
			CommentUtils.response(response, JSON.toJSONStringWithDateFormat(supplierList, "yyyy-MM-dd"));
		} catch (Exception e) {
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(e));
		}
	}

	/**
	 * 查询供应商
	 * 
	 * @param json
	 * @param response
	 */
	@RequestMapping("/getSupplier")
	public void getSupplier(String json, HttpServletResponse response) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			Supplier supplier = supplierService.getSupplier(param);
			CommentUtils.response(response, JSON.toJSONStringWithDateFormat(supplier, "yyyy-MM-dd"));
		} catch (Exception e) {
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(e));
		}
	}
}
