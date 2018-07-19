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
<title>活动详情</title>
<link rel="stylesheet" href="purchaser/css/iosSelect.css">
<script type="text/javascript" src="purchaser/js/vue.min.js"></script>
<script type="text/javascript" src="purchaser/js/jquery.min.js"></script>
<script type="text/javascript" src="purchaser/js/commonUtils.js"></script>
<script type="text/javascript" src="purchaser/js/areaData_v2.js"></script>
<script type="text/javascript" src="purchaser/js/iosSelect.js"></script>
<style type="text/css">
	html,body{
		width: 100%;
		height: 100%;
		margin: 0;
		padding: 0;
	}
	
	img{
		width: 100%;
		height: auto;
	}

	.active-image{
		width: 100%;
		height: 188px;
		overflow: hidden;
	}
	
	.active-info-row{
		padding: 10px;
		overflow: hidden;
		border-bottom: 1px solid #F0F0F2;
	}
	
	.active-name-time{
		float: left;
	}
	
	.active-name{
		font-size: 13px;
		color: #101010;
	}
	
	.active-time{
		margin-top: 5px;
		font-size: 12px;
		color: #BBB;
	}
	
	.active-price{
		float: right;
		height: 38px;
		line-height: 38px;
		font-size: 15px;
		color: #FF0000;
	}
	
	.active-address-row{
		padding: 10px;
		overflow: hidden;
		border-bottom: 1px solid #F0F0F2;
	}
	
	.active-address-title{
		float: left;
		overflow: hidden;
	}
	
	.active-address-title-icon{
		float: left;
		width: 14px;
		height: 18px;
		overflow: hidden;
	}
	
	.active-address-title-content{
		float: left;
		margin-left: 10px;
		height: 18px;
		line-height: 18px;
		font-size: 12px;
	}
	
	.active-address-content{
		float: right;
		height: 18px;
		line-height: 18px;
		font-size: 12px;
		color: #BBB;
	}
	
	.active-mobilephone-row{
		padding: 10px;
		overflow: hidden;
		border-bottom: 1px solid #F0F0F2;
	}
	
	.active-mobilephone-title{
		float: left;
		overflow: hidden;
	}
	
	.active-mobilephone-title-icon{
		float: left;
		width: 14px;
		height: 18px;
	}
	
	.active-mobilephone-title-content{
		float: left;
		margin-left: 10px;
		height: 18px;
		line-height: 18px;
		font-size: 13px;
	}
	
	.active-users{
		padding: 10px;
		border-bottom: 1px solid #F0F0F2;
	}
	
	.active-users-title{
		overflow: hidden;
	}
	
	.active-users-title-content{
		float: left;
		font-size: 13px;
		height: 21px;
		line-height: 21px;
	}
	
	.active-user-title-icon{
		float: right;
		width: 14px;
	}
	
	.active-users-wraper{
		overflow: scroll;
	}
	
	.active-user-list{
		margin-top: 10px;
		overflow: hidden;
	}
	
	.active-user-item{
		float: left;
		margin-right: 10px;
		width: 80px;
	}
	
	.active-user-image > img{
		border-radius: 50%;
	}
	
	.active-user-name{
		text-align: center;
		font-size: 12px;
		color: #BBB;
	}
	
	.active-remark-title{
		padding: 10px;
		overflow: hidden;
	}
	
	.active-remark-title-icon{
		float: left;
		width: 22px;
	}
	
	.active-remark-title-content{
		float: left;
		margin-left: 10px;
		font-size: 13px;
		height: 20px;
		line-height: 20px;
	}
	
	.active-remark-content{
		margin-bottom: 55px;
		padding: 0 22px;
		font-size: 12px;
		color: #BBB;
	}
	
	.footer{
		position: fixed;
		bottom: 0;
		left: 0;
		z-index: 1;
		width: 100%;
		height: 44px;
		text-align: center;
		line-height: 44px;
		font-size: 14px;
		color: #FFF;
		overflow: hidden;
	}
	
	.active-date{
		float: left;
		width: 70%;
		background-color: #FED100;
	}
	
	.go-buy{
		float: left;
		width: 30%;
		background-color: #E60012;
	}
