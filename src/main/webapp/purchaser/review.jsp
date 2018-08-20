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
<title>精彩回顾</title>
<script type="text/javascript" src="purchaser/js/vue.min.js"></script>
<script type="text/javascript" src="purchaser/js/jquery.min.js"></script>
<script type="text/javascript" src="purchaser/js/commonUtils.js"></script>
<style>
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
	
	.content-list :last-child{
		border: 0;
	}
	
	.content-item{
		padding: 10px;
		overflow: hidden;
		border-bottom: 1px solid #F0F0F2;
	}
	
	.content-image{
		float: left;
		width: 86px;
		height: 64px;
		overflow: hidden;
	}
	
	.content-body{
		float: left;
		margin-left: 10px;
		width: 70%;
	}
	
	.content-name{
		margin-top: 5px;
		font-size: 13px;
		color: #101010;
	}
	
	.content-remark{
		margin-top: 10px;
		font-size: 12px;
		color: #BBB;
	}
	
	.no-data{
		margin-top: 15%;
	}
	
	.no-data-image{
		margin: 0 auto;
		width: 69px;
		height: 49px;
		overflow: hidden;
	}
	
	.no-data-text{
		margin-top: 5px;
		text-align: center;
		font-size: 12px;
		color: #BBB;
	}
</style>
</head>
<body>
	<div id="wraper">
		<div class="content-list">
			<div class="no-data" v-if="contentList.length == 0">
				<div class="no-data-image"><img src="purchaser/img/no_data.png"></div>
				<div class="no-data-text">暂无数据</div>
			</div>
			<div class="content-item" v-for="(item,i) in contentList" @click="toDetail(i)">
				<div class="content-image">
					<img :src="'http://purchaser.ecartoon.com.cn/picture/' + item.image">
				</div>
				<div class="content-body">
					<div class="content-name">
						{{item.name}}
					</div>
					<div class="content-remark">
						{{ item.remark | overflow_hidden}}
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		var vue = new Vue({
			el: "#wraper",
			data: {
				contentList: []
			},
			created: function () {
				// 页面初始化
				this.init();
			},
			filters: {
				overflow_hidden: function (text) {
					if(text && text.length > 40){
						return text.substring(0, 40) + "...";
					}
					return text;
				}
			},
			methods: {
				// 请求服务端
				requestServer: function (url, param, callback) {
					$.ajax({
						url: url,
						type: "post",
						data: {
							json: encodeURI(JSON.stringify(param))
						},
						dataType: "json",
						success: callback,
						error: e => console.log(e)
					});
				},
				
				// 初始化
				init: function () {
					if ("${user}" == "") {
						location.href = "user/checkLogin.pur?redirectURL=" + encodeURI("purchaser/review.jsp");
					}
					
					var param = {contentType : "review"}
					var callback = res => {
						vue.contentList = res.contentList;
						// 初始化图片
						clipImgs("img");
					}
					// 请求服务端数据
					this.requestServer("content/getContentList.pur", param, callback);
				},
				
				// 去详情
				toDetail: function (i) {
					location.href = this.contentList[i].contentUrl; 
				}
			}
		});
	</script>
</body>
</html>