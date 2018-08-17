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
<title></title>

<link rel="stylesheet" href="purchaser/css/iosSelect.css">
<script type="text/javascript" src="purchaser/js/vue.min.js"></script>
<script type="text/javascript" src="purchaser/js/jquery.min.js"></script>
<script type="text/javascript" src="purchaser/js/commonUtils.js"></script>
<script type="text/javascript" src="purchaser/js/areaData_v2.js" ></script>
<script type="text/javascript" src="purchaser/js/iosSelect.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript" src="http://imgcache.qq.com/open/qcloud/js/vod/sdk/ugcUploader.js"></script>



<style type="text/css">
    html,body{
      width: 100%;
      height: auto;
      margin: 0;
      padding: 0;
      background: #f0f2f2;
    }
    
    .content{
       width: 100%;
       height: 100%;
       overflow: hidden;
    }
    
    .base_info{
      background: white;
    }
    
    .line{
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 0 10px;
      border-bottom: 1px solid #f0f2f2;
    }
    
    .line:last-child {
	  border:none;
    }
    
    .title{
      font-size: 13px;
      padding: 14px 0;
    }
    
    .inputValue{
      font-size: 13px;
      color: #bbb;
      border: none;
      text-align: right;
    }
    
    input{ 
	 -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
	 -webkit-user-modify: read-write-plaintext-only;
	 outline: none;
	}
	
	.pic_remark{
	   margin-top: -20px;
	   padding: 10px 0;
	}
	
	.imgDiv{
	  width: 66px;
	  height: 58px;
	}
	
	#previwer{
	  width: 100%;
	  height: 100%;
	}
	
	.company_info{
	   background: white;
	   margin-top: 20px;
	}
	
	.member_info{
	  background: white;
	  margin-top: 20px;
	}
	
	.footerFill{
	  width: 100%;
	  height: 50px;
	}
	
	
	.funButton{
	  position: fixed;
	  left: 0;
	  bottom: 0;
	  width: 100%;
	  height: auto;
	  overflow: hidden;
	}
	
	.footer{
	  float: left;
	  color: white;
	  font-size: 13px;
	  padding: 15px 0;
	  text-align: center;
	}
	
	.getMobilecode{
	  width: 65%;
	  background: #FDD223;
	}
	
	.countdown{
	  width: 65%;
	  background: #FDD223;
	}
	
	.pay{
	  width: 35%;
	  background: #E71C2A;
	}
	
	
    
    
