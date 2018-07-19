package com.purchaser.wechat;
/*
 * 作者: dou
 * 时间: 2018-06-19 10:33:35
 * des: 微信签名专用类
 * */

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.purchaser.util.HttpRequestUtils;

public class SignManager {
	
	private String JSAPI_TICKET;
	
	private String ACCESS_TOKEN;
	
	private String APP_ID;
	
	private String APP_SECRET;
	
	
	/**
	 * jsapi签名
	 */
	public String jsapiSign(JSAPIRequest jsapiRequest) {
		JSONObject result = new JSONObject();
			String jsapi_ticket = JSAPI_TICKET;
			String nonce_str = UUID.randomUUID().toString();
			String timestamp = Long.toString(System.currentTimeMillis() / 1000);
			String code = null;
			String signature = null;
			// 注意这里参数名必须全部小写，且必须有序
			code = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + jsapiRequest.getUrl();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.reset();
			md.update(code.getBytes("UTF-8"));
			signature = byteToHex(md.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.fluentPut("appid", APP_ID);
		result.fluentPut("timestamp", timestamp);
		result.fluentPut("nonceStr", nonce_str);
		result.fluentPut("signature", signature);
		return result.toString();
	}
	
	/**
	 * 获取access_token、jsapi_tiket
	 */
	public void getAccessToken () {
		String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
				+ APP_ID + "&secret=" + APP_SECRET;
		JSONObject json = JSONObject.parseObject(HttpRequestUtils.httpRequest(GET_ACCESS_TOKEN_URL, null));
		String access_token = json.getString("access_token");
		this.ACCESS_TOKEN = access_token;
		String GET_JSAPI_TICKET_Url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
				+ access_token + "&type=jsapi";
		json = JSONObject.parseObject(HttpRequestUtils.httpRequest(GET_JSAPI_TICKET_Url, null));
		this.JSAPI_TICKET = json.getString("ticket");
	}


	
	/**
	 * 加密
	 * 
	 * @param hash
	 * @return
	 */
	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
	
	
	/**
	 * 构造函数
	 * @param aPP_ID
	 * @param aPP_SECRET
	 * @param jsapiURL
	 */
	public SignManager(String aPP_ID, String aPP_SECRET) {
		super();
		APP_ID = aPP_ID;
		APP_SECRET = aPP_SECRET;
		getAccessToken();
	}

	/**
	 * setter && getter
	 */
	public String getJSAPI_TICKET() {
		return JSAPI_TICKET;
	}

	public void setJSAPI_TICKET(String jSAPI_TICKET) {
		JSAPI_TICKET = jSAPI_TICKET;
	}

	public String getACCESS_TOKEN() {
		return ACCESS_TOKEN;
	}

	public void setACCESS_TOKEN(String aCCESS_TOKEN) {
		ACCESS_TOKEN = aCCESS_TOKEN;
	}

	public String getAPP_ID() {
		return APP_ID;
	}

	public void setAPP_ID(String aPP_ID) {
		APP_ID = aPP_ID;
	}

	public String getAPP_SECRET() {
		return APP_SECRET;
	}

	public void setAPP_SECRET(String aPP_SECRET) {
		APP_SECRET = aPP_SECRET;
	}

	
	
	

}
