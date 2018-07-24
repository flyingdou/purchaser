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
<title>采购师录入</title>

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
	      	         
	      	         <!-- 性别  -->
	      	         <div class="line">
	      	              <div class="title">性别</div>
	      	              <div class="value" @click="selectGender()" >
	      	              		<span class="inputValue">{{model.gender | gender}}</span><span class="inputValue">&nbsp;&gt;</span>
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
	      	              		<input class="inputValue" v-model="model.mobilephone" type="number" placeholder="请输入手机号"/>
	      	              </div>
	      	         </div>
	      	         
	      	         <!-- 电子邮箱  -->
	      	         <div class="line">
	      	              <div class="title">电子邮箱 </div>
	      	              <div class="value">
	      	              		<input class="inputValue" v-model="model.email" type="text" placeholder="请输入电子邮箱 "/>
	      	              </div>
	      	         </div>
	      	         
	      	         
	      	         <!-- 现居住地 -->
	      	         <div class="line">
	      	              <div class="title">现居住地 </div>
	      	              <div class="value">
	      	              		<input class="inputValue" v-model="model.address" type="text" placeholder="输入地址 "/>
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
      	    
      	    <!-- 资历信息 -->
      	    <div class="company_info">
      	         
      	         <!-- 学历 -->
      	         <div class="line">
      	              <div class="title">学历 </div>
      	              <div class="value">
      	              		<input class="inputValue" v-model="model.study" type="text" placeholder="请输入最高学历"/>
      	              </div>
      	         </div>
      	         
      	         <!-- 语言能力 -->
      	         <div class="line">
      	              <div class="title">语言能力</div>
      	              <div class="value" >
      	                     <input class="inputValue" v-model="model.language" type="text" placeholder="请输入语言能力" />
      	              </div>
      	         </div>
      	         
      	         <!-- 资格认证 -->
      	         <div class="line">
      	              <div class="title">资格认证 </div>
      	              <div class="value">
      	              		<input class="inputValue" v-model="model.qualification" type="text" placeholder="请输入资格认证信息" />
      	              </div>
      	         </div>
      	         
      	         
      	         <!-- 教育经历-->
      	         <div class="line">
      	              <div class="title">教育经历 </div>
      	              <div class="value">
      	              		<input class="inputValue" v-model="model.learning" type="text" placeholder="请输入教育经历" />
      	              </div>
      	         </div>
      	         
      	         
      	         <!-- 工作履历 ，跳转二级页面-->
      	         <div class="line">
      	              <div class="title">工作履历 </div>
      	              <div class="value" @click="gotoExperience()">
      	              		<span class="inputValue" v-if="experience.post">{{experience.post}}/{{experience.company_name}}</span><span class="inputValue" v-if="experience.post">&nbsp;&gt;</span>
      	              		<span class="inputValue" v-if="!experience.post">{{experience.post}}{{experience.company_name}}</span><span class="inputValue" v-if="!experience.post">&nbsp;&gt;</span>
      	              </div>
      	         </div>
      	         
      	         
      	         <!-- 自我评价 -->
      	         <div class="line">
      	              <div class="title">自我评价 </div>
      	              <div class="value">
      	              		<input class="inputValue" v-model="model.self_evaluation" type="text" placeholder="请输入自我评价" />
      	              </div>
      	         </div>
      	         
      	         
      	         <!-- 短信验证码 -->
      	         <div class="line">
      	              <div class="title">短信验证码 </div>
      	              <div class="value">
      	              		<input class="inputValue" v-model="model.code" type="number" placeholder="请输入验证码" />
      	              </div>
      	         </div>
      	         
      	    </div>
      	    
      	    
      	    <!-- 填充高度  -->
      	    <div class="footerFill"></div>
      	    
      	    <!-- 底部功能按钮 -->
      	    <div class="funButton">
      	    	 <div class="footer getMobilecode" v-if = "model.sendCode == '1' " @click='sendMobileCode()' >获取验证码</div>
      	    	 <div class="footer countdown" v-if = "model.sendCode == '0'">重新获取({{model.countdown}})</div>
      	    	 <div class="footer pay" @click='save()'>保存</div>
      	    </div>
      	    
      	    
      	   
      	    
      	    
      </div>
</body>

