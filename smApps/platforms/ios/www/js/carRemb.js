/*
 * 初始化车辆维修报销单申请
 */
function editCarRembProc() {
	$.mobile.navigate( "#carRembProcEditPage" );
	
	var now = formatDayString(new Date());
	$("#cr_plateNumber").val("");
	$("#cr_repairTime").val(now);
	$("#cr_reimbursementSum").val("0");
	$("#cr_reimbursementComment").val("");
}
/*
 * 提交车辆维修报销单流程
 */
function submitCarRembProc() {
	console.log("submit CarRemb Proc ...");  
	if(!validateCarRembEdit())
		return false;
	var formData = $("#carRembProcEditForm").serialize();
	if(port != 80) {					
		strUrl = "http://" + server +":" + port + "/salesManager/car!editCr";
	}else {
		strUrl = "http://" + server + "/salesManager/car!editCr";
	}				
	$.ajax({  
		type: "POST",  
		url: strUrl,  
		cache: false,  
		data: formData ,  
		dataType:"jsonp",
		jsonpCallback:"callback",
		timeout: 20000,
		success: onSubmitCarRembEditSuccess,  
		error: onSubmitCarRembEditError  
	});  
	return false;  
}
/*
 * 验证车辆维修报销单填写信息是否合法
 */
function validateCarRembEdit() {
	
	if (parseInt($("#cr_reimbursementSum").val()) <=0 ) {
		alert('维修报销费用必须大于0！');
		return false;
	}
	
	return true;
}
/*
 * 初始化车辆维修报销单流程
 */
function onInitCarRembEditSuccess(data, status) {
	
}
/*
 * 初始化车辆维修报销单流程
 */
function onInitCarRembEditError(data, status) {
	navigator.notification.alert('系统服务连接错误！',callback,'系统错误','确定');
}

/*
 * 提交车辆维修报销单流程
 */
function onSubmitCarRembEditSuccess(data, status) {
	$.mobile.loading( "hide" );
	var now = formatDayString(new Date());
	$("#cr_plateNumber").val("");
	$("#cr_repairTime").val(now);
	$("#cr_reimbursementSum").val("0");
	$("#cr_reimbursementComment").val("");
	
	//导航到“我提交的流程页面”
	proc_pageNum=1;
	proc_maxpages=0;
	loadProcessList(3);
}
/*
 * 提交车辆维修报销单流程
 */
function onSubmitCarRembEditError(data, status) {
	$.mobile.loading( "hide" );
	navigator.notification.alert('系统服务连接错误！',callback,'系统错误','确定');
}
