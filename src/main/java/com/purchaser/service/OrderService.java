package com.purchaser.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.purchaser.pojo.User;

/**
 * 
 * @author 华文
 * @Date 2018-07-18
 * @Time 14:17
 *
 */
public interface OrderService {

	/**
	 * 获取活动商品信息
	 */
	public Map<String, Object> getActiveProductInfo(User user);

	/**
	 * 创建订单
	 * 
	 * @param param
	 * @return
	 */
	public String createOrder(JSONObject param, HttpServletRequest request) throws Exception;
	
	/**
	 * 支付回调，修改订单状态
	 * @param orderno
	 * @return
	 */
	public void updateOrderStatus (String orderno);
}
