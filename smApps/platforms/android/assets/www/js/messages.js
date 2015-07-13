var msg_pageNum=1;
var msg_pageSize=10;
var msg_maxpages=0;

var msg_current_type=0;

/*
 * 加载消息类型列表
 */
function loadMessageTypeList() {
	$.mobile.loading( "show", {
	            text: 'loading',
	            textVisible: true,
	            theme: $.mobile.loader.prototype.options.theme,
	            textonly: false,
	            html: ''
    });

	if(port != 80) {					
		strUrl = "http://" + server +":" + port + "/salesManager/messageAction!getMessageGroupCounts";
	}else {
		strUrl = "http://" + server + "/salesManager/messageAction!getMessageGroupCounts";
	}
	$.ajax({  
		type: "POST",  
		url: strUrl,  
		cache: false,  
		dataType:"jsonp",
		jsonpCallback:"callback",
		timeout: 20000,
		success: onloadMessageTypeListSuccess,  
		error: onloadProcessListError
	});	
}
/*
 * 加载消息类型列表及未读消息数量
 */
function onloadMessageTypeListSuccess(data, status) {
	$.mobile.loading( "hide");
	console.log('onloadMessageTypeListSuccess');
	$.mobile.navigate("#messageTypeListPage");
	
	$("#msg_plan_count").text(data.planMessageCount);
	$("#msg_quote_count").text(data.quoteMessageCount);
	$("#msg_customer_count").text(data.customerMessageCount);
	$("#msg_sales_count").text(data.salesMessageCount);
}

/*
 * 加载销售单据审核列表
 */
function loadSaleAuditList(messageType) {
	$.mobile.loading( "show", {
	            text: 'loading',
	            textVisible: false,
	            theme: $.mobile.loader.prototype.options.theme,
	            textonly: false,
	            html: ''
    		});
	msg_current_type = messageType;
	if(port != 80) {					
		strUrl = "http://" + server +":" + port + "/salesManager/messageAction!getMessageList";
	}else {
		strUrl = "http://" + server + "/salesManager/messageAction!getMessageList";
	}
	
	$.ajax({  
		type: "POST",  
		url: strUrl,  
		cache: false,  
		data: 'pageSize=' + msg_pageSize + '&pages=' + msg_pageNum + '&messageType=' + messageType,  
		dataType:"jsonp",
		jsonpCallback:"callback",
		timeout: 20000,
		success: onloadSaleAuditListSuccess,  
		error: onloadSaleAuditListError
	});
	
}

/*
 * 加载销售单据审核列表成功，显示消息列表
 */
function onloadSaleAuditListSuccess(data, status) {
	$.mobile.navigate("#saleAuditListPage");
	clearTheListview($("#saleAuditListview"));
	
	var str = '<li data-role="list-divider">消息通知列表 <span class="ui-li-count">' + data.total + '</span></li>';
	var list = $(str);
	$("#saleAuditListview").append(list);
	msg_maxpages = data.pages;
	type = data.msgType;
	if(msg_pageNum > 1) {
		$("#saleAuditListview").append('<li data-role="list-divider"><div data-role="content" align="center">' +
			'<a href="#" onclick="prePage(' + type + ');" data-role="button" data-corners="false" >上一页</a></div></li>');
	}
	for(i in data.rows) {
		str = '';
		
		if( data.rows[i].status == 1)
			str = '<li data-icon="audio" data-theme="b">';
		else
			str='<li data-icon="audio" data-theme="a">';
		str = str + '<a href="#" onclick="openMessageDetail(' + data.rows[i].informationId + ',' + data.rows[i].type + ',' + data.rows[i].buzId + ');">' +
					'<h3>' + data.rows[i].userName + '</h3>' +
					'<p><strong>类型：' + data.rows[i].title + '</strong></p>' +
					'<p>内容：' + data.rows[i].content + '</p>' +
					'<p>状态：' + data.rows[i].statusName + 
					'<p class="ui-li-aside"><strong>' + data.rows[i].timeShow + '</strong></p>' +
					'</li>';
		var list = $(str);
		$("#saleAuditListview").append(list);
	}
	if(msg_pageNum < msg_maxpages) {
		$("#saleAuditListview").append('<li data-role="list-divider"><div data-role="content" align="center">' +
			'<a href="#" onclick="nextPage(' + type + ');" data-role="button" data-corners="false" >下一页</a></div></li>');
	}
	$('#saleAuditListview').listview('refresh');  
	$.mobile.loading( "hide" );
	console.log("Ajax data onSuccess!");
}

