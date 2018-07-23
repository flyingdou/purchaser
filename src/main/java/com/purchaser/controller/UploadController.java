package com.purchaser.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.purchaser.constants.Constant;
import com.purchaser.util.CommentUtils;

/**
 * 上传文件
 * 
 * @author Administrator
 *
 */
@Controller
public class UploadController {

	/**
	 * 上传单个文件
	 * 
	 * @param request
	 * @param response
	 * @param myfile
	 */
	@RequestMapping("/uploadFile")
	@ResponseBody
	public String uploadFile(@RequestParam MultipartFile file, HttpServletRequest request) {
		try {
			// 这里的路径(可以自定义)是tomcat安装路径/webapps/项目名/picture/
			String filePath = Constant.PICTURE_PATH;
			// 获取图片上传的文件路径
			File picFile = new File(filePath);
			// 判断文件是否存在,如果不存在就创建
			if (!picFile.isDirectory()) {
				picFile.mkdir();
			}
			// 需要返回的文件名
			String originalFilename = null;
			// 生成新的文件名
			originalFilename = CommentUtils.getNewFileName(file.getOriginalFilename());
			// 这里不必处理IO流关闭的问题,因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
			// 此处也可以使用Spring提供的MultipartFile.transferTo(File dest)方法实现文件的上传
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filePath, originalFilename));
			// 图片压缩
			CommentUtils.compress(filePath + "/" + originalFilename);
			JSONObject result = new JSONObject();
			result.fluentPut("success", true).fluentPut("picture", originalFilename);
			return JSON.toJSONString(result);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

	/**
	 * 多文件上传
	 * 
	 * @param request
	 * @param response
	 * @param myfiles
	 * @return
	 */
	@RequestMapping("/uploadFiles")
	@ResponseBody
	public String uploadFiles(@RequestParam MultipartFile[] files, HttpServletRequest request) {
		JSONObject result = new JSONObject();
		try {
			// 这里的路径(可以自定义)是tomcat安装路径/webapps/项目名/picture/
			String filePath = Constant.PICTURE_PATH;
			// 获取图片上传的文件路径
			File picFile = new File(filePath);
			// 判断文件是否存在,如果不存在就创建
			if (!picFile.isDirectory()) {
				picFile.mkdir();
			}
			// 存储多个图片路径
			List<String> picPaths = new ArrayList<String>();
			// 需要返回的文件名
			String originalFilename = null;
			for (MultipartFile file : files) {
				// 生成新的文件名
				originalFilename = CommentUtils.getNewFileName(file.getOriginalFilename());
				// 这里不必处理IO流关闭的问题,因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
				// 此处也可以使用Spring提供的MultipartFile.transferTo(File dest)方法实现文件的上传
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filePath, originalFilename));
				picPaths.add(originalFilename); // 添加当前上传的图片路径
			}
			result.fluentPut("success", true).fluentPut("picList", picPaths);
			return JSON.toJSONString(result);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}
}
