/**
 * 图片裁剪显示
 */
function clipImgs (selector) {
	setTimeout(function(){
		$(selector).each(function(){
			var defaultHeight = $(this).parent().height();
			var imgHeight = $(this).height();
			if(imgHeight > defaultHeight){
				var deviation = 0 - ((imgHeight - defaultHeight) * 0.5);
				$(this).css({"margin-top": deviation + "px"});
			}
		});
	}, 10);
}

/**
 * 请求服务端
 */
function requestServer (url, param, callback) {
	$.ajax({
		url: url,
		type: "post",
		data: {
			json: encodeURI(JSON.stringify(param))
		},
		dataType: "json",
		success: callback,
		error: function (e) {
			console.log(e);
		}
	});
}
