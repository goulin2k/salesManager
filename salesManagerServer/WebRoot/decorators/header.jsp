<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator"%>
<%@ taglib uri="/WEB-INF/sitemesh-page.tld" prefix="page"%>

<div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">凯扬工业</a>
        </div>
        
        <div class="navbar-collapse collapse" >
        	
			<ul class="nav navbar-nav navbar-right">
		      <li style="color:white;"><a href="#"> 欢迎使用销售管理系统！</a></li>
		      <li><a href="workbench!workbenchIndex" title="工作台"><span class="glyphicon glyphicon-th-large"></span>&nbsp;</a><li>
		      <li><a target='_blank' href="workCalendar!view" title="我的日程"><span class="glyphicon glyphicon-plane"></span>&nbsp;</a><li>
		      <li><a target='_blank'  href="info!infoList" title="消息列表">
	              	<span class="glyphicon glyphicon-volume-up"></span> <span id="messgeCount" class="badge messgeCount">${infoCount}</span></a>
					<input type="hidden" id="infoCount" name="infoCount" value="<s:property value="#session.infoCount"/>" />
		      	
		      </li>
		      <li class="dropdown">
		        <a href="#" class="dropdown-toggle" data-toggle="dropdown">${USER_INFO.userName}<b class="caret"></b></a>
		        <ul class="dropdown-menu">
		          <li><a target='_blank' href="process!processList"><span class="glyphicon glyphicon-random">&nbsp;我的流程</span></a></li>
		          <li><a target='_blank' href="workbench!setWorkbenchInit"><span class="glyphicon glyphicon-cog">&nbsp;工作台设置</span></a></li>
		          <li><a target='_blank' href="sUser!userSetInit"><span class="glyphicon glyphicon-user">&nbsp;个人信息</span></a></li>
		          <li class="divider"></li>
		          <li><a href="login!userLogout"><span class="glyphicon glyphicon-log-out">&nbsp;注销</span></a></li>
		        </ul>
		      </li>
		    </ul>
		  
        </div><!--/.navbar-collapse -->
</div>

<script type="text/javascript">
	function getMsgCount() {
		$.ajax({
			 type: "POST",
			 url: "info!getMessageCount",
			 dataType:'text',
			 cache: false,
			 async: true,
			 success: function(data){
				 //alert(data);
				 $("#messgeCount").html(data);   
			 }
	    });
	}
	setInterval("getMsgCount()",120*1000);
</script>
