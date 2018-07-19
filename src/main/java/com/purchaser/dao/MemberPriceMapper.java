package com.purchaser.dao;

import com.purchaser.pojo.MemberPrice;

public interface MemberPriceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberPrice record);

    int insertSelective(MemberPrice record);

    MemberPrice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberPrice record);

    int updateByPrimaryKey(MemberPrice record);
}