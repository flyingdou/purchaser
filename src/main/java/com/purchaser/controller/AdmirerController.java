package com.purchaser.controller;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.purchaser.pojo.PageInfo;
import com.purchaser.service.AdmirerService;
import com.purchaser.util.CommentUtils;

/*
 * 作者: dou
 * 时间: 2018-07-19 15:40:18
 * desc: 采购师控制器
 * */
@Controller
@RequestMapping("/admirer")
public class AdmirerController {
	
	/**
	 * 注入admirerService对象
	 */
	@Autowired
	private AdmirerService admirerService;
	
	
	/**
	 * 保存采购师信息
	 * @param response
	 * @param session
	 * @param json
	 */
	@RequestMapping("/saveAdmirer")
	@ResponseBody
	public void saveAdmirer (HttpServletResponse response, HttpServletRequest request, String json) {
		JSONObject ret = new JSONObject();
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			ret = admirerService.saveAdmirer(param, request);
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
	 * 查询采购师列表(微信前端)
	 * @param response
	 * @param session
	 * @param json
	 */
	@RequestMapping("/getAdmirerList")
	@ResponseBody
	public void getAdmirerList (HttpServletResponse response, HttpSession session, String json) {
		JSONObject ret = new JSONObject();
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			
			// 查询的采购师数据
			List<Map<String, Object>> admirerListx = admirerService.getAdmirerList(param);
			ret.fluentPut("success", true)
			   .fluentPut("message", "OK")
			   .fluentPut("admirerList", JSONArray.parse(JSON.toJSONString(admirerListx)))
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
	 * 根据采购师id查询采购师详情
	 * @param response
	 * @param json
	 */
	@RequestMapping("/admirerDetailById")
	@ResponseBody
	public void admirerDetailById (HttpServletResponse response, String json) {
		JSONObject ret = new JSONObject();
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			
			// 查询user
			JSONObject userJson = admirerService.getUserByAdmirer(param.getString("id"));
			
			param.fluentPut("user", userJson.getString("user"));
			// 查询信息
			JSONObject admirerDetail = admirerService.admirerDetail(param);
			ret.fluentPut("success", true)
			   .fluentPut("message", "OK")
			   .fluentPut("admirerDetail", admirerDetail)
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
	 * 查询采购师列表(后台管理系统)
	 * @param response
	 * @param json
	 */
	@RequestMapping("/getAdmirerList4admin")
	@ResponseBody
	public void getAdmirerList4admin (HttpServletResponse response, String json) {
		JSONObject ret = new JSONObject();
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			
			// 查询数据
			PageInfo pageInfo = admirerService.getAdmirerList4admin(param);
			
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
	
	
	
	/**
	 * 审核采购师
	 * @param response
	 * @param json
	 */
	@RequestMapping("/auditAdmirer")
	@ResponseBody
	public void auditAdmirer (HttpServletResponse response, String json) {
		JSONObject ret = new JSONObject();
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			
			// 影响行数
		    admirerService.auditAdmirer(param);
		    
		    ret.fluentPut("success", true)
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
	
	
}
