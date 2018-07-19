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
<title>报名人员</title>
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

	.active-users{
		padding: 10px;
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
</style>
</head>
<body>
	<div class="active-users" id="wraper">
		<div class="active-users-title">
			<div class="active-users-title-content">
				已报名 <span style="color:#FF0000;">{{active.joinCount}}</span> 人 / 限额 
				<span style="color:#FF0000;">{{active.upperLimit}}</span> 人
			</div>
			<div class="active-user-title-icon">
				<img src="https://www.ecartoon.com.cn/picture/201806151423.png">
			</div>
		</div>
		<div class="active-users-wraper">
			<div class="active-user-list">
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
	<script>
		var vue = new Vue({
			el: "#wraper",
			data: {
				active: {}
			},
			created: function () {
				var active = JSON.parse(sessionStorage.getItem("active"));
				this.active = active;
			}
		});
	</script>
</body>
</html>