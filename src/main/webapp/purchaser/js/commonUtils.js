/**
 * 图片裁剪显示
 * 
 * @param selector
 * @returns
 */
function clipImgs(selector) {
	setTimeout(function() {
		$(selector).each(function() {
			var defaultHeight = $(this).parent().height();
			var imgHeight = $(this).height();
			if (imgHeight > defaultHeight) {
				var deviation = 0 - ((imgHeight - defaultHeight) * 0.5);
				$(this).css({
					"margin-top" : deviation + "px"
				});
			}
		});
	}, 10);
}

/**
 * 请求服务端
 * 
 * @param url
 * @param param
 * @param callback
 * @returns
 */
function requestServer(url, param, success, error) {
	$.ajax({
		url : url,
		type : "post",
		data : {
			json : encodeURI(JSON.stringify(param))
		},
		dataType : "json",
		success : success,
		error : error
	});
}

/**
 * 判断登录
 * 
 * @param e
 * @returns
 */
(function(e) {
	if (location.href.indexOf("/admin/") != -1 && location.href.indexOf("/admin/login.html") == -1) {
		var url = location.origin + "/admin/toPage.pur?url=user_list.html";
		var param = {}
		requestServer(url, param, null, function(e) {
			if (e.responseText.indexOf("/admin/login.pur") != -1) {
				location.href = location.origin + "/admin/login.html";
			}
		})
	}
})();



/**
 * 根据数组下标删除元素
 */
Array.prototype.remove=function(obj){ 
    for(var i =0;i <this.length;i++){ 
    var temp = this[i]; 
    if(!isNaN(obj)){ 
    temp=i; 
    } 
    if(temp == obj){ 
    for(var j = i;j <this.length;j++){ 
    this[j]=this[j+1]; 
    } 
    this.length = this.length-1; 
    } 
    } 
    } 