<script type="text/javascript">
     var pageData = {};
     $(function () {
    	 var src = "purchaser/img/uploadIcon.png";
    	 $("#previwer").attr({"src":src});
    	 
    	 var gender = [{'id':'M','value':'男'},{'id':'F','value':'女'}];
    	 pageData.gender = gender;
    	 
    	 var admirer = sessionStorage.getItem('admirer');
    	 
    	 // 初始化当前页面数据
    	 if (!admirer || admirer == '') {
    	 	findUserInfo();
    	 } else {
    		joinApply.model = JSON.parse(admirer);
    	 }
    	 
    	 // 工作履历初始化
    	 var experienceList = sessionStorage.getItem('experienceList');
    	 if (experienceList && experienceList != '') {
    		 experienceList = JSON.parse(experienceList);
    		 joinApply.experience = experienceList[0];
    	 }
    	
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
    		 model: {
    			 gender_name:"男"
    		 },
    		 experience: {}
    	 },
    	 
    	 // 初始化函数
    	 created: function () {
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
    	 
    	 // 过滤器
    	 filters: {
    		 // 性别过滤
    		 gender: function (value) {
    			 if (value == 'M') {
    				 value = '男';
    			 } else {
    				 value = '女';
    			 }
    			 return value;
    		 }
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
    		 
    		 // 选择性别，弹出层
    		 selectGender: function () {
    			 createSelector({
    				data: pageData.gender,
    				title: "选择性别",
    				callback: function (res) {
    					console.log(res);
    					joinApply.model.gender = res.id;
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
    		
    		// 手机号校验通过，访问后台服务器，发送短信验证码(暂时不发送)
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
    		
    	},
    	
    	// 跳转到填写工作履历的页面
    	gotoExperience: function () {
    		// 去之前将本页面已填写的数据保存下来
    		var admirer = joinApply.model;
    		sessionStorage.setItem('admirer', JSON.stringify(admirer));
    		window.location.href = 'purchaser/experience.jsp';
    	},
    	
    	
    	
    	// 用户点击'保存'按钮时
    	save: function () {
    		var model = joinApply.model;
    		var experience = joinApply.experience;
    		
    		// 姓名
    		if (!model.name || model.name == '') {
    			alert('请先填写姓名！');
    			return;
    		}
    		
    		// 身份证号
    		if (!model.id_card_num || model.id_card_num == '') {
    			alert('请先填写身份证号！');
    			return;
    		}
    		
    		// 籍贯
    		if (!model.province || model.province == '' || !model.city || model.city == '') {
    			alert('请先选择籍贯！');
    			return;
    		}
    		
    		// 手机号
    		if (!model.mobilephone || model.mobilephone == '') {
    			alert('请先填写手机号！');
    			return;
    		}
    		
    		// 电子邮箱
    		if (!model.email || model.email == '') {
    			alert('请先填写电子邮箱！');
    			return;
    		}
    		
    		// 现居地址
    		if (!model.address || model.address == '' ) {
    			alert('请先填写现居地址！');
    			return;
    		}
    		
    		// 照片
    		if ((!model.image || model.image == '') && (!model.imgs || model.imgs.length < 1) ) {
    			alert('请先上传一寸免冠照！');
    			return;
    		}
    		
    		// 学历
    		if (!model.study || model.study == '' ) {
    			alert('请先填写学历！');
    			return;
    		}
    		
    		// 语言能力
    		if (!model.language || model.language == '' ) {
    			alert('请先填写语言能力！');
    			return;
    		}
    		
    		
    		// 资格认证
    		if (!model.qualification || model.qualification == '' ) {
    			alert('请先填写资格认证！');
    			return;
    		}
    		
    		
    		// 教育经历
    		if (!model.learning || model.learning == '' ) {
    			alert('请先填写教育经历！');
    			return;
    		}
    		
    		// 工作履历
    		if (!experience || experience == '') {
    			alert('请先填写工作履历！');
    			return;
    		}
    		
    		
    		// 自我评价
    		if (!model.self_evaluation || model.self_evaluation == '' ) {
    			alert('请先填写自我评价信息！');
    			return;
    		}
    		
    		// 手机验证码
    		if (!model.code || model.code == '') {
    			alert('请先填写手机验证码！');
    			return;
    		}
    		
    		// 数据校验通过，将数据保存到服务器
    		var url = 'admirer/saveAdmirer.pur';
    		var param = model;
    		// 请求服务器
    		requestServer(url, param, function (res) {
    			if (res.success) {
    				// 数据保存成功
    				console.log('数据保存成功，即将跳转到采购师列表页面');
    			} else {
    				// 程序异常
    				console.log('程序异常，原因: ' + res.message);
    			}
    			
    		});
    		
    		
    		
    		
    		
    	}
    	
    		
    		 
    }
 
});
  
</script>

</html>