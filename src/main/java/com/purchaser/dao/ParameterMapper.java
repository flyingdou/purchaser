package com.purchaser.dao;

import java.util.List;
import java.util.Map;

import com.purchaser.pojo.Parameter;

public interface ParameterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Parameter record);

    int insertSelective(Parameter record);

    Parameter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Parameter record);

    int updateByPrimaryKey(Parameter record);
    
    List<Map<String, Object>> findParameterByParent (Map<String, Object> paramMap);
}