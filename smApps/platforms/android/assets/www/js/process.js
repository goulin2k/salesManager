var proc_pageNum=1;
var proc_pageSize=10;
var proc_maxpages=0;
var proc_opType=0;

/*
 * 加载工作流程列表
 */
function loadProcessTypeList() {
	$.mobile.loading( "show", {
	            text: 'loading',
	            textVisible: true,
	            theme: $.mobile.loader.prototype.options.theme,
	            textonly: false,
	            html: ''
    });

	if(port != 80) {					
		strUrl = "http://" + server +":" + port + "/salesManager/messageAction!getProcessCount";
	}else {
		strUrl = "http://" + server + "/salesManager/messageAction!getProcessCount";
	}
	$.ajax({  
		type: "POST",  
		url: strUrl,  
		cache: false,  
		dataType:"jsonp",
		jsonpCallback:"callback",
		timeout: 20000,
		success: onloadProcessTypeListSuccess,  
		error: onloadProcessListError
	});	
}

/*
 * 加载工作流程列表
 */
function onloadProcessTypeListSuccess(data, status) {
	$.mobile.loading( "hide" );
	$("#total_undealed").text(data.total_undealed);
	$("#total_mysubmit").text(data.total_mysubmit);
	$("#total_dealed").text(data.total_dealed);
	$.mobile.navigate("#procTypeListPage");
}
/*
 * 加载工作流程列表
 */
function loadProcessList(opType) {
	proc_opType = opType;
	$.mobile.loading( "show", {
	            text: 'loading',
	            textVisible: true,
	            theme: $.mobile.loader.prototype.options.theme,
	            textonly: false,
	            html: ''
    		});
	
	console.log("http://" + server +":" + port + "/salesManager/messageAction!processList");
	if(port != 80) {					
		strUrl = "http://" + server +":" + port + "/salesManager/messageAction!processList";
	}else {
		strUrl = "http://" + server + "/salesManager/messageAction!processList";
	}
	$.ajax({  
		type: "POST",  
		url: strUrl,  
		cache: false,  
		data: 'pageSize=' + msg_pageSize + '&pages=' + proc_pageNum + '&opType=' + opType,  
		dataType:"jsonp",
		jsonpCallback:"callback",
		timeout: 20000,
		success: onloadProcessListSuccess,  
		error: onloadProcessListError
	});
}

/*
 * 加载工作流程列表成功，显示消息列表
 */
function onloadProcessListSuccess(data, status) {
	
	
	clearTheListview($("#processListview"));
	
	var str = '<li data-role="list-divider">我的流程列表 <span class="ui-li-count">' + data.total + '</span></li>';
	var list = $(str);
	$("#processListview").append(list);
	proc_maxpages = data.pages;
	var optType = data.msgType;
	var procType = 0;
	if(proc_pageNum > 1) {
		$("#processListview").append('<li data-role="list-divider"><div data-role="content" align="center">' +
			'<a href="#" onclick="preTaskPage(' + optType +');" data-role="button" data-corners="false" >上一页</a></div></li>');
	}
	for(i in data.rows) {
		str = '';
		
		if(optType == 1) {
			procType = getProcessType(data.rows[i].typeName);
			str = str + '<li data-icon="tag"><a href="#" onclick="viewProcDetail(' + data.rows[i].taskId + ',' + procType + ');">' +
						'<h3>' + data.rows[i].typeName + '</h3>' +
						'<p><strong>申请人：' + data.rows[i].userName + '</strong></p>' +
						'<p>申请人部门：' + data.rows[i].deptName  + '</p>' +
						'<p class="ui-li-aside">申请时间：<strong>' + data.rows[i].createTime + '</strong></p>' +
										
						'</a></li>';
		} else {
			procType = getProcessType(data.rows[i].processType);
			str = str + '<li data-icon="tag"><a href="#" onclick="viewProcDetail2(' + data.rows[i].detailId + ',' + procType + ');">' +
						'<h3>' + data.rows[i].processType + '</h3>' +
						'<p><strong>申请人：' + data.rows[i].userName + '</strong></p>' +
						'<p>申请人部门：' + data.rows[i].deptName  + '</p>' +
						'<p><strong>审批流程状态：' + data.rows[i].activityName + '</strong></p>' +
						'<p class="ui-li-aside">申请时间：<strong>' + data.rows[i].appTime + '</strong></p>' +
												
						'</a></li>';
		}
		var list = $(str);
		$("#processListview").append(list);
	}
	if(proc_pageNum < proc_maxpages) {
		$("#processListview").append('<li data-role="list-divider"><div data-role="content" align="center">' +
			'<a href="#" onclick="nextTaskPage(' + optType +');" data-role="button" data-corners="false" >下一页</a></div></li>');
	}
	$.mobile.loading( "hide" );
	$.mobile.navigate("#processList");
	$('#processListview').listview('refresh');  
	
	console.log("Ajax data onSuccess!");
}

function preTaskPage(opType) {
	if(proc_pageNum > 1) {
		proc_pageNum--;
		loadProcessList(opType);
	}
}

