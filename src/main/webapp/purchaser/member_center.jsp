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
<title>会员中心</title>
<script type="text/javascript" src="purchaser/js/vue.min.js"></script>
<script type="text/javascript" src="purchaser/js/jquery.min.js"></script>
<script type="text/javascript" src="purchaser/js/commonUtils.js"></script>

<style type="text/css">
    html,body{
       margin: 0;
       padding: 0;
       width: 100%;
       height: 100%;
       background: #f0f0f2;
    }
    
    .content{
       position: relative;
       width: 100%;
       height: 100%;
    }
    
    .userInfo{
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 10px 16px;
      background: white;
      margin-bottom: 20px;
    }
    
    .userImgDiv{
       width: 50px;
       height: 50px;
       border-radius: 50%;
       overflow: hidden;
    }
    
    .userImg{
      width: 100%;
      height: 100%;
    }
    
    .nick{
      margin-left: -52%;
    }
    
    .memberInfoDiv{
      width: 100%;
      overflow: hidden;
      background: url("purchaser/img/member_bg.png") no-repeat;
      background-size: 100% 100%;
    }
    
    .memberInfo{
      position: absolute;
      bottom: 20px;
      width: 100%;
      padding: 10px 0;
    }
    
    .top{
      margin-top: 65%;
      font-size: 18px;
      width: 100%;
      text-align: center;
    }
    
    .memberImg{
       padding-top: 15px;
       width: 90px;
       height: 100px;
    }
    
    .description{
       font-size: 13px;
       color: white;
       text-align: center;
    }
    
    .left{
      float: left;
      padding-left: 10%;
    }
    
    .right{
      float: left;
      padding-left: 10%;
    }
    
    .titleFont{
      font-size: 18px;
      color: white;
      line-height: 36px;
    }
    
    
    .notMemberDiv{
      width: 100%;
      background: white;
    }
    
    .remindTop{
      padding: 0 15%;
      padding-top: 40px;
      padding-bottom: 20px;
      margin: 0 auto;
    }
    
    .logoDiv{
       width: 100px;
       height: 90px;
       margin: 0 auto;
    }
    
    .logo{
      width: 100%;
      height: 100%;
    }  
    
    .remind{
      font-size: 18px;
      padding: 20px;
    }
    
    .joinUsNow{
      width: 96%;
      margin: 0 auto;
      padding: 10px 0;
      background: #E50313;
      font-size: 13px;
      color: white;
      text-align: center;
      border-radius: 5px;
    }
    
    .details{
       padding-top: 20%;
       padding-left: 70px;
       padding-right: 40px;
    }
    
    .detailLine{
       font-size: 14px;
       color: white;
    }
    
    .toMemberInfoDiv{
       position: fixed;
       bottom: 20px;
       left: 0;
       width: 100%;
       z-index: 1;
    }
    
    .toMemberInfo{
       font-size: 16px;
       color: white;
       text-align: center;
       margin: 0 auto;
    }
    
</style>

