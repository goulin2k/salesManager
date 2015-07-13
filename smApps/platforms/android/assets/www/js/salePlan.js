/*
 * 销售计划导航——Ajax加载分页数据
 */
function loadPlanlist() {		
	$.mobile.loading( "show", {
            text: 'loading',
            textVisible: false,
            theme: $.mobile.loader.prototype.options.theme,
            textonly: false,
            html: ''
    		});
	if(port != 80) {					
		strUrl = "http://" + server +":" + port + "/salesManager/salePlanAction!getSalePlanJsonList";
	}else {
		strUrl = "http://" + server + "/salesManager/salePlanAction!getSalePlanJsonList";
	}
	console.log(strUrl);
	var responseUserId = $("#responseUserId").val();
	var planStart = $("#planStart").val();
	var planEnd = $("#planEnd").val();
	var completStart = $("#completStart").val();
	var completEnd = $("#completEnd").val();
	var orderBy = $("#orderBy").val();
	var orderType = $("#orderType").val();
	$.ajax({  
		type: "POST",  
		url: strUrl,  
		cache: false,  
		data: 'pageSize=' + pagesize + '&pages=' + pages + '&responseUserId=' + responseUserId + '&planStart=' + planStart + '&planEnd=' + planEnd
				+ '&completStart=' + completStart+ '&completEnd=' + completEnd 
				+'&orderBy=' + orderBy+ '&orderType=' + orderType,  
		dataType:"jsonp",
		jsonpCallback:"callback",
		timeout: 20000,
		success: onSuccess,  
		error: onError
	});
}

/*
 * 销售计划Ajax请求成功后，将计划显示到列表
 */
function onSuccess(data, status) {
	clearListview();
	$.mobile.loading( "hide" );
	var str;
	var preday='';
	maxpages = data.pages;
	console.log("maxpages:" + maxpages);
	
	$("#thelist").append('<li data-icon="forward"><a href="#planFilterPage">销售计划列表</a></li>');
	str='';
	if(pages > 1) {
		$("#thelist").append('<li data-role="list-divider"><div data-role="content" align="center">' +
			'<a href="#" onclick="prev();" data-role="button" data-corners="false" >上一页</a></div></li>');
	}
	for(i in data.rows) {
		var day = data.rows[i].startTime.toString();
		if(preday != day) {
			preday = day;
		}
		str = '' + '<li data-icon="tag"><a href="#" onclick="assign(' + data.rows[i].projectId + ',' + data.rows[i].projectRole + ');">' +
			'<h3>' + data.rows[i].customerName + '</h3>' +
			'<p><strong>主题：' + data.rows[i].topical + '</strong></p>' +
			'<p><strong>负责人：' + data.rows[i].responseUserName + '</strong></p>' +
			'<p>计划开始日期：' + data.rows[i].startTime + '</p>' +
			'<p>计划完成日期：' + data.rows[i].endTime + '</p>' +
			'<p>目标：' + data.rows[i].goal + '</p>' +
			'<p>计划内容：' + data.rows[i].comment + '</p></a>' +
			'<p> <a href="#" onclick="getActivities(' + data.rows[i].projectId + ');">已签到：（' + data.rows[i].activityCount + 
			'）<span class="ui-btn ui-mini ui-shadow ui-corner-all ui-icon-eye ui-btn-icon-notext ui-btn-b ui-btn-inline" style="width:12px;height: 12px;"></span>' + 
			'<a href="#" onclick="getAssess(' + data.rows[i].projectId + ');">督办项：（' + data.rows[i].assessListCount + '）</a></p>' +
			'</li>';
		var list = $(str);
		$("#thelist").append(list);
	}
	if(pages < maxpages) {
		$("#thelist").append('<li data-role="list-divider"><div data-role="content" align="center">' +
		'<a href="#" onclick="next();" data-role="button" data-corners="false" >下一页</a></div></li>');
	}
	console.log("Before data refresh!");
	$('#thelist').listview('refresh');  
	console.log("Ajax data onSuccess!");
}

/*
 * 清空计划列表页面显示
 */
function clearListview() {
	var ListTab = $("#thelist").children('li');
	
	$(ListTab).each(function() {
		console.log('children:' + $(this).attr('html'));
		$(this).remove();
	});
	
}

/*
 * 销售计划状态文字显示
 */
function getStatusString(status) {
	if(status==1)
		return '完成';
	else
		return '未完成';
}

/*
 * 销售计划页面刷新错误处理
 */
