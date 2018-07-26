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
<title>会员详情</title>

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
	
	.footerFill{
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
	      	              		<span class="inputValue" >{{model.name}}</span>
	      	              </div>
	      	         </div>
	      	         
	      	         <!-- 职务   -->
	      	         <div class="line">
	      	              <div class="title">职务</div>
	      	              <div class="value">
	      	              		<span class="inputValue" >{{model.duty}}</span>
	      	              </div>
	      	         </div>
	      	         
	      	         <!-- 身份证号   -->
	      	         <div class="line">
	      	              <div class="title">身份证号</div>
	      	              <div class="value">
	      	              		<span class="inputValue" >{{model.id_card_num}}</span>
	      	              </div>
	      	         </div>
	      	         
	      	         <!-- 籍贯   -->
	      	         <div class="line">
	      	              <div class="title">籍贯</div>
	      	              <div class="value">
	      	              		<span class="inputValue">{{model.province}}/{{model.city}}</span>
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
	      	              		<span class="inputValue" >{{model.email}}</span>
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
      	              		<span class="inputValue" >{{model.affiliation}}</span>
      	              </div>
      	         </div>
      	         
      	         <!-- 行业  -->
      	         <div class="line">
      	              <div class="title">行业 </div>
      	              <div class="value" >
      	                     <span class="inputValue" >{{model.business}}</span>
      	              </div>
      	         </div>
      	         
      	         <!-- 企业类型  -->
      	         <div class="line">
      	              <div class="title">企业类型 </div>
      	              <div class="value">
      	              		<span class="inputValue" >{{model.company_type}}</span>
      	              </div>
      	         </div>
      	         
      	    </div>
      	    
      	    <!-- 会员信息 -->
      	    <div class="member_info">
      	        
      	         <!-- 会员类型  -->
      	         <div class="line">
      	              <div class="title">会员类型</div>
      	              <div class="value" >
      	                     <span class="inputValue" >{{model.type}}</span>
      	              </div>
      	         </div>
      	         
      	         <!-- 会员年费 -->
      	         <div class="line">
      	              <div class="title">会员年费</div>
      	              <div class="value">
      	              		<span class="inputValue">{{model.price}}元</span>
      	              </div>
      	         </div>
      	         
      	         
      	    </div>
      	    
      	    <!-- 填充高度  -->
      	    <div class="footerFill"></div>
      	    
      	  
      	    
      </div>
</body>

<script type="text/javascript">


     // 定义vue全局变量
     var memberDetail = new Vue({
    	 el: "#app",
    	 data: {
    		 model: {}
    	 },
    	 
    	 // vue初始化函数
    	 created: function () {
    		 // 请求服务器接口，查询会员信息
    		 var url = 'member/memberDetail.pur';
    		 var param = {};
    		 requestServer(url, param, function (res) {
    			 if (res.success) {
    				 // 数据请求成功
    				 memberDetail.model = res.memberDetail;
    				 
    				 // 头像初始化
    				 var imgUrl = 'http://purchaser.ecartoon.com.cn/picture/' + memberDetail.model.image;
    				 $("#previwer").attr({"src":imgUrl});
    				 
    			 } else {
    				 // 程序异常，数据请求失败
    				 console.log('程序异常，原因: ' + res.message);
    			 }
    		 });
    	 }
     

      });
  
</script>

</html>