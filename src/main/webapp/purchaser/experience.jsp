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
<title>工作履历</title>

<link rel="stylesheet" href="purchaser/css/iosSelect.css">
<script type="text/javascript" src="purchaser/js/vue.min.js"></script>
<script type="text/javascript" src="purchaser/js/jquery.min.js"></script>
<script type="text/javascript" src="purchaser/js/commonUtils.js"></script>
<script type="text/javascript" src="purchaser/js/areaData_v2.js" ></script>
<script type="text/javascript" src="purchaser/js/iosSelect.js"></script>

<style type="text/css">
    html,body{
      margin: 0;
      padding: 0;
      width: 100%;
      height: 100%;
    }
    
    input,textarea{
      border: none;
      outline: none;
    }
    
    input::-webkit-input-placeholder, textarea::-webkit-input-placeholder {
　　   color:#939393;
      font-size: 13px;
　　 }

    .content{
      position: relative;
      width: 100%;
      height: 100%;
      background: white;
    }
    
    .myExprience{
      width: 100%;
    }
    
    .bigTitle{
      font-size: 14px;
      padding: 10px 0;
      padding-left: 5%;
      border-bottom: 1px solid #f0f2f2;
    }
    
    .lineAreaA{
       width: 86%;
       margin: 0 auto;
    }
    
    .lineA{
       display: flex;
       justify-content: space-between;
       align-items: center;
       font-size: 13px;
       padding: 10px 0;
       border-bottom: 1px solid #f0f2f2;
    }
    
    .lineA::last-child {
	   border: none;
    }
    
    
    .fillx{
      width: 100%;
      height: 20px;
      background: #f0f2f2;
    }
    
    .addExperience{
      width: 100%;
    }
    
    
    .lineAreaB{
       width: 86%;
       margin: 0 auto;
    }
    
    .lineB{
       display: flex;
       justify-content: space-between;
       align-items: center;
       font-size: 13px;
       padding: 10px 0;
       border-bottom: 1px solid #f0f2f2;
    }
    
    .inputValue{
       text-align: right;
    }
    
    .lineBValue{
      color: #939393;
    }
    
    
    .doux{
      border: none;
    }
    
    
    .description{
      margin-top: 10px;
      margin-left: 5px;
    }
    
    .save{
      position: fixed;
      bottom: 10px;
      width: 96%;
      left: 2%;
      font-size: 13px;
      padding: 12px 0;
      text-align: center;
      color: white;
      background: #E50313;
      border-radius: 5px;
    }
   
   
    
</style>
</head>
<body>

      <!-- 页面最外层 -->
      <div class='content' id = 'app'>
      
             <!-- 我的履历 -->
             <div class='myExprience'>
                   <!-- 大标题 -->
                   <div class='bigTitle'>我的履历</div>
                   
                   <!-- 下面的内容行 -->
                   <div class='lineAreaA' >
                   
                        <div class='lineA' v-for='experiencex in model.experienceList' >
                             <div class='lineATitle'>{{experiencex.startTime}}至{{experiencex.endTime}}</div>
                             <div class='lineAValue'>{{experiencex.company_name}}</div>
                        </div>
                        
                   </div>
             </div>
             
             <!-- 填充高度 -->
             <div class='fillx'></div>
             
             <!-- 添加履历 -->
             <div class='addExperience'>
                   <!-- 大标题 -->
                   <div class='bigTitle'>添加履历</div>
                   
                   <!-- 添加履历区域 -->
                   <div class='lineAreaB'>
                         <!-- 开始时间 -->
                         <div class="lineB">
                               <div class='lineBTitle'>开始时间</div>
                               <div class='lineBValue' @click='selectDate(0)' >
                                     <span>{{model.startTime}}</span><span>&nbsp;&nbsp;&gt;</span>
                               </div>
                         </div>
                         
                         <!-- 结束时间 -->
                         <div class="lineB">
                               <div class='lineBTitle'>结束时间 </div>
                               <div class='lineBValue' @click='selectDate(1)' >
                                     <span>{{model.endTime}}</span><span>&nbsp;&nbsp;&gt;</span>
                               </div>
                         </div>
                         
                         <!-- 所属公司 -->
                         <div class="lineB">
                               <div class='lineBTitle'>所属公司</div>
                               <input class='inputValue' v-model='model.company_name' type="text" placeholder="请输入公司名称" />
                         </div>
                         
                         
                         <!-- 在职岗位 -->
                         <div class="lineB">
                               <div class='lineBTitle'>在职岗位</div>
                               <input class='inputValue' v-model='model.post' type="text" placeholder="请输入岗位" />
                         </div>
                         
                         <!-- 工作简介 -->
                         <div class="lineB doux">
                               <div class='lineBTitle'>工作简介</div>
                         </div>
                         
	                     <!-- 工作简介输入框 -->
	                     <textarea class='description' v-model='model.post_detail' rows="10" cols="44" placeholder="请输入工作内容"></textarea>
                         
                   </div>
                   
             </div>
             
             <!-- 底部保存按钮 -->
             <div class='save'>保存</div>
             
      </div>
      
