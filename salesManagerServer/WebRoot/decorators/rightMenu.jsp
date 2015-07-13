<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator"%>
<%@ taglib uri="/WEB-INF/sitemesh-page.tld" prefix="page"%>
<script type="text/javascript"> 
function getNews(){
$.ajax({
    type: "POST",
    url: "news!jsonNews",  
    dataType: "json", 
    success: function (data) {  
       	var content = "";
       	$.each(data, function(i, value) {   
       	    content += "<li><a href='news!showNews?news.newsId=" + value.newsId + "'><p>" + value.title.substring(0,10)+' ...' + "</p></a></li>";  
            $("#news").html(content);
        });   
    }
});
}

function getInfos(){
$.ajax({
    type: "POST",
    url: "info!jsonInfos",  
    dataType: "json", 
    success: function (data) {  
       	var content = "";
       	$.each(data, function(j, value) {   
       	    //content += "<li><a href='info!queryInfo?infoId=" + value.informationId + "'><p>" + value.title + "</p></a></li>";  
       	    content +="<li><a href='javascript:openModal(" + value.informationId +")'> <h3>"
       	    		+value.contentCut+"</h3><span>"
       	    		+value.timeShow+"</span><div class='clear'></div></a></li>";
            $("#infos").html(content);
        });   
    }
});
}

function openModal(infoId) {
		$.ajax({
			 type: "POST",
			 url: "info!getInfoJson?infoId=" + infoId,
			 dataType:'json',
			 cache: false,
			 async: true,
			 error:function(XMLHttpRequest, textStatus, errorThrown){
			 	alert(textStatus + '-' + errorThrown);
			 },
			 success: function(data){
				 $("#info_title").text(data.title);
				 $("#info_status").text(data.statusName);
				 $("#info_addTime").text(data.timeShow);
				 $("#info_type").text(data.typeName);
				 $("#info_revUser").text(data.sendUserName);
				 $("#info_sendUser").text(data.userName);
				 if(data.imageUrl.length==0)
					 $("#info_url").text('无');
				 else
					 $("#info_url").html("<a target='_blank' href='" + data.imageUrl + "'>相关信息</a>");
				 
			 }
	    });
		
		$('#modal').modal({
				keyboard: true
		});
	}
	
 getNews()
 getInfos()
</script>

	<!--======================消息中心的样式文件===============================-->
               <ul class="ui-subnav-three">
           		    <div class="top"><p><img  src="skin/Default/images/ui-sublists-news.png" />消息中心</p></div>
           		    <div id="infos">
           		</div>
 
             	   <div class="bottom"></div>
        	  </ul>
<!--======================消息中心的样式文件结束===============================-->
<div  class="hr-one " /></div>
                  <ul class="ui-subnav-four">
               <div class="top">
       		        <p><img  src="skin/Default/images/ui-sublists-bulletin.png">新闻公告</p>
               </div>
           		<div id="news">
           		</div>
               <div class="bottom"></div>
        	  </ul> 
        	  
        	    <!-- Modal -->
		<div class="modal fade bs-example-modal-sm" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-sm">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">
		        <span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel"><span class="glyphicon glyphicon-info-sign"></span>&ensp;消息查看</h4>
		      </div>
		      <div class="modal-body">
		        <%@ include file="infoShow.jsp"%>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		      </div>
		    </div>
		  </div>
		</div>    
              