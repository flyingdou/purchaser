package com.purchaser.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.purchaser.pojo.PageInfo;
import com.purchaser.pojo.Supplier;
import com.purchaser.pojo.User;

/**
 * 
 * @author 华文
 *
 */
public interface SupplierService {

	/**
	 * 供应商录入
	 * 
	 * @param json
	 * @param response
	 */
	Map<String, Object> release(Supplier supplier, User user);

	/**
	 * 查询供应商列表
	 * 
	 * @param param
	 * @return
	 */
	List<Supplier> getSupplierList(JSONObject param);

	/**
	 * 查询供应商
	 * 
	 * @param json
	 * @param response
	 */
	Supplier getSupplier(JSONObject param);

	/**
	 * 查询供应商
	 * 
	 * @param param
	 * @return
	 */
	PageInfo getSupplierListForAdmin(JSONObject param);

	/**
	 * 供应商审核
	 * 
	 * @param param
	 * @return
	 */
	int supplierAudit(JSONObject param);
}
