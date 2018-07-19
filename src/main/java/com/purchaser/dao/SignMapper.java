package com.purchaser.dao;

import com.purchaser.pojo.Sign;

public interface SignMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Sign record);

    int insertSelective(Sign record);

    Sign selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sign record);

    int updateByPrimaryKey(Sign record);
}