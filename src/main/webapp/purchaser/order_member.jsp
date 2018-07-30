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
<title>入会缴费</title>
<script type="text/javascript" src="purchaser/js/vue.min.js"></script>
<script type="text/javascript" src="purchaser/js/jquery.min.js"></script>
<script type="text/javascript" src="purchaser/js/commonUtils.js"></script>
<style type="text/css">
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
	
	input{
		border: 0;
		outline: none;
	}
	
	/* 活动信息 */
	.active-wraper{
		padding: 10px;
		overflow: hidden;
	}
	
	.active-image{
		float: left;
		width: 84px;
		height: 64px;
		overflow: hidden;
	}
	
	.active-other{
		float: left;
		margin-left: 10px;
	}
	
	.active-name{
		margin-top: 3px;
		font-size: 13px;
	}
	
	.active-date{
		margin-top: 5px;
		font-size: 12px;
		color: #BBB;
	}
	
	.active-hostUnit{
		margin-top: 5px;
		font-size: 12px;
		color: #BBB;
	}
	
	/* 会员价格和参加费用 */
	.price-wraper{
		display: flex;
		justify-content: space-between;
		padding: 10px;
		font-size: 13px;
	}
	
	/* 邀请码输入 */
	.code-input-wraper{
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 10px;
		font-size: 13px;
	}
	
	/* 邀请码按钮 */
	.code-button{
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
	.payment-button-wraper{
		position: fixed;
		bottom: 10px;
		left: 0;
		width: 100%;
	}
	
	.payment-button{
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
	<div class="wraper" id = "app" >
		<!-- 活动信息 -->
		<div class="active-wraper">
			<div class="active-image">
				<img src="purchaser/img/logo.png">
			</div>
			<div class="active-other">
				<div class="active-name">
					入会申请缴费
				</div>
				<div class="active-date">
					 {{model.orderDate}}
				</div>
				<div class="active-hostUnit">
					厦门采购师协会
				</div>
			</div>
		</div>
		<div style="height: 10px;background-color: #F0F0F2;"></div>
		<!-- 会员价格 -->
		<div class="price-wraper">
			<div>价格:</div>
			<div style="color: #E60012;">
				<span style="font-size: 9px;">¥</span> {{model.price | toFixed()}}元
			</div>
		</div>
		<div style="height: 10px;background-color: #F0F0F2;"></div>
		
		<!-- 支付按钮 -->
		<div class="payment-button-wraper">
			<div class="payment-button" @click='createOrder()'>确定支付</div>
		</div>
	</div>
	<script type="text/javascript">
		clipImgs("img");
        var order = new Vue({
        	el:'#app',
        	data:{
        		model:{}
        	},
        	created: function () {
        		// vue的初始化函数
        		$.ajax({
        			url:'member/getMemberPrice.pur',
        			dataType:'json',
        			data:{},
        			success: function (res) {
        				if (res.success) {
        					order.model = res;
        				} else {
        					console.log('程序异常，原因: ' + res.message);
        				}
        			},
        			error: function (e) {
        				console.log('网络异常');
        			}
        		})
        	},
        	filters: {
        		toFixed: function (value) {
        			if (parseFloat(value) == 0) {
        				value = 0;
        			} else {
        				value = parseFloat(value).toFixed(2);
        			}
        			
        			return value;
        			
        		}
        	},
        	
        	method: {
        		// 生成订单，并签名
        		createOrder: function () {
        			var param = {};
        			param.productId = this.model.memberId;
        			param.productType = "M";
        			param.price = model.price;
        			$.ajax({
        				url: 'order/createOrder.pur',
        				dataType: 'json',
        				data: {
        					json: encodeURI(JSON.stringify(param))
        				},
        				success: function (res) {
        					// 调用微信支付方法
        					order.wechatPay(res);
        				},
        				error: function (e) {
        					console.log('网络异常');
        				}
        			})
        		},
        		
        		// 调用微信支付API
        		wechatPay: function (paySign) {
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
    								// 购买成功，跳转到会员中心
    								location.href = "purchaser/member_center.jsp";
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