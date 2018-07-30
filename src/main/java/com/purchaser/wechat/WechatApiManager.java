package com.purchaser.wechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONObject;
import com.purchaser.constants.Constant;
import com.purchaser.pojo.Order;
import com.purchaser.util.CommentUtils;
import com.purchaser.util.HttpRequestUtils;

/*
 * 作者: dou
 * 时间: 2018-07-12 10:47:53
 * desc: 管理系统与微信服务器的交互
 * */
public class WechatApiManager {

	/**
	 * app_id
	 */
	private String APP_ID;
	
	/**
	 * app_secret
	 */
	private String APP_SECRET;
	
	/**
	 * 获取accessToken
	 * @return
	 */
	public String getAccessToken () {
    	String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
    			+ this.getAPP_ID() + "&secret=" + this.getAPP_SECRET();
    	JSONObject json = JSONObject.parseObject(HttpRequestUtils.httpRequest(GET_ACCESS_TOKEN_URL, null));
    	String access_token = json.getString("access_token");
		return access_token;
	}
	
	
	/**
	 * 获取openId
	 * @param openIdRequest
	 * @return
	 */
	public JSONObject getOpenId (OpenIdRequest openIdRequest) {
		String reqUrl = openIdRequest.getRequestUrl();
		reqUrl = reqUrl.replace("APPID", this.getAPP_ID()).replace("SECRET", this.getAPP_SECRET());
		return HttpRequestUtils.httpGet(reqUrl);
	}

	
	/**
	 * 根据openId请求用户信息
	 * @param userInfoRequest
	 * @return
	 */
	public JSONObject getUserInfo (UserInfoRequest userInfoRequest, String openid) {
		String reqUrl = userInfoRequest.getReqUrl();
		reqUrl += "access_token=" + getAccessToken() + "&openid=" + openid;
		return HttpRequestUtils.httpGet(reqUrl);
	}
	
	
	
