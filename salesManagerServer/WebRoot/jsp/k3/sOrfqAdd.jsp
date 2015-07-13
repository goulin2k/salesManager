<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sales" uri="/WEB-INF/sales-util.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base target="_self"/>
	<title>销售报价单编辑</title>
	<!-- Bootstrap core CSS -->
    <link href="skin/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="skin/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    
    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <link type="text/css" href="<%=basePath %>skin/Default/css/fisheye.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/bootstrap/css/jquery-ui-1.10.0.custom.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/bootstrap/css/jquery.ui.1.10.0.ie.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/Default/css/base.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/Default/css/global.css" rel="stylesheet"  />
    <link href="skin/bootstrap/css/bootstrap-dialog.min.css" rel="stylesheet">
    

	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-1.8.3.min.js"></script>	
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-ui-1.10.0.custom.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap-dialog.min.js"></script>
		
	<!-- easyUI js & css -->
	<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/common.js"></script>
	<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/plugins/jquery.parser.js"></script>
	<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/jquery.easyui.min.js"></script>
	
	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/bootstrap/easyui.css" /> 
</head>
<body class="ui-lv2bg">
<!-- 标准工具栏 -->
<nav class="navbar navbar-default crm-toolbar" role="navigation">
	<ul class="nav navbar-nav">
    	<li class="active"><a id="saveButton" href="javascript:submitOrfq();"><span class="glyphicon glyphicon-save"></span>&nbsp;保存</a></li>
    	<s:if test="quotation == null || quotation.quotationCode == null">
    		<li class="active"><a href="javascript:getProducts();"><span class="glyphicon glyphicon-plus"></span>&nbsp;添加物料</a></li>
    	</s:if>
    	<li class="active"><a href="javascript:this.close();"><span class="glyphicon glyphicon-arrow-left"></span>&nbsp;取消</a></li>
    </ul>
</nav>
<s:if test="orfq.fInterID == null">
	<div class="alert alert-danger">单据新增状态，未保存！</div>	
</s:if><s:else>
	<div class="alert alert-success">单据修改状态，单据号：未审核生成</div>
</s:else>

