package com.purchaser.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.serializer.ValueFilter;

import net.coobird.thumbnailator.Thumbnails;

public class CommentUtils {

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 响应
	 * 
	 * @param response
	 * @param result
	 */
	public static void response(HttpServletResponse response, String result){
		try {
			response.getWriter().write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 计算两个经纬度之间的距离(单位:米)
	 * 
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static double GetDistance(double lat1, double lng1, double lat2, double lng2) {
		double EARTH_RADIUS = 6371.393;
		double radLat1 = lat1 * Math.PI / 180.0;
		double radLat2 = lat2 * Math.PI / 180.0;
		double a = radLat1 - radLat2;
		double b = (lng1 * Math.PI / 180.0) - (lng2 * Math.PI / 180.0);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 1000);
		return s;
	}

	/**
	 * JSONObject 转换时，将null值转换成""
	 * 
	 * @return
	 */
	public static ValueFilter getValueFilterNullStringFillNull() {
		ValueFilter valueFilter = new ValueFilter() {
			@Override
			public Object process(Object object, String name, Object value) {
				if (value == null) {
					value = "";
				}
				return value;
			}
		};
		return valueFilter;
	}
	
	
	/**
	 * 将date类型的数据，格式化为自己想要的格式
	 * @param format
	 * @return
	 */
	public static ValueFilter dateformatValue (String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		ValueFilter valueFilter = new ValueFilter() {
			@Override
			public Object process(Object object, String name, Object value) {
				if (value instanceof Date) {
					value = sdf.format(value);
				}
				return value;
			}
		};
		
		return valueFilter;
	}

	
	/**
	 * 生成一个6位数的随机数字
	 * @return
	 */
	public static String getRandomString() {
		return "" + Math.round(Math.random() * 1000000);
	}

	/**
	 * 获得指定位数的随机数
	 * 
	 * @param count
	 * @return
	 */
	public static String getRandom(int count) {
		int param = 1;
		for (int i = 0; i < count; i++) {
			param *= 10;
		}
		return String.valueOf(Math.round(Math.random() * param));
	}
	
	
	/**
	 * 获得指定位数的随机数
	 * 
	 * @param count
	 * @return
	 */
	public static String getRandomFullStr(int count) {
		String str = "";
		for (int i = 0; i < count; i++) {
			Double x = Math.random() * 10;
			Integer ig = x.intValue();
			str += String.valueOf(ig);
		}
		return str;
		
		
	}
	

	/**
	 * 获取当前日期加上指定位数的随机数
	 * 
	 * @param count
	 * @return
	 */
	public static String getRandomByDate(int count) {
		int param = 1;
		for (int i = 0; i < count; i++) {
			param *= 10;
		}
		return sdf.format(new Date()) + String.valueOf(Math.round(Math.random() * param));
	}
	
	
	/**
	 * 获取当前日期加上指定位数的随机数(日期格式自定义)
	 * @param count
	 * @param format
	 * @return
	 */
	public static String getRandomByDate(int count, String format) {
		// 设置默认值
		if (StringUtils.isBlank(format)) {
			format = "yyyyMMdd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		int param = 1;
		for (int i = 0; i < count; i++) {
			param *= 10;
		}
		return sdf.format(new Date()) + String.valueOf(Math.round(Math.random() * param));
	}
	

	/**
	 * 生成新的文件名
	 * @param fileName
	 * @return
	 */
	public static String getNewFileName(String fileName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String newFileName = sdf.format(new Date()) + getRandomString();
		String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		return newFileName + suffix;
	}

	public static String format(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 获取客户端公网ip
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			if (ip.indexOf(",") != -1) {
				ip = ip.split(",")[0];
			}
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			System.out.println("Proxy-Client-IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			System.out.println("WL-Proxy-Client-IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			System.out.println("HTTP_CLIENT_IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
			System.out.println("X-Real-IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			System.out.println("getRemoteAddr ip: " + ip);
		}
		return ip;
	}

	/**
	 * MD5
	 * 
	 * @param buffer
	 * @return
	 * @throws Exception 
	 */
	public static String MD5(String buffer) throws Exception {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		MessageDigest mdTemp = MessageDigest.getInstance("MD5");
		mdTemp.update(buffer.getBytes("UTF-8"));
		byte[] md = mdTemp.digest();
		int j = md.length;
		char str[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = md[i];
			str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			str[k++] = hexDigits[byte0 & 0xf];
		}
		return new String(str);
	}

	/**
	 * 读取请求中流的参数
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public static String readStream(HttpServletRequest request) throws Exception {
		InputStream inStream = request.getInputStream();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		String resultStr = new String(outSteam.toByteArray(), "utf-8");
		return resultStr;
	}

	/**
	 * emoji表情替换
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String cutStringEmoji(String str) throws Exception {
		char[] chr = str.toCharArray();
		String strx = "";
		for (int i = 0; i < chr.length; i++) {
			if (((chr[i] >= 0x4e00) && (chr[i] <= 0x9fbb))
					|| ((chr[i] >= 'A' && chr[i] <= 'Z') || (chr[i] >= 'a' && chr[i] <= 'z'))
					|| (chr[i] > 47 && chr[i] < 58)) {
				strx += String.valueOf(chr[i]);
			} else {
				chr[i] = '*';
				strx += String.valueOf(chr[i]);
			}
		}

		return strx;
	}

	/**
	 * 把String转成Date
	 * 
	 * @param dateStr
	 * @return
	 * @throws Exception 
	 */
	public static Date formatStringToDate(String date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(date);
	}
	
	/**
	 * 将string转换成Date，自定义格式
	 * @param source
	 * @param formart
	 * @return
	 * @throws Exception
	 */
	public static Date formatString2Date(String source, String formart) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(formart);
		return sdf.parse(source);
	}

	/**
	 * 时间相加的方法
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDate(Date date, long day) {
		// 得到指定日期的毫秒数
		long time = date.getTime();
		// 要加上的天数转换成毫秒数
		day = day * 24 * 60 * 60 * 1000;
		// 相加得到新的毫秒数
		time += day;
		// 将毫秒数转换成日期
		return new Date(time);
	}

	/**
	 * 根据链接下载图片
	 * 
	 * @param urlString
	 * @param filename
	 * @param savePath
	 * @throws Exception
	 */
	public static void download(String urlString, String filename, String savePath) throws Exception {
		// 构造URL
		URL url = new URL(urlString);
		// 打开连接
		URLConnection con = url.openConnection();
		// 设置请求超时为5s
		con.setConnectTimeout(5 * 1000);
		// 输入流
		InputStream is = con.getInputStream();

		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		File sf = new File(savePath);
		if (!sf.exists()) {
			sf.mkdirs();
		}
		OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();
	}

	/**
	 * 图片压缩
	 * 
	 * @param filePath
	 * @throws IOException 
	 */
	public static void compress(String filePath) throws IOException {
		// 图片尺寸不变，压缩图片文件大小outputQuality实现,参数1为最高质量
		Thumbnails.of(filePath).scale(1f).outputQuality(0.25f).toFile(filePath);
	}

	
	/**
	 * 日期格式化
	 * @param list
	 * @param formart
	 * @return
	 */
	public static List<Map<String, Object>> listDateAndFormat (List<Map<String, Object>> list, String formart) {
		SimpleDateFormat sdf = new SimpleDateFormat(formart);
		
		// 遍历list
		for (Map<String, Object> map : list) {
			
			// 遍历map
			Set<String> set = map.keySet();
			for (String string : set) {
				if (map.get(string) instanceof Date) {
					map.put(string, sdf.format(map.get(string)));
				}
			}
		}
		return list;
	}
	
	
}
