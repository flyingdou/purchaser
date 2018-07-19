package com.purchaser.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.purchaser.constants.Constant;
import com.purchaser.dao.MemberMapper;
import com.purchaser.dao.MemberPriceMapper;
import com.purchaser.dao.UserMapper;
import com.purchaser.pojo.Member;
import com.purchaser.pojo.MemberPrice;
import com.purchaser.pojo.User;
import com.purchaser.service.MemberService;
import com.purchaser.util.CommentUtils;

/*
 * 作者: dou
 * 时间: 2018-07-11 10:10:44
 * */
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private MemberPriceMapper mpMapper;
	
	@Override
	public JSONObject findMemberInfo(Long userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("valid", Constant.MEMBER_VALID);
		Map<String, Object> memberInfoMap = memberMapper.findMemberInfo(paramMap);
		return JSON.parseObject(JSON.toJSONString(memberInfoMap, CommentUtils.getValueFilterNullStringFillNull()));
	}

	@Override
	public JSONObject saveMember(JSONObject param, HttpServletRequest request) {
		// 更新用户基本数据
		User user = (User) request.getSession().getAttribute("user");
		user.setName(param.getString("name"));
		user.setMobilephone(param.getString("mobilephone"));
		user.setImage(param.getString("image"));
		user.setEmail(param.getString("email"));
		user.setIdCardNum(param.getString("id_card_num"));
		user.setConcord(param.getString("province") + "/" + param.getString("city"));
		// 持久化数据
		userMapper.updateByPrimaryKeySelective(user);
		
		request.getSession().setAttribute("user", user);
		
		// 新增会员费用数据
		MemberPrice mp = new MemberPrice();
		mp.setPrice(param.getLong("price"));
		mp.setType(param.getInteger("type_id"));
		mp.setTypeName(param.getString("type"));
		mpMapper.insert(mp);
		
		
		// 增加会员数据
		Member member = new Member();
		member.setUser(user.getId());
		member.setAffiliation(param.getString("affiliation"));
		member.setType(mp.getId().intValue());
		member.setCompanyType(param.getInteger("company_type_id"));
		member.setBusiness(param.getInteger("business_id"));
		member.setDuty(param.getString("duty"));
		member.setApplyDate(new Date());
		member.setAudit(Constant.AUDIT_STATUS_NOT);
		member.setValid(Constant.MEMBER_INVALID);
		// 持久化数据
		memberMapper.insert(member);
		
		JSONObject ret = new JSONObject();
		ret.fluentPut("success", true)
		   .fluentPut("message", "OK")
		   .fluentPut("memberId", member.getId())
		   .fluentPut("price", param.getString("price"))
		   .fluentPut("type", param.getString("type"))
		   ;
		return ret;
	}

	/**
	 * 查询用户的会员价格信息
	 */
	@Override
	public JSONObject getMemberPrice(Long userId) {
		Map<String, Object> priceMap = memberMapper.getMemberPrice(userId);
		return JSON.parseObject(JSON.toJSONString(priceMap));
	}

}
