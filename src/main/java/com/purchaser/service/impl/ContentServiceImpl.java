package com.purchaser.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.purchaser.dao.ContentMapper;
import com.purchaser.pojo.Content;
import com.purchaser.pojo.User;
import com.purchaser.service.ContentService;

@Service
@Transactional
public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentMapper contentMapper;

	/**
	 * 查询内容列表
	 */
	@Override
	public JSONObject getContentList(JSONObject param) {
		JSONObject result = new JSONObject();
		List<Content> contentList = contentMapper.getContentList(param);
		result.fluentPut("success", true).fluentPut("contentList", contentList);
		return result;
	}

	/**
	 * 发布内容
	 */
	@Override
	public int release(JSONObject param, User user) {
		Content content = JSONObject.toJavaObject(param, Content.class);
		content.setSetTop(0);
		content.setCreateDate(new Date());
		return contentMapper.insertSelective(content);
	}

}
