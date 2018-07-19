package com.purchaser.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.purchaser.constants.Constant;
import com.purchaser.dao.ActiveMapper;
import com.purchaser.dao.SignMapper;
import com.purchaser.pojo.Active;
import com.purchaser.pojo.Sign;
import com.purchaser.service.ActiveService;
import com.purchaser.util.CommentUtils;

@Service
@Transactional
public class ActiveServiceImpl implements ActiveService {

	@Autowired
	private ActiveMapper activeMapper;

	@Autowired
	private SignMapper signMapper;

	/**
	 * 查询挑战列表
	 */
	@Override
	public JSONObject getActiveList(JSONObject param) {
		List<Active> activeList = activeMapper.getActiveList(param.getIntValue("type"));
		JSONArray activeListJson = new JSONArray();
		for (Active active : activeList) {
			// 活动状态: 0.未开始, 1.进行中, 2.已结束
			int status = 0;
			if (active.getStartDate().getTime() < new Date().getTime()) {
				status = 0;
			} else if (active.getStartDate().getTime() >= new Date().getTime()
					&& active.getStartDate().getTime() <= new Date().getTime()) {
				status = 1;
			} else {
				status = 2;
			}
			JSONObject activeJson = JSONObject.parseObject(JSON.toJSONStringWithDateFormat(active, "yyyy-MM-dd HH:mm"));
			activeJson.fluentPut("status", status);
			activeListJson.add(activeJson);
		}
		JSONObject result = new JSONObject();
		result.fluentPut("success", true).fluentPut("activeList", activeListJson);
		return result;
	}

	/**
	 * 根据id查询活动信息
	 */
	@Override
	public Map<String, Object> getActiveById(JSONObject param) {
		Map<String, Object> result = new HashMap<String, Object>();
		Active active = activeMapper.selectByPrimaryKey(param.getLong("activeId"));
		List<Map<String, Object>> userList = activeMapper.getJionUserListById(param.getString("activeId"));
		result.put("active", active);
		result.put("userList", userList);
		return result;
	}

	/**
	 * 活动签到
	 */
	@Override
	public JSONObject sign(JSONObject param) {
		JSONObject result = new JSONObject();
		Active active = activeMapper.selectByPrimaryKey(param.getLong("resultStr"));

		// 签到检查1: 当前用户是否加入本活动
		if (activeMapper.checkUserJoinActiveByActiveId(param) < 1) {
			result.fluentPut("success", false).fluentPut("message", "您没有报名参加本次活动 , 请联系会务工作人员");
			return result;
		}

		// 签到检查2: 当前用户签到位置
		double distance = CommentUtils.GetDistance(param.getDoubleValue("latitude"), param.getDoubleValue("longitude"),
				active.getLatitude(), active.getLongitude());
		if (distance > Constant.SIGN_MAX_DISTANCE) {
			result.fluentPut("success", false).fluentPut("message", "签到距离超出3000米 , 无法签到");
			return result;
		}

		// 签到检查通过: 生成签到数据
		Sign sign = new Sign();
		sign.setActive(active.getId());
		sign.setUser(param.getLong("userId"));
		sign.setStatus(1);
		sign.setSignDate(new Date());
		signMapper.insertSelective(sign);
		result.fluentPut("success", true).fluentPut("message", "欢迎您光临本次活动 ! 请根据会务组安排入座");

		return result;
	}

	/**
	 * 验证活动邀请码
	 */
	@Override
	public Map<String, Object> checkActiveCode(JSONObject param) {
		
		return activeMapper.checkActiveCode(param);
	}
}
