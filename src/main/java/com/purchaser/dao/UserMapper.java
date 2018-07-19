package com.purchaser.dao;

import java.util.Map;

import com.purchaser.pojo.User;

public interface UserMapper {
	int deleteByPrimaryKey(Long id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	/**
	 * 根据id查询用户信息
	 * 
	 * @param userId
	 * @return
	 */
	Map<String, Object> findUser(Long userId);

	User findUserByWechatId(String wechatId);

	/**
	 * 查询当前登录用户是否为会员
	 * 
	 * @param userId
	 * @return
	 */
	int checkIsMember(Long userId);
}