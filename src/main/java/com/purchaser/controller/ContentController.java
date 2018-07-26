package com.purchaser.controller;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.purchaser.pojo.Content;
import com.purchaser.pojo.User;
import com.purchaser.service.ContentService;
import com.purchaser.util.CommentUtils;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;

	/**
	 * 查询内容列表
	 * 
	 * @param json
	 * @param response
	 */
	@RequestMapping("/getContentList")
	public void getContentList(String json, HttpServletResponse response, HttpSession session) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			JSONObject result = contentService.getContentList(param);
			CommentUtils.response(response, JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm"));
		} catch (Exception e) {
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(e));
		}
	}

	/**
	 * 发布内容
	 * 
	 * @param json
	 * @param response
	 */
	@RequestMapping("/release")
	public void release(String json, HttpServletResponse response, HttpSession session) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			User user = (User) session.getAttribute("user");
			contentService.release(param, user);
			JSONObject result = new JSONObject();
			result.fluentPut("success", true);
			CommentUtils.response(response, JSON.toJSONString(result));
		} catch (Exception e) {
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(e));
		}
	}

	/**
	 * 查询内容
	 * 
	 * @param json
	 * @param response
	 */
	@RequestMapping("/getContent")
	public void getContent(String json, HttpServletResponse response) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			Content content = contentService.getContent(param);
			CommentUtils.response(response, JSON.toJSONStringWithDateFormat(content, "yyyy-MM-dd HH:mm"));
		} catch (Exception e) {
			e.printStackTrace();
			CommentUtils.response(response, JSON.toJSONString(e));
		}
	}
}
