package com.purchaser.dao;

import java.util.List;
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
	
	/**
	 * 后台管理系统登录
	 * @param paramMap
	 * @return
	 */
	User adminLogin(Map<String, Object> paramMap);
	
	
	/**
	 * 查询用户列表
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> getUserList4admin (Map<String, Object> param);
	
	/**
	 * 查询用户列表总条数
	 * @return
	 */
	int getUserListCount4admin();
	
}