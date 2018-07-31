package com.purchaser.dao;

import java.util.List;
import java.util.Map;

import com.purchaser.pojo.Member;

public interface MemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
    
    /**
     * 查询会员详情接口
     * @param paramMap
     * @return
     */
    Map<String, Object> findMemberInfo (Map<String, Object> paramMap);
    
    
    /**
     * 查询会员价格
     * @param userId
     * @return
     */
    Map<String, Object> getMemberPrice (Long userId);
    
    /**
     * 查询会员简要信息
     * @param paramMap
     * @return
     */
    Map<String, Object> memberSimple (Map<String, Object> paramMap);
    
    
    /**
     * 查询会员列表
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getMemberList (Map<String, Object> paramMap);
    
    /**
     * 查询会员总数量
     * @param paramMap
     * @return
     */
    Integer memberListCount(Map<String, Object> paramMap);
    
}