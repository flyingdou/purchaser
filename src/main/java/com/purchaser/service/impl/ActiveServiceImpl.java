package com.purchaser.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.purchaser.constants.Constant;
import com.purchaser.dao.ActiveMapper;
import com.purchaser.dao.SignMapper;
import com.purchaser.pojo.Active;
import com.purchaser.pojo.InvitationCode;
import com.purchaser.pojo.PageInfo;
import com.purchaser.pojo.Sign;
import com.purchaser.service.ActiveService;
import com.purchaser.util.CommentUtils;

/**
 * 
 * @author 华文
 *
 */
@Service
@Transactional
public class ActiveServiceImpl implements ActiveService {

	@Autowired
	private ActiveMapper activeMapper;

	@Autowired
	private SignMapper signMapper;

	/**
	 * 发起活动
	 */
	@Override
	public void release(Active active) {
		// 首先生成一条活动数据
		active.setStatus(Constant.ACTIVE_STATUS_OPEN);
		active.setCreateDate(new Date());
		activeMapper.insertSelective(active);
		// 然后生成活动的邀请码(暂时20个(嘉宾10个, 厂商10个)), 保存到数据库
		List<InvitationCode> list = new ArrayList<InvitationCode>();
		for (int i = 0; i < 20; i++) {
			InvitationCode code = new InvitationCode();
			code.setActive(active.getId());
			code.setCode(UUID.randomUUID().toString().substring(0, 6));
			code.setEffective(Constant.ACTIVE_CODE_USE);
			code.setCreateDate(new Date());
			if (i < 10) {
				// 嘉宾
				code.setType(Constant.ACTIVE_CODE_DISTINGUISHED);
			} else {
				// 厂商
				code.setType(Constant.ACTIVE_CODE_MANUFACTURER);
			}
			list.add(code);
		}
		activeMapper.addActiveCodeList(list);
	}

	/**
	 * 查询挑战列表
	 */
	@Override
	public List<Active> getActiveList(JSONObject param) {
		return activeMapper.getActiveList(param);
	}

	/**
	 * 查询挑战列表(后台管理系统)
	 */
	@Override
	public PageInfo getActiveListForAdmin(JSONObject param) {
		PageInfo pageInfo = null;
		pageInfo = JSONObject.toJavaObject(param, PageInfo.class);
		param.fluentPut("start", pageInfo.getStart());
		List<Map<String, Object>> activeList = activeMapper.getActiveListForAdmin(param);
		int count = activeMapper.getActiveListCountForAdmin();
		pageInfo.setTotalCount(count);
		pageInfo.setData(activeList);
		return pageInfo;
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
	 * 修改活动数据
	 */
	@Override
	public int updateActive(JSONObject param) {
		Active active = JSONObject.toJavaObject(param, Active.class);
		return activeMapper.updateByPrimaryKeySelective(active);
	}

	/**
	 * 活动签到
	 */
	@Override
	public JSONObject sign(JSONObject param) {
		JSONObject result = new JSONObject();
		Active active = activeMapper.selectByPrimaryKey(param.getLong("activeId"));

		// 签到检查1: 当前用户是否加入本活动
		if (activeMapper.checkUserJoinActiveByActiveId(param) < 1) {
			result.fluentPut("success", false).fluentPut("message", "您没有报名参加本次活动 , 请联系会务工作人员");
			return result;
		}

		// 签到检查2: 当前用户签到位置
		double distance = CommentUtils.GetDistance(param.getDoubleValue("latitude"), param.getDoubleValue("longitude"),
				active.getLatitude().doubleValue(), active.getLongitude().doubleValue());
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

	/**
	 * 改变活动状态
	 */
	@Override
	public int changeActiveStatus(JSONObject param) {

		return activeMapper.changeActiveStatus(param);
	}
}
