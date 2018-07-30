package com.purchaser.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.purchaser.constants.Constant;
import com.purchaser.dao.ActiveMapper;
import com.purchaser.dao.MemberMapper;
import com.purchaser.dao.OrderMapper;
import com.purchaser.dao.UserMapper;
import com.purchaser.pojo.Member;
import com.purchaser.pojo.Order;
import com.purchaser.pojo.User;
import com.purchaser.service.OrderService;
import com.purchaser.util.CommentUtils;
import com.purchaser.wechat.PayRequest;
import com.purchaser.wechat.WechatApiManager;

/**
 * 
 * @author 华文
 * @Date 2018-07-18
 * @Time 14:21
 *
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private ActiveMapper activeMapper;
	
	@Autowired
	private MemberMapper memberMapper;
	

	/**
	 * 获取活动商品信息
	 * 
	 */
	@Override
	public Map<String, Object> getActiveProductInfo(User user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int code = userMapper.checkIsMember(user.getId());
		resultMap.put("isMember", code);
		return resultMap;
	}

	/**
	 * 创建订单
	 * 
	 * @throws Exception
	 */
	@Override
	public String createOrder(JSONObject param, HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("user");

		// 生成订单
		Order order = new Order();
		order.setNo(CommentUtils.getRandomByDate(4));
		order.setProductId(param.getLong("productId"));
		order.setProductType(param.getString("productType"));
		order.setMoney(param.getDouble("price"));
		order.setUser(user.getId());
		order.setStatus(Constant.ORDER_STATUS_BEPAIED);
		order.setCreateDate(new Date());
		orderMapper.insertSelective(order);

		// 如果使用邀请码 , 修改邀请码为已使用
		if (param.containsKey("activeCodeId") && StringUtils.isNoneEmpty(param.getString("activeCodeId"))) {
			activeMapper.updateActiveCodeUse(param.getLongValue("activeCodeId"));
		}

		// 调用微信支付签名
		PayRequest payRequest = new PayRequest(Constant.MCH_ID, Constant.APP_KEY, user.getWechatId(), request);
		WechatApiManager wechatApiManager = new WechatApiManager(Constant.APP_ID, Constant.APP_SECRET);
		return wechatApiManager.paySign(payRequest, order);
	}

	
	/**
	 * 支付回调，修改订单状态
	 */
	@Override
	public void updateOrderStatus(String no) {
		// 根据订单号，查询订单信息
		Order order = orderMapper.findOrderByNo(no);
		
		// 会员订单 ，需要更新会员信息
		if (Constant.MEMBER_PRODUCT_TYPE.equals(order.getProductType())) {
			Member member = memberMapper.selectByPrimaryKey(order.getProductId());
			member.setValid(Constant.MEMBER_VALID);
			memberMapper.updateByPrimaryKeySelective(member);
		}
		
		// 修改订单状态
		order.setStatus(Constant.ORDER_STATUS_PAIED);
		order.setPayTime(new Date());
		orderMapper.updateByPrimaryKeySelective(order);
		
		
	}

}
