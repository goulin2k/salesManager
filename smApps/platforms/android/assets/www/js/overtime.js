/*
 * 初始化加班单申请
 */
function editOvertimeProc() {
	$.mobile.navigate( "#overtimeProcEditPage" );
	
	var now = formatDayString(new Date());
	$("#ot_hours").val(0);
	$("#ot_startTime").val(now);
	$("#ot_comment").val("");
}
/*
 * 提交加班单流程
 */
function submitOvertimeProc() {
	console.log("submit Overtime Proc ...");  
	if(!validateOvertimeEdit())
		return false;
	var formData = $("#overtimeProcEditForm").serialize();
	if(port != 80) {					
		strUrl = "http://" + server +":" + port + "/salesManager/overtime!editOt";
	}else {
		strUrl = "http://" + server + "/salesManager/overtime!editOt";
	}				
	$.ajax({  
		type: "POST",  
		url: strUrl,  
		cache: false,  
		data: formData ,  
		dataType:"jsonp",
		jsonpCallback:"callback",
		timeout: 20000,
		success: onSubmitOvertimeEditSuccess,  
		error: onSubmitOvertimeEditError  
	});  
	return false;  
}
/*
 * 验证加班单填写信息是否合法
 */
function validateOvertimeEdit() {
	
	if (parseInt($("#ot_hours").val()) <=0 ) {
		alert('加班小时数必须大于0！');
		return false;
	}
	
	return true;
}
/*
 * 初始化加班单流程
 */
function onInitOvertimeEditSuccess(data, status) {
	
}
/*
 * 初始化加班单流程
 */
function onInitOvertimeEditError(data, status) {
	navigator.notification.alert('系统服务连接错误！',callback,'系统错误','确定');
}

/*
 * 提交加班单流程
 */
function onSubmitOvertimeEditSuccess(data, status) {
	$.mobile.loading( "hide" );
	var str="";
	$("#ot_hours").val(0);
	$("#ot_startTime").val("");
	$("#ot_comment").val("");
	
	//导航到“我提交的流程页面”
	proc_pageNum=1;
	proc_maxpages=0;
	loadProcessList(3);
}
/*
 * 提交加班单流程
 */
function onSubmitOvertimeEditError(data, status) {
	$.mobile.loading( "hide" );
	navigator.notification.alert('系统服务连接错误！',callback,'系统错误','确定');
}
