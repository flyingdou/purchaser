package com.purchaser.wechat;
/*
 * 作者: dou
 * 时间: 2018-07-12 10:57:42
 * desc: 请求openId
 * */
public class OpenIdRequest {

	private String code;
	
	private String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&&grant_type=authorization_code";
	
	/**
	 * 有参构造函数
	 * @param code
	 */
	public OpenIdRequest(String code) {
		super();
		this.code = code;
		this.requestUrl = this.requestUrl.replace("CODE", code);
	}
	
	
	/**
	 * setter && getter
	 * @return
	 */
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	
}
