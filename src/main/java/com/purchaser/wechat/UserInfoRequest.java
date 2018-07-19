package com.purchaser.wechat;
/*
 * 作者: dou
 * 时间: 2018-07-12 13:17:23
 * */
public class UserInfoRequest {
	
	/**
	 * openid
	 */
	private String openId;
	
	/**
	 * 请求链接
	 */
	private String reqUrl = "https://api.weixin.qq.com/cgi-bin/user/info?";

	
	
	/**
	 * 有参构造函数
	 * @param openId
	 */
	public UserInfoRequest() {
		super();
	}

	/**
	 * setter && getter
	 * @return
	 */
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getReqUrl() {
		return reqUrl;
	}

	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}
	
	

}
