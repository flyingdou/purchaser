package com.purchaser.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.purchaser.constants.Constant;


/*
 * 作者: dou
 * 时间: 2018-07-30 10:41:10
 * desc: 发送短信管理类
 * */
public class MessageManager {
	

	/**
	 * 发送短信
	 * @param mobilephone 手机号
	 * @param templateCode 短信模板id
	 * @param param 参数(json字符串)
	 */
    public static void sendSms(String mobilephone, String templateCode, String param) {
        try {
			//可自助调整超时时间
			System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
			System.setProperty("sun.net.client.defaultReadTimeout", "10000");

			//初始化acsClient,暂不支持region化
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", Constant.ACCESSKEY_ID, Constant.ACCESSKEY_SECRET);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", Constant.PRODUCT, Constant.DOMAIN);
			IAcsClient acsClient = new DefaultAcsClient(profile);

			//组装请求对象-具体描述见控制台-文档部分内容
			SendSmsRequest request = new SendSmsRequest();
			//必填:待发送手机号
			request.setPhoneNumbers(mobilephone);
			//必填:短信签名-可在短信控制台中找到
			request.setSignName(Constant.SIGN_NAME);
			//必填:短信模板-可在短信控制台中找到
			request.setTemplateCode(templateCode);
			//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
      
			request.setTemplateParam(param);

			//选填-上行短信扩展码(无特殊需求用户请忽略此字段)
			//request.setSmsUpExtendCode("90997");

			//可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
			request.setOutId("yourOutId");

			//hint 此处可能会抛出异常，注意catch
			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			JSONObject ret = new JSONObject();
			
			if ("OK".equals(sendSmsResponse.getCode())){
				ret.fluentPut("success", true).fluentPut("mobilephone", request.getPhoneNumbers());
			} else {
				ret.fluentPut("success", false).fluentPut("message", sendSmsResponse.getMessage());
			}
			System.out.println(ret.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
	
	
	
	public static void main(String[] args) {
		try {
			 JSONObject obj = new JSONObject();
		        obj.fluentPut("code", "123456")
		           ;
			sendSms("15527930302", Constant.VALID_CODE_TEMPLATE, JSON.toJSONString(obj));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
