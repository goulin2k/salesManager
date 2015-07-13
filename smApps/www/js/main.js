var address = '未知地理位置';
var gps_lat = '';		//GPS纬度
var gps_lon = '';		//GPS经度

/*
 * phoneGap 本地资源初始化
 */
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
		var osDevice = device.platform;
		console.log(osDevice);		
		if(osDevice.trim() == 'iOS') {
			console.log('Device Platform: ' + device.platform);
			navigator.geolocation.getCurrentPosition(geolocationSuccess,
										 geolocationError,{ maximumAge: 3000, timeout: 30000, enableHighAccuracy: true });
		}else {
			console.log('Device Platform 2: ' + device.platform);
			getGeoByLocal();
		}
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
 * 获取本地GPS坐标成功后，调用百度api实现坐标转换为百度地图坐标
 */		
function geolocationSuccess(position) {
	
	gps_lat = position.coords.latitude;
	gps_lon = position.coords.longitude;
	var gpsPoint = new BMap.Point(gps_lon, gps_lat);
	setTimeout(function(){
		BMap.Convertor.translate(gpsPoint,0,translateCallback);     //真实经纬度转成百度坐标
	}, 5000);
	
	
}

/*
 * 坐标转换完之后的回调函数：获取坐标所在的地址信息
 */	
function translateCallback(point){
		// 百度地图API功能
	var map = new BMap.Map("allmap");
	var geoc = new BMap.Geocoder();
	geoc.getLocation(point, function(rs){
		var addComp = rs.addressComponents;
		address = addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber;
		//alert(address);
	});
		
	}

/*
 * 获取坐标所在的地址信息发生错误提示
 */
function geolocationError(error) {
	//alert('code: '    + error.code    + '\n' +
	//	  'message: ' + error.message + '\n');
	navigator.notification.alert('无法获取GPS定位信息，请检查手机定位服务设置！（' + error.code + ')',callback,'系统错误','确定');
}


/*
 * 坐标转换完之后的回调函数：获取坐标所在的地址信息
 */
function callback() {
}

/*
 * 退出登录，返回登录页面
 */
function exit() {
	window.location.href="../index.html";
}

/*
 * 日期比较函数: 如果a>b, return false，如果a<b， return true
 */
function dateCompareBigger(a, b) {
	var arr = a.split("-");
	var starttime = new Date(arr[0], arr[1], arr[2]);
	var starttimes = starttime.getTime();

	var arrs = b.split("-");
	var lktime = new Date(arrs[0], arrs[1], arrs[2]);
	var lktimes = lktime.getTime();
	//alert(starttimes+":"+lktimes);
	if (starttimes > lktimes) {
		return false;
	}
	else
		return true;

}

/*
 * 格式化日期为“YYYY-MM-DD”
 */
function formatDayString(aDay) {
	 
	year = aDay.getFullYear();
	month = aDay.getMonth() + 1; 
	day = aDay.getDate(); 
    month = month < 10 ? "0"+month : month;
	day = day<10 ? "0"+day:day;

	var res=year + "-" + month + "-" + day;
	return res;
}

function getGeoByLocal() {
	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			
			//alert('您的位置：'+r.point.lng+','+r.point.lat);
			var map = new BMap.Map("allmap");
			var geoc = new BMap.Geocoder();
			geoc.getLocation(r.point, function(rs){
				var addComp = rs.addressComponents;
				address = addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber;
			});
			
			/** 百度地址无需转换
			var map = new BMap.Map("allmap");
			var geoc = new BMap.Geocoder();
			var gpsPoint = new BMap.Point(r.point.lng, r.point.lat);
			setTimeout(function(){
				BMap.Convertor.translate(gpsPoint,0,translateCallback);     //真实经纬度转成百度坐标
			}, 5000);
			*/
		}
		else {
			alert('failed'+this.getStatus());
		}        
	},{enableHighAccuracy: true})
}