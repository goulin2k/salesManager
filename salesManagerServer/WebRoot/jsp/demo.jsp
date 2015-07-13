<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>sitemesh使用示例</title>
		
	</head>
	<body class="ui-lv2bg">
		
			<div class="ui-content-title">
				<p>
					<img src="<%=basePath %>skin/Default/images/ui-content-ico/1.png" />
					标题名称
				</p>
			</div>
			<!--======内容区域子导航======-->
			<div class="ui-content-subnav">
				<ul>
					<li>
						<a href="#"><img
								src="<%=basePath %>skin/Default/images/ui-content-ico/Summary.jpg" />
							<p>
								少数图标集
							</p>
						</a>
					</li>
					<li>
						<a href="#"><img
								src="<%=basePath %>skin/Default/images/ui-content-ico/verify.jpg" />
							<p>
								少数图标集
							</p>
						</a>
					</li>
					<li>
						<a href="#"><img
								src="<%=basePath %>skin/Default/images/ui-content-ico/add.jpg" />
							<p>
								少数图标集
							</p>
						</a>
					</li>
					<li>
						<a href="#"><img
								src="<%=basePath %>skin/Default/images/ui-content-ico/add.jpg" />
							<p>
								少数图标集
							</p>
						</a>
					</li>
					<li>
						<a href="#"><img
								src="<%=basePath %>skin/Default/images/ui-content-ico/add2.jpg" />
							<p>
								少数图标集
							</p>
						</a>
					</li>
					<li>
						<a href="#"><img
								src="<%=basePath %>skin/Default/images/ui-content-ico/set.jpg" />
							<p>
								少数图标集
							</p>
						</a>
					</li>
				</ul>
			</div>
			<!--===== 分割线======-->
			<hr class="hr-two" />




			<!--======搜索======-->
			<div class="ui-com-search">
				<div class="ui-com-search-title ">
					搜索名称：
					<input class="ui-com-search-input" name="" type="text" />
					类型：
					<select class="ui-com-search-options" name="">
						<option>
							类型A
						</option>
						<option>
							类型B
						</option>
					</select>
					<br />
					时间范围：
					<input class="ui-com-search-year" name="" type="text" />
					至
					<input class="ui-com-search-year" name="" type="text" />
					<div class="ui-combottom fr">
						<a href="#"> 查 询</a>
					</div>
				</div>

			</div>
			<div class="clear"></div>
			<div class="ui-table-style1">
				<table cellspacing="0" cellpadding="0">
					<tr class="ui-table-style1-tr1">
						<td class="td2">
							类型1
						</td>
						<td class="td3">
							类型2
						</td>
						<td class="td4">
							类型3
						</td>
						<td class="td5">
							时间
						</td>
						<td>
							操作
						</td>
					</tr>
					<!--      =================表格循环===============-->
					<tr class="ui-table-style1-tr2">
						<td>
							这是一个名称
						</td>
						<td>
							比如是备注的信息
						</td>
						<td>
							文字信息
						</td>
						<td>
							2012/12/31 12:43:57
						</td>
						<td>
							<a href="#">查看</a>
						</td>
					</tr>


					<tr class="ui-table-style1-tr2">
						<td>
							这是一个名称
						</td>
						<td>
							比如是备注的信息
						</td>
						<td>
							文字信息
						</td>
						<td>
							2012/12/31 12:43:57
						</td>
						<td>
							<a href="#">查看</a>
						</td>
					</tr>
					<tr class="ui-table-style1-tr2">
						<td>
							这是一个名称
						</td>
						<td>
							比如是备注的信息
						</td>
						<td>
							文字信息
						</td>
						<td>
							2012/12/31 12:43:57
						</td>
						<td>
							<a href="#">查看</a>
						</td>
					</tr>
					<tr class="ui-table-style1-tr2">
						<td>
							这是一个名称
						</td>
						<td>
							比如是备注的信息
						</td>
						<td>
							文字信息
						</td>
						<td>
							2012/12/31 12:43:57
						</td>
						<td>
							<a href="#">查看</a>
						</td>
					</tr>
					<tr class="ui-table-style1-tr2">
						<td>
							这是一个名称
						</td>
						<td>
							比如是备注的信息
						</td>
						<td>
							文字信息
						</td>
						<td>
							2012/12/31 12:43:57
						</td>
						<td>
							<a href="#">查看</a>
						</td>
					</tr>
					<tr class="ui-table-style1-tr2">
						<td>
							这是一个名称
						</td>
						<td>
							比如是备注的信息
						</td>
						<td>
							文字信息
						</td>
						<td>
							2012/12/31 12:43:57
						</td>
						<td>
							<a href="#">查看</a>
						</td>
					</tr>
					<tr class="ui-table-style1-tr2">
						<td>
							这是一个名称
						</td>
						<td>
							比如是备注的信息
						</td>
						<td>
							文字信息
						</td>
						<td>
							2012/12/31 12:43:57
						</td>
						<td>
							<a href="#">查看</a>
						</td>
					</tr>
					<tr class="ui-table-style1-tr2">
						<td>
							这是一个名称
						</td>
						<td>
							比如是备注的信息
						</td>
						<td>
							文字信息
						</td>
						<td>
							2012/12/31 12:43:57
						</td>
						<td>
							<a href="#">查看</a>
						</td>
					</tr>
					<tr class="ui-table-style1-tr2">
						<td>
							这是一个名称
						</td>
						<td>
							比如是备注的信息
						</td>
						<td>
							文字信息
						</td>
						<td>
							2012/12/31 12:43:57
						</td>
						<td>
							<a href="#">查看</a>
						</td>
					</tr>
					<tr class="ui-table-style1-tr2">
						<td>
							这是一个名称
						</td>
						<td>
							比如是备注的信息
						</td>
						<td>
							文字信息
						</td>
						<td>
							2012/12/31 12:43:57
						</td>
						<td>
							<a href="#">查看</a>
						</td>
					</tr>
					<tr class="ui-table-style1-tr2">
						<td>
							这是一个名称
						</td>
						<td>
							比如是备注的信息
						</td>
						<td>
							文字信息
						</td>
						<td>
							2012/12/31 12:43:57
						</td>
						<td>
							<a href="#">查看</a>
						</td>
					</tr>
					<tr class="ui-table-style1-tr2">
						<td>
							这是一个名称
						</td>
						<td>
							比如是备注的信息
						</td>
						<td>
							文字信息
						</td>
						<td>
							2012/12/31 12:43:57
						</td>
						<td>
							<a href="#">查看</a>
						</td>
					</tr>
					<tr class="ui-table-style1-tr2">
						<td>
							这是一个名称
						</td>
						<td>
							比如是备注的信息
						</td>
						<td>
							文字信息
						</td>
						<td>
							2012/12/31 12:43:57
						</td>
						<td>
							<a href="#">查看</a>
						</td>
					</tr>
					<tr class="ui-table-style1-tr2">
						<td>
							这是一个名称
						</td>
						<td>
							比如是备注的信息
						</td>
						<td>
							文字信息
						</td>
						<td>
							2012/12/31 12:43:57
						</td>
						<td>
							<a href="#">查看</a>
						</td>
					</tr>
					<tr class="ui-table-style1-tr2">
						<td>
							这是一个名称
						</td>
						<td>
							比如是备注的信息
						</td>
						<td>
							文字信息
						</td>
						<td>
							2012/12/31 12:43:57
						</td>
						<td>
							<a href="#">查看</a>
						</td>
					</tr>
					<!--      =================表格循环over===============-->
				</table>
			</div>
			<!--      =================页=、==============-->
			<div class="ui-pagelist">
				<ul>
					<li>
						<a href="#">首页</a>
					</li>
					<li>
						<a href="#">上一页</a>
					</li>
					<li class="ui-pagelist-current">
						<a href="#">1</a>
					</li>
					<li>
						<a href="#">2</a>
					</li>
					<li>
						<a href="#">3</a>
					</li>
					<p>
						...
					</p>
					<li>
						<a href="#">下一页</a>
					</li>
					<li>
						<a href="#">末页</a>
					</li>
				</ul>
				<p>
					共5页
				</p>
			</div>
			<div class="clear"></div>
			<!--      =================大子信息内容==============-->
			<div class="ui-content-SlaveInformation-big ml15">
				<div class="ui-content-SlaveInformation-title">
					<p>
						子功能信息内容-大
					</p>
				</div>
				<!--======内容区域子导航======-->
				<div class="ui-content-subnav">
					<ul>
						<li>
							<a href="#"><img
									src="<%=basePath %>skin/Default/images/ui-content-ico/Summary.jpg" />
								<p>
									少数图标集
								</p>
							</a>
						</li>
						<li>
							<a href="#"><img
									src="<%=basePath %>skin/Default/images/ui-content-ico/verify.jpg" />
								<p>
									少数图标集
								</p>
							</a>
						</li>
						<li>
							<a href="#"><img
									src="<%=basePath %>skin/Default/images/ui-content-ico/add.jpg" />
								<p>
									少数图标集
								</p>
							</a>
						</li>
						<li>
							<a href="#"><img
									src="<%=basePath %>skin/Default/images/ui-content-ico/add.jpg" />
								<p>
									少数图标集
								</p>
							</a>
						</li>
						<li>
							<a href="#"><img
									src="<%=basePath %>skin/Default/images/ui-content-ico/add2.jpg" />
								<p>
									少数图标集
								</p>
							</a>
						</li>
						<li>
							<a href="#"><img
									src="<%=basePath %>skin/Default/images/ui-content-ico/set.jpg" />
								<p>
									少数图标集
								</p>
							</a>
						</li>
					</ul>
				</div>
			</div>

			<div class="ui-content-SlaveInformation-small ml15 fl">
				<div class="ui-content-SlaveInformation-title">
					<p>
						子功能信息内容-小
					</p>
				</div>
				<div class="ui-content-subnav">
					<ul>
						<li>
							<a href="#"><img
									src="<%=basePath %>skin/Default/images/ui-content-ico/Summary.jpg" />
								<p>
									少数图标集
								</p>
							</a>
						</li>
						<li>
							<a href="#"><img
									src="<%=basePath %>skin/Default/images/ui-content-ico/verify.jpg" />
								<p>
									少数图标集
								</p>
							</a>
						</li>
						<li>
							<a href="#"><img
									src="<%=basePath %>skin/Default/images/ui-content-ico/add.jpg" />
								<p>
									少数图标集
								</p>
							</a>
						</li>
						<li>
							<a href="#"><img
									src="<%=basePath %>skin/Default/images/ui-content-ico/add.jpg" />
								<p>
									少数图标集
								</p>
							</a>
						</li>
						<li>
							<a href="#"><img
									src="<%=basePath %>skin/Default/images/ui-content-ico/add2.jpg" />
								<p>
									少数图标集
								</p>
							</a>
						</li>
						<li>
							<a href="#"><img
									src="<%=basePath %>skin/Default/images/ui-content-ico/set.jpg" />
								<p>
									少数图标集
								</p>
							</a>
						</li>
					</ul>
				</div>
			</div>

			<div class="ui-content-SlaveInformation-small ml15 fl">
				<div class="ui-content-SlaveInformation-title">
					<p>
						子功能信息内容-小
					</p>
				</div>
				<div class="ui-content-subnav">
					<ul>
						<li>
							<a href="#"><img
									src="<%=basePath %>skin/Default/images/ui-content-ico/Summary.jpg" />
								<p>
									少数图标集
								</p>
							</a>
						</li>
						<li>
							<a href="#"><img
									src="<%=basePath %>skin/Default/images/ui-content-ico/verify.jpg" />
								<p>
									少数图标集
								</p>
							</a>
						</li>
						<li>
							<a href="#"><img
									src="<%=basePath %>skin/Default/images/ui-content-ico/add.jpg" />
								<p>
									少数图标集
								</p>
							</a>
						</li>
					</ul>
				</div>

			</div>

		
		
	</body>
</html>