function onError(data, status) {
	$.mobile.loading( "hide" );			
	console.log('get planlist error: ' + status); 
}

/*
 * 销售计划导航——上一页
 */
function prev() {
	if(pages > 1) {
		pages--;
		loadPlanlist();
	}
	console.log('pre page ...');
}

/*
 * 销售计划导航——下一页
 */
function next() {
	if(pages < maxpages) {
		pages++;
		loadPlanlist();
	}
	console.log('next page ...');
}

/*
 * 点击销售计划，进入活动录入页面
 */
function assign(planId, projectRoleID) {
	$("#geo").text(address);
	$("#locAddress").val(address);
	$("#planId").val(planId);
	$("#locLatitude").val(gps_lat);
	$("#locLongitude").val(gps_lon);
	$("#planRoleId").val(projectRoleID);
	$("#topical").val('');
	console.log("To assign planId:" + $("#planId").val() + ",projectRoleID:" + $("#planRoleId").val());
	
	if(projectRoleID >1 ) {
		$("#lblTopical").html('计划督办内容：');
		$("#dvActivityType").hide();
	}
	else {
		$("#lblTopical").html('销售活动内容：');
		$("#dvActivityType").show();
	}
	
	$.mobile.navigate("#assignPage");
}

/*
 * 点击签到按钮，直接进入活动录入页面
 */
function assignDirect(planId) {
	$("#geo").text(address);
	$("#locAddress").val(address);
	$("#locLatitude").val(gps_lat);
	$("#locLongitude").val(gps_lon);
	
	
	$.mobile.navigate("#assignPage");
}

/*
 * 提交保存销售活动记录
 */
function submitAssign() {
	$.mobile.loading( "show", {
	            text: 'loading',
	            textVisible: false,
	            theme: $.mobile.loader.prototype.options.theme,
	            textonly: false,
	            html: ''
    		});
	var formData = $("#activityAssignForm").serialize();  
			
	$.ajax({  
		type: "POST",  
		url: "http://" + server +":" + port + "/salesManager/salePlanAction!addSaleActivityJson",  
		cache: false,  
		data: formData,  
		dataType:"jsonp",
		jsonpCallback:"callback",
		timeout: 20000,
		success: onActivityAssignedSuccess,  
		error: onActivityAssignedError  
	});  
	return false;
}
		
/*
 * 坐标转换完之后的回调函数：获取坐标所在的地址信息
 */		
function onActivityAssignedSuccess(data, status)  
{  
	$.mobile.loading( "hide" );
	if(data.result == '0') {		
		navigator.notification.alert('活动记录提交失败！',callback,'提示','确定');
	}else {	
		$.mobile.navigate("#main");
		loadPlanlist();
		$("[data-role='navbar']").navbar();
		$("[data-role='header'], [data-role='footer']").toolbar();
	} 
}  
/*
 * 坐标转换完之后的回调函数：获取坐标所在的地址信息
 */
function onActivityAssignedError(data, status)  
{  
	$.mobile.loading( "hide" );
	navigator.notification.alert('系统发生异常，活动记录提交失败！',callback,'系统错误','确定');
	console.log('login error: ' + status);  
}

/*
 * 取消保存销售活动记录，返回计划列表
 */
function cancelAssign() {
	$.mobile.navigate("#main");
}

/*
 * 获取指定销售计划的销售活动列表
 */
function getActivities(projectId) {
	console.log('projectId is: ' + projectId); 
	var ListTab = $("#avtivitylist").children('li');
	
	$(ListTab).each(function() {
		console.log('children:' + $(this).attr('html'));
		$(this).remove();
	});
	
	 
	$.mobile.loading( "show", {
	            text: 'loading',
	            textVisible: true,
	            theme: $.mobile.loader.prototype.options.theme,
	            textonly: false,
	            html: ''
    		});
	if(port != 80) {					
		strUrl = "http://" + server +":" + port + "/salesManager/salePlanAction!getSaleActivityListJson";
	}else {
		strUrl = "http://" + server + "/salesManager/salePlanAction!getSaleActivityListJson";
	} 
			
	$.ajax({  
		type: "POST",  
		url: strUrl,  
		cache: false,  
		data: 'pageSize=' + 100 + '&pages=' + 1 + '&projectId=' + projectId, 
		dataType:"jsonp",
		jsonpCallback:"callback",
		timeout: 20000,
		success: onLoadActivityListSuccess,  
		error: onLoadActivityListError  
	});  
	return false;
}

