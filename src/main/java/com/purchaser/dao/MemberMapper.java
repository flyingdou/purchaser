package com.purchaser.dao;

import java.util.Map;

import com.purchaser.pojo.Member;

public interface MemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
    
    Map<String, Object> findMemberInfo (Map<String, Object> paramMap);
    
    Map<String, Object> getMemberPrice (Long userId);
}