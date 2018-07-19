package com.purchaser.wechat;

import javax.servlet.http.HttpServletRequest;

import com.purchaser.util.CommentUtils;

public class PayRequest {
	
	private String MCH_ID;
	
	private String key;
	
	private String openId;
	
	private String ip;
	
	
	/**
	 * 不需要appSecret的构造
	 * @param appId
	 * @param mCH_ID
	 * @param key
	 * @param openId
	 */
	public PayRequest(String mCH_ID, String key, String openId, HttpServletRequest request) {
		super();
		MCH_ID = mCH_ID;
		this.key = key;
		this.openId = openId;
		this.ip = CommentUtils.getIpAddr(request);
	}
	

	/**
	 * setter && getter
	 */
	public String getMCH_ID() {
		return MCH_ID;
	}

	public void setMCH_ID(String mCH_ID) {
		MCH_ID = mCH_ID;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	

}
