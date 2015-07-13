/*
 * 加载销售单据审核列表
 */
function loadAnnouncementList() {
	$.mobile.loading( "show", {
	            text: 'loading',
	            textVisible: false,
	            theme: $.mobile.loader.prototype.options.theme,
	            textonly: false,
	            html: ''
    		});
	
	console.log("http://" + server +":" + port + "/salesManager/messageAction!getCorpInfomationlist");
	$.ajax({  
		type: "POST",  
		url: "http://" + server +":" + port + "/salesManager/messageAction!getCorpInfomationlist",  
		cache: false,  
		data: 'pageSize=' + msg_pageSize + '&pages=' + msg_pageNum,  
		dataType:"jsonp",
		jsonpCallback:"callback",
		timeout: 20000,
		success: onloadAnnouncementListSuccess,  
		error: onloadAnnouncementListError
	});
}

/*
 * 加载销售单据审核列表成功，显示消息列表
 */
function onloadAnnouncementListSuccess(data, status) {
	
	$.mobile.navigate("#announcementListPage");
	clearTheListview2($("#announcementListView"));
	
	$("#announcementListView").append(list);
	msg_maxpages = data.pages;
	
	if(msg_pageNum > 1) {
		$("#announcementListView").append('<li data-role="list-divider"><div data-role="content" align="center">' +
			'<a href="#" onclick="preAnnouncementListPage();" data-role="button" data-corners="false" >上一页</a></div></li>');
	}
	for(i in data.rows) {
		str = '';		
		str = str + '<li data-icon="tag"><a href="#" onclick="openAnnouncementDetail(' + data.rows[i].newsId + ');">' +
					'<h3>' + data.rows[i].title + '</h3>' +
					'<p>' + data.rows[i].userName + '</p>' +
					'<p class="ui-li-aside"><strong>' + data.rows[i].addTimeString + '</strong></p>' +
					'</li>';
		var list = $(str);
		$("#announcementListView").append(list);
	}
	if(msg_pageNum < msg_maxpages) {
		$("#announcementListView").append('<li data-role="list-divider"><div data-role="content" align="center">' +
			'<a href="#" onclick="nextAnnouncementListPage();" data-role="button" data-corners="false" >下一页</a></div></li>');
	}
	$('#announcementListView').listview('refresh');  
	$.mobile.loading( "hide");
	console.log("Ajax data onSuccess!");
}

function preAnnouncementListPage() {
	if(msg_pageNum > 1) {
		msg_pageNum--;
		loadAnnouncementList();
	}
}

function nextAnnouncementListPage() {
	if(msg_pageNum < msg_maxpages) {
		msg_pageNum++;
		loadAnnouncementList();
	}
	console.log('next page ...');
}

/*
 * 加载销售单据审核列表失败，错误信息处理
 */
function onloadAnnouncementListError(data, status) {
	$.mobile.loading( "hide");
	console.log("onloadAnnouncementList Error:" + status);
}

function openAnnouncementDetail(newsId) {
	$.mobile.loading( "show", {
	            text: 'loading',
	            textVisible: false,
	            theme: $.mobile.loader.prototype.options.theme,
	            textonly: false,
	            html: ''
    		});
	
	console.log("http://" + server +":" + port + "/salesManager/news!show");
	$.ajax({  
		type: "POST",  
		url: "http://" + server +":" + port + "/salesManager/news!show",  
		cache: false,  
		data: 'news.newsId=' + newsId + '&requestFrom=mobile',  
		dataType:"jsonp",
		jsonpCallback:"callback",
		timeout: 20000,
		success: onOpenAnnouncementDetailSuccess,  
		error: onloadAnnouncementListError
	});
	
	
}

function onOpenAnnouncementDetailSuccess(data, status) {
	$.mobile.navigate("#announcementInfoPage");
	$.mobile.loading( "hide");
	
	$("#announcement_title").text(data.title);
	$("#announcement_content").html(data.content);
}

/*
 * 清空公告信息列表页面显示内容
 */
function clearTheListview2(listview) {
	var ListTab = listview.children('div');
	
	$(ListTab).each(function() {
		console.log('children:' + $(this).attr('html'));
		$(this).remove();
	});
	
	ListTab = listview.children('li');
	
	$(ListTab).each(function() {
		console.log('children:' + $(this).attr('html'));
		$(this).remove();
	});
}
