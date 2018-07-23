package com.purchaser.service;

import com.purchaser.pojo.User;

/**
 * 
 * @author 华文
 *
 */
public interface AdminService {

	/**
	 * 管理员登录
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	public User login(String account, String password);
}
