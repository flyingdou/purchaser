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
<title>供应商详情</title>
<link rel="stylesheet" href="purchaser/css/iosSelect.css">
<script type="text/javascript" src="purchaser/js/vue.min.js"></script>
<script type="text/javascript" src="purchaser/js/jquery.min.js"></script>
<script type="text/javascript" src="purchaser/js/commonUtils.js"></script>
<script type="text/javascript" src="purchaser/js/areaData_v2.js"></script>
<script type="text/javascript" src="purchaser/js/iosSelect.js"></script>
<script type="text/javascript"
	src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript"
	src="http://imgcache.qq.com/open/qcloud/js/vod/sdk/ugcUploader.js"></script>

<style type="text/css">
html, body {
	width: 100%;
	height: auto;
	margin: 0;
	padding: 0;
	background: #f0f2f2;
}

.content {
	width: 100%;
	height: 100%;
	overflow: hidden;
}

.base_info {
	background: white;
}

.line {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 0 10px;
	border-bottom: 1px solid #f0f2f2;
}

.line:last-child {
	border: none;
}

.title {
	font-size: 13px;
	padding: 14px 0;
}

.value {
	overflow: hidden;
}

.metering {
	float: right;
	height: 21px;
	line-height: 21px;
	font-size: 14px;
	color: #BBB;
}

.metering-input {
	display: flex;
	align-items: center;
	float: right;
	margin-right: 5px;
	height: 21px;
}

.inputValue {
	font-size: 13px;
	color: #bbb;
	border: none;
	text-align: right;
}

input {
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
	-webkit-user-modify: read-write-plaintext-only;
	outline: none;
}

input::input-placeholder {
	color: #BBB;
}

input::-webkit-input-placeholder, textarea::-webkit-input-placeholder {
	color: #BBB;
}

input:-moz-placeholder, textarea:-moz-placeholder {
	color: #BBB;
}

input::-moz-placeholder, textarea::-moz-placeholder {
	color: #BBB;
}

input:-ms-input-placeholder, textarea:-ms-input-placeholder {
	color: #BBB;
}

.pic_remark {
	margin-top: -20px;
	padding: 10px 0;
}

.imgDiv {
	width: 66px;
	height: 58px;
}

.previwer {
	width: 100%;
	height: 100%;
}

.company_info {
	background: white;
	margin-top: 20px;
}

.member_info {
	background: white;
	margin-top: 20px;
}

.business_scope {
	padding: 10px;
	height: 100px;
	font-size: 14px;
	color: #BBB;
	border: none;
	outline: none;
}

.footerFill {
	width: 100%;
	height: 50px;
}

.funButton {
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	height: auto;
	overflow: hidden;
}

.footer {
	float: left;
	color: white;
	font-size: 13px;
	padding: 15px 0;
	text-align: center;
}

.getMobilecode {
	width: 65%;
	background: #FDD223;
}

.countdown {
	width: 65%;
	background: #FDD223;
}

