package com.purchaser.controller;

import java.net.URLDecoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author 华文
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/checkLogin")
	public String checkLogin(String redirectURL) {
		try {
			return URLDecoder.decode(redirectURL, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return "www.ecartoon.com.cn";
		}
	}
}
