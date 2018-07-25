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

	/**
	 * 查询供应商列表(后台管理系统)
	 * 
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> getSupplierListForAdmin(Map<String, Object> param);

	/**
	 * 查询供应商列表总数(后台管理系统)
	 * 
	 * @return
	 */
	int getSupplierListForAdminCount();

	/**
	 * 供应商审核
	 * 
	 * @param param
	 * @return
	 */
	int supplierAudit(Map<String, Object> param);
}