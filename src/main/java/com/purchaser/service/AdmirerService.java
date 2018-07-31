package com.purchaser.service;
/*
 * 作者: dou
 * 时间: 2018-07-24 10:46:59
 * desc: 采购师业务逻辑接口
 * */

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.purchaser.pojo.PageInfo;

public interface AdmirerService {

	/**
	 * 保存采购师
	 * @param param
	 * @return
	 */
	public JSONObject saveAdmirer (JSONObject param, HttpServletRequest request);
	
	
	/**
	 * 查询采购师列表(微信前端)
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> getAdmirerList (JSONObject param);
	
	
	/**
	 * 通过采购师id查询user
	 * @param admirerId
	 * @return
	 */
	public JSONObject getUserByAdmirer (String admirerId);
	
	
	/**
	 * 查询采购师详情信息
	 * @param param
	 * @return
	 */
	public JSONObject admirerDetail (JSONObject param);
	
	
	/**
	 * 查询采购师列表(后台管理系统)
	 * @param param
	 * @return
	 */
	public PageInfo getAdmirerList4admin (JSONObject param);
	
	
	
	/**
	 * 修改采购师的审核状态
	 * @param param
	 * @return
	 */
	public Integer auditAdmirer (JSONObject param);
	
	
}
