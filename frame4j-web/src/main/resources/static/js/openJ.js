// 获取URL地址参数
function getQueryString(name, url) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	if (!url || url == "") {
		url = window.location.search;
	} else {
		url = url.substring(url.indexOf("?"));
	}
	r = url.substr(1).match(reg)
	if (r != null)
		return unescape(r[2]);
	return null;
}
