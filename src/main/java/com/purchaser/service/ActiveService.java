package com.purchaser.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.purchaser.pojo.Active;
import com.purchaser.pojo.PageInfo;

public interface ActiveService {

	/**
	 * 发起活动
	 * 
	 * @param active
	 * @return
	 */
	void release(Active active);

	/**
	 * 查询挑战列表
	 * 
	 * @param param
	 * @return
	 */
	List<Active> getActiveList(JSONObject param);

	/**
	 * 查询挑战列表(后台管理系统)
	 * 
	 * @param param
	 * @return
	 */
	PageInfo getActiveListForAdmin(JSONObject param);

	/**
	 * 根据id查询挑战信息
	 * 
	 * @param param
	 * @return
	 */
	Map<String, Object> getActiveById(JSONObject param);

	/**
	 * 修改活动数据
	 * 
	 * @param param
	 * @return
	 */
	int updateActive(JSONObject param);

	/**
	 * 活动签到
	 * 
	 * @param param
	 * @return
	 */
	JSONObject sign(JSONObject param);

	/**
	 * 验证活动邀请码
	 * 
	 * @param param
	 * @return
	 */
	Map<String, Object> checkActiveCode(JSONObject param);

	/**
	 * 改变活动状态
	 * 
	 * @param param
	 * @return
	 */
	int changeActiveStatus(JSONObject param);
}
