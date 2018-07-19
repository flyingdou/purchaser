package com.purchaser.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.purchaser.pojo.Content;

public interface ContentMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Content record);

	int insertSelective(Content record);

	Content selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Content record);

	int updateByPrimaryKey(Content record);

	/**
	 * 查询内容列表
	 * 
	 * @param param
	 * @return
	 */
	List<Content> getContentList(JSONObject param);
}