function prePage(messageType) {
	if(msg_pageNum > 1) {
		msg_pageNum--;
		loadSaleAuditList(messageType);
	}
}

function nextPage(messageType) {
	if(msg_pageNum < msg_maxpages) {
		msg_pageNum++;
		loadSaleAuditList(messageType);
	}
	console.log('next page ...');
}

function openMessageDetail(infoId, type, buzId) {
	if(type==0 || buzId==0) {
		console.log('infoId=' + infoId + ', type=' + type + ', buzId=' + buzId);
		return;
	}
	$.mobile.loading( "show", {
            text: 'loading',
            textVisible: false,
            theme: $.mobile.loader.prototype.options.theme,
            textonly: false,
            html: ''
    	});
	if(port != 80) {					
		strUrl = "http://" + server +":" + port + "/salesManager/messageAction!getMessageDetail";
	}else {
		strUrl = "http://" + server + "/salesManager/messageAction!getMessageDetail";
	}
	$.ajax({  
		type: "POST",  
		url: strUrl,  
		cache: false,  
		data: 'infoId=' + infoId + '&type=' + type + '&buzId=' + buzId,  
		dataType:"jsonp",
		jsonpCallback:"callback",
		timeout: 20000,
		success: onSetMessageReadedSuccess,  
		error: onloadSaleAuditListError
	});
}

function onSetMessageReadedSuccess(data, status) {
	console.log("on SetMessage Readed Success ...");
	
	$.mobile.navigate("#buzInfomationPage");
	$("#buzView").html('');
	var str ="<h2>" + data.title + "</h2>";
	for(i in data.data) {
		str = str + '<p id="setting_position">' + data.data[i].label + ': ' + data.data[i].value + '</p>';
	}
	
	if(data.listData) {
		str=str + '<table data-role="table" id="table-custom-2" data-mode="columntoggle" ';
		str = str + 'class="ui-body-d ui-shadow table-stripe ui-responsive" data-column-btn-theme="b" ';
		str = str + 'data-column-btn-text="Columns to display..." data-column-popup-theme="a">';
		str = str + '<thead><tr class="ui-bar-d">';
		for(i in data.listData.labels) {
			str = str + '<th data-priority="2">' + data.listData.labels[i] + '</th>';
		}
		str = str + '</tr></thead><tbody>';
		for(i in data.listData.data) {
			str = str + '<tr>';
			for(j in data.listData.data[i]) {
				str = str + '<td>' + data.listData.data[i][j] + '</td>';
			}
			str = str + '</tr>';
		}	
		str = str + '</tbody></table>';
	}
	$("#buzView").html(str);
	//loadSaleAuditList(msg_current_type);
}	

/*
 * 加载销售单据审核列表失败，错误信息处理
 */
function onloadSaleAuditListError(data, status) {
	$.mobile.loading( "hide");
	console.log("onloadSaleAuditList Error:" + status);
}

/*
 * 清空计划列表页面显示
 */
function clearTheListview(listview) {
	var ListTab = listview.children('li');
	
	$(ListTab).each(function() {
		console.log('children:' + $(this).attr('html'));
		$(this).remove();
	});
	
}

function dealProc(procId) {
	$.mobile.navigate("#auditPopup");
}