function nextTaskPage(opType) {
	if(proc_pageNum < proc_maxpages) {
		proc_pageNum++;
		loadProcessList(opType);
	}
	console.log('next page ...');
}

/*
 * 流程审批
 */
function viewProcDetail(taskId, processType) {
	$.mobile.loading( "show", {
	            text: 'loading',
	            textVisible: false,
	            theme: $.mobile.loader.prototype.options.theme,
	            textonly: false,
	            html: ''
	});
	if(port != 80) {					
		strUrl = "http://" + server +":" + port + "/salesManager/messageAction!getProcessDetail";
	}else {
		strUrl = "http://" + server + "/salesManager/messageAction!getProcessDetail";
	}
	$.ajax({  
		type: "POST",  
		url: strUrl,  
		cache: false,  
		data: 'typeId=' + processType + '&taskId=' + taskId,  
		dataType:"jsonp",
		jsonpCallback:"callback",
		timeout: 20000,
		success: onLoadProcessDetailSuccess,  
		error: onLoadProcessDetailError
	});
	
	console.log('taskId：' + taskId + ', processType:' + processType);
}

/*
 * 流程审批
 */
function viewProcDetail2(taskId, processType) {
	$.mobile.loading( "show", {
	            text: 'loading',
	            textVisible: false,
	            theme: $.mobile.loader.prototype.options.theme,
	            textonly: false,
	            html: ''
	});
	if(port != 80) {					
		strUrl = "http://" + server +":" + port + "/salesManager/process!showDetail";
	}else {
		strUrl = "http://" + server + "/salesManager/process!showDetail";
	}
	$.ajax({  
		type: "POST",  
		url: strUrl,  
		cache: false,  
		data: 'typeId=' + processType + '&taskId=' + taskId,  
		dataType:"jsonp",
		jsonpCallback:"callback",
		timeout: 20000,
		success: onSetMessageReadedSuccess,  
		error: onLoadProcessDetailError
	});
	
	console.log('taskId：' + taskId + ', processType:' + processType);
}

/*
 * 提交流程审批结果
 */
function submitAudit() {
	$.mobile.loading( "show", {
	            text: 'loading',
	            textVisible: false,
	            theme: $.mobile.loader.prototype.options.theme,
	            textonly: false,
	            html: ''
	});
	
	if(port != 80) {					
		strUrl = "http://" + server +":" + port + "/salesManager/messageAction!auditProcess";
	}else {
		strUrl = "http://" + server + "/salesManager/messageAction!auditProcess";
	}
	var formData = $("#auditPopupForm").serialize();
	
	$.ajax({  
		type: "POST",  
		url: strUrl,  
		cache: false,  
		data: formData,  
		dataType:"jsonp",
		jsonpCallback:"callback",
		timeout: 20000,
		success: onAuditSuccess, 
		error: onAuditError  
	});  
	return false;  
}

/*
 * 根据单据名称获取单据类型id
 */
function getProcessType(typeName) {
	if(typeName =='休假单') {
		return 1;
	}
	else if(typeName =='出差申请单' || typeName =='出差单') {
		return 2;
	}
	else if(typeName =='加班申请单' || typeName =='加班单') {
		return 3;
	}
	else if(typeName =='费用申请单') {
		return 4;
	}
	else if(typeName =='费用报销单') {
		return 5;
	}
	else if(typeName =='车辆维修申请单') {
		return 6;
	}
	else if(typeName =='车辆维修报销单') {
		return 7;
	}
	else {
		alert("Error Process Type: " + typeName) ;
	}
}

/*
 * 加载工作流程列表失败，错误信息处理
 */
function onloadProcessListError(data, status) {
	$.mobile.loading( "hide");
	console.log("onloadProcessList Error:" + status);
}
/*
 * 加载工作流程列表失败，错误信息处理
 */
function onLoadProcessDetailSuccess(data, status) {	
	
	$.mobile.navigate("#auditPopup");
	$("#audit_taskId").val(data.taskId);
	$("#audit_title").text(data.typeName);
	$("#audit_user").text("申请  人：" + data.userName);
	$("#audit_status").text("流程状态：" + data.status);
	$("#audit_applyContent").html("" + data.applyInfo);
	$("#audit_time").text("申请时间：" + data.applyTime);
	
	$("#audit_action").val("0");
	$("#audit_content").val("");
	$.mobile.loading( "hide");
}
/*
 * 加载工作流程列表失败，错误信息处理
 */
function onLoadProcessDetailError(data, status) {
	$.mobile.loading( "hide");
	console.log('onLoadProcessDetailError: ' + status);
}

/*
 * 加载工作流程列表失败，错误信息处理
 */
function onAuditSuccess(data, status) {		
	$.mobile.navigate("#processList");
	loadProcessList(proc_opType);
	$.mobile.loading( "hide");	
	console.log('on Audit Success: ' + data.result);
	
}
/*
 * 加载工作流程列表失败，错误信息处理
 */
function onAuditError(data, status) {
	$.mobile.loading( "hide");
	console.log('on Audit Error: ' + data.status);
}