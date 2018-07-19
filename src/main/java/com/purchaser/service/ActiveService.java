package com.purchaser.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface ActiveService {

	/**
	 * 查询挑战列表
	 * 
	 * @param param
	 * @return
	 */
	JSONObject getActiveList(JSONObject param);

	/**
	 * 根据id查询挑战信息
	 * 
	 * @param param
	 * @return
	 */
	Map<String, Object> getActiveById(JSONObject param);

	/**
	 * 活动签到
	 * 
	 * @param param
	 * @return
	 */
	JSONObject sign(JSONObject param);

	/**
	 * 验证活动邀请码
	 * @param param
	 * @return
	 */
	Map<String, Object> checkActiveCode(JSONObject param);
}