	/**
	 * 获取用户上传的图片下载链接
	 * @param DownloadPictureRequest
	 * @param mediaId
	 * @return
	 */
	public String getDownloadUrl (DownloadPictureRequest downloadPictureRequest, String mediaId) {
		String reqUrl = downloadPictureRequest.getReqUrl();
		reqUrl.replace("ACCESS_TOKEN", this.getAccessToken()).replace("MEDIA_ID", mediaId);
		return reqUrl;
	}
	
	
	/**
	 * 从微信服务器下载用户上传的照片
	 * @param mediaId
	 * @return
	 */
	public String downloadPicture(String mediaId) {
		JSONObject ret = new JSONObject();
		try {
			// 处理请求参数
			WechatApiManager wechatManager = new WechatApiManager(Constant.APP_ID, Constant.APP_SECRET);
			DownloadPictureRequest downPic = new DownloadPictureRequest();
			String downPicUrl = wechatManager.getDownloadUrl(downPic, mediaId);
			// 生成图片名, 保存图片
			String filename = CommentUtils.getRandomByDate(6) + ".jpg";
			CommentUtils.download(downPicUrl, filename, Constant.PICTURE_PATH);
			return filename;
		} catch (Exception e) {
			ret.fluentPut("success", false).fluentPut("message", e.toString());
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * 微信公众号支付签名
	 * 
	 * @param openid
	 *            String 微信公众号用户id
	 * @param productPrice
	 *            String 商品价格
	 * @param productDetail
	 *            String 商品描述
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String paySign( PayRequest payRequest, Order order) throws Exception {
		// 获取发送给微信的参数
		// 随机字符串
		String nonce_str = create_nonce_str();
		// 微信签名
		String sign = "";
		// 商品描述
		String body = "purchaser";
		// 商品订单号
		String out_trade_no = order.getNo();
		// 商品价格(单位:分)
		String total_fee = String.valueOf((new Double(order.getMoney() * 100)).intValue());
		// 交易类型
		String trade_type = "JSAPI";
		// 微信回调通知地址
		String notify_url = "http://purchaser.ecartoon.com.cn/wechat/updateOrder.pur";
		// 终端设备类型
		String deviceInfo = "WEB";
		
		//微信支付分配的商户号
		String MCH_ID = payRequest.getMCH_ID();

		//key为商户平台设置的密钥key
		String KEY = payRequest.getKey();
		
		// openid
		String openid = payRequest.getOpenId();
		
		// appid
		String appid = this.getAPP_ID();
		
		// ip
		String ip = payRequest.getIp();

		// 参数
		TreeMap paramMap = new TreeMap();
		paramMap.put("appid", appid);// 公众账号ID
		paramMap.put("device_info", deviceInfo);// 支付终端型号
		paramMap.put("mch_id", MCH_ID);// 商户号
		paramMap.put("nonce_str", nonce_str);// 随机字符串
		paramMap.put("body", body);// 商品描述
		paramMap.put("out_trade_no", out_trade_no);// 商户订单号
		// 交易金额默认为人民币交易，接口中参数支付金额单位为【分】，参数值不能带小数。
		paramMap.put("total_fee", total_fee);// 标价金额
		paramMap.put("spbill_create_ip", ip);// 终端IP
		paramMap.put("notify_url", notify_url);// 通知地址
		paramMap.put("trade_type", trade_type);// 交易类型
		paramMap.put("openid", openid);// 用户标识

		// 调用加密方法生成签名
		// 注：MD5签名方式
		sign = createSign(paramMap, KEY);
		// 生成xml发送消息
		StringBuilder xml = new StringBuilder("<xml>");
		xml.append("<appid>").append(appid).append("</appid>");
		xml.append("<device_info>").append(deviceInfo).append("</device_info>");
		xml.append("<mch_id>").append(MCH_ID).append("</mch_id>");
		xml.append("<nonce_str>").append(nonce_str).append("</nonce_str>");
		xml.append("<sign>").append(sign).append("</sign>");
		xml.append("<body><![CDATA[").append(body).append("]]></body>");
		xml.append("<out_trade_no>").append(out_trade_no).append("</out_trade_no>");
		xml.append("<total_fee>").append(total_fee).append("</total_fee>");
		xml.append("<spbill_create_ip>").append(ip).append("</spbill_create_ip>");
		xml.append("<notify_url>").append(notify_url).append("</notify_url>");
		xml.append("<trade_type>").append(trade_type).append("</trade_type>");
		xml.append("<openid>").append(openid).append("</openid>");
		xml.append("</xml>");
		// 调用微信统一支付接口
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		String prepay_id = getPayNo(createOrderURL, xml.toString());
		String timeStamp = create_timestamp();
		String packageValue = "prepay_id=" + prepay_id;
		TreeMap paySignMap = new TreeMap();
		paySignMap.put("appId", appid);
		paySignMap.put("timeStamp", timeStamp);
		paySignMap.put("nonceStr", nonce_str);
		paySignMap.put("package", packageValue);
		paySignMap.put("signType", "MD5");
		// 參數返回給前台
		JSONObject res = new JSONObject();
		res.fluentPut("appId", appid);
		res.fluentPut("timeStamp", timeStamp);
		res.fluentPut("nonceStr", nonce_str);
		res.fluentPut("packageValue", packageValue);
		res.fluentPut("signType", "MD5");
		res.fluentPut("paySign", createSign(paySignMap, KEY));
		res.fluentPut("success", true);
		res.fluentPut("prepay_id", prepay_id);
		return res.toString();
	}
	
	
	
	
	
	/**
	 * 生成签名
	 * 
	 * @param parameters
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static String createSign(SortedMap<String, Object> parameters, String key) throws Exception {
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		// 注：key为商户平台设置的密钥key
		sb.append("key=" + key);
		System.out.println("签名参数：" + sb.toString());
		String xx = sb.toString();
		String sign = CommentUtils.MD5(xx).toUpperCase();
		System.out.println("签名: " + sign);
		return sign;
	}
	
	
	
	
	/**
	 * 获取支付id
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	private static String getPayNo(String url, String param) {
		try {
			String xml = httpReques(url, param);
			Document doc = DocumentHelper.parseText(xml);
			// 指向根节点
			Element root = doc.getRootElement();
			// 获得perpay_id节点
			Element element = root.element("prepay_id");
			return element.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	/**
	 * http请求
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static String httpReques(String url, String param) {
		// 创建httpclient工具对象
		HttpClient client = new HttpClient();
		// 创建post请求方法
		PostMethod myPost = new PostMethod(url);
		try {
			// 设置post请求参数
			// 设置请求头部类型
			myPost.setRequestHeader("Content-Type", "text/xml");
			myPost.setRequestHeader("charset", "utf-8");
			// 设置请求体，即xml文本内容，一种是直接获取xml内容字符串，一种是读取xml文件以流的形式
			myPost.setRequestBody(param);
			int statusCode = client.executeMethod(myPost);
			// url = URLDecoder.decode(url, "UTF-8");
			// 请求发送成功，并得到响应
			if (statusCode == 200) {
				String str = "";
				try {
					// 读取服务器返回过来的json字符串数据
					// str = EntityUtils.toString(result.getEntity());
					InputStream inputStream = myPost.getResponseBodyAsStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
					StringBuffer stringBuffer = new StringBuffer();
					while ((str = br.readLine()) != null) {
						stringBuffer.append(str);
					}
					return stringBuffer.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * 获取唯一字符串
	 * 
	 * @return
	 */
	private static String create_nonce_str() {
		return (int) Math.floor(Math.random() * 1000000) + "";
	}

	/**
	 * 获取时间戳
	 * 
	 * @return
	 */
	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
	
	
	
	
	
	
	/**
	 * 有参构造函数
	 * @param aPP_ID
	 * @param aPP_SECRET
	 */
	public WechatApiManager(String aPP_ID, String aPP_SECRET) {
		super();
		APP_ID = aPP_ID;
		APP_SECRET = aPP_SECRET;
	}

	/**
	 * setter && getter
	 * @return
	 */
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
