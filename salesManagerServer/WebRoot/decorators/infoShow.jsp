<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<table class="ui-table-one" cellspacing="2" cellpadding="0">
	<tr>
		<td class="ui-table-title">消息标题：</td>
		<td class="ui-table-input-r"><lable id="info_title"></lable></td>
		<td class="ui-table-title">消息状态：</td>
		<td class="ui-table-input-r"><lable id="info_status"></lable></td>
	</tr>
	<tr>
		<td class="ui-table-title">消息时间：</td>
		<td class="ui-table-input-r"><lable id="info_addTime"></lable></td>
		<td class="ui-table-title">消息类型：</td>
		<td class="ui-table-input-r"><lable id="info_type"></lable></td>
	</tr>
	<tr>
		<td class="ui-table-title">消息接收人：</td>
		<td class="ui-table-input-r"><lable id="info_revUser"></lable></td>
		<td class="ui-table-title">消息发送人：</td>
		<td class="ui-table-input-r"><lable id="info_sendUser"></lable></td>
	</tr>
	<tr>
		<td class="ui-table-title">相关信息：</td>
		<td class="ui-table-input-r" cols="3"><lable id="info_url"></lable></td>

	</tr>
	<tr>
		<td class="ui-table-title">消息详情：</td>
		<td class="ui-table-input-r" cols="3"><lable id="info_url"></lable></td>
	</tr>

</table>