<form id="orfq" name="orfq" action="<%=basePath %>orfq!addSOrfq" class="form-horizontal" method="post" target="hiddenFrame">
	<div class="panel panel-default" id="searchResult" style="padding:2px;">
	<div class="panel-heading crm-table-title"><B>报价单基本信息</B></div>
	<input type="hidden" name="orfq.fInterID" id="fInterID" value="<s:property value="orfq.fInterID"/>" />
	
	<input type="hidden" id="basepath" value="<%=basePath %>" />
    <input type="hidden" id="orfqEntryLists" value="<s:property value="orfq.orfqEntryList.size"/>" />
	<table class="ui-table-one" cellspacing="2" cellpadding="0" >
	
	  <tr> 
	    <td  class="ui-table-title">购货单位：</td>
	    <td>
	    	<s:if test="quotation == null">
		    	<input type="hidden" name="orfq.fCustID" id="fCustID" value="<s:property value="orfq.fCustID"/>" />
		    	<input type="text" name="orfq.customerName" id="customerName" value="<s:property value="orfq.customerName"/>" 
		    		class="form-control " placeholder="选择客户" onClick="getCustomer();" required/></td>
		    </s:if><s:else>
		    	<input type="hidden" name="orfq.fCustID" id="fCustID" value="<s:property value="quotation.customerId"/>" />
			    <input type="hidden" name="orfq.quotationCode" id="quotationCode" value="<s:property value="quotation.quotationCode"/>" />
			    <input type="text" name="orfq.customerName" id="customerName" value="<s:property value="quotation.customerName"/>" 
			    	onClick="getCustomer();" class="form-control " placeholder="选择客户" required/></td>
		    </s:else>
	    <td class="ui-table-title">付款条件：</td>
	    <td>
	    	<s:if test="quotation == null">
		    	<s:select theme="simple" id="fPayCondition" name="orfq.fPayCondition" cssClass="form-control" 
		    		onchange="setPay(this)" listKey="fId" listValue="fName" headerKey="" headerValue="请选择付款条件" list="payConditionList" />
		    	<input name="orfq.payCondition" id="payCondition" type="hidden" />
		    </s:if><s:else>
		        <s:if test="orfq.fPayCondition>0">
			    	<s:select disabled="true" 
			    		theme="simple" id="fPayCondition" name="orfq.fPayCondition" cssClass="form-control" value="orfq.fPayCondition"
			    		onchange="setPay(this)" listKey="fId" listValue="fName" headerKey="" headerValue="请选择付款条件" list="payConditionList" />
		    	<input name="orfq.payCondition" id="payCondition" value="<s:property value="orfq.payCondition"/>"  type="hidden" />
		    	</s:if><s:else>
		    		<s:select disabled="false" 
			    		theme="simple" id="fPayCondition" name="orfq.fPayCondition" cssClass="form-control" value="orfq.fPayCondition"
			    		onchange="setPay(this)" listKey="fId" listValue="fName" headerKey="" headerValue="请选择付款条件" list="payConditionList" />
		    		<input name="orfq.payCondition" id="payCondition" value="<s:property value="orfq.payCondition"/>"  type="hidden" />
		    	</s:else>
		    	
		    </s:else>
	    </td>
	  </tr> 
	  <tr>
	    <td  class="ui-table-title">币别：</td>
	    <td>
	    	<s:select id="fCurrencyID" name="orfq.fCurrencyID" cssClass="form-control"	
	    		listKey="fCurrencyID" listValue="fNumber" list="currencyList" required="true"
	    		headerKey="" headerValue="请选择币别" onchange="getExchangeRate(this);" />
	    	<input name="orfq.currencyName" id="currencyName" type="hidden" />
	    </td>
	    <td  class="ui-table-title">汇率：</td>
	    <td>
	    	<input type="text" id="fExchangeRate" name="orfq.fExchangeRate" value="<s:property value="orfq.fExchangeRate"/>" disabled/></td>
	  </tr>
	  <tr>
	    <td  class="ui-table-title">开票方式：</td>
	    <td>
	    	<s:select theme="simple" id="invoiceTypeId" name="orfq.invoiceTypeId" onchange="setInvoiceType(this)" 
	    		listKey="typeId" listValue="name" list="invoiceTypeList" required="true" 
	    		headerKey="" headerValue="请选择开票方式" cssClass="form-control" /></td>
	    	<input name="orfq.invoiceTypeName" id="invoiceTypeName" type="hidden" />
	    <td  class="ui-table-title">运输方式：</td>
	    <td>
	    	<input name="orfq.transTypeName" id="transTypeName" type="hidden" />
	    	<s:select theme="simple" id="transTypeId" name="orfq.transTypeId" onchange="setTransType(this)" 
	    		listKey="typeId" listValue="name" list="transTypeList" required="true" 
	    		headerKey="" headerValue="请选择运输方式" cssClass="form-control"/>
	    </td>
	  </tr>
	  <tr>
	    <td  class="ui-table-title">部门：</td>
	    <td class="ui-table-input-r">
	    	<s:select theme="simple" id="fDeptID" name="orfq.fDeptID" onchange="setDepart(this)" 
	    		listKey="departId" listValue="name" headerKey="" headerValue="请选择部门" list="departList" cssClass="form-control"/>
	    	<input name="orfq.departName" id="departName" type="hidden" />
	    </td>
	    <td  class="ui-table-title">主管：</td>
	    <td>
	    	<s:select theme="simple" id="fMangerID" name="orfq.fMangerID" listKey="staffId" 
	    		onchange="setManger(this)" listValue="name" 
	    		headerKey="" headerValue="请选择主管" list="staffList" cssClass="form-control"/>
	    	<input name="orfq.managerName" id="managerName" type="hidden" />
	    </td>
	  </tr> 
	  <tr>
	    <td  class="ui-table-title">业务员：</td>
	    <td>
	    	<s:select theme="simple" id="fEmpID" name="orfq.fEmpID" listKey="staffId" 
	    		onchange="setEmployee(this)" listValue="name" headerKey="" headerValue="请选择业务员" 
	    		list="staffList" cssClass="form-control"/>
	    	<input name="orfq.employeeName" id="employeeName" type="hidden" />
	    </td>
	    <td  class="ui-table-title">制单人：</td>
	    <td>
	    	<s:property value="orfq.billUserName"/>
	    	<input name="orfq.billUserName" id="billUserName" type="hidden" value="${orfq.billUserName}" /></td>
	  </tr> 
	  
	  <tr>
	  <s:if test="quotation == null || quotation.quotationCode==''">
		    <td  class="ui-table-title">选择物料：</td>
		    <td>
		    	<a id="newProduct" class="btn btn-warning " onClick="getProducts();">选择物料</a></td>
	  </s:if><s:else>
	 		<td  class="ui-table-title">是否开票</td>
		    <td style="font-weight: bold;color: red;"><s:if test="quotation.issued==1">开票</s:if>
		    	<s:else>不开票</s:else></td>
	  </s:else>
	 </tr> 
	  
	</table>

	</div>
	
	<div class="panel panel-default" id="searchResult" style="padding:2px;">
	     <div class="panel-heading crm-table-title"><B>物料明细列表</B></div>
	    <table id="productTable" class="table table-hover" cellspacing="2" cellpadding="0">
	      <thead>
	        <th >物料代码</th>
	        <th>
		        <s:if test="quotation != null && quotation.quotationCode != null">
		        	询价采购回复单价
		        </s:if><s:else>
		        	计量单位
		        </s:else>
	        </th>
	        <th>数量</th>
	        <th>销售单价</th>
	        <th>含税单价</th>
	        <th>金额</th>
	        <th>税率(%)</th> 
	        <th>折扣率(%)</th>
	        <th>折扣额</th>
	        <th>含税折扣后单价</th> 
	        <th>税额</th>
	        <th>价税合计</th> 
	        <th>货期</th> 
	        <th>操作</th> 
	      </thead>
	      <s:if test="quotation == null">
	      <s:iterator value="orfq.orfqEntryList" status="dl">
		      <tr> 
		        <td id='demo-tip-darkgray'  title="<s:property value="iCItemName"/><s:property value="fModel"/>">
		        	<input type='hidden' name='orfqEntrys[<s:property value="#dl.index"/>].fItemID' 
		        		value='<s:property value="fItemID"/>'/>
		        	<input type='hidden' name='orfqEntrys[<s:property value="#dl.index"/>].iCNumber' 
		        		value='<s:property value="iCNumber"/>'/>
		        	<input type='hidden' name='orfqEntrys[<s:property value="#dl.index"/>].iCItemName' 
		        		value='<s:property value="iCItemName"/>'/>
		        	<input type='hidden' name='orfqEntrys[<s:property value="#dl.index"/>].fModel' 
		        		value='<s:property value="fModel"/>'/>
		        	<s:property value="iCNumber"/></td> 
		        <td>
		        	<input type='hidden' name='orfqEntrys[<s:property value="#dl.index"/>].fUnitID' 
		        		value='<s:property value="fUnitID"/>'/>
			        <input type='hidden' name='orfqEntrys[<s:property value="#dl.index"/>].unitName' 
			        	value='<s:property value="unitName"/>'/>
			        <s:property value="unitName"/></td>
		        <td>
		        	<input style='width:100%' type='text' class='easyui-numberbox ' precision='0' min='0' max='100000' required='true' 
		        		name='orfqEntrys[<s:property value="#dl.index"/>].fAuxQty' value="<s:property value="fAuxQty"/>" 
		        		onchange='changeAuxQty($(this).parent().parent())'/>
		        </td>
		        
		        <td>
		        	<input style='width:100%' type='text' class='easyui-numberbox ' precision='2' min='0' max='10000000' required='true' 
		        		name='orfqEntrys[<s:property value="#dl.index"/>].fAuxPrice' value="<s:property value="fAuxPrice"/>" 
		        		onchange='changeAuxPrice($(this).parent().parent())'/>
		        </td>
		        <td>
		        	<input style='width:100%' type='text' name='orfqEntrys[<s:property value="#dl.index"/>].fAuxPriceIncludeTax' 
		        		value="<s:property value="fAuxPriceIncludeTax"/>" onchange="changeAuxPriceIncludeTax($(this).parent().parent())"/></td> 
		        <td>
		        	<input style='width:100%' type='text' name='orfqEntrys[<s:property value="#dl.index"/>].fAmount' 
		        		value="<s:property value="fAmount"/>" disabled/></td>
		        <td>
		        	<input style='width:100%' type='text' class='easyui-numberbox ' precision='2' min='0' max='100' 
		        		name='orfqEntrys[<s:property value="#dl.index"/>].fCess' required='true'
		        		onchange="changeCess($(this).parent().parent())" value="<s:property value="fCess"/>"/></td> 
		        <td>
		        	<input style='width:100%' type='text' class='easyui-numberbox ' precision='2' min='0' max='100' 
			        	name='orfqEntrys[<s:property value="#dl.index"/>].FDescount' value="<s:property value="FDescount"/>" 
			        	onchange='changeDescount($(this).parent().parent())'/></td>
		        <td>
		        	<input style='width:100%' type='text' name='orfqEntrys[<s:property value="#dl.index"/>].fDiscountAmt' 
		        		value="<s:property value="fDiscountAmt"/>" disabled/></td>
		        <td>
		        	<input style='width:100%' type='text' name='orfqEntrys[<s:property value="#dl.index"/>].fAuxTaxPriceDiscount' 
		        		value="<s:property value="fAuxTaxPriceDiscount"/>" disabled/></td>
		        <td>
		        	<input style='width:100%' type='text' name='orfqEntrys[<s:property value="#dl.index"/>].fTaxAmount' 
		        		value="<s:property value="fTaxAmount"/>" disabled/></td>
		        <td>
		        	<input style='width:100%' type='text' name='orfqEntrys[<s:property value="#dl.index"/>].fAmountIncludeTax' 
		        		value="<s:property value="fAmountIncludeTax"/>" disabled/></td>
		        <td>
		        	<input style='width:100%' type='text' class='easyui-validatebox' validType='maxLength["备注",20]' 
		        		name='orfqEntrys[<s:property value="#dl.index"/>].fText' value="<s:property value="fText"/>"/></td> 
		        <td><a href='#' onclick='$(this).parent().parent().remove();'>删除</a></td> 
		      </tr>
	      </s:iterator>
	      </s:if>
	      <s:else>
	      <s:iterator value="quotation.quotationProductList" status="dl">
		      <tr> 
		        <td id='demo-tip-darkgray' title="<s:property value="productName"/><s:property value="productModel"/>">
		        	<input type='hidden' name='orfqEntrys[<s:property value="#dl.index"/>].fItemID' value='<s:property value="itemId"/>'/>
		        	<input type='hidden' name='orfqEntrys[<s:property value="#dl.index"/>].iCNumber' value='<s:property value="productCode"/>'/>
		        	<input type='hidden' name='orfqEntrys[<s:property value="#dl.index"/>].iCItemName' value='<s:property value="productName"/>'/>
		        	<input type='hidden' name='orfqEntrys[<s:property value="#dl.index"/>].fModel' value='<s:property value="productModel"/>'/>
		        	<s:property value="productCode"/></td> 
		        <td><input type='hidden' name='orfqEntrys[<s:property value="#dl.index"/>].fUnitID' value='<s:property value="unitId"/>'/>
			        <input type='hidden' name='orfqEntrys[<s:property value="#dl.index"/>].unitName' value='<s:property value="measureUnitName"/>'/>
			        
			        <s:if test="quotation != null && quotation.quotationCode != null">
			        	￥<s:property value="orfqEntry.fAuxPrice"/>/
			        </s:if>
			        <s:property value="measureUnitName"/>
			    </td>
		        <td><input style='width:100%' type='text' class='easyui-numberbox' precision='0' min='0' max='100000' required='true' name='orfqEntrys[<s:property value="#dl.index"/>].fAuxQty' value="<s:property value="num"/>" onchange='changeAuxQty($(this).parent().parent())'/>
		        	</td>
		        <td><input type='text' class='easyui-numberbox' precision='2' min='0' max='10000000' 
		        		required='true' name='orfqEntrys[<s:property value="#dl.index"/>].fAuxPrice' 
		        		value='<s:property value="orfqEntry.fAuxPrice"/>' onchange='changeAuxPrice($(this).parent().parent())'/>
		        	</td>
		        <td><input style='width:100%' type='text' name='orfqEntrys[<s:property value="#dl.index"/>].fAuxPriceIncludeTax' 
		        	value="<s:property value="orfqEntry.fAuxPriceIncludeTax"/>" onchange="changeAuxPriceIncludeTax($(this).parent().parent())"/></td> 
		        <td><input style='width:100%' type='text' name='orfqEntrys[<s:property value="#dl.index"/>].fAmount' value="<s:property value="orfqEntry.fAmount"/>" disabled/></td>
		        <td><input style='width:100%' type='text' class='easyui-numberbox w30' precision='2' min='0' max='100' name='orfqEntrys[<s:property value="#dl.index"/>].fCess' value='17' onchange='changeCess($(this).parent().parent())'/></td> 
		        <td><input style='width:100%' type='text' class='easyui-numberbox w30' precision='2' min='0' max='100' name='orfqEntrys[<s:property value="#dl.index"/>].FDescount' value="<s:property value="FDescount"/>" onchange='changeDescount($(this).parent().parent())'/></td>
		        <td><input style='width:100%' type='text' name='orfqEntrys[<s:property value="#dl.index"/>].fDiscountAmt' value="<s:property value="fDiscountAmt"/>" disabled/></td>
		        <td><input style='width:100%' type='text' name='orfqEntrys[<s:property value="#dl.index"/>].fAuxTaxPriceDiscount' value="<s:property value="fAuxTaxPriceDiscount"/>" disabled/></td>
		        <td><input type='text' name='orfqEntrys[<s:property value="#dl.index"/>].fTaxAmount' value="<s:property value="orfqEntry.fTaxAmount"/>" disabled/></td>
		        <td><input style='width:100%' type='text' name='orfqEntrys[<s:property value="#dl.index"/>].fAmountIncludeTax' value="<s:property value="orfqEntry.fAmountIncludeTax"/>" disabled/></td>
		        <td><input style='width:100%' type='text' class='easyui-validatebox' validType='maxLength["备注",20]' name='orfqEntrys[<s:property value="#dl.index"/>].fText' value="<s:property value="futures"/>"/></td> 
		        <td><a href='#' onclick='$(this).parent().parent().remove();'>删除</a></td> 
		      </tr>
	      </s:iterator>
	      </s:else>
	    </table>
	    
		<div class="panel-footer" align="center" id="footer"><p>单据已选择物料 条.</p></div>
	</div>
</form> 
<iframe name="hiddenFrame" style="display: none;"></iframe>
</body>

<script type="text/javascript" language="javascript" src="<%=basePath %>script/jsp/sorfqAdd.js"></script>
</html>