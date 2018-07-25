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
<title>采购师详情</title>

<script type="text/javascript" src="purchaser/js/vue.min.js"></script>
<script type="text/javascript" src="purchaser/js/jquery.min.js"></script>
<script type="text/javascript" src="purchaser/js/commonUtils.js"></script>


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
	
	.fillxx{
	  width: 100%;
	  height: 50px;
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
	      	              		<span class="inputValue">{{model.name}}</span>
	      	              </div>
	      	         </div>
	      	         
	      	         <!-- 性别  -->
	      	         <div class="line">
	      	              <div class="title">性别</div>
	      	              <div class="value" >
	      	              		<span class="inputValue">{{model.gender | gender}}</span>
	      	              </div>
	      	         </div>
	      	         
	      	         <!-- 身份证号   -->
	      	         <div class="line">
	      	              <div class="title">身份证号</div>
	      	              <div class="value">
	      	              		<span class="inputValue">{{model.id_card_num}}</span>
	      	              </div>
	      	         </div>
	      	         
	      	         <!-- 籍贯   -->
	      	         <div class="line">
	      	              <div class="title">籍贯</div>
	      	              <div class="value">
	      	              		<span class="inputValue">{{model.concord}}</span>
	      	              </div>
	      	         </div>
	      	         
	      	         <!-- 手机   -->
	      	         <div class="line">
	      	              <div class="title">手机</div>
	      	              <div class="value">
	      	              		<span class="inputValue" >{{model.mobilephone}}</span>
	      	              </div>
	      	         </div>
	      	         
	      	         <!-- 电子邮箱  -->
	      	         <div class="line">
	      	              <div class="title">电子邮箱 </div>
	      	              <div class="value">
	      	              		<span class="inputValue">{{model.email}}</span>
	      	              </div>
	      	         </div>
	      	         
	      	         
	      	         <!-- 现居住地 -->
	      	         <div class="line">
	      	              <div class="title">现居住地 </div>
	      	              <div class="value">
	      	              		<span class="inputValue">{{model.address}}</span>
	      	              </div>
	      	         </div>
	      	         
	      	         
	      	         <!-- 照片 -->
	      	         <div class="line">
	      	              <div class="pic_title">
	      	              	    <div class="title">照片</div>
	      	              	    <div class="inputValue pic_remark">一寸免冠照</div>
	      	              </div>
	      	              <div class="imgDiv">
	      	              		<img id="previwer" />
	      	              </div>
	      	         </div>
	      	         
      	    </div>
      	    
      	    <!-- 资历信息 -->
      	    <div class="company_info">
      	         
      	         <!-- 学历 -->
      	         <div class="line">
      	              <div class="title">学历 </div>
      	              <div class="value">
      	              		<span class="inputValue">{{model.study}}</span>
      	              </div>
      	         </div>
      	         
      	         <!-- 语言能力 -->
      	         <div class="line">
      	              <div class="title">语言能力</div>
      	              <div class="value" >
      	                     <span class="inputValue">{{model.language}}</span>
      	              </div>
      	         </div>
      	         
      	         <!-- 资格认证 -->
      	         <div class="line">
      	              <div class="title">资格认证 </div>
      	              <div class="value">
      	              		<span class="inputValue">{{model.qualification}}</span>
      	              </div>
      	         </div>
      	         
      	         
      	         <!-- 教育经历-->
      	         <div class="line">
      	              <div class="title">教育经历 </div>
      	              <div class="value">
      	              		<span class="inputValue">{{model.learning}}</span>
      	              </div>
      	         </div>
      	         
      	         <!-- 工作履历 -->
      	         <div class="line">
      	              <div class="title">工作履历 </div>
      	              <div class="value">
      	              		<span class="inputValue">{{model.post}}/{{model.company_name}}</span>
      	              </div>
      	         </div>
      	         
      	         
      	         <!-- 自我评价 -->
      	         <div class="line">
      	              <div class="title">自我评价 </div>
      	              <div class="value">
      	              		<span class="inputValue">{{model.self_evaluation}}</span>
      	              </div>
      	         </div>
      	         
	      	    <!-- 填充高度 -->
	      	    <div class="fillxx"></div>
      	         
      	    
      	    </div>
      	    
      	    
      	    
      </div>
</body>

<script type="text/javascript">
     var pageData = {};
     $(function () {
    	 var src = "purchaser/img/uploadIcon.png";
    	 $("#previwer").attr({"src":src});
    	 
    	 // 初始化当前页面数据
    	 findUserInfo();
    	 
     })
     
     // 查询用户的基本信息
     var findUserInfo = function () {
    	 // 从上一页面取出数据
    	 var admirerId = sessionStorage.getItem('admirerId');
    	 var param = {
    			 id: admirerId
    	 };
    	 $.ajax({
    		 url: 'admirer/admirerDetailById.pur',
    		 data: {
    			 json: encodeURI(JSON.stringify(param))
    		 },
    		 dataType: 'json',
    		 success: function (res) {
    			 if (res.success) {
  					 var dou = res.admirerDetail;
    				 var src = 'http://purchaser.ecartoon.com.cn/picture/' + dou.image;
  					 $("#previwer").attr({"src": src});
    				 admirer.model = dou;
    			 
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
     var admirer = new Vue({
    	 el: "#app",
    	 data: {
    		 model: {
    			 gender_name:"男"
    		 }
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
    	 
    	 
 
});
  
</script>

</html>