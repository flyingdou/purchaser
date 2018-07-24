package com.purchaser.service;
/*
 * 作者: dou
 * 时间: 2018-07-24 10:46:59
 * desc: 采购师业务逻辑接口
 * */

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

public interface AdmirerService {

	/**
	 * 保存采购师
	 * @param param
	 * @return
	 */
	public JSONObject saveAdmirer (JSONObject param, HttpSession session);
	
	/**
	 * 查询采购师列表
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> getAdmirerList (JSONObject param);
}
