<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"+ request.getServerPort() + path + "/";
%>

<!doctype html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>扫码签到</title>
<script type="text/javascript" src="purchaser/js/vue.min.js"></script>
<script type="text/javascript" src="purchaser/js/jquery.min.js"></script>
<script type="text/javascript" src="purchaser/js/commonUtils.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<style>
	html,body{
		margin: 0;
		padding: 0;
		width: 100%;
		height: 100%;
	}

	img{
		width: 100%;
		height: auto;
	}
	
	.user_info{
		padding: 10px;
		overflow: hidden;
	}
	
	
	.user-image{
		float: left;
		width: 54px;
		height: 54px;
		overflow: hidden;
	}
	
	.user-image > img{
		border-radius: 50%;
	}
	
	.user-nick{
		float: left;
		margin-left: 15px;
		height: 54px;
		line-height: 54px;
		font-size: 14px;
		color: #787878;
	}
	
	.banner{
		position: relative;
	}
	
	.message{
		position: absolute;
		top: 45%;
		left: 0;
		width: 100%;
		font-size: 20px;
		text-align: center;
		color: #787878;
	}
	
	.message-tip{
		left: 25%;
		width: 50%;
		font-size: 14px;
	}
	
	.sign-button{
		position: absolute;
		top: 60%;
		left: 5%;
		width: 90%;
		height: 44px;
		text-align: center;
		line-height: 44px;
		font-size: 12px;
		color: #FFF;
		background-color: #E60012;
		border-radius: 5px;
	}
</style>
</head>
<body>
	<div id="wraper">
		<div class="user_info">
			<div class="user-image">
				<img src="https://purchaser.ecartoon.com.cn/picture/${user.image}" />
			</div>
			<div class="user-nick">
				${user.name}
			</div>
		</div>
		<div style="height:10px;background-color:#F0F0F2;"></div>
		<div class="banner">
			<img src="purchaser/img/member_card.png" />
			<div class="message" v-if="!signStatus">
				请扫描本次活动的二维码
			</div>
			<div class="message message-tip" v-if="signStatus">
				{{message}}
			</div>
			<div class="sign-button" @click="sign()" v-if="!signStatus">
				开始扫码
			</div>
		</div>
	</div>
	<script>
		var vue = new Vue({
			el: "#wraper",
			data: {
				message: "",
				status: 0,
				signStatus: 0
			},
			created: function () {
				this.message = "您没有报名参加本次活动 , 请联系会务工作人员";
				
				this.init();
			},
			methods: {
				
				// 请求服务端
				requestServer: function (url, param, callback) {
					$.ajax({
						url: url,
						type: "post",
						data: {
							json: encodeURI(JSON.stringify(param))
						},
						dataType: "json",
						success: callback,
						error: function (e) {
							console.log(e)
						} 
					});
				},
				
				// 初始化
				init: function () {
					if ("${user}" == "") {
						location.href = "user/checkLogin.pur?redirectURL=" + encodeURI("purchaser/sign.jsp");
					}
					
					// 请求微信签名，准备调用jsapi接口
					$.ajax({
						url : "wechat/jsapiSign.pur",
						data : {
							url : location.href.split("#")[0]
						},
						success : function(sign) {
							sign = JSON.parse(sign);
							wx.config({
								debug : false,
								appId : sign.appid,
								timestamp : sign.timestamp,
								nonceStr : sign.nonceStr,
								signature : sign.signature,
								jsApiList : ["getLocation", "scanQRCode"]
							});
						}
					})
				},
				
				// 获取地理位置和扫码结果
				getLocationAndCode: function (callback) {
					type: 'gcj02',
					wx.getLocation({
					    success: function (location) {
							wx.scanQRCode({
							    needResult: 1, 
							    scanType: ["qrCode","barCode"], 
							    success: function (res) {
							    	res.latitude = location.latitude;
							    	res.longitude = location.longitude;
							    	callback(res);
							    }
							});
					    },
					    cancel: function (res) {
					        alert('扫码签到，需获取您的地理位置信息');
					    }
					 });
				},
				
				// 调用签到接口
				sign: function () {
					// 函数执行中, 防止重复触发
					if(this.status){
						return;
					} else {
						this.status = 1;
					}
					
					// 获取地理位置和扫码结果
					this.getLocationAndCode(function (param) {
						// 请求服务端签到接口
						var url = "active/sign.pur";
						var param = param;
						param.activeId = param.resultStr;
						vue.requestServer(url, param, function (res) {
							// 重置函数执行状态
							vue.status = 0;
							vue.signStatus = 1;
							vue.message = res.message;
						});
					});
				}
				
			}
		});
	</script>
</body>
</html>