</style>
</head>
<body>
	<div id="wraper">
		<div class="active-image">
			<img src="https://www.ecartoon.com.cn/picture/wangyan1.jpg">
		</div>
		<div class="main">
			<div class="active-info-row">
				<div class="active-name-time">
					<div class="active-name">{{active.name}}</div>
					<div class="active-time">{{active.startDate}}</div>
				</div>
				<div class="active-price">
					¥{{active.price}}/人
				</div>
			</div>
			<div class="active-address-row" @click="goActiveMap">
				<div class="active-address-title">
					<div class="active-address-title-icon">
						<img src="https://www.ecartoon.com.cn/picture/201806151307.png">
					</div>
					<div class="active-address-title-content">
						{{active.address}}
					</div>
				</div>
				<div class="active-address-content">
					查看地图
				</div>
			</div>
			<div class="active-mobilephone-row">
				<div class="active-mobilephone-title">
					<div class="active-mobilephone-title-icon">
						<img src="https://www.ecartoon.com.cn/picture/201806151359.png">
					</div>
					<div class="active-mobilephone-title-content">
						13657277062
					</div>
				</div>
			</div>
			<div class="active-mobilephone-row">
				<div class="active-mobilephone-title">
					<div class="active-mobilephone-title-icon">
						<img src="https://www.ecartoon.com.cn/picture/201806151404.png">
					</div>
					<div class="active-mobilephone-title-content">
						主办单位 : {{active.hostUnit}}
					</div>
				</div>
			</div>
			<div class="active-users">
				<div class="active-users-title" @click="goUserListPage">
					<div class="active-users-title-content">
						已报名 <span style="color:#FF0000;">{{active.joinCount}}</span> 人 / 限额 
						<span style="color:#FF0000;">{{active.upperLimit}}</span> 人
					</div>
					<div class="active-user-title-icon">
						<img src="https://www.ecartoon.com.cn/picture/201806151423.png">
					</div>
				</div>
				<div class="active-users-wraper">
					<div class="active-user-list" :style="user_list_width">
						<div class="active-user-item" v-for="(item,i) in active.userList">
							<div class="active-user-image">
								<img src="https://www.ecartoon.com.cn/picture/wangyan1.jpg">
							</div>
							<div class="active-user-name">
								{{item.nick}}
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="active-remark">
				<div class="active-remark-title">
					<div class="active-remark-title-icon">
						<img src="https://www.ecartoon.com.cn/picture/201806151519.png">
					</div>
					<div class="active-remark-title-content">
						活动说明
					</div>
				</div>
				<div class="active-remark-content">
					{{active.remark}}
				</div>
			</div>
		</div>
		<div class="footer">
			<div class="active-date">
				报名截止日 : {{active.joinEndDate}}
			</div>
			<div class="go-buy" @click="submitOrder()">
				我要报名
			</div>
		</div>
	</div>
<script>
	var vue = new Vue({
		el: "#wraper",
		data: {
			active: {},
			userList: [],
			user_list_width: "width: 100%"
		},
		created: function () {
			// 初始化页面
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
			
			// 初始化页面
			init: function () {
				var activeId = sessionStorage.getItem("activeId");
				// 回调处理
				var callback = function (res) {
					vue.active = res.active;
					vue.userList = res.userList;
					vue.active.joinCount = res.userList.length;
					var width = res.userList.length * 25;
					if(width > 100){
						vue.user_list_width = `width: ${width}%`;
					}
					// 处理图片
					clipImgs("img");
				}
				// 请求服务端数据
				this.requestServer("active/getActiveById.pur", {activeId : activeId}, callback);
			},
			
			// 查看活动地点(在地图中看)
			goActiveMap: function () {
				sessionStorage.setItem("active", JSON.stringify(this.active));
				location.href = "purchaser/active_map.jsp";
			},
			
			// 查看活动报名人员
			goUserListPage: function () {
				var active = this.active;
				active.userList = this.userList;
				sessionStorage.setItem("active", JSON.stringify(this.active));
				location.href = "purchaser/active_user_list.jsp";
			},
			
			// 到订单也面提交订单
			submitOrder: function () {
				if (new Date(this.active.joinEndDate) < new Date()) {
					alert("报名时间已结束!");
					return;
				}
				if (this.active.joinCount < this.active.upperLimit) {
					sessionStorage.setItem("active", JSON.stringify(this.active));
					location.href = "purchaser/order_active.jsp";
				} else {		
					alert("活动报名人数已达上限");
				}
			}
		}
	});
</script>
</body>
</html>