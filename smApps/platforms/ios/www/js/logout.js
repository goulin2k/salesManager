function logout() {		
	$.mobile.loading( "show", {
            text: 'loading',
            textVisible: false,
            theme: $.mobile.loader.prototype.options.theme,
            textonly: false,
            html: ''
    		});
	if(port != 80) {					
		strUrl = "http://" + server +":" + port + "/salesManager/appUserAction!logout";
	}else {
		strUrl = "http://" + server + "/salesManager/appUserAction!logout";
	}
	console.log(strUrl);
	$.ajax({  
		type: "POST",  
		url: strUrl,  
		cache: false,  
		data: 'pageSize=' + pagesize + '&pages=' + pages,  
		dataType:"jsonp",
		jsonpCallback:"callback",
		timeout: 20000,
		success: onLogoutSuccess,  
		error: onLogoutError
	});
}

function onLogoutSuccess(data, status) {
	$.mobile.loading( "hide" );
	window.localStorage.setItem('setting_user_name', '');
	window.localStorage.setItem('setting_dept_name', '');
	window.localStorage.setItem('setting_user_post', '');
	window.localStorage.setItem('setting_user_mobile', '');
	window.location.href="../index.html";
}

function onLogoutError(data, status) {
	$.mobile.loading( "hide" );
	navigator.notification.alert('用户登出错误，请联系系统管理检查服务设置！',callback,'系统错误','确定');
	console.log('login error: ' + status);
}