</style>
</head>
<body>
      <!-- 页面最外层 -->
      <div class="content" id = "app">
      	    <!-- 基本信息 -->
      	    <div class="base_info">
	      	         <!-- 姓名   -->
	      	         <div class="line">
	      	              <div class="title">姓名</div>
	      	              <div class="value">
	      	              		<input class="inputValue" v-model="model.name" type="text" placeholder="请输入真实姓名"/>
	      	              </div>
	      	         </div>
	      	         
	      	         <!-- 职务   -->
	      	         <div class="line">
	      	              <div class="title">职务</div>
	      	              <div class="value">
	      	              		<input class="inputValue" v-model="model.duty" type="text" placeholder="请输入工作职务"/>
	      	              </div>
	      	         </div>
	      	         
	      	         <!-- 身份证号   -->
	      	         <div class="line">
	      	              <div class="title">身份证号</div>
	      	              <div class="value">
	      	              		<input class="inputValue" v-model="model.id_card_num" type="text" placeholder="请输入身份证号码"/>
	      	              </div>
	      	         </div>
	      	         
	      	         <!-- 籍贯   -->
	      	         <div class="line">
	      	              <div class="title">籍贯</div>
	      	              <div class="value" @click="getLocation()">
	      	              		<span class="inputValue">{{model.province}}/{{model.city}}</span><span class="inputValue">&nbsp;&gt;</span>
	      	              </div>
	      	         </div>
	      	         
	      	         <!-- 手机   -->
	      	         <div class="line">
	      	              <div class="title">手机</div>
	      	              <div class="value">
	      	              		<input class="inputValue" v-if="!updatePage" v-model="model.mobilephone" type="number" placeholder="请输入手机号"/>
	      	              		
	      	              		<!-- 不能修改的信息 -->
	      	              		<span class="inputValue" v-if="updatePage">{{model.mobilephone}}</span>
	      	              </div>
	      	         </div>
	      	         
	      	         <!-- 电子邮箱  -->
	      	         <div class="line">
	      	              <div class="title">电子邮箱 </div>
	      	              <div class="value">
	      	              		<input class="inputValue" v-model="model.email" type="text" placeholder="请输入电子邮箱 "/>
	      	              </div>
	      	         </div>
	      	         
	      	         
	      	         <!-- 照片 -->
	      	         <div class="line">
	      	              <div class="pic_title">
	      	              	    <div class="title">照片</div>
	      	              	    <div class="inputValue pic_remark">请上传一寸免冠照</div>
	      	              </div>
	      	              <div class="imgDiv" @click="selectPicture()">
	      	              		<img id="previwer" />
	      	              		<input type="hidden" v-model = "model.image" >
	      	              </div>
	      	         </div>
	      	         
      	    </div>
      	    
      	    <!-- 企业信息 -->
      	    <div class="company_info">
      	         
      	         <!-- 企业名称  -->
      	         <div class="line">
      	              <div class="title">企业名称 </div>
      	              <div class="value">
      	              		<input class="inputValue" v-model="model.affiliation" type="text" placeholder="请输入企业名称"/>
      	              </div>
      	         </div>
      	         
      	         <!-- 行业  -->
      	         <div class="line">
      	              <div class="title">行业 </div>
      	              <div class="value" @click="selectCompanyType()">
      	              		<span class="inputValue" v-model="model.company_type">{{model.company_type}}</span><span class="inputValue">&nbsp;&gt;</span>
      	              </div>
      	         </div>
      	         
      	    </div>
      	    
      	    <!-- 会员信息 -->
      	    <div class="member_info">
      	        
      	         <!-- 会员类型  -->
      	         <div class="line">
      	              <div class="title">会员类型</div>
      	              <div class="value" v-if="!updatePage" @click="selectType()">
      	                     <span class="inputValue" v-model="model.type">{{model.type}}</span><span class="inputValue">&nbsp;&gt;</span>
      	              </div>
      	              
      	              <!-- 不能修改的信息 -->
      	              <div class="value" v-if="updatePage">
      	                     <span class="inputValue" v-model="model.type">{{model.type}}</span>
      	              </div>
      	         </div>
      	         
      	         <!-- 会员年费 -->
      	         <div class="line">
      	              <div class="title">会员年费</div>
      	              <div class="value">
      	              		<span class="inputValue">{{model.price}}元</span>
      	              </div>
      	         </div>
      	         
      	         <!-- 手机验证码-->
      	         <div class="line">
      	              <div class="title">手机验证码</div>
      	              <div class="value">
      	              		<input class="inputValue" v-model="model.code" type="number" placeholder="请输入验证码"/>
      	              </div>
      	         </div>
      	         
      	    </div>
      	    
      	    <!-- 填充高度  -->
      	    <div class="footerFill"></div>
      	    
      	    <!-- 底部功能按钮 -->
      	    <div class="funButton">
      	    	 <div class="footer getMobilecode" v-if = "model.sendCode == '1' " @click='sendMobileCode()' >获取验证码</div>
      	    	 <div class="footer countdown" v-if = "model.sendCode == '0'">重新获取({{model.countdown}})</div>
      	    	 <div class="footer pay" v-if = "!updatePage" @click='toPay()'>支付</div>
      	    	 <div class="footer pay" v-if = "updatePage" @click='update()'>修改</div>
      	    </div>
      	    
      	    
      	   
      	    
      	    
      </div>
