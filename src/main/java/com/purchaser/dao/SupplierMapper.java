package com.purchaser.dao;

import java.util.List;
import java.util.Map;

import com.purchaser.pojo.Supplier;

public interface SupplierMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Supplier record);

	int insertSelective(Supplier record);

	Supplier selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Supplier record);

	int updateByPrimaryKey(Supplier record);

	/**
	 * 查询供应商列表
	 * 
	 * @param param
	 * @return
	 */
	List<Supplier> getSupplierList(Map<String, Object> param);
}