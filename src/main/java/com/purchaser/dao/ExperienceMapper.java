package com.purchaser.dao;

import com.purchaser.pojo.Experience;

public interface ExperienceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Experience record);

    int insertSelective(Experience record);

    Experience selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Experience record);

    int updateByPrimaryKeyWithBLOBs(Experience record);

    int updateByPrimaryKey(Experience record);
}