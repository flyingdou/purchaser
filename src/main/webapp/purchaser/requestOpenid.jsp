<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录中</title>
</head>
<body>
</body>
<script>
var page = {
     status: "${status}" || 0,
     upUrl: "${upUrl}"
}
if(page.status == 0){
	sessionStorage.upUrl = page.upUrl;
	location.href='https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa8d461eba30468fd&redirect_uri=http://purchaser.ecartoon.com.cn/wechat/wechatLogin.pur&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect';
}else if(page.status == 1){
	var upUrl = sessionStorage.upUrl;
	sessionStorage.removeItem("upUrl");
	location.href = upUrl;
} 
</script>
</html>