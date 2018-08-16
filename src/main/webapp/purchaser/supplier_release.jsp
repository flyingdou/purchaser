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
<title>供应商录入</title>
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
	margin: 0;
	padding: 0;
	width: 100%;
	height: auto;
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
	padding: 10px 5%;
	width: 90%;
	height: 100px;
	font-size: 14px;
	color: #BBB;
	border: none;
	outline: none;
	resize: none;
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
					<input class="inputValue" v-model="model.name" type="text"
						placeholder="请输入公司名称" />
				</div>
			</div>

			<!-- 注册资本  -->
			<div class="line">
				<div class="title">注册资本</div>
				<div class="value">
					<div class="metering">万元</div>
					<div class="metering-input">
						<input class="inputValue" v-model="model.registeredCapital"
							type="number" />
					</div>
				</div>
			</div>

			<!-- 法人代表  -->
			<div class="line">
				<div class="title">法人代表</div>
				<div class="value">
					<input class="inputValue" v-model="model.representative"
						type="text" placeholder="请输入姓名" />
				</div>
			</div>

			<!-- 地址   -->
			<div class="line">
				<div class="title">地址</div>
				<div class="value">
					<input class="inputValue" v-model="model.address" type="text"
						placeholder="请输入地址" />
				</div>
			</div>

			<!-- 成立日期 -->
			<div class="line" @click="selectDate">
				<div class="title">成立日期</div>
				<div class="value">
					<span class="inputValue">{{model.createDate}}</span><span
						class="inputValue">&nbsp;&gt;</span>
				</div>
			</div>

			<!-- 职工人数  -->
			<div class="line">
				<div class="title">职工人数</div>
				<div class="value">
					<div class="metering">人</div>
					<div class="metering-input">
						<input class="inputValue" v-model="model.workers" type="number" />
					</div>
				</div>
			</div>

			<!-- 工厂面积  -->
			<div class="line">
				<div class="title">工厂面积</div>
				<div class="value">
					<div class="metering">平方米</div>
					<div class="metering-input">
						<input class="inputValue" v-model="model.factoryArea"
							type="number" />
					</div>
				</div>
			</div>

			<!-- 行业类别  -->
			<div class="line" @click="selectBusiness">
				<div class="title">行业类别</div>
				<div class="value">
					<span class="inputValue">{{model.businessValue}}</span><span
						class="inputValue">&nbsp;&gt;</span>
				</div>
			</div>

			<!-- 官网  -->
			<div class="line">
				<div class="title">官网</div>
				<div class="value">
					<input class="inputValue" v-model="model.officialNetwork"
						type="text" placeholder="请输入网址" />
				</div>
			</div>

			<!-- 照片 -->
			<div class="line">
				<div class="pic_title">
					<div class="title">照片</div>
					<div class="inputValue pic_remark">请上传3张公司照片</div>
				</div>
				<div class="imgDiv" @click="selectPicture()" v-if="imageList.length == 0">
					<img src="" class="previwer" />
				</div>
				<div class="imgDiv" @click="selectPicture()" v-for="(item, i) in imageList">
					<img :src="item.localId" class="previwer" />
				</div>
			</div>
		</div>

		<!-- 联系人信息 -->
		<div class="company_info">
			<!-- 联系人名称 -->
			<div class="line">
				<div class="title">联系人</div>
				<div class="value">
					<input class="inputValue" v-model="model.contact" type="text"
						placeholder="请输入姓名" />
				</div>
			</div>

			<!-- 职务 -->
			<div class="line">
				<div class="title">职务</div>
				<div class="value">
					<input class="inputValue" v-model="model.duty" type="text"
						placeholder="请输入职务" />
				</div>
			</div>

			<!-- 联系手机  -->
			<div class="line">
				<div class="title">联系手机</div>
				<div class="value">
					<input class="inputValue" v-model="model.mobilephone" type="text"
						placeholder="请输入手机号" />
				</div>
			</div>

			<!-- 电子邮箱  -->
			<div class="line">
				<div class="title">电子邮箱</div>
				<div class="value">
					<input class="inputValue" v-model="model.email" type="text"
						placeholder="请输入电子邮箱" />
				</div>
			</div>

			<!-- 传真-->
			<div class="line">
				<div class="title">传真</div>
				<div class="value">
					<input class="inputValue" v-model="model.faxNumber" type="text"
						placeholder="请输入传真号" />
				</div>
			</div>

			<!-- 手机验证码-->
			<div class="line">
				<div class="title">手机验证码</div>
				<div class="value">
					<input class="inputValue" v-model="model.code" type="number"
						placeholder="请输入验证码" />
				</div>
			</div>
		</div>

		<!-- 业务范围 -->
		<div class="member_info">
			<div style="padding: 10px;">
				<div style="font-size: 14px;">业务范围</div>
				<textarea class="business_scope" placeholder="请输入业务范围"
					v-model="model.remark"></textarea>
			</div>
		</div>

		<!-- 填充高度  -->
		<div class="footerFill"></div>

		<!-- 底部功能按钮 -->
		<div class="funButton" v-if="!isHide">
			<div class="footer getMobilecode" v-if="model.sendCode == '1' "
				@click='sendMobileCode()'>获取验证码</div>
			<div class="footer countdown" v-if="model.sendCode == '0'">重新获取({{timeout}})</div>
			<div class="footer pay" @click='submit()'>保存</div>
		</div>
	</div>
	<script>
		var vue = new Vue({
			el : "#wraper",
			data : {
				businessList : [],
				model : {
					sendCode : 1,
					createDate : '',
					businessValue : ''
				},
				imageList : [],
				timeout: 0,
				isHide: false
			},

			// 初始化函数
			created : function() {
				// 初始化页面
				this.init();
				
				// ios软键盘弹出不会触发resize事件
			    $(window).resize(function(){ 
			    	Vue.set(vue, "isHide", vue.isHide ? false : true);
			    });
			},

			// 自定义方法
			methods : {

				// 页面初始化
				init : function() {
					// 初始化时间
					var now = new Date();
					var year = now.getFullYear();
					var month = now.getMonth() + 1;
					var date = now.getDate();
					this.model.createDate = year + "-"
							+ (month < 10 ? "0" + month : month) + "-"
							+ (date < 10 ? "0" + date : date);

					// 请求后台参数数据
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
						success : function(res) {
							// 网络请求成功
							res = JSON.parse(res);
							if (res.success) {
								vue.businessList = res.business;
							} else {
								console.log("程序异常");
							}
						},
						error : function(e) {
							console.log("网络异常");
						}
					})

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
								jsApiList : [ "chooseImage", "uploadImage",
										"downloadImage" ]
							});
						}
					})
				},

				// 选择行业，弹出层
				selectBusiness : function() {
					createSelector({
						data : this.businessList,
						title : "选择行业",
						callback : function(res) {
							vue.model.business = res.id;
							vue.model.businessValue = res.value;
						}
					});
				},

				// 选择日期
				selectDate : function() {
					createDateSelector(function(year, month, date) {
						vue.model.createDate = year + "-"
								+ (month < 10 ? "0" + month : month) + "-"
								+ (date < 10 ? "0" + date : date);
					});
				},

				// 图片选择和上传
				selectPicture : function() {
					// 调用微信的选择相册和上传接口
					wx.chooseImage({
						// 照片选择数量
						count : 3, 
						// 可以指定是原图还是压缩图，默认二者都有
						sizeType : [ 'original', 'compressed' ], 
						// 可以指定来源是相册还是相机，默认二者都有
						sourceType : [ 'album', 'camera' ], 
						// 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
						success : function(res) {
							vue.wxUpload(res.localIds, 0);
						}
					});
				},
				
				// 上传图片到微信服务器
				wxUpload: function (localIds, index) {
					// 递归调用自身 
					if (index < localIds.length) {
						var localId = localIds[index];
						wx.uploadImage({
							localId: localId, // 需要上传的图片的本地ID，由chooseImage接口获得
							isShowProgressTips : 0, // 默认为1，显示进度提示
							success : function(res) {
								// 返回图片的服务器端Id, 保存到vue的model中
								vue.imageList.push({
									localId: localId,
									serverId: res.serverId
								});
							}
						});
						
						index++;
						arguments.callee(localIds, index);
					}
				},

				// 用户点击发送短信
				sendMobileCode : function() {
					// 对手机号进行校验
					var mobilephone = vue.model.mobilephone;
					if (!mobilephone || mobilephone == '') {
						alert('请先输入手机号！');
						return;
					}
					
					// 手机号校验通过，访问后台服务器，发送短信验证码
					var url = "user/getMobilecode.pur";
					var param = {
						mobilephone: mobilephone
					}
					requestServer(url, param, function(res) {
						if (res.success) {
							// 倒计时60秒
							alert('验证码已下发至您的手机，请注意查收！');
							var timeout = 60;
							setTimeout(function() {
								timeout--;
								if (timeout > 0) {
									vue.model.sendCode = '0';
									vue.timeout = timeout;
									setTimeout(arguments.callee, 1000);
								} else {
									vue.model.sendCode = '1';
								}
							}, 0);
						} else {
							alert(res.message);
						}
					})
				},

				// 提交资料
				submit : function() {
					var model = vue.model;

					// 姓名
					if (!model.name || model.name == '') {
						alert('请先填写姓名！');
						return;
					}

					// 注册资本
					if (!model.registeredCapital || model.registeredCapital == '') {
						alert('请先填写注册资本！');
						return;
					}
					
					// 法人代表
					if (!model.representative || model.representative == '') {
						alert('请先填写法人代表！');
						return;
					}
					
					// 地址
					if (!model.address || model.address == '') {
						alert('请先填写地址！');
						return;
					}
					
					// 职工人数
					if (!model.workers || model.workers == '') {
						alert('请先填写职工人数！');
						return;
					}
					
					// 工厂面积
					if (!model.factoryArea || model.factoryArea == '') {
						alert('请先填写工厂面积！');
						return;
					}
					
					// 官网
					if (!model.officialNetwork || model.officialNetwork == '') {
						alert('请先填写官网地址！');
						return;
					}

					// 联系人名称
					if (!model.contact || model.contact == '') {
						alert('请先填联系人姓名！');
						return;
					}
					
					// 职务
					if (!model.business || model.business == '') {
						alert('请输入职务！');
						return;
					}

					// 职务
					if (!model.business || model.business == '') {
						alert('请输入职务！');
						return;
					}

					// 联系手机
					if (!model.mobilephone || model.mobilephone == '') {
						alert('请输入手机号码！');
						return;
					}

					// 电子邮箱
					if (!model.email || model.email == '') {
						alert('请先填写电子邮箱！');
						return;
					}
					
					// 传真
					if (!model.faxNumber || model.faxNumber == '') {
						alert('请先填写传真号！');
						return;
					}
					
					// 手机验证码
		    		if (!model.code || model.code == '') {
		    			alert('请先填写手机验证码！');
		    			return;
		    		}
					
					// 处理图片
					vue.imageList.forEach(function (item, i) {
						if (i < 3 && item.serverId) {
							model["image" + (i + 1)] = item.serverId;
						}
					});

					// 数据校验通过，提交服务器
					$.ajax({
						url: "supplier/release.pur",
						type: "post",
						data: {
							json: encodeURI(JSON.stringify(model))
						},
						dataType: "json",
						success: function (res) {
							if (res.success) {
								alert("发布成功!");
								location.href = "purchaser/supplier_list.jsp";
							} else {
								alert(res.message);
							}
						}
					});
				}
			}
		});
	</script>
</body>
</html>