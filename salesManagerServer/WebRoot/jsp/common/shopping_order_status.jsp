<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
   
</head>
<body>
<div class="row shop-tracking-status">
    
    <div class="col-md-12" style="margin-bottom:2px;">
        <div class="well">
            <div class="order-status">
                <div class="order-status-timeline">
                    <!-- class names: c0 c1 c2 c3 and c4 -->
                    <div class="order-status-timeline-completion c<s:property value="orderBillStatus"/>"></div>
                </div>
                
                <a href="order!show?order.fInterID=<s:property value="order.fInterID"/>">
					<div class="image-order-status image-order-status-order active img-circle">
	                    <span class="status">销售订单<s:property value="order.fDate"/></span>
	                    <div class="icon"></div>
	                </div>
                </a>
                
				<s:iterator value="saleBillList" status="dl">
					<s:if test="relationType==1">
						<s:if test="billIdStr!=null">
							<a href="orfq!showAll?billIdStr=<s:property value="billIdStr"/>">
							<div class="image-order-status image-order-status-orfq active img-circle">
			                    <span class="status">销售报价</span>
			                    <div class="icon"></div>
			                </div></a>
						</s:if><s:else>
							<div class="image-order-status image-order-status-orfq-unactive active img-circle">
			                    <span class="status">销售报价</span>
			                    <div class="icon"></div>
			                </div>
						</s:else>
					</s:if>
					<s:if test="relationType==2">
						<s:if test="billIdStr!=null">
							<a href="request!showAll?billIdStr=<s:property value="billIdStr"/>">
							<div class="image-order-status image-order-status-porequest active img-circle">
				                    <span class="status">采购申请</span>
				                    <div class="icon"></div>
			                </div></a>
						</s:if><s:else>
							<div class="image-order-status image-order-status-porequest-unactive active img-circle">
			                    <span class="status">采购申请</span>
			                    <div class="icon"></div>
			                </div>
						</s:else>
					</s:if>
					<s:if test="relationType==11">
						<s:if test="billIdStr!=null">
							 <a href="poorder!showAll?billIdStr=<s:property value="billIdStr"/>">
							 <div class="image-order-status image-order-status-poorder active img-circle">
			                    <span class="status">采购订单</span>
			                    <div class="icon"></div>
			                </div></a>
						</s:if><s:else>
							 <div class="image-order-status image-order-status-poorder-unactive active img-circle">
			                    <span class="status">采购订单</span>
			                    <div class="icon"></div>
			                </div>
						</s:else>
					</s:if>
					<s:if test="relationType==3">
						<s:if test="billIdStr!=null">
							 <a href="stockBill!showAll?billIdStr=<s:property value="billIdStr"/>">
							 <div class="image-order-status image-order-status-postockin active img-circle">
			                    <span class="status">采购入库</span>
			                    <div class="icon"></div>
			                </div></a>
						</s:if><s:else>
							 <div class="image-order-status image-order-status-postockin-unactive active img-circle">
			                    <span class="status">采购入库</span>
			                    <div class="icon"></div>
			                </div>
						</s:else>
					</s:if>
					<s:if test="relationType==4">
						<s:if test="billIdStr!=null">
							 <a href="outStock!showAll?billIdStr=<s:property value="billIdStr"/>">
							 <div class="image-order-status image-order-status-outstock active img-circle">
			                    <span class="status">发货通知</span>
			                    <div class="icon"></div>
			                </div></a>
						</s:if><s:else>
							 <div class="image-order-status image-order-status-outstock-unactive active img-circle">
			                    <span class="status">发货通知</span>
			                    <div class="icon"></div>
			                </div>
						</s:else>
					</s:if>
					<s:if test="relationType==5">
						<s:if test="billIdStr!=null">
							 <a href="stockBillOut!showAll?billIdStr=<s:property value="billIdStr"/>">
							 <div class="image-order-status image-order-status-stockBillOut active img-circle">
			                    <span class="status">销售出库</span>
			                    <div class="icon"></div>
			                </div></a>
						</s:if><s:else>
							 <div class="image-order-status image-order-status-stockBillOut-unactive active img-circle">
			                    <span class="status">销售出库</span>
			                    <div class="icon"></div>
			                </div>
						</s:else>
					</s:if>
					<s:if test="relationType==6">
						<s:if test="billIdStr!=null">
							 <a href="sale!showAll?billIdStr=<s:property value="billIdStr"/>">
							 <div class="image-order-status image-order-status-salebill active img-circle">
			                    <span class="status">销售发票</span>
			                    <div class="icon"></div>
			                </div></a>
						</s:if><s:else>
							 <div class="image-order-status image-order-status-salebill-unactive active img-circle">
			                    <span class="status">销售发票</span>
			                    <div class="icon"></div>
			                </div>
						</s:else>
					</s:if>
					<s:if test="relationType==7">
						<s:if test="billIdStr!=null">
							 <a href="receiveBill!showAll?billIdStr=<s:property value="billIdStr"/>">
							 <div class="image-order-status image-order-status-receivebill active img-circle">
			                    <span class="status">销售收款</span>
			                    <div class="icon"></div>
			                </div></a>
						</s:if><s:else>
							 <div class="image-order-status image-order-status-receivebill-unactive active img-circle">
			                 	<div class="icon"></div>
			                </div>
						</s:else>
					</s:if>
	            </s:iterator>
             
            </div>
        </div>
    </div>

</div>
<script type="text/javascript">
</script>
</body>
</html>
