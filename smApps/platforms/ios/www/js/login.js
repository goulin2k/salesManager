
var app = {
    // Application Constructor
    initialize: function() {
        this.bindEvents();
    },
    // Bind Event Listeners
    //
    // Bind any events that are required on startup. Common events are:
    // 'load', 'deviceready', 'offline', and 'online'.
    bindEvents: function() {
        document.addEventListener('deviceready', this.onDeviceReady, false);
    },
    // deviceready Event Handler
    //
    // The scope of 'this' is the event. In order to call the 'receivedEvent'
    // function, we must explicitly call 'app.receivedEvent(...);'
    onDeviceReady: function() {
        app.receivedEvent('deviceready');
        navigator.geolocation.getCurrentPosition(geolocationSuccess,
                                         [geolocationError],
                                         [geolocationOptions]);
    },
    // Update DOM on a Received Event
    receivedEvent: function(id) {
        //var myDiv = document.getElementById('props');

        //myDiv.innerHTML =   'Device Name: '     + device.name     + '<br />' + 
        //                    'Device PhoneGap: ' + device.phonegap + '<br />' + 
        //                    'Device Platform: ' + device.platform + '<br />' + 
        //                    'Device UUID: '     + device.uuid     + '<br />' + 
        //                    'Device Version: '  + device.version  + '<br />';
        console.log('Received Event: ' + id);
    }
};


/*
 * 远程调用登录认证成功
 */
function onLoginSuccess(data, status)  
{  
	$.mobile.loading( "hide" );
	if(data.result == '0') {		
		$("#notification").text('错误的用户名或密码！');
		navigator.notification.alert('错误的用户名或密码！',callback,'登录提示','确定');
	}else {	
		$("#notification").text('登录成功...');
		window.localStorage.setItem('setting_user_name', data.name);
		window.localStorage.setItem('setting_dept_name', data.department);
		window.localStorage.setItem('setting_user_post', data.roleName);
		window.localStorage.setItem('setting_user_mobile', data.mobile);
		window.location.href="pages/main.html#main";
	} 
}  

/*
 * 远程调用登录认证失败
 */
function onLoginError(data, status)  
{  
	$.mobile.loading( "hide" );
	console.log('login error: ' + status); 
	navigator.notification.alert('请检查登录服务信息是否正确！',callback,'系统错误','确定');
	 
}       



function callback() {
	console.log('call back...');
}   
 
