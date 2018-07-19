package com.purchaser.service;

import com.alibaba.fastjson.JSONObject;

public interface ContentService {

	/**
	 * 查询内容列表
	 * 
	 * @param param
	 * @return
	 */
	JSONObject getContentList(JSONObject param);

}
