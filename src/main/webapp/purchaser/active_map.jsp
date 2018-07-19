<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!doctype html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>活动地点</title>
<script type="text/javascript" src="purchaser/js/vue.min.js"></script>
<script type="text/javascript" src="purchaser/js/jquery.min.js"></script>
<script type="text/javascript" src="purchaser/js/commonUtils.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=22GR1VoZdYMI3SLs8eGRI2HmhvhC1BQQ"></script>
</head>
<body>
	<div class="demo_main" style="width: 100%; height: auto;">
		<div style="min-height: 700px; width: 100%;" id="map"></div>
		<script type="text/javascript">
			var markerArr = [
			/*  { title: "门店一", point: "114.264531,30.157003"},  
			 { title: "门店二", point: "114.330934,30.113401"},  
			 { title: "门店三", point: "114.312213,30.147267"},  
			 { title: "门店四", point: "114.372867,30.134274"} */
			];

			// 百度地图API功能
			// 创建Map实例
			// 初始化地图,设置中心点坐标和地图级别
			// 循环生成新的地图点  
			// 按照地图点坐标生成标记  
			// 创建信息窗口对象
			var active = JSON.parse(sessionStorage.getItem("active"));
			var map = new BMap.Map("map");
			map.centerAndZoom(
					new BMap.Point(active.longitude, active.latitude), 14);
			var point = [], marker = [], info = [];
			point[0] = new BMap.Point(active.longitude,
					active.latitude);
			marker[0] = new BMap.Marker(point[0]);
			map.addOverlay(marker[0]);
			info[0] = new window.BMap.InfoWindow(
					"<p style=’font-size:12px;lineheight:1.8em;’>"
							+ active.address + "</p>");
			marker[0].addEventListener("click", function() {
				this.openInfoWindow(info[0]);
			});
		</script>
	</div>
</body>
</html>