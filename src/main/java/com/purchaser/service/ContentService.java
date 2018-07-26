package com.purchaser.service;

import com.alibaba.fastjson.JSONObject;
import com.purchaser.pojo.Content;
import com.purchaser.pojo.PageInfo;
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
	 * 查询内容列表(后台管理系统)
	 * 
	 * @param param
	 * @return
	 */
	PageInfo getContentListForAdmin(JSONObject param);

	/**
	 * 发布内容
	 * 
	 * @param param
	 * @return
	 */
	int release(JSONObject param, User user);

	/**
	 * 内容置顶
	 * 
	 * @param param
	 * @return
	 */
	int contentSetTop(JSONObject param);

	/**
	 * 查询内容
	 * 
	 * @param param
	 * @return
	 */
	Content getContent(JSONObject param);

	/**
	 * 修改内容
	 * 
	 * @param param
	 * @return
	 */
	int updateContent(JSONObject param);
}