</body>

<script type="text/javascript">
// 全局vue变量
var experience = new Vue({
    el:'#app',
    data:{
    	model:{
    		startTime:"请选择",
    		endTime:"请选择"
    	}
    },
    
    // vue初始化方法
    create: function () {
    	// 初始化当前页面数据
    	var url = 'experience/getExperiences.pur';
    	var param = {};
    	requestServer(url, param, function (res) {
    		if (res.success) {
    			// 数据请求成功
    			experience.model.experienceList = res.experienceList;
    			
    			// 将工作履历存储起来
    			sessionStorage.setItem("experienceList", res.experienceList);
    		} else {
    			console.log('程序异常，原因: ' + res.message);
    		}
    	})
    },
    // 自定义方法
    methods: {
    	// 选择时间
    	selectDate: function (flag) {
    		createDateSelector(function (year,month,day) {
    			// 处理month
    			if (month < 10 ) {
    				month = "0" + month;
    			}
    			
    			if (flag == 0) {
    				experience.model.startTime = year + '-' + month;
    			} else {
    				experience.model.endTime = year + '-' + month;
    			}
    		}, 2)
    		
    	},
    	
    	// 添加工作履历
    	addExperience: function () {
    		var exper = experience.model;
    		
    		// 校验数据
    		// startTime
    	    if (exper.startTime.indexOf("请") > 0) {
    	    	alert("请先选择开始时间！");
    	    	return;
    	    }
    		
    		// endTime
    		if (exper.endTime.indexOf("请") > 0) {
    			alert("请先选择结束时间！");
    			return;
    		} 
    		
    		// 结算时间不能小于开始时间
    		var startTS = exper.startTime.split("-");
    		var startDate = new Date(startTS[0],startTS[1]);
    		var startTime = startDate.getTime();
    		
    		var endTS = exper.endTime.split("-");
    		var endDate = new Date(endTS[0],endTS[1]);
    		var endTime = endDate.getTime();
    		if (endTime > startTime) {
    			alert("开始时间不能大于结束时间！");
    			return;
    		}
    		
    		// company_name
    		if (!exper.company_name || exper.company_name == '') {
    			alert("请先填写公司名称！");
    			return;
    		}
    		
    		// post
    		if (!exper.post || exper.post == '') {
    			alert("请先填写在职岗位！");
    			return;
    		}
    		
    		// post_detail
    		if (!exper.post_detail || exper.post_detail == '') {
    			alert("请先填写工作简介！");
    			return;
    		}
    		
    		// 数据校验 通过，将数据保存到数据库
    		var url = 'experience/saveExperience.pur';
    		var param = {
    				json: encodeURI(JSON.stringify(exper))
    		};
    		requestServer(url, param, function(res) {
    			if (res.success) {
    				// 数据请求成功，刷新当前页面
    				window.location.reload();
    			} else {
    				console.log('程序异常，原因: ' + res.message);
    			}
    			
    		})
    		
    		
    		
    		
    	}
    	
    }
	   
})

</script>

</html>