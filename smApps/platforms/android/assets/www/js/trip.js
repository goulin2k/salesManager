/*
 * 初始化出差单申请
 */
function editTripProc() {
	$.mobile.navigate( "#tripProcEditPage" );
	
	var now = formatDayString(new Date());
	$("#tripApplication_endTime").val(now);
	$("#tripApplication_startTime").val(now);
	$("#tripApplication_tripComment").val("");
}
/*
 * 提交出差单流程
 */
function submitTripProc() {
	console.log("submit Trip Proc ...");  
	if(!validateTripEdit())
		return false;
	var formData = $("#tripProcEditForm").serialize();
	if(port != 80) {					
		strUrl = "http://" + server +":" + port + "/salesManager/trip!editTrip";
	}else {
		strUrl = "http://" + server + "/salesManager/trip!editTrip";
	}				
	$.ajax({  
		type: "POST",  
		url: strUrl,  
		cache: false,  
		data: formData ,  
		dataType:"jsonp",
		jsonpCallback:"callback",
		timeout: 20000,
		success: onSubmitTripEditSuccess,  
		error: onSubmitTripEditError  
	});  
	return false;  
}
/*
 * 验证出差单填写信息是否合法
 */
function validateTripEdit() {
	
	if (!dateCompareBigger($('#tripApplication_startTime').val(), $('#tripApplication_endTime').val())) {
		alert('出差起止日期填报错误！');
		return false;
	} 
	
	return true;
}
/*
 * 初始化出差单流程
 */
function onInitTripEditSuccess(data, status) {
	
}
/*
 * 初始化出差单流程
 */
function onInitTripEditError(data, status) {
	navigator.notification.alert('系统服务连接错误！',callback,'系统错误','确定');
}

/*
 * 提交出差单流程
 */
function onSubmitTripEditSuccess(data, status) {
	$.mobile.loading( "hide" );
	var str="";
	$("#tripApplication_endTime").text("");
	$("#tripApplication_startTime").val("");
	$("#tripApplication_tripLocation").val("");
	$("#tripApplication_tripComment").val("");
	
	//导航到“我提交的流程页面”
	proc_pageNum=1;
	proc_maxpages=0;
	loadProcessList(3);
}
/*
 * 提交出差单流程
 */
function onSubmitTripEditError(data, status) {
	$.mobile.loading( "hide" );
	navigator.notification.alert('系统服务连接错误！',callback,'系统错误','确定');
}
