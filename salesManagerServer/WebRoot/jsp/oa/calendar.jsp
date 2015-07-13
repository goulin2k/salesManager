<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>工作日历</title>

<link href='<%=basePath %>script/fullcalendar/fullcalendar.css' rel='stylesheet' />
<link href='<%=basePath %>script/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
<script src='<%=basePath %>script/jquery/jquery-1.9.1.min.js'></script>
<script src='<%=basePath %>script/jquery/jquery-ui-1.10.2.custom.min.js'></script>
<script src='<%=basePath %>script/fullcalendar/fullcalendar.min.js'></script>

<script type="text/javascript">
	$(document).ready(function() {
	
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,basicWeek,basicDay'
			},
			editable: true,
			monthNames:['一月','二月', '三月', '四月', '五月', '六月', '七月','八月', '九月', '十月', '十一月', '十二月'],
			dayNames:['星期日', '星期一', '星期二', '星期三','星期四', '星期五', '星期六'],  //换成中文星期
			buttonText:{                    //按钮对应的文本  
		        prev: 'prev',         //不建议改这个值，因为它本身是含[去年、上一周、前天]三个意思，你就让它默认  
		        next: 'next',         //同上  
		        today:    '今天',  
		        month:    '月',  
		        basicWeek:     '周',  
		        basicDay:      '日'  
		    },  
		    dayNamesShort:['周日', '周一', '周二', '周三','周四', '周五', '周六'],  //短格式的星期  
		    titleFormat:{                   //格式化标题  
		        month: 'MMMM yyyy',                             // 如：September 2009  
		        week: "MMMM d[yyyy]{'&#8212;'[ MMM] d yyyy}",    // 如：Sep 7 - 13 2009  
		        day: 'dddd, MMMM d, yyyy'                        // 如：Tuesday, Sep 8, 2009  
		    },  
		     eventRender:function(calEvent, element, view){ //日程事件渲染之前触发,每个事件触发一次  
		        //alert('事件: ' + calEvent.className+'View: ' + view.name);
		    },
			events: "<%=basePath %>workCalendar!getProjectsList?startTime=" + y + "-01-01&endTime=" + y + "-12-31"
		});
		
	});
			
</script>
<style>
	.completedEvent {
		font-weight: bolder; color: black; background: green
	}
	.delayEvent {
		font-weight: bolder; color: white; background: red
	}
	.doingEvent {
		font-weight: bolder; color: black; background: yellow
	}
</style>

</head>

<body class="ui-lv2bg">
	<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/1.png" />工作日历</p></div>
    <div class="ui-content-subnav">
    	<ul>
    		<li><a href="project!editNew"><img src="skin/Default/images/ui-content-ico/add.jpg" /><p>新增计划</p></a></li>
        </ul>
    </div>
    <div class="clear"></div>
    
	 <div class="ui-table-style1">
		<div id='calendar' style='width: 98%; margin: 0 auto;'></div>     
    </div>
    <div class="clear"></div>
	
	
</body>
</html>