</head>
<body>
      <!-- 页面最外层 -->
      <div class='content' id = 'app'>
           <!-- 用户信息 -->
           <div class='userInfo' @click='gotoMemberDetail()'>
                 <!-- 用户头像 -->
                 <div class='userImgDiv'>
                       <img class='userImg' :src='"http://purchaser.ecartoon.com.cn/picture/" + model.image' />
                 </div>
                 
                 <!-- 用户昵称 -->
                 <div class='nick'>{{model.name}}</div>
                 
                 <!-- 右边箭头 -->
                 <div class='more'>&gt;</div>
                 
           </div>
           
           
           <!-- 是会员时 -->
           <div class='memberInfoDiv' v-if='isMember'>
               
                <!-- 会员类型 -->
                <div class='top'>
                		<!-- 会员类型 -->
                     	<div v-if = '!showDetail'>{{model.type_name}}</div>
                     	
                     	<!-- 会员权益 -->
                     	<div v-if = 'showDetail'>会员权益</div>
                </div>
               
               <!-- 会员证信息 -->
               <div class='memberInfo' v-if = '!showDetail'>
                     
                     <div class='left'>
                             <img class='memberImg' :src='"http://purchaser.ecartoon.com.cn/picture/" + model.image'>
                     
		                     <!-- 图片描述 -->
		                     <div class='description' @click='toShowDetails'>查看会员权益</div>
                     </div>
                     
                     <div class='right'>
                     
                             <!-- 姓名 -->
                             <div class='titleFont'>姓名：<span class='valueFont'>{{model.name}}</span></div>
                             
                             <!-- 职务 -->
                             <div class='titleFont'>职务：<span class='valueFont'>{{model.duty}}</span></div>
                             
                             <!-- 编号 -->
                             <div class='titleFont'>编号：<span class='valueFont'>{{model.no}}</span></div>
                             
                             <!-- 有效期 -->
                             <div class='titleFont'>有效期：<span class='valueFont'>{{model.valid_period}}</span></div>
                             
                     </div>
                     
               </div>
               
               <!-- 会员权益信息 -->
               <div class = 'details' v-if = 'showDetail'>
                    
                    <div class='detailLine'>
                    		<span>1.拥护本会章程，自觉缴交会费</span>
                    </div>
                    
                    <div class='detailLine'>
                    		<span>2.享受采购师团体服务的优先权</span>
                    </div>
                    
                    <div class='detailLine'>
                    		<span>3.拥有选举权、被选举权、表决权、建议权和监督权</span>
                    </div>
                    
                    <div class='detailLine'>
                    		<span>4.优先参加本会组织的各类活动、参访、培训、沙龙讲座</span>
                    </div>
                    
                    <div class='detailLine'>
                    		<span>5.拥有查找/发布招聘求职、名优产品、采购需求、供应商库、采购师库等信息</span>
                    </div>
                    
                    <div class='detailLine'>
                    		<span>6.入会自愿，退会自由 </span>
                    </div>
                    
               </div>
               
               <!-- 返回按钮 -->
               <div class='toMemberInfoDiv' v-if = 'showDetail'>
               		 <div class='toMemberInfo'  @click='toMemberInfo'>&lt;返回</div>
               </div>
               
           
           </div>
           
           
           
           <!-- 不是会员时 -->
           <div class='notMemberDiv' v-if='notMember'>
                  
                  <div class='remindTop'>
		                  <!-- logo -->
		                  <div class='logoDiv'>
		                         <img class='logo' src='purchaser/img/logo.png'>
		                  </div>
		                  
		                  <!-- 提示语 -->
		                  <div class='remind'>您目前不是厦门采购师协会会员欢迎加入本协会！</div>
		           
                  </div>
                  
                  <!-- 马上加入 -->
                  <div class='joinUsNow' @click='gotoJoinApply()' >马上加入</div>
           </div>
           
           
           
      </div>

</body>
  <!-- js开始 -->
  <script type="text/javascript">
      // jq载入函数
      $(function (){
    	  init();
      })
      
      function init () {
    	  if ("${user}" == "") {
				location.href = "user/checkLogin.pur?redirectURL=" + encodeURI("purchaser/member_center.jsp");
    	  }
    	  
    	  var bodyHeight = $("body").innerHeight();
    	  var userHeight = $(".userInfo").outerHeight(true);
    	  var memberHeight = bodyHeight - userHeight;
    	  $(".memberInfoDiv").css({"height": memberHeight + 'px'});
    	  $(".notMemberDiv").css({"height": memberHeight + 'px'});
    	  
    	  // 请求后台数据
		  var url = 'member/memberSimple.pur';
		  param = {};
		  requestServer(url, param, function (res) {
			  if (res.success) {
				  // 数据请求成功
				  member.isMember = res.isMember;
				  member.model = res.memberInfo;
				  
				  if (res.isMember) {
					  member.notMember = false;
				  } else {
					  member.notMember = true;
				  }
				  
			  } else {
				  // 程序异常，数据请求失败
				  console.log('程序异常，原因: ' + res.message);
			  }
		  });
		  
		  
		  
      }
      
      //全局vue变量
      var member = new Vue({
    	  el:'#app',
    	  data: {
    		  model:{
    			  image:'20180712173921373090.jpg'
    		  },
    		  isMember: true,
    		  notMember: true,
    		  showDetail: false,
    		  time: 0
    	  },
    	  // 自定义方法
    	  methods:{
    		  // 跳转到会员详情页
    		  gotoMemberDetail: function () {
    			  var isMember = member.isMember;
    			  if (!isMember) {
    				  alert('请先成为会员！');
    				  return;
    			  }
    			  // 会员，跳转到会员详情页
    			  location.href = 'purchaser/join_apply.jsp?update=1';
    		  },
    		  
    		  // 跳转到入会申请页面
    		  gotoJoinApply: function () {
    			  location.href='member/joinApply.pur';
    		  },
    		  
    		  // 用户点击查看会员权益
    		  toShowDetails: function () {
    			  var cuTime = new Date().getTime();
    			  if (cuTime - this.time < 100) {
    				  return;
    			  }
    			  
    			  this.showDetail = true;
    			  this.time = new Date().getTime();
    		  },
    		  
    		  // 用户点击返回会员证信息
    		  toMemberInfo: function () {
    			  var cuTime = new Date().getTime();
    			  if (cuTime - this.time < 100) {
    				  return;
    			  }
    			  
    			  this.showDetail = false;
    			  this.time = new Date().getTime();
    		  }
    		  
    		  
    	  }
      
      })
  
  
  </script>

</html>