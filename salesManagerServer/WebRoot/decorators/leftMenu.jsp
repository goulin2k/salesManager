<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator"%>
<%@ taglib uri="/WEB-INF/sitemesh-page.tld" prefix="page"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<ul class="nav nav-pills nav-stacked ui-subnav-three">
	<div class="top">
		<p><img  src="skin/Default/images/ui-sublists-news.png" />功能导航</p>
	</div>
	<s:iterator id="mn" value="#request.moduleList" status="status">
		<li <s:if test="#mn.moduleUrl == #session.action">
				class="active"
			</s:if>
			>
			<a href="<s:property value="#mn.moduleUrl" />">
			<img src="skin/Default/images/ui-subnav-2.png" />
			&nbsp;<s:property value="#mn.moduleNameChinese" /></a>
			
		</li>
	</s:iterator>
</ul>
<div  class="hr-one " /></div>
<ul class="ui-subnav-two">
	<div class="top">
		<p><img  src="skin/Default/images/ui-sublists-news.png" />销售指标</p>
	</div>
	
	<li>
		<a target="_blank" href="report!orderWeekly"> 本季业绩:${quarterOrderAmount}
			<div class="progress">
			  <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" 
			  		aria-valuemin="0" aria-valuemax="100" style="width: ${quarterOrderPercent};">
			    <span>${quarterOrderPercent}</span>
			  </div>
			</div>
		</a>
	</li>
	<li>
		<a target="_blank" href="report!orderMonthly"> 本年业绩:${yearOrderAmount}
			<div class="progress"> 
			  <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="60" 
			  		aria-valuemin="0" aria-valuemax="100" style="width: ${yearOrderPercent};">
			    <span>${yearOrderPercent} </span>
			  </div>
			</div>
		</a>
	</li>
	<li></li>
	<div class="top">
		<p><img  src="skin/Default/images/ui-sublists-news.png" />收款指标</p>
	</div>
	
	<li>
		<a href="#"> 本季收款率:${quarterRecievePercent}
			<div class="progress">
			  <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" 
			  		aria-valuemin="0" aria-valuemax="100" 
			  				style="width:${quarterRecievePercentlen}">
			    <span>${quarterRecievePercent}</span>
			  </div>
			</div>
		</a>
	</li>
	<li>
		<a href="#"> 本季金额:${quarterRecieveAmount}
		</a>
	</li>
	
	<div class="bottom"></div>
</ul>

