/*
 * 初始化请假单申请
 */
function editLeaveProc() {
	$.mobile.navigate( "#leaveProcEditPage" );
	var strUrl = '';
	if(port != 80) {					
		strUrl = "http://" + server +":" + port + "/salesManager/leave!toEditLeave";
	}else {
		strUrl = "http://" + server + "/salesManager/leave!toEditLeave";
	}
	
	$.mobile.loading( "show", {
			            text: 'loading',
			            textVisible: true,
			            theme: $.mobile.loader.prototype.options.theme,
			            textonly: false,
			            html: ''
		    		});
	
	$.ajax({  
		type: "POST",  
		url: strUrl,  
		cache: false,  
		data: "requestFrom=mobile",  
		dataType:"jsonp",
		jsonpCallback:"callback",
		timeout: 20000,
		success: onInitLeaveEditSuccess,  
		error: onInitLeaveEditError
	});  
	return false;    
}
/*
 * 提交请假单流程
 */
function submitLeaveProc() {
	console.log("submit Leave Proc ...");  
	if(!validateLeaveEdit())
		return false;
	var formData = $("#leaveProcEditForm").serialize();
	if(port != 80) {					
		strUrl = "http://" + server +":" + port + "/salesManager/leave!editLeave";
	}else {
		strUrl = "http://" + server + "/salesManager/leave!editLeave";
	}				
	$.ajax({  
		type: "POST",  
		url: strUrl,  
		cache: false,  
		data: formData ,  
		dataType:"jsonp",
		jsonpCallback:"callback",
		timeout: 20000,
		success: onSubmitLeaveEditSuccess,  
		error: onSubmitLeaveEditError  
	});  
	return false;  
}
/*
 * 验证请假单填写信息是否合法
 */
function validateLeaveEdit() {
	if (parseInt($("#leave_leaveDays").val()) <=0 ) {
		alert('休假小时数必须大于0！');
		return false;
	}
	if (!dateCompareBigger($('#leave_startTime').val(), $('#leave_endTime').val())) {
		alert('休假起止日期填报错误。');
		return false;
	} 
	var type = $('#leave_typeId').val();
	
	if (type == 7) {
		if (parseInt($("#leave_leaveDays").val()) <= parseInt($("#leave_over").val())) {
			return true;
		}else {
			alert('当前剩余换休时间为'+$("#leave_over").val()+'小时, 已经小于所填调休小时数。');
			return false;
		}
	} 
	else if (type == 3) {
		if (parseInt($("#leave_leaveDays").val()) <= parseInt($("#leave_annu").val())) {
			return true;
		}else {
			alert('当前剩余年假时间为'+$("#leave_annu").val()+'小时, 已经小于所填请假小时数。');
			return false;
		}
	}
	return true;
}
/*
 * 初始化请假单流程
 */
function onInitLeaveEditSuccess(data, status) {
	$.mobile.loading( "hide" );
	var now = formatDayString(new Date());
	var str="提示：你的年假剩余 " + data.annualHours + " 小时, 调休剩余 " + data.overtimeHours + " 小时。";
	$("#leave_tips").text(str);
	$("#leave_annu").val(data.annualHours);
	$("#leave_over").val(data.overtimeHours);
	
	$("#leave_leaveDays").val(0);
	$("#leave_endTime").val(now);
	$("#leave_startTime").val(now);
	$("#leave_comment").val("");
}
/*
 * 初始化请假单流程
 */
function onInitLeaveEditError(data, status) {
	$.mobile.loading( "hide" );
	navigator.notification.alert('系统服务连接错误！',callback,'系统错误','确定');
}

/*
 * 提交请假单流程
 */
function onSubmitLeaveEditSuccess(data, status) {
	$.mobile.loading( "hide" );
	var str="";
	$("#leave_tips").text(str);
	$("#leave_annu").val(0);
	$("#leave_over").val(0);
	
	//导航到“我提交的流程页面”
	proc_pageNum=1;
	proc_maxpages=0;
	loadProcessList(3);
}
/*
 * 提交请假单流程
 */
function onSubmitLeaveEditError(data, status) {
	$.mobile.loading( "hide" );
	navigator.notification.alert('系统服务连接错误！',callback,'系统错误','确定');
}
