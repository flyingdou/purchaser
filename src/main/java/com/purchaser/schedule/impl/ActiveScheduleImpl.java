package com.purchaser.schedule.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.purchaser.constants.Constant;
import com.purchaser.dao.ActiveMapper;
import com.purchaser.schedule.ActiveSchedule;
import com.purchaser.util.CommentUtils;
import com.purchaser.wechat.SendTemplateRequest;
import com.purchaser.wechat.WechatApiManager;

/*
 * 作者: dou
 * 时间: 2018-08-01 17:29:22
 * desc: 活动定时任务实现类
 * */
@Transactional
@Component
@Configuration
@EnableAsync 
@EnableScheduling
public class ActiveScheduleImpl implements ActiveSchedule {

	/**
	 * 注入activeMapper对象
	 */
	@Autowired
	private ActiveMapper activeMapper;
	
	
	
	/**
	 * 活动开始前(24小时内)
	 */
	@Scheduled(cron = "1/60 * * * * ?") // 秒 分 时 日 月 周 * 年
	@Override
	public void activeNotify() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("product_type", Constant.ACTIVE_PRODUCT_TYPE);
		param.put("status", Constant.ORDER_STATUS_PAIED);
		param.put("interval_value", Constant.ACTIVE_NOTIFY_INTERVAL_VALUE);
		// 查询数据
		List<Map<String, Object>> activeNotifyList = activeMapper.activeNotify(param);
		
		// 初始化wechatManager
		WechatApiManager wechatManager = new WechatApiManager(Constant.APP_ID, Constant.APP_SECRET);
		String template_id = Constant.ACTIVE_STARTING_NOTIFY_TEMPLATE;
		String url = "";
		// 给查到的用户发微信模板消息提醒
		for (Map<String, Object> active : activeNotifyList) {
			String openid = active.get("wechat_id") + "";
			JSONObject dataJson = new JSONObject();
			dataJson.fluentPut("first", new JSONObject().fluentPut("value", Constant.ACTIVE_STARTING_NOTIFY_FIRST))
			        .fluentPut("keyword1", new JSONObject().fluentPut("value", active.get("name")))
			        .fluentPut("keyword2", new JSONObject().fluentPut("value", active.get("host_unit")))
			        .fluentPut("keyword3", new JSONObject().fluentPut("value", active.get("address")))
			        .fluentPut("keyword4", new JSONObject().fluentPut("value", CommentUtils.format((Date)active.get("start_date"), "yyyy-MM-dd HH:mm:ss")))
			        .fluentPut("remark", new JSONObject().fluentPut("value", Constant.ACTIVE_STARTING_NOTIFY_REMARK))
			        ;
			SendTemplateRequest sendTemplateRequest = new SendTemplateRequest(openid, template_id, url, dataJson);
		    
			// 发送模板消息
			wechatManager.sendTemplateMessage(sendTemplateRequest);
		}
		
		
		
	}

}
