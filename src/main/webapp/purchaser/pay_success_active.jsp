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
<title>报名成功</title>
<script type="text/javascript" src="purchaser/js/vue.min.js"></script>
<script type="text/javascript" src="purchaser/js/jquery.min.js"></script>
<script type="text/javascript" src="purchaser/js/commonUtils.js"></script>
<style type="text/css">
	html,body{
		margin: 0;
		padding: 0;
		width: 100%;
		height: 100%;
		background-color: #FFF;
	}
	
	img{
		width: 100%;
		height: auto;
	}
	
	.wraper{
		position: relative;
		height: 100%;
	}
	
	.text-block{
		position: absolute;
		top: 30%;
		left: 0;
		width: 100%;
	}
	
	.text{
		margin: 0 auto;
		width: 56%;
		font-size: 16px;
	}
</style>
</head>
<body>
	<div id="wraper">
		<img src="purchaser/img/member_card.png">
		<div class="text-block">
			<div class="text">
				您已经成功报名“ {{active.name}} ”
				<div style="height: 5px;"></div>
				请按时出席本活动 ，
				<div style="height: 5px;"></div>
				谢谢您的参与！
			</div>
			<div style="padding-top: 30px; font-size: 12px; color: #BBB; text-align: center;" @click="getActive()">
				查看活动详情
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var vue = new Vue({
			el: "#wraper",
			data: {
				active: {}
			},
			created: function () {
				var active = JSON.parse(sessionStorage.getItem("active"));
				Vue.set(this, "active", active);
				clipImgs("img");
			},
			methods: {
				// 去活动详情页面
				getActive: function () {
					sessionStorage.setItem("activeId", this.active.id);
					location.href = "purchaser/active_detail.jsp";
				}
			}
		});
	</script>
</body>
</html>