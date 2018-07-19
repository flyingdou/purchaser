package com.purchaser.wechat;
/*
 * 作者: dou
 * 时间: 2018-06-19 10:12:13
 * des: JSAPI签名
 * */
public class JSAPIRequest {
	
    /**
     * 被签名的前端url
     */
    private String url;
    

    /**
     * 有参构造函数   
     * @param url
     */
    public JSAPIRequest(String url) {
		super();
		this.url = url;
	}
    
    
	/**
     * setter && getter
     */
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

    
    
    

    

    
    
    
    
	
	
}