/*
 * 获取指定销售计划的督办批注信息列表
 */
function getAssess(projectId) {
	console.log('projectId is: ' + projectId); 
	var ListTab = $("#avtivitylist").children('li');
	
	$(ListTab).each(function() {
		console.log('children:' + $(this).attr('html'));
		$(this).remove();
	});
	
	 
	$.mobile.loading( "show", {
	            text: 'loading',
	            textVisible: true,
	            theme: $.mobile.loader.prototype.options.theme,
	            textonly: false,
	            html: ''
    		});
	if(port != 80) {					
		strUrl = "http://" + server +":" + port + "/salesManager/salePlanAction!getSalePlanAssessListJson";
	}else {
		strUrl = "http://" + server + "/salesManager/salePlanAction!getSalePlanAssessListJson";
	} 
			
	$.ajax({  
		type: "POST",  
		url: strUrl,  
		cache: false,  
		data: 'pageSize=' + 100 + '&pages=' + 1 + '&projectId=' + projectId, 
		dataType:"jsonp",
		jsonpCallback:"callback",
		timeout: 20000,
		success: onLoadAssessListSuccess,  
		error: onLoadActivityListError  
	});  
	return false;
}
/*
 *  ajax加载销售活动列表显示内容
 */
function onLoadActivityListSuccess(data, status) {
	var str;
	console.log('onLoadActivityList Success!'); 
	
	
	if(data.rows.length>0) {
		$.mobile.navigate("#activitiesListPage");
		for(i in data.rows) {
			str = '';
		
			str = str + '<li data-icon="tag"><a href="#">' +
						'<h3>' + data.rows[i].customerName + '</h3>' +
						'<p><strong>签到人：' + data.rows[i].responseUserName + '</strong></p>' +
						'<p>签到日期：' + data.rows[i].activityDateString + '</strong></p>' +
						'<p>签到内容：' + data.rows[i].topical + '</p></a>' +					
						'</li>';
			var list = $(str);
			$("#avtivitylist").append(list);
		}
	
	
		$('#avtivitylist').listview('refresh'); 
	} 
	$.mobile.loading( "hide" );
	
}

/*
 *  ajax加载计划督办、批注信息列表显示内容
 */
function onLoadAssessListSuccess(data, status) {
	var str;
	console.log('on LoadAssess List Success!'); 
	
	if(data.rows.length>0) {
		$.mobile.navigate("#activitiesListPage");
		for(i in data.rows) {
			str = '';
		
			str = str + '<li data-icon="tag"><a href="#">' +
						'<p><strong>督办人：' + data.rows[i].assessUserName + '</strong></p>' +
						'<p>督办日期：' + data.rows[i].commentTimeString + '</strong></p>' +
						'<p>督办内容：' + data.rows[i].comment + '</p></a>' +					
						'</li>';
			var list = $(str);
			$("#avtivitylist").append(list);
		}
	
	
		$('#avtivitylist').listview('refresh'); 
	} 
	$.mobile.loading( "hide" );
	
}

/*
 * 取消保存销售活动记录，返回计划列表
 */
function onLoadActivityListError(data, status) {
	console.log('onLoadActivityList Error:' + status); 
	
	$.mobile.loading( "hide" );
}

/*
 * 取消保存销售活动记录，返回计划列表
 */
function initPlanfilterForm() {
	if(port != 80) {					
		strUrl = "http://" + server +":" + port + "/salesManager/appUserAction!getSalers";
	}else {
		strUrl = "http://" + server + "/salesManager/appUserAction!getSalers";
	}
	console.log("initPlanfilterForm:\t" + strUrl);
	$.ajax({  
		type: "POST",  
		url: strUrl,  
		cache: false,   
		dataType:"jsonp",
		jsonpCallback:"callback",
		timeout: 20000,
		success: onGetUserListSuccess,  
		error: onError
	});
	
}

/*
 * 提交过滤查询条件，返回计划列表
 */
function submitPlanFilter() {
	pages = 0;
	loadPlanlist();
	$.mobile.navigate( "#main" );
}

/*
 * 初始化用户列表
 */
function onGetUserListSuccess(data, status) {
	var str;
	console.log('on Get User List Success!'); 
	
	if(data.length>0) {
		for(i in data) {
			if(data[i].userId >1)
				$("#responseUserId").append( "<option value=" + data[i].userId + ">"+data[i].userName+"</option>" );
		}
	}
}
