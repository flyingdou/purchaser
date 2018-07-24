package com.purchaser.service;
/*
 * 作者: dou
 * 时间: 2018-07-24 10:46:59
 * desc: 采购师业务逻辑接口
 * */

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

public interface AdmirerService {

	/**
	 * 保存采购师
	 * @param param
	 * @return
	 */
	public JSONObject saveAdmirer (JSONObject param, HttpSession session);
	
	
	
}
