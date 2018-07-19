package com.purchaser.wechat;

/*
 * 作者: dou
 * 时间: 2018-07-11 17:03:50
 * 从微信服务器下载，已上传的图片
 * */
public class DownloadPictureRequest {
	
	/**
	 * reqUrl
	 */
	private String reqUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

	public String getReqUrl() {
		return reqUrl;
	}

	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}


  
	
}