.pay {
	width: 35%;
	background: #E71C2A;
}
</style>
</head>
<body>
	<!-- 页面最外层 -->
	<div class="content" id="wraper">
		<!-- 基本信息 -->
		<div class="base_info">
			<!-- 公司名称   -->
			<div class="line">
				<div class="title">公司名称</div>
				<div class="value">
						<span class="inputValue">{{model.name}}</span>
				</div>
			</div>

			<!-- 注册资本  -->
			<div class="line">
				<div class="title">注册资本</div>
				<div class="value">
					<div class="metering">万元</div>
					<div class="metering-input">
						<span class="inputValue">{{model.registeredCapital}}</span>	
					</div>
				</div>
			</div>

			<!-- 法人代表  -->
			<div class="line">
				<div class="title">法人代表</div>
				<div class="value">
					<span class="inputValue">{{model.representative}}</span>
				</div>
			</div>

			<!-- 地址   -->
			<div class="line">
				<div class="title">地址</div>
				<div class="value">
					<span class="inputValue">{{model.address}}</span>
				</div>
			</div>

			<!-- 成立日期 -->
			<div class="line">
				<div class="title">成立日期</div>
				<div class="value">
					<span class="inputValue">{{model.createDate}}</span>
				</div>
			</div>

			<!-- 职工人数  -->
			<div class="line">
				<div class="title">职工人数</div>
				<div class="value">
					<div class="metering">人</div>
					<div class="metering-input">
						<span class="inputValue">{{model.workers}}</span>
					</div>
				</div>
			</div>

			<!-- 工厂面积  -->
			<div class="line">
				<div class="title">工厂面积</div>
				<div class="value">
					<div class="metering">平方米</div>
					<div class="metering-input">
						<span class="inputValue">{{model.factoryArea}}</span>
					</div>
				</div>
			</div>

			<!-- 行业类别  -->
			<div class="line">
				<div class="title">行业类别</div>
				<div class="value">
					<span class="inputValue">{{model.business}}</span>
				</div>
			</div>

			<!-- 官网  -->
			<div class="line">
				<div class="title">官网</div>
				<div class="value">
					<span class="inputValue">{{model.officialNetwork}}</span>
				</div>
			</div>

			<!-- 照片 -->
			<div class="line">
				<div class="pic_title">
					<div class="title">照片</div>
					<div class="inputValue pic_remark" style="width: 111.63px;height: 17px;"></div>
				</div>
				<div class="imgDiv">
					<img :src="model.image3" class="previwer"/>
				</div>
				<div class="imgDiv">
					<img :src="model.image2" class="previwer"/>
				</div>
				<div class="imgDiv">
					<img :src="model.image1" class="previwer" />
				</div>
			</div>
		</div>

		<!-- 联系人信息 -->
		<div class="company_info">
			<!-- 联系人名称 -->
			<div class="line">
				<div class="title">联系人</div>
				<div class="value">
					<span class="inputValue">{{model.contact}}</span>
				</div>
			</div>

			<!-- 职务 -->
			<div class="line">
				<div class="title">职务</div>
				<div class="value">
					<span class="inputValue">{{model.duty}}</span>
				</div>
			</div>

			<!-- 联系手机  -->
			<div class="line">
				<div class="title">联系手机</div>
				<div class="value">
					<span class="inputValue">{{model.mobilephone}}</span>
				</div>
			</div>

			<!-- 电子邮箱  -->
			<div class="line">
				<div class="title">电子邮箱</div>
				<div class="value">
					<span class="inputValue">{{model.email}}</span>
				</div>
			</div>

			<!-- 传真-->
			<div class="line">
				<div class="title">传真</div>
				<div class="value">
					<span class="inputValue">{{model.faxNumber}}</span>
				</div>
			</div>
		</div>

		<!-- 业务范围 -->
		<div class="member_info">
			<div style="padding: 10px;">
				<div style="font-size: 14px;">业务范围</div>
				<div class="business_scope">{{model.faxNumber}}</div>
			</div>
		</div>
	</div>
	<script>
		var vue = new Vue({
			el : "#wraper",
			data : {
				model : {
					businessValue: ""
				}
			},

			// 初始化函数
			created : function() {
				// 初始化页面
				this.init();
			},

			// 自定义方法
			methods : {
				// 页面初始化
				init : function() {
					// 请求服务器供应商数据
					$.ajax({
						url: "supplier/getSupplier.pur",
						type: "post",
						data: {
							json: encodeURI(JSON.stringify({supplierId: sessionStorage.getItem("supplierId")}))
						},
						dataType: "json",
						success: function (res) {
							vue.model = res;
						}
					});
					
					// 查询行业类型参数
					var param = {
		    			 business: 1,
		    			 company_type: 2,
		    			 type: 3
			    	 };
					$.ajax({
						url : "member/findParameters.pur",
						data : {
							json : encodeURI(JSON.stringify(param))
						},
						dataType: "json",
						success : function(res) {
							// 网络请求成功
							if (res.success) {
								res.business.forEach(function (item) {
									if (vue.model.business == item.id) {
										vue.model.business = item.value;										
									}
								});
							} else {
								console.log("程序异常");
							}
						},
						error : function(e) {
							console.log("网络异常");
						}
					});
				}
			}
		});
	</script>
</body>
</html>