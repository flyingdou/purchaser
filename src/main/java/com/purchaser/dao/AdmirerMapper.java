package com.purchaser.dao;

import com.purchaser.pojo.Admirer;
import com.purchaser.pojo.AdmirerWithBLOBs;

public interface AdmirerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdmirerWithBLOBs record);

    int insertSelective(AdmirerWithBLOBs record);

    AdmirerWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdmirerWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AdmirerWithBLOBs record);

    int updateByPrimaryKey(Admirer record);
}