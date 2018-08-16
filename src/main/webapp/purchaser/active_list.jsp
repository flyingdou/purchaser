<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"+ request.getServerPort() + path + "/";
%>
<!doctype html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>活动列表</title>
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

	.nav{
		display: flex;
		justify-content: space-around;
		align-items: cneter;
		height: 40px;
		background-color: #F0F0F2;
	}
	
	.nav-text{
		line-height: 40px;
		font-size: 12px;
	}
	
	.nav-text-acitve{
		color: #FF0000;
	}
	
	.active-list :last-child{
		border-bottom: 0;
	}
	
	.active-item{
		padding: 10px;
		overflow: hidden;
		border-bottom: 1px solid #F0F0F2;
	}
	
	.active-image{
		float: left;
		margint-left: 10px;
		width: 84px;
		height: 64px;
		overflow: hidden;
	}
	
	.active-image > img{
		width: 100%;
		height: auto;
	}
	
	.active-content{
		float: left;
		margin-left: 10px;
	}
	
	.active-name{
		margin-top: 2px;
		font-size: 13px;
		color: #101010;
	}
	
	.active-start-time{
		margin-top: 5px;
		font-size: 13px;
		color: #BBB;
	}
	
	.active-address{
		margin-top: 5px;
		font-size: 13px;
		color: #BBB;
	}
	
	.active-status{
		float: right;
		height: 64px;
		line-height: 64px;
		font-size: 13px;
		color: #FF0000;
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
		<div class="nav">
			<div :class="item.class" v-for="(item, i) in nav_items" @click="changeCurrent(i)">
				{{item.text}}
			</div>
		</div>
		<div class="active-list" v-if="currentIndex == 0">
			<div class="no-data" v-if="activeList1.length == 0">
				<div class="no-data-image"><img src="purchaser/img/no_data.png"></div>
				<div class="no-data-text">暂无数据</div>
			</div>
			<div class="active-item" v-for="(item, i) in activeList1" @click="goAcitveDetail(i)">
				<div class="active-image">
					<img :src="'http://purchaser.ecartoon.com.cn/picture/' + item.image"/>
				</div>
				<div class="active-content">
					<div class="active-name">{{item.name}}</div>
					<div class="active-start-time">开始时间 : {{item.startDate}}</div>
					<div class="active-address">地点 : {{item.address}}</div>
				</div>
				<div class="active-status">
					{{item.activeStatus}}
				</div>
			</div>
		</div>
		<div class="active-list" v-if="currentIndex == 1">
			<div class="no-data" v-if="activeList2.length == 0">
				<div class="no-data-image"><img src="purchaser/img/no_data.png"></div>
				<div class="no-data-text">暂无数据</div>
			</div>
			<div class="active-item" v-for="(item, i) in activeList2" @click="goAcitveDetail(i)">
				<div class="active-image">
					<img :src="'http://purchaser.ecartoon.com.cn/picture/' + item.image"/>
				</div>
				<div class="active-content">
					<div class="active-name">{{item.name}}</div>
					<div class="active-start-time">开始时间 : {{item.startDate}}</div>
					<div class="active-address">地点 : {{item.address}}</div>
				</div>
				<div class="active-status">
					{{item.activeStatus}}
				</div>
			</div>
		</div>
		<div class="active-list" v-if="currentIndex == 2">
			<div class="no-data" v-if="activeList3.length == 0">
				<div class="no-data-image"><img src="purchaser/img/no_data.png"></div>
				<div class="no-data-text">暂无数据</div>
			</div>
			<div class="active-item" v-for="(item, i) in activeList3" @click="goAcitveDetail(i)">
				<div class="active-image">
					<img :src="'http://purchaser.ecartoon.com.cn/picture/' + item.image"/>
				</div>
				<div class="active-content">
					<div class="active-name">{{item.name}}</div>
					<div class="active-start-time">开始时间 : {{item.startDate}}</div>
					<div class="active-address">地点 : {{item.address}}</div>
				</div>
				<div class="active-status">
					{{item.activeStatus}}
				</div>
			</div>
		</div>
	</div>
<script src="purchaser/js/vue.min.js"></script>
<script src="purchaser/js/jquery.min.js"></script>
<script src="purchaser/js/commonUtils.js"></script>
<script>
	var vue = new Vue({
		el: "#wraper",
		data: {
			currentIndex: 0,
			nav_items: [],
			activeList1: [],
			activeList2: [],
			activeList3: []
		},
		created: function () {
			// 调用初始化方法
			this.init();
		},
		methods: {
			// 初始化
			init: function () {
				if ("${user}" == "") {
					location.href = "user/checkLogin.pur?redirectURL=" + encodeURI("purchaser/active_list.jsp");
				}
				
				// 初始化顶部栏选项
				var nav_text_list = ["联谊活动","培训沙龙","参观访问"];
				nav_text_list.forEach(item => this.nav_items.push({text : item, "class" : "nav-text"}));
				this.nav_items[this.currentIndex]["class"] = "nav-text nav-text-acitve";
				
				// 调用服务端接口
				this.requestServer(0);
				this.requestServer(1);
				this.requestServer(2);
				
				// 初始化图片
				setTimeout(function () {
					clipImgs("img");
				}, 100);
			},
			
			// 请求服务端数据
			requestServer: function (type) {
				$.ajax({
					url: "active/getActiveList.pur",
					type: "post",
					data: {
						json: encodeURI(JSON.stringify({type: type}))
					},
					dataType: "json",
					success: function(res){
						Vue.set(vue, ('activeList' + (type + 1)), res.map(vue.checkActiveStatus));
					},
					error: function (e) {
						console.log(e);
					}
				});
			},
			
			// 检查活动状态
			checkActiveStatus: function (active) {
				var currentTime = new Date().getTime();
				var startDataTime = new Date(active.startDate).getTime();
				var endDataTime = new Date(active.endDate).getTime();
				if (currentTime < startDataTime) {
					active.activeStatus = "未开始";
				} else if (currentTime > startDataTime && currentTime < endDataTime) {
					active.activeStatus = "进行中";
				} else {
					active.activeStatus = "已结束";
				}
				return active;
			},
			
			// 改变当前选项卡索引
			changeCurrent: function (index) {
				this.currentIndex = index;
				this.nav_items.forEach((item, i) => i == index ? this.nav_items[i]["class"] = "nav-text nav-text-acitve" : 
					this.nav_items[i]["class"] = "nav-text");
				// 初始化图片列表
				clipImgs("img");
			},
			
			// 去详情
			goAcitveDetail: function (index) {
				var activeId = this['activeList' + (this.currentIndex + 1)][index].id;
				sessionStorage.setItem("activeId", activeId);
				location.href = "purchaser/active_detail.jsp";
			}
		}
	});
</script>
</body>
</html>