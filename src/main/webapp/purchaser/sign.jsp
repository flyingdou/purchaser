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
			<div class="sign-button" v-if="!signStatus">
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
						location.href = "user/checkLogin.pur?redirectURL=" + encodeURI("sign");
					}
					
					var url = location.href.split("#")[0];
					// 创建回调
					var callback = res => {
						//微信sdk配置
						wx.config({
						    debug: false, 
						    appId: res.appid, 
						    timestamp: res.timestamp, 
						    nonceStr: res.nonceStr, 
						    signature: res.signature,
						    jsApiList: [        
						       "getLocation",    
						       "scanQRCode"
						    ] 
						});
					}
					
					// 请求服务端
					this.requestServer("wechat/jsapiSign.pur", {url:url}, callback);
					
				},
				
				// 获取地理位置和扫码结果
				getLocationAndCode: function (callback) {
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
					// 签到接口调用成功回调函数
					var callback = res => {
						// 重置函数执行状态
						this.status = 0;
						this.signStatus = 1;
						this.message = res.message;
					}
					
					// 获取地理位置和扫码结果
					this.getLocationAndCod(function (res) {
						// 请求服务端签到接口
						var url = "active/sign.pur";
						var param = res;
						param.activeId = res.resultStr;
						vue.requestServer(url, param, callback);
					});
				}
				
			}
		});
	</script>
</body>
</html>