</body>

<script type="text/javascript">
     var pageData = {};
     $(function () {
    	 
    	 var src = "purchaser/img/uploadIcon.png";
    	 $("#previwer").attr({"src":src});
    	 
    	 var param = {
    			 company_type: 1,
    			 type: 3,
    			 valid: 1
    	 };
    	 // 请求后台参数数据
    	 $.ajax({
    		 url: "member/findParameters.pur",
    		 data: {
    			 json: encodeURI(JSON.stringify(param))
    		 },
    		 success: function (res) {
    			// 网络请求成功
    			res = JSON.parse(res);
    			if (res.success) {
    				pageData.company_type = res.company_type;
    				pageData.type = res.type;
    				findUserInfo();
    			} else {
    				console.log("程序异常");
    			}
    		 },
    		 error: function (e) {
    			 console.log("网络异常");
    		 }
    	 })
     })
     
     // 查询用户的基本信息
     var findUserInfo = function () {
    	 $.ajax({
    		 url: 'member/findPurchaserInfo.pur',
    		 data: {},
    		 dataType: 'json',
    		 success: function (res) {
    			 if (res.success) {
        			 // 省份初始化
        			 var dou = res.memberInfo;
        			 if (!dou.province || dou.province == '') {
        				 dou.province = "福建";
        			 }
    			 
    			     // 城市初始化
    			     if (!dou.city || dou.city == '') {
    			    	 dou.city = "厦门";
    			     }
    			     
    			     // business 初始化
    			     if (!dou.business || dou.business == '') {
    			     	dou.business = '请选择';
    			     }
    			     
    			     // company_type 初始化
    			     if (!dou.company_type || dou.company_type == '') {
    			     	dou.company_type = '请选择';
    			     }
    			     
    			     // type初始化
    			     if (!dou.type || dou.type == '') {
    			    	 var initType = pageData.type[0];
    			    	 dou.type = initType.value;
    			    	 dou.price = initType.price;
    			    	 dou.type_id = initType.id;
    			     }
    			     
    			     // 初始化操作
    			     dou.sendCode = '1';
    	    		 dou.countdown = 60;
    	    		 
    	    		 // 照片
    				 if (dou.image && dou.image != '') {
    					 var src = 'http://purchaser.ecartoon.com.cn/picture/' + dou.image;
    					 $("#previwer").attr({"src": src});
    				 }
    			     
    				 // 数据请求成功
    				 joinApply.model = dou;
    				 joinApply.isMember = res.isMember;
    				 
    			 } else {
    				 console.log('程序异常，原因: ' + res.message);
    			 }
    		 },
    		 error: function (e) {
    			 // 网络异常
    			 alert('网络异常！');
    		 }
    	 })
     }
     
     
     // 定义vue全局变量
     var joinApply = new Vue({
    	 el: "#app",
    	 data: {
    		 model: {},
    		 updatePage: false
    	 },
    	 
    	 
    	 // 初始化函数
    	 created: function () {
    		 var cuUrl = location.href;
        	 var updatePage = false;
        	 var pageTitle = '入会申请';
        	 if (cuUrl.indexOf("update") != -1) {
    			 // 修改会员信息
    			 updatePage = true;
    			 pageTitle = '修改会员信息';
        	 }
        	 
        	 // 修改页面标题
        	 document.title = pageTitle;
        	 this.updatePage = updatePage;
        	 
        	 
    		 // 请求微信签名，准备调用jsapi接口
    		 $.ajax({
    			 url: "wechat/jsapiSign.pur",
    			 data: {
    				 url: location.href.split("#")[0]
    			 },
    			 success: function (sign) {
    				    sign = JSON.parse(sign);
						wx.config({
							    debug: false, 
							    appId: sign.appid,
							    timestamp: sign.timestamp,
							    nonceStr: sign.nonceStr,
							    signature: sign.signature,
							    jsApiList: [        
									   "chooseImage", 
							           "uploadImage", 
							           "downloadImage"
							    ] 
						});
    			 }
    		 })
    	 },
    	 
    	 // 自定义方法
    	 methods: {
    		 // 跳转到，选择籍贯信息的页面
    		 getLocation: function () {
    			 createCitySelector(function (selectOneObj, selectTwoObj, selectThreeObj) {
	                   joinApply.model.province = selectOneObj.value;
	                   joinApply.model.city = selectTwoObj.value;
	                });
    		 },
    		 
    		 // 行业，弹出层
    		 selectCompanyType: function () {
    			 createSelector({
    				data: pageData.company_type,
    				title: "行业",
    				callback: function (res) {
    					console.log(res);
    					joinApply.model.company_type_id = res.id;
    					joinApply.model.company_type = res.value;
    				}
    			 });
    		 },
    		 
    		 // 选择会员类型
    		 selectType: function () {
    			 createSelector({
    				 data: pageData.type,
    				 title: "会员类型",
    				 callback: function (res) {
    					 console.log(res);
    					 joinApply.model.type_id = res.id;
    					 joinApply.model.type = res.value;
    					 joinApply.model.price = res.price;
    				 }
    			 });
    		 },
    		 
    		 
    		 // 图片选择和上传
    		 selectPicture:	function (){
   				// 调用微信的选择相册和上传接口
   				wx.chooseImage({
   					count: 1, // 默认1
   					sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
   					sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
   					success: function (res) {
						// 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
						wx.uploadImage({
							localId: res.localIds[0], // 需要上传的图片的本地ID，由chooseImage接口获得
							isShowProgressTips: 0, // 默认为1，显示进度提示
							success: function (resx) {
								var serverId = resx.serverId; // 返回图片的服务器端ID
								$("#previwer").attr({"src": res.localIds[0]});
								var serverIds = [serverId];
						        joinApply.downloadPic(serverIds);
							}
						});
					
 					}
   			});
    	},
    	
    	// 从微信服务器下载，用户已上传的照片，到我们自己的服务器上
    	downloadPic: function (serverIds) {
			alert('开始下载图片');
    		var param = {};
    		param.serverIds = serverIds;
    		$.ajax({
    			url: 'wechat/downloadPicture.pur',
    			data: {
    				json: encodeURI(JSON.stringify(param))
    			},
    			dataType: 'json',
    			success: function (res) {
    				if (res.success) {
    					// 图片成功下载到本地服务器
    					joinApply.model.image = res.imgs[0];
    					joinApply.model.imgs = res.imgs;
    				} else {
    					console.log('下载图片失败，原因: ' + res.message);
    				}
    			},
    			error: function (e) {
    				console.log('网络异常！');
    			}
    		
    		})
    	},
    	
    	
    	
    	// 用户点击发送短信
    	sendMobileCode: function () {
    		// 对手机号进行校验
    		var mobilephone = joinApply.model.mobilephone;
    		if (!mobilephone || mobilephone == '') {
    			alert('请先输入手机号！');
    			return;
    		}
    		
    		// 手机号校验通过，访问后台服务器，发送短信验证码
    		var url = 'user/getMobilecode.pur';
    		var param = {
    				mobilephone:mobilephone
    		};
    		requestServer(url, param, function (res) {
    			if (res.success) {
    				// 短信发送成功
    				alert('验证码已下发至您的手机，请注意查收！');
    				
    				// 倒计时60秒
    	    		x = 60;
    	    		setTimeout(function () {
    		    	    x--;
    		    	    if ( x > 0 ) {
    		    	    	joinApply.model.sendCode = '0';
    			    		joinApply.model.countdown = x;
    			    		setTimeout(arguments.callee, 1000);
    		    	    } else {
    		    	    	joinApply.model.sendCode = '1';
    		    	    }
    		    	    
    	    		}, 0);
    				
    				
    			}
    		})
    		
    		
    	},
    	
    	
    	// 用户点击'支付'按钮时
    	toPay: function () {
    		// 数据校验不通过
    		if (!this.checkData()) {
    			return;
    		}
    		
    		var model = joinApply.model;
    		
    		// 去后台将用户数据存储起来
    		$.ajax({
    			url:'member/saveMember.pur',
    			dataType:'json',
    			data: {
    				json: encodeURI(JSON.stringify(model))
    			},
    			success: function (res) {
    				if (res.success) {
    					// 后台请求成功, 跳转到下个页面
    					location.href = "purchaser/order_member.jsp";
    				} else {
    					alert('程序异常，原因: ' + res.message);
    				}
    			},
    			error: function (e) {
    				console.log('网络异常');
    			}
    		})
    		
    	},
    	
    	// 用户点击'修改'按钮时
    	update: function () {
    		// 数据校验
    		if (!this.checkData()){
    			return;
    		}
    		
    		var model = joinApply.model;
    		
    		// 去后台将用户数据存储起来
    		$.ajax({
    			url:'member/updateMember.pur',
    			dataType:'json',
    			data: {
    				json: encodeURI(JSON.stringify(model))
    			},
    			success: function (res) {
    				if (res.success) {
    					// 后台请求成功, 跳转到下个页面
    					location.href = "purchaser/member_center.jsp";
    				} else {
    					alert('程序异常，原因: ' + res.message);
    				}
    			},
    			error: function (e) {
    				console.log('网络异常');
    			}
    		})
    		
    		
    	},
    	
    	// 校验数据
    	checkData: function () {
    		// 如果当前修改当前页面时，则不做会员判断
    		if (!joinApply.updatePage) {
    			// 当前用户已经是会员，中断后续操作
        		if (joinApply.isMember) {
        			alert('你当前已经是会员，无需再次申请成为会员！');
        			return false;
        		}
    		}
    		
    		var model = joinApply.model;
    		
    		// 姓名
    		if (!model.name || model.name == '') {
    			alert('请先填写姓名！');
    			return false;
    		}
    		
    		// 职务
    		if (!model.duty || model.duty == '') {
    			alert('请先填写职务！');
    			return false;
    		}
    		
    		// 身份证号
    		if (!model.id_card_num || model.id_card_num == '') {
    			alert('请先填写身份证号！');
    			return false;
    		}
    		
    		// 籍贯
    		if (!model.province || model.province == '' || !model.city || model.city == '') {
    			alert('请先填写籍贯！');
    			return;
    		}
    		
    		// 手机号
    		if (!model.mobilephone || model.mobilephone == '') {
    			alert('请先填写手机号！');
    			return false;
    		}
    		
    		// 电子邮箱
    		if (!model.email || model.email == '') {
    			alert('请先填写电子邮箱！');
    			return;
    		}
    		
    		// 照片
    		if ((!model.image || model.image == '') && (!model.imgs || model.imgs.length < 1)) {
    			alert('请先上传一寸免冠照！');
    			return false;
    		}
    		
    		// 企业名称
    		if (!model.affiliation || model.affiliation == '') {
    			alert('请先填写企业名称！');
    			return false;
    		}
    		// 行业
    		if (!model.business || model.business == '' || model.business.indexOf('请') > 0) {
    			alert('请先选择行业！');
    			return false;
    		}
    		
    		// 企业类型
    		if (!model.company_type || model.company_type == '' || model.company_type.indexOf('请') > 0) {
    			alert('请先选择企业类型！');
    			return false;
    		}
    		
    		// 会员类型
    		if (!model.type || model.type == '') {
    			alert('请先选择会员类型！');
    			return false;
    		}
    		
    		// 手机验证码
    		if (!model.code || model.code == '') {
    			alert('请先填写手机验证码！');
    			return false;
    		}
    		
    		// 数据校验通过
    		return true;
    		
    	}
    	
    		
    		 
    }
 
});
  
</script>

</html>