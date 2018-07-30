package com.purchaser.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/*
 * 作者: dou
 * 时间: 2018-07-30 09:29:56
 * desc: 短信验证码
 * */
public class Validcode {

	/**
	 * 手机号
	 */
	private String mobilephone;
	
	/**
	 * 验证码
	 */
	private String code;
	
	/**
	 * request
	 */
	private HttpServletRequest request;
	
	
	
	/**
	 * 有参构造器
	 * @param mobilephone
	 * @param code
	 */
	public Validcode(String mobilephone, HttpServletRequest request) {
		super();
		this.mobilephone = mobilephone;
		this.request = request;
	}

	
	
	
	/**
	 * 校验验证码是否正确
	 * @param code
	 * @return
	 */
	public Boolean isRightful (String code) {
		String mobilephone = this.mobilephone;
		String sessionCode = (String) request.getSession().getAttribute(mobilephone);
		if (StringUtils.isNotBlank(sessionCode) && sessionCode.equals(code)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	
	
	/**
	 * setter && getter
	 * @return
	 */
	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	/**
	 * 生成随机的6位数验证码
	 * @return
	 */
	public String getCode() {
		this.code = CommentUtils.getRandom(6);
		// 将code存储起来
		this.request.getSession().setAttribute(mobilephone, this.code);
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	



	
	
	
}
