package com.purchaser.service;
/*
 * 作者: dou
 * 时间: 2018-07-11 10:09:52
 * */

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

public interface MemberService {

	/**
	 * 查询会员基本信息
	 * @param userId
	 * @return
	 */
	public JSONObject findMemberInfo (Long userId);
	
	/**
	 * 保存会员数据
	 * @param param
	 * @return
	 */
	public JSONObject saveMember (JSONObject param, HttpServletRequest request);
	
	/**
	 * 查询用户的会员价格信息
	 * @param userId
	 * @return
	 */
	public JSONObject getMemberPrice (Long userId);
	
	
	/**
	 * 查询会员简要信息
	 * @param userId
	 * @return
	 */
	public JSONObject memberSimple (Long userId);
	
	
	
}
