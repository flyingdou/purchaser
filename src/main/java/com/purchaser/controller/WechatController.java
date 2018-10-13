package com.purchaser.controller;

import java.io.InputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.purchaser.constants.Constant;
import com.purchaser.pojo.User;
import com.purchaser.service.OrderService;
import com.purchaser.service.UserService;
import com.purchaser.util.CommentUtils;
import com.purchaser.wechat.DownloadPictureRequest;
import com.purchaser.wechat.JSAPIRequest;
import com.purchaser.wechat.SignManager;
import com.purchaser.wechat.WechatApiManager;

/*
 * 作者: dou
 * 时间: 2018-06-19 14:26:36
 * desc: 与微信交互的controller
 * */
@Controller
@RequestMapping("/wechat")
public class WechatController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * jsapi签名
	 */
	@RequestMapping("/jsapiSign")
	public void jsapiSign (HttpServletResponse response, String url) {
		String sign = "";
		try {
			JSAPIRequest jsapiRequest = new JSAPIRequest(url);
			SignManager signManager = new SignManager(Constant.APP_ID, Constant.APP_SECRET);
			sign = signManager.jsapiSign(jsapiRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		CommentUtils.response(response, sign);
	}
	
	/**
	 * simpleLogin
	 */
	@RequestMapping("/simpleLogin")
	public void simpleLogin (HttpServletRequest request, HttpServletResponse response, String id) {
		JSONObject ret = new JSONObject();
		try {
			User user = userService.findUserById(Long.valueOf(id));
			// 登录失败处理
			if (user == null) {
				ret.fluentPut("success", false).fluentPut("message", "登录异常");
				CommentUtils.response(response, ret.toJSONString());
				return;
			}
			
			// 登录成功处理
			request.getSession().setAttribute("user", user);
			ret.fluentPut("success", true).fluentPut("message", "OK").fluentPut("key", user.getId());
		} catch (Exception e) {
			ret.fluentPut("success", false).fluentPut("message", e.toString());
			e.printStackTrace();
		}
		CommentUtils.response(response, ret.toJSONString());
	}
	
	/**
	 * 从微信服务器下载用户上传的照片
	 */
	@RequestMapping("/downloadPicture")
	public void downloadPicture (HttpServletResponse response, String json) {
		JSONObject ret = new JSONObject();
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			JSONArray mediaIds = param.getJSONArray("serverIds");
			// wechatApiManager
			WechatApiManager wechatManager = new WechatApiManager(Constant.APP_ID, Constant.APP_SECRET);
			DownloadPictureRequest downPic = new DownloadPictureRequest();
			JSONArray imgs = new JSONArray();
			for (Object object : mediaIds) {
				String downPicUrl = wechatManager.getDownloadUrl(downPic ,String.valueOf(object));
				// 生成图片名, 保存图片
				String filename = CommentUtils.getRandomByDate(6) + ".jpg";
				CommentUtils.download(downPicUrl, filename, Constant.PICTURE_PATH);
				imgs.add(filename);
			}
			
			ret.fluentPut("success", true)
			   .fluentPut("message", "OK")
			   .fluentPut("imgs", imgs)
			   ;
		} catch (Exception e) {
			ret.fluentPut("success", false).fluentPut("message", e.toString());
			e.printStackTrace();
		}
		
		// 返回数据
		CommentUtils.response(response, ret.toJSONString());
	}
	
	/**
	 * wechatLogin 用户在前台页面点击登录授权，微信服务器回调的方法，传回code
	 * @param request
	 * @param code
	 */
	@RequestMapping("/wechatLogin")
	public void wechatLogin (HttpServletRequest request, HttpServletResponse response) {
		try {
			// 获取请求参数code
			JSONObject param = new JSONObject();
			param.fluentPut("code", request.getParameter("code"));
			JSONObject res = userService.wechatLogin(param, request);
			if (res.containsKey("openId") && res.get("openId") != null) {
				// 登录成功
				request.getSession().setAttribute("status", 1);
				request.getSession().setAttribute("openId", res.get("openId"));
				// 跳转登录页面
				response.sendRedirect("http://purchaser.ecartoon.com.cn/purchaser/requestOpenid.jsp");
			} else if (res.containsKey("subscribe")) {
				// 该用户暂未关注本公众号，应该跳转到本公众号二维码页面，让用户关注
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * 微信支付回调
	 * @param request
	 * @param response
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping("/updateOrder")
	public void updateOrder (HttpServletRequest request, HttpServletResponse response) {
	       try {
	    	   DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    	   String FEATURE = null;
	    	   
	    	   FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
	    	   dbf.setFeature(FEATURE, true);
	    	   
	    	   FEATURE = "http://xml.org/sax/features/external-general-entities";
	    	   dbf.setFeature(FEATURE, false);
	    	   
	    	   FEATURE = "http://xml.org/sax/features/external-parameter-entities";
	    	   dbf.setFeature(FEATURE, false);
	    	   
	    	   FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
	    	   dbf.setFeature(FEATURE, false);
	    	   
	    	   dbf.setXIncludeAware(false);
	    	   dbf.setExpandEntityReferences(false);
	    	   
	    	   DocumentBuilder safebuilder = dbf.newDocumentBuilder();
	    		
	    	   // 解析结果存储在HashMap
				Map<String, String> map = new HashMap<String, String>();
				InputStream inputStream = request.getInputStream();
				
				// test 
				Document document = (Document) safebuilder.parse(inputStream);
				// 读取输入流
//				SAXReader reader = new SAXReader();
//				Document document = reader.read(inputStream);
				// 得到xml根元素
				Element root = document.getRootElement();
				// 得到根元素的所有子节点
				List<Element> elementList = root.elements();
		
				// 遍历所有子节点
				for (Element e : elementList)
					map.put(e.getName(), e.getText());
		
				// 释放资源
				inputStream.close();
				inputStream = null;
				String resultcode = map.get("result_code");
				String orderno = map.get("out_trade_no");
				
				// 支付成功，更新订单数据
				if (Constant.SUCCESS.equals(resultcode)) {
					orderService.updateOrderStatus(orderno);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	

}
