package com.purchaser.dao;

import com.purchaser.pojo.InvitationCode;

public interface InvitationCodeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InvitationCode record);

    int insertSelective(InvitationCode record);

    InvitationCode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InvitationCode record);

    int updateByPrimaryKey(InvitationCode record);
}