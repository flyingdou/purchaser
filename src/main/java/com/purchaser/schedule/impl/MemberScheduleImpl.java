package com.purchaser.schedule.impl;

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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.purchaser.constants.Constant;
import com.purchaser.dao.MemberMapper;
import com.purchaser.pojo.Member;
import com.purchaser.schedule.MemberSchedule;
import com.purchaser.util.CommentUtils;
import com.purchaser.wechat.SendTemplateRequest;
import com.purchaser.wechat.WechatApiManager;

/*
 * 作者: dou
 * 时间: 2018-08-02 13:36:47
 * desc: 会员定时任务实现类
 * */
@Transactional
@Component
@Configuration
@EnableAsync
@EnableScheduling
public class MemberScheduleImpl implements MemberSchedule {
	
	/**
	 * 注入memberMapper对象
	 */
	@Autowired
	private MemberMapper memberMapper;

	/**
	 * 会员续费提醒(每天11:00扫描提醒)
	 */
	@Scheduled(cron = "0 0 11 * * ?") // 秒 分 时 日 月 周 * 年
	@Override
	public void memberExpiredNotify() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("valid", Constant.MEMBER_VALID);
		paramMap.put("dayMin", Constant.MEMBER_BEFORE_EXPIRED_DAY -1);
		paramMap.put("dayMax", Constant.MEMBER_BEFORE_EXPIRED_DAY);
		// 查询数据
		List<Map<String, Object>> memberNotifyList = memberMapper.expirationList(paramMap);
		
		// 发送微信模板消息
		WechatApiManager wechatManager = new WechatApiManager(Constant.APP_ID, Constant.APP_SECRET);
		String template_id = Constant.MEMBER_VALID_EXPIRED_NOTIFY_TEMPLATE;
		String url = "";
		for (Map<String, Object> map : memberNotifyList) {
			JSONObject member = JSONObject.parseObject(JSON.toJSONString(map, CommentUtils.dateformatValue("yyyy-MM-dd")));
		    String openid = member.getString("wechat_id");
		    JSONObject dataJson = new JSONObject();
		    dataJson.fluentPut("first", new JSONObject().fluentPut("value", Constant.MEMBER_VALID_EXPIRED_NOTIFY_FIRST))
		            .fluentPut("keyword1", new JSONObject().fluentPut("value", member.getString("name")))
		            .fluentPut("keyword2", new JSONObject().fluentPut("value", member.getString("type_name")))
		            .fluentPut("keyword3", new JSONObject().fluentPut("value", member.getString("no")))
		            .fluentPut("keyword4", new JSONObject().fluentPut("value", member.getString("expiration")))
		            .fluentPut("remark", new JSONObject().fluentPut("value", Constant.MEMBER_VALID_EXPIRED_NOTIFY_REMARK))
		            ;
			SendTemplateRequest sendTemplateRequest = new SendTemplateRequest(openid, template_id, url, dataJson);
		    // 发送微信模板消息
			wechatManager.sendTemplateMessage(sendTemplateRequest);
		}
		

	}

	/**
	 * 会员过期状态修改(每个小时检查一次)
	 */
	@Scheduled(cron = "0 1/60 * * * ?") // 秒 分 时 日 月 周 * 年
	@Override
	public void memberExpired() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("valid", Constant.MEMBER_VALID);
		// 查询数据
		List<Map<String, Object>> memberList =  memberMapper.expiredMember(paramMap);
		
		// 把查询到的会员状态修改掉
		for (Map<String, Object> map : memberList) {
			Member member = new Member();
			member.setId((Long)map.get("id"));
			member.setValid(Constant.MEMBER_VALID_EXPIRED);
			
			// 持久化
			memberMapper.updateByPrimaryKeySelective(member);
			
		}
	}

}
