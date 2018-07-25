package com.purchaser.service;

import com.alibaba.fastjson.JSONObject;
import com.purchaser.pojo.User;

public interface ContentService {

	/**
	 * 查询内容列表
	 * 
	 * @param param
	 * @return
	 */
	JSONObject getContentList(JSONObject param);

	/**
	 * 发布内容
	 * 
	 * @param param
	 * @return
	 */
	int release(JSONObject param, User user);

}
