<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!doctype html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>活动报名</title>
<script type="text/javascript" src="purchaser/js/vue.min.js"></script>
<script type="text/javascript" src="purchaser/js/jquery.min.js"></script>
<script type="text/javascript" src="purchaser/js/commonUtils.js"></script>
<style type="text/css">
html, body {
	margin: 0;
	padding: 0;
	width: 100%;
	height: 100%;
}

img {
	width: 100%;
	height: auto;
}

input {
	border: 0;
	outline: none;
}

/* 活动信息 */
.active-wraper {
	padding: 10px;
	overflow: hidden;
}

.active-image {
	float: left;
	width: 84px;
	height: 64px;
	overflow: hidden;
}

.active-other {
	float: left;
	margin-left: 10px;
}

.active-name {
	margin-top: 3px;
	font-size: 13px;
}

.active-date {
	margin-top: 5px;
	font-size: 12px;
	color: #BBB;
}

.active-hostUnit {
	margin-top: 5px;
	font-size: 12px;
	color: #BBB;
}

/* 会员价格和参加费用 */
.price-wraper {
	display: flex;
	justify-content: space-between;
	padding: 10px;
	font-size: 13px;
}

/* 邀请码输入 */
.code-input-wraper {
	display: flex;
	justify-content: space-between;
	align-items: center; padding : 10px;
	font-size: 13px;
	padding: 10px;
}

/* 邀请码按钮 */
.code-button {
	margin: 0 auto;
	width: 80px;
	height: 30px;
	text-align: center;
	line-height: 30px;
	font-size: 12px;
	color: #FFF;
	background-color: #E60012;
	border-radius: 5px;
}

/* 支付按钮 */
.payment-button-wraper {
	position: fixed;
	bottom: 10px;
	left: 0;
	width: 100%;
}

.payment-button {
	margin: 0 auto;
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
		<!-- 活动信息 -->
		<div class="active-wraper">
			<div class="active-image">
				<img src="https://www.ecartoon.com.cn/picture/wangyan1.jpg">
			</div>
			<div class="active-other">
				<div class="active-name">{{active.name}}</div>
				<div class="active-date">{{active.startDate}}</div>
				<div class="active-hostUnit">{{active.hostUnit}}</div>
			</div>
		</div>
		<div style="height: 10px; background-color: #F0F0F2;"></div>
		<!-- 会员价格 -->
		<div class="price-wraper">
			<div>会员价:</div>
			<div style="color: #E60012;">
				<span style="font-size: 9px;">¥</span> {{active.price}}元
			</div>
		</div>
		<div style="height: 10px; background-color: #F0F0F2;"></div>
		<!-- 邀请码和参加费用 -->
		<div class="code-wraper">
			<div class="code-input-wraper">
				<div>请输入邀请码</div>
				<div>
					<input type="text" placeholder="请输入6位邀请码"
						style="text-align: right;" v-model="code" />
				</div>
			</div>
			<div class="code-button" @click="checkCode()">确定</div>
			<div class="price-wraper">
				<div>参加费用:</div>
				<div style="color: #E60012;">
					<span style="font-size: 9px;">¥</span> {{finalPrice}}元
				</div>
			</div>
		</div>
		<div style="height: 10px; background-color: #F0F0F2;"></div>
		<!-- 支付按钮 -->
		<div class="payment-button-wraper" @click="payMent()">
			<div class="payment-button">确定支付</div>
		</div>
	</div>
	<script type="text/javascript">
		var vue = new Vue({
			el: "#wraper",
			data: {
				active: {},
				isMember: 0,
				finalPrice: 0,
				code: '',
				activeCode: {}
			},
			created: function () {
				// 页面初始化
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
							console.log(e);
						}
					});
				},
				
				// 页面初始化
				init: function () {
					var active = JSON.parse(sessionStorage.getItem("active"));
					this.active = active;
					this.finalPrice = active.price;
					// 处理图片
					clipImgs("img");
					
					// 请求服务端查询当前角色是否为会员
					var url = "order/getActiveProductInfo.pur";
					var param = {}
					// 请求服务接口
					this.requestServer(url, param, function (res) {
						vue.isMember = res.isMember;
					});
				},
				
				// 验证邀请码
				checkCode: function () {
					var url = "active/checkActiveCode.pur";
					var param = {
							activeId: this.active.id,
							code: this.code
					}
					// 请求服务接口
					this.requestServer(url, param, function (res) {
						if (res) {
							vue.activeCode = res;
							if (res.type == 0) {
								vue.finalPrice = vue.active.distinguishedPrice;
							} else if (res.type == 1) {
								vue.finalPrice = vue.active.manufacturerPrice;
							}
							alert("邀请码激活成功!");
						} else {
							alert("邀请码无效!");
						}
					});
				},
				
				// 支付
				payMent: function () {
					
					if (this.isMember == 0 && !this.activeCode.id) {
						alert("您没有会员权限，请先验证邀请码");
						return;
					} 
					
					// 请求服务接口生成订单并生成支付签名
					var url = "order/createOrder.pur";
					var param = {
							productId: this.active.id,
							productType: "A",
							price: this.finalPrice
					}
					if (this.activeCode.id) {
						param.activeCodeId = this.activeCode.id; 
					}
					this.requestServer(url, param, function(res) {
						// 调用微信支付API
						vue.wechatPay(res);
					});
				},
				
				// 微信支付
				wechatPay: function (res) {
					WeixinJSBridge.invoke('getBrandWCPayRequest',
						{
							"appId" : res.appId,
							"timeStamp" : res.timeStamp,
							"nonceStr" : res.nonceStr,
							"package" : res.packageValue,
							"signType" : res.signType,
							"paySign" : res.paySign
						},
						function(res) {
							if (res.err_msg == "get_brand_wcpay_request:ok") {
								alert('购买成功!');
								location.href = "purchaser/pay_success_active.jsp";
							} else if (res.err_msg == "get_brand_wcpay_request:cancel") {
								// alert("已取消支付!");
							} else {
								alert("未知原因,支付失败,请稍后再试")
							}
					});
				}
			}
		});
	</script>
</body>
</html>