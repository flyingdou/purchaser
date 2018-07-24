package com.purchaser.dao;

import java.util.List;
import java.util.Map;

import com.purchaser.pojo.Active;
import com.purchaser.pojo.InvitationCode;

public interface ActiveMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Active record);

	int insertSelective(Active record);

	Active selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Active record);

	int updateByPrimaryKey(Active record);

	/**
	 * 批量添加活动邀请码
	 * 
	 * @param list
	 * @return
	 */
	int addActiveCodeList(List<InvitationCode> list);

	/**
	 * 查询挑战列表
	 * 
	 * @param type
	 * @return
	 */
	List<Active> getActiveList(Map<String, Object> param);

	/**
	 * 查询挑战列表(后台管理系统)
	 * 
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> getActiveListForAdmin(Map<String, Object> param);

	/**
	 * 管理系统查询活动列表总数
	 * 
	 * @param param
	 * @return
	 */
	int getActiveListCountForAdmin();

	/**
	 * 根据id查询参加的用户列表
	 * 
	 * @param activeId
	 * @return
	 */
	List<Map<String, Object>> getJionUserListById(String activeId);

	/**
	 * 检查用户是否已经参加活动
	 * 
	 * @param queryParam
	 * @return
	 */
	int checkUserJoinActiveByActiveId(Map<String, Object> queryParam);

	/**
	 * 校验活动邀请码
	 * 
	 * @param queryParam
	 * @return
	 */
	Map<String, Object> checkActiveCode(Map<String, Object> queryParam);

	/**
	 * 修改活动邀请码更改为已使用
	 * 
	 * @param activeCodeId
	 * @return
	 */
	int updateActiveCodeUse(long activeCodeId);

	/**
	 * 修改活动状态
	 * 
	 * @param param
	 * @return
	 */
	int changeActiveStatus(Map<String, Object> param);
}