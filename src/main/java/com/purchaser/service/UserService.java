package com.purchaser.service;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.purchaser.pojo.User;

/*
 * 作者: dou
 * 时间: 2018-06-21 09:18:02
 * des: user逻辑层
 * */
public interface UserService {

	/**
	 * 根据id查询user基本信息
	 * @param userId
	 * @return
	 */
	public JSONObject findUser (Long userId);
	
	/**
	 * 根据id查询用户Bean
	 * @param userId
	 * @return
	 */
	public User findUserById (Long userId);
	
	/**
	 * wechatLogin
	 * @param param
	 * @param request
	 * @return
	 */
	public JSONObject wechatLogin (JSONObject param, HttpServletRequest request);
	
	/**
	 * 查询当前用户是否是会员
	 * @param userId
	 * @return
	 */
	public Boolean userIsMember (Long userId);
}
