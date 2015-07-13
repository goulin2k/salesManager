/*
 * 初始化加班单申请
 */
function editCarProc() {
	$.mobile.navigate( "#carProcEditPage" );
	
	var now = formatDayString(new Date());
	$("#car_plateNumber").val("");
	$("#car_repairTime").val(now);
	$("#car_repairSum").val("0");
	$("#car_repairComment").val("");
}
/*
 * 提交加班单流程
 */
function submitCarProc() {
	console.log("submit Car Proc ...");  
	if(!validateCarEdit())
		return false;
	var formData = $("#carProcEditForm").serialize();
	if(port != 80) {					
		strUrl = "http://" + server +":" + port + "/salesManager/car!editCa";
	}else {
		strUrl = "http://" + server + "/salesManager/car!editCa";
	}				
	$.ajax({  
		type: "POST",  
		url: strUrl,  
		cache: false,  
		data: formData ,  
		dataType:"jsonp",
		jsonpCallback:"callback",
		timeout: 20000,
		success: onSubmitCarEditSuccess,  
		error: onSubmitCarEditError  
	});  
	return false;  
}
/*
 * 验证加班单填写信息是否合法
 */
function validateCarEdit() {
	
	if (parseInt($("#car_repairSum").val()) <=0 ) {
		alert('预计维修费用必须大于0！');
		return false;
	}
	
	return true;
}
/*
 * 初始化加班单流程
 */
function onInitCarEditSuccess(data, status) {
	
}
/*
 * 初始化加班单流程
 */
function onInitCarEditError(data, status) {
	navigator.notification.alert('系统服务连接错误！',callback,'系统错误','确定');
}

/*
 * 提交加班单流程
 */
function onSubmitCarEditSuccess(data, status) {
	$.mobile.loading( "hide" );
	var now = formatDayString(new Date());
	$("#car_plateNumber").val("");
	$("#car_repairTime").val(now);
	$("#car_repairSum").val("0");
	$("#car_repairComment").val("");
	
	//导航到“我提交的流程页面”
	proc_pageNum=1;
	proc_maxpages=0;
	loadProcessList(3);
}
/*
 * 提交加班单流程
 */
function onSubmitCarEditError(data, status) {
	$.mobile.loading( "hide" );
	navigator.notification.alert('系统服务连接错误！',callback,'系统错误','确定');
}
