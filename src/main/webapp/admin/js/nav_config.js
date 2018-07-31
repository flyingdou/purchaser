// 侧边栏配置
var nav = [
	// 用户管理
	{
		name : "用户管理", 
		link : "javascript:void(0)", 
		sub_nav : [
			{name : "用户列表" , link : "toPage.pur?url=user_list.html"}
		]
	},
	
	// 会员管理 
	{
		name : "会员管理", 
		link : "javascript:void(0)", 
		sub_nav : [
			{name : "会员信息录入" , link : "toPage.pur?url=member_release.html"},
			{name : "会员列表" , link : "toPage.pur?url=member_list.html"},
			{name : "设定会员收费标准" , link : "javascript:void(0)"}
		]
	},
	
	
	// 活动管理 
	{
		name : "活动管理", 
		link : "javascript:void(0)", 
		sub_nav : [
			{name : "发起活动" , link : "toPage.pur?url=active_release.html"},
			{name : "活动列表" , link : "toPage.pur?url=active_list.html"}
		]
	},
	
	// 采购师管理
	{
		name : "采购师管理", 
		link : "javascript:void(0)", 
		sub_nav : [
			{name : "采购师录入审核" , link : "toPage.pur?url=admirer_list.html"}
		]
	},
	
	// 供应商管理
	{
		name : "供应商管理", 
		link : "javascript:void(0)", 
		sub_nav : [
			{name : "供应商录入审核" , link : "toPage.pur?url=supplier_list.html"}
		]
	},
	
	// 内容管理
	{
		name : "内容管理", 
		link : "javascript:void(0)", 
		sub_nav : [
			{name : "发布内容" , link : "toPage.pur?url=content_release.html"},
			{name : "编辑内容列表" , link : "toPage.pur?url=content_list.html"}
		]
	}
]