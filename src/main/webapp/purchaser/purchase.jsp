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
<title>采购需求</title>
<link rel="stylesheet" href="purchaser/css/swiper.min.css" />
<script type="text/javascript" src="purchaser/js/vue.min.js"></script>
<script type="text/javascript" src="purchaser/js/jquery.min.js"></script>
<script type="text/javascript" src="purchaser/js/commonUtils.js"></script>
<script type="text/javascript" src="purchaser/js/swiper.min.js"></script>
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
	
	.swiper-slide{
		height: 188px;
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
		margin-top: 2px;
		font-size: 13px;
		color: #101010;
	}
	
	.content-remark{
		margin-top: 5px;
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
		<!-- Swiper -->
	    <div class="swiper-container">
	        <div class="swiper-wrapper">
	            <div class="swiper-slide" v-for="(item,i) in topList" v-if="topList.length != 0">
	            	<a :href="item.contentUrl">
	            		<img :src="'http://purchaser.ecartoon.com.cn/picture/' + item.image">
	            	</a>
	            </div>
	        </div>
	        <!-- Add Pagination -->
	        <div class="swiper-pagination"></div>
	    </div>
		<div class="nav">
			<div :class="item.class" v-for="(item, i) in nav_items" @click="changeCurrent(i)">
				{{item.text}}
			</div>
		</div>
		<div class="content-list" v-if="currentIndex == 0">
			<div class="no-data" v-if="contentList1.length == 0">
				<div class="no-data-image"><img src="purchaser/img/no_data.png"></div>
				<div class="no-data-text">暂无数据</div>
			</div>
			<div class="content-item" v-for="(item,i) in contentList1" @click="toDetail(i)">
				<div class="content-image">
					<img src="https://www.ecartoon.com.cn/picture/wangyan1.jpg">
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
		<div class="content-list" v-if="currentIndex ==1">
			<div class="no-data" v-if="contentList2.length == 0">
				<div class="no-data-image"><img src="purchaser/img/no_data.png"></div>
				<div class="no-data-text">暂无数据</div>
			</div>
			<div class="content-item" v-for="(item,i) in contentList2" @click="toDetail(i)">
				<div class="content-image">
					<img src="https://www.ecartoon.com.cn/picture/wangyan1.jpg">
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
		<div class="content-list" v-if="currentIndex == 2">
			<div class="no-data" v-if="contentList3.length == 0">
				<div class="no-data-image"><img src="purchaser/img/no_data.png"></div>
				<div class="no-data-text">暂无数据</div>
			</div>
			<div class="content-item" v-for="(item,i) in contentList3" @click="toDetail(i)">
				<div class="content-image">
					<img src="https://www.ecartoon.com.cn/picture/wangyan1.jpg">
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
	<script type="text/javascript">
		var vue = new Vue({
			el: "#wraper",
			data: {
				currentIndex: 0,
				topList: [],
				nav_items: [],
				contentList1: [],
				contentList2: [],
				contentList3: []
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
						location.href = "user/checkLogin.pur?redirectURL=" + encodeURI("purchase");
					}
					
					// 初始化顶部栏选项
					var nav_text_list = ["原辅材料","机械电子","其他"];
					nav_text_list.forEach(item => this.nav_items.push({text : item, "class" : "nav-text"}));
					this.nav_items[this.currentIndex]["class"] = "nav-text nav-text-acitve";
					
					// 获取置顶内容列表
					this.getContentListBySetTop();
					
					// 获取数据列表
					this.getContentList();
				},
				
				// 改变当前选项卡索引
				changeCurrent: function (index) {
					// 切换选项卡
					this.currentIndex = index;
					this.nav_items.forEach((item, i) => i == index ? this.nav_items[i]["class"] = "nav-text nav-text-acitve" : 
						this.nav_items[i]["class"] = "nav-text");
					
					// 获取数据列表
					this.getContentList();
				},
				
				// 获取置顶内容列表
				getContentListBySetTop: function () {
					// 请求服务端数据
					var param = {contentType : "purchase", setTop : 1}
					var callback = res =>{
						vue.topList = res.contentList;

						// 添加轮播图
						setTimeout(() => {
							var swiper = new Swiper('.swiper-container', {
						        pagination: '.swiper-pagination',
						        paginationClickable: true
						    });
						}, 100);
						
						// 初始化图片列表
						clipImgs("img");
					}
					this.requestServer("content/getContentList.pur", param, callback);
				},
				
				// 获取内容数据列表
				getContentList: function () {
					// 请求服务端数据
					var param = {contentType : "purchase", classification : this.currentIndex, setTop : 0}
					var callback = res => {
						vue["contentList" + (vue.currentIndex + 1)] = res.contentList;
						
						// 初始化图片列表
						clipImgs("img");
					}
					this.requestServer("content/getContentList.pur", param, callback);
				},
				
				// 去详情
				toDetail: function (i) {
					location.href = this["contentList" + (vue.currentIndex + 1)][i].contentUrl; 
				}
			}
		});
	</script>
</body>
</html>