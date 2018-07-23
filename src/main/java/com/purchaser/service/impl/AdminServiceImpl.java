package com.purchaser.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.purchaser.dao.UserMapper;
import com.purchaser.pojo.User;
import com.purchaser.service.AdminService;

/**
 * 
 * @author 华文
 *
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 后台管理系统登录
	 */
	@Override
	public User login(String account, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", account);
		map.put("password", password);
		return userMapper.adminLogin(map);
	}

}
