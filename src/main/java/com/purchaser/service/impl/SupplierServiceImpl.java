package com.purchaser.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.purchaser.constants.Constant;
import com.purchaser.dao.SupplierMapper;
import com.purchaser.pojo.PageInfo;
import com.purchaser.pojo.Supplier;
import com.purchaser.pojo.User;
import com.purchaser.service.SupplierService;
import com.purchaser.wechat.WechatApiManager;

/**
 * 
 * @author 华文
 *
 */
@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierMapper supplierMapper;

	/**
	 * 供应商录入
	 * 
	 * @param json
	 * @param response
	 */
	@Override
	public Map<String, Object> release(Supplier supplier, User user) {
		// 处理图片 , 当前图片存在微信服务器中, 需要下载到本服务器上
		WechatApiManager wechatApiManager = new WechatApiManager(Constant.APP_ID, Constant.APP_SECRET);
		if (supplier.getImage1() != null) {
			String image1 = wechatApiManager.downloadPicture(supplier.getImage1());
			supplier.setImage1(image1);
		}
		if (supplier.getImage2() != null) {
			String image2 = wechatApiManager.downloadPicture(supplier.getImage2());
			supplier.setImage1(image2);
		}
		if (supplier.getImage3() != null) {
			String image3 = wechatApiManager.downloadPicture(supplier.getImage3());
			supplier.setImage1(image3);
		}
		supplier.setCreator(user.getId());
		supplier.setAudit(Constant.AUDIT_STATUS_NOT);
		supplier.setApplyDate(new Date());
		Map<String, Object> result = new HashMap<String, Object>();
		int code = supplierMapper.insertSelective(supplier);
		result.put("success", true);
		result.put("code", code);
		return result;
	}

	/**
	 * 查询供应商列表
	 */
	@Override
	public List<Supplier> getSupplierList(JSONObject param) {
		// 调用mapper接口
		return supplierMapper.getSupplierList(param);
	}

	/**
	 * 查询供应商列表(后台管理系统)
	 */
	@Override
	public PageInfo getSupplierListForAdmin(JSONObject param) {
		PageInfo pageInfo = JSONObject.toJavaObject(param, PageInfo.class);
		param.fluentPut("start", pageInfo.getStart());
		List<Map<String, Object>> supplierList = supplierMapper.getSupplierListForAdmin(param);
		int totalCount = supplierMapper.getSupplierListForAdminCount();
		pageInfo.setTotalCount(totalCount);
		pageInfo.setData(supplierList);
		return pageInfo;
	}

	/**
	 * 查询供应商
	 * 
	 * @param json
	 * @param response
	 */
	@Override
	public Supplier getSupplier(JSONObject param) {
		return supplierMapper.selectByPrimaryKey(param.getLong("supplierId"));
	}

	/**
	 * 供应商审核
	 */
	@Override
	public int supplierAudit(JSONObject param) {
		return supplierMapper.supplierAudit(param);
	}
}
