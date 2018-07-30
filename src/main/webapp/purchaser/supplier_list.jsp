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
<title>供应商列表</title>
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
	
	img{
		width: 100%;
		height: 100%;
	}
	
	input{
		outline: none;
	}
	
	/*搜索框*/
	.search-box{
		padding: 10px;
		overflow: hidden;
		background-color: #ccc;
	}
	
	.search{
		float: left;
		width: 86%;
		text-align: center;
		position: relative;
	}
	
	.search > input{
		width: 100%;
		height: 30px;
		padding-left: 24px;
		border-radius: 5px;
		border: 0;
	}
	
	.icon-search{
		position: absolute;
		top: 25%;
		left: 5px;
		width: 16px;
		height: 16px;
	}
	
	.search-button{
		float: left;
		width: 14%;
		height: 30px;
		text-align: center;
		line-height: 30px;
		font-size: 14px;
	}
	
	/* nav */
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
	
	/* content */
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
	
	/* 添加供应商按钮 */
	.supplier-release-button{
		position: fixed;
		bottom: 20px;
		right: 10px;
		width: 40px;
		height: 40px;
	}
	
	.supplier-release-button > img{
		border-radius: 50%;
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
		<div class="search-box">
			<div class="search">
				<img src="purchaser/img/search.png" class="icon-search">
				<input type="search" placeholder="搜索赠送对象" v-model="model.name" />
			</div>
			<div class="search-button" @click="search()">
				确定
			</div>
		</div>
		<div class="supplier-box">
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
						<img :src="'http://purchaser.ecartoon.com.cn/picture/' + item.image">
					</div>
					<div class="content-body">
						<div class="content-name">
							{{item.name}}
						</div>
						<div class="content-remark">
							{{ item.address | overflow_hidden}}
						</div>
						<div class="content-remark">
							{{ item.remark | overflow_hidden}}
						</div>
					</div>
				</div>
			</div>
			<div class="content-list" v-if="currentIndex == 1">
				<div class="no-data" v-if="contentList2.length == 0">
					<div class="no-data-image"><img src="purchaser/img/no_data.png"></div>
					<div class="no-data-text">暂无数据</div>
				</div>
				<div class="content-item" v-for="(item,i) in contentList2" @click="toDetail(i)">
					<div class="content-image">
						<img :src="'http://purchaser.ecartoon.com.cn/picture/' + item.image">
					</div>
					<div class="content-body">
						<div class="content-name">
							{{item.name}}
						</div>
						<div class="content-remark">
							{{ item.address | overflow_hidden}}
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
						<img :src="'http://purchaser.ecartoon.com.cn/picture/' + item.image">
					</div>
					<div class="content-body">
						<div class="content-name">
							{{item.name}}
						</div>
						<div class="content-remark">
							{{ item.address | overflow_hidden}}
						</div>
						<div class="content-remark">
							{{ item.remark | overflow_hidden}}
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="supplier-release-button" @click="toRelease">
			<img src="http://purchaser.ecartoon.com.cn/picture/201807120937.png">
		</div>
	</div>
	<script>
		var vue = new Vue({
			el: "#wraper",
			data: {
				currentIndex: 0,
				nav_items: [],
				contentList1: [],
				contentList2: [],
				contentList3: [],
				model: {}
			},
			
			// 页面创建完成回调函数(vue生命周期函数)
			created: function () {
				// 调用页面初始化函数
				this.init();
			},
			
			// 自定义函数
			methods: {
				// 请求服务端函数
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
				
				// 页面初始化
				init: function () {
					if ("${user}" == "") {
						location.href = "user/checkLogin.pur?redirectURL=" + encodeURI("supplier_list");
					}
					
					// 初始化顶部栏选项
					var nav_text_list = ["原辅材料","机械电子","其他"];
					nav_text_list.forEach(item => this.nav_items.push({text : item, "class" : "nav-text"}));
					this.nav_items[this.currentIndex]["class"] = "nav-text nav-text-acitve";
					
					// 获取数据列表
					this.getSupplierList();
				},
				
				// 改变当前选项卡索引
				changeCurrent: function (index) {
					// 切换选项卡
					this.currentIndex = index;
					this.nav_items.forEach((item, i) => i == index ? this.nav_items[i]["class"] = "nav-text nav-text-acitve" : 
						this.nav_items[i]["class"] = "nav-text");
					
					// 获取数据列表
					this.getSupplierList();
				},
				
				// 根据行业类别查询供应商列表
				getSupplierList: function () {
					// 创建参数对象
					var url = "supplier/getSupplierList.pur";
					var param = {
						business: this.currentIndex
					}
					// 请求服务端数据
					this.requestServer(url, param, function (res) {
						vue["contentList" + (vue.currentIndex + 1)] = res;
						
						// 处理图片
						clipImgs("img");
					});
				},
				
				// 根据名称查询供应商列表
				search: function () {
					// 创建参数对象
					var url = "supplier/getSupplierList.pur";
					var param = {
						name: this.model.name
					}
					// 请求服务端数据
					this.requestServer(url, param, function (res) {
						vue["contentList" + (vue.currentIndex + 1)] = res;
						
						// 处理图片
						clipImgs("img");
					});
				},
				
				// 到录入供应商页面
				toRelease: function () {
					location.href = "purchaser/supplier_release.jsp";
				},
				
				// 到供应商详情页面
				toDetail: function (index) {
					var supplierId = this["contentList" + (this.currentIndex + 1)][index].id;
					sessionStorage.setItem("supplierId", supplierId);
					location.href = "purchaser/supplier_detail.jsp";
				}
			}
		});
	</script>
</body>
</html>