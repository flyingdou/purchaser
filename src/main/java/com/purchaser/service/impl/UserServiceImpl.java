package com.purchaser.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.purchaser.constants.Constant;
import com.purchaser.dao.UserMapper;
import com.purchaser.pojo.PageInfo;
import com.purchaser.pojo.User;
import com.purchaser.service.UserService;
import com.purchaser.util.CommentUtils;
import com.purchaser.wechat.OpenIdRequest;
import com.purchaser.wechat.UserInfoRequest;
import com.purchaser.wechat.WechatApiManager;

/*
 * 作者: dou
 * 时间: 2018-06-21 09:20:33
 * */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 根据id查询用户基本信息
	 */
	@Override
	public JSONObject findUser(Long userId) {
		// 查询用户的基本信息
		Map<String, Object> userInfo = userMapper.findUser(userId);
		return JSON.parseObject(JSON.toJSONString(userInfo));
	}
	
	/**
	 * 根据id查询用户信息Bean
	 */
	@Override
	public User findUserById (Long userId) {
		User user = userMapper.selectByPrimaryKey(userId);
		return user;
	}

	/**
	 * wechatLogin
	 */
	@Override
	public JSONObject wechatLogin(JSONObject param, HttpServletRequest request) {
		JSONObject ret = new JSONObject();
		try {
			// 获取openId 
			WechatApiManager wechatManager = new WechatApiManager(Constant.APP_ID, Constant.APP_SECRET);
			OpenIdRequest openIdRequest = new OpenIdRequest(param.getString("code"));
			JSONObject resJson = wechatManager.getOpenId(openIdRequest);
			if (resJson.containsKey("openid")) {
				// 请求到了openid, 根据openid查询用户信息
				UserInfoRequest userInfoRequest = new UserInfoRequest();
				JSONObject userInfo = wechatManager.getUserInfo(userInfoRequest ,resJson.getString("openid"));
				
				// 当前用户关注了公众号
				if (userInfo.getInteger("subscribe") == 1) {
					// 查询当前openid在用户表中是否存在，存在就刷新数据，不存在就注册用户
					User user = userMapper.findUserByWechatId(resJson.getString("openid"));
					// 没有查询到数据，就新注册用户
					if (user == null) {
						user = new User();
						
						String name = userInfo.getString("nickname");
						// 微信昵称特殊字符过滤
						name = CommentUtils.cutStringEmoji(name);
						
						// 省份
						String province =userInfo.getString("province");
						
						// 城市
						String city = userInfo.getString("city");
						
						// 图片名称 
						String filename = "";
						if (userInfo.containsKey("headimgurl") && StringUtils.isNotEmpty(userInfo.getString("headimgurl"))) {
							filename = CommentUtils.getRandomByDate(6) + ".jpg";
							// 下载用户头像
							CommentUtils.download(userInfo.getString("headimgurl"), filename, Constant.PICTURE_PATH);
						}
						
						// 姓名 暂时已微信昵称代替
						user.setName(name);
						// 籍贯信息
						user.setConcord(province + "/" + city);
						// 性别  男:M  女:F
						user.setGender(userInfo.getInteger("sex") == 1 ? "M" : "F");
						// 头像
						user.setImage(filename);
						// 用户权限
						user.setPower(0);
						// 微信唯一标识
						user.setWechatId(resJson.getString("openid"));
						// 注册时间
						user.setRegisterDate(new Date());
						
						// 持久化用户数据
						userMapper.insert(user);
					}
					
					// 将用户登录信息存储起来
					request.getSession().setAttribute("user", user);
					ret.fluentPut("success", true)
					   .fluentPut("message", "OK")
					   .fluentPut("openId", resJson.getString("openid"))
					   ;
				}
				
				// 用户未关注当前微信公众号
				if (userInfo.getInteger("subscribe") == 0) {
					ret.fluentPut("success", false)
					   .fluentPut("message", "该用户暂未关注本公众号")
					   .fluentPut("subscribe", 0)
					   ;
				}
				
				
			}
			
		} catch (Exception e) {
			ret.fluentPut("success", false)
			   .fluentPut("message", e.toString());
			
		}
		
		return ret;
	}

	/**
	 * 判断当前用户，是否是会员
	 */
	@Override
	public Boolean userIsMember(Long userId) {
		Boolean flag = false;
		Integer res = userMapper.checkIsMember(userId);
		if (res > 0) {
			flag = true;
		}
		return flag;
	}

	
	/**
	 * 获取微信用户列表
	 */
	@Override
	public PageInfo userList4admin(JSONObject param) {
		PageInfo pageInfo = null;
		pageInfo = JSONObject.toJavaObject(param, PageInfo.class);
		param.fluentPut("start", pageInfo.getStart());
		// 查询用户列表
		List<Map<String, Object>> userList = userMapper.getUserList4admin(param);
		
		// 查询totalCount
		int count = userMapper.getUserListCount4admin();
		pageInfo.setTotalCount(count);
		pageInfo.setData(userList);
		
		// 返回数据
		return pageInfo;
	}

}
