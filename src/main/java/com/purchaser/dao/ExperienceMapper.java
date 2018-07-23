package com.purchaser.dao;

import java.util.List;
import java.util.Map;

import com.purchaser.pojo.Experience;

public interface ExperienceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Experience record);

    int insertSelective(Experience record);

    Experience selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Experience record);

    int updateByPrimaryKeyWithBLOBs(Experience record);

    int updateByPrimaryKey(Experience record);
    
    /**
     * 查询当前用户的工作履历
     * @param userId
     * @return
     */
    List<Map<String, Object>> getExperiences (String userId);
}