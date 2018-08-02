package com.purchaser.wechat;
/*
 * 作者: dou
 * 时间: 2018-08-01 11:18:25
 * desc: 发送微信模板消息request类
 * */

import com.alibaba.fastjson.JSONObject;

public class SendTemplateRequest {
	
	/**
	 * 发送微信模板的请求连接
	 */
	private String reqUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	
	
	/**
	 * openid
	 */
	private String openid;
	
	/**
	 * 模板id
	 */
	private String template_id;
	
	/**
	 * 用户点击详情时访问的链接(url)
	 */
	private String url;
	
	/**
	 * 数据包
	 */
	private JSONObject dataJson;

	
	
	/**
	 * 有参构造函数
	 * @param openid
	 * @param template_id
	 * @param dataJson
	 */
	public SendTemplateRequest(String openid, String template_id, String url, JSONObject dataJson) {
		super();
		this.openid = openid;
		this.template_id = template_id;
		this.url = url;
		this.dataJson = dataJson;
		
	}

	/**
	 * setter && getter 
	 * @return
	 */
	public String getReqUrl() {
		return reqUrl;
	}

	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public JSONObject getDataJson() {
		return dataJson;
	}

	public void setDataJson(JSONObject dataJson) {
		this.dataJson = dataJson;
	}
	
	
	
	
	
	
}
