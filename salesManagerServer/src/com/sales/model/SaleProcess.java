package com.sales.model;

import java.util.Calendar;
import java.util.Date;
import com.sales.common.WorkCalendar;

public class SaleProcess {
    private String porfqBillNo;

    private Date porfqBillDate;

    private Date porfqBillCheckdate;

    private Date porfqFinCheckdate;

    private Integer orderId;

    private String orderNo;

    private Date orderBillDate;

    private Date orderBillCheckdate;

    private String porequestBillNo;

    private Date porequestBillDate;

    private Date porequestBillCheckdate;

    private String poorderBillNo;

    private Date poorderBillDate;

    private Date poorderBillCheckdate;

    private String stockinBillNo;

    private Date stockinBillDate;

    private String stockoutBillNo;

    private Date stockoutBillDate;

    private String saleoutBillNo;

    private Date saleoutBillDate;
    
    private Integer billCheckHours; //报价单业务审核小时数
    private Integer finCheckHours; //报价单财务审核 小时数
    private Integer orderHours; //销售订单 小时数
    private Integer orderCheckHours; //销售订单审核 小时数
    private Integer poRequestHours;  //采购申请小时数
    private Integer poRequestCheckHours; //采购申请审核小时数
    private Integer poOrderHours; //采购订单小时数
    private Integer poOrderCheckHours; //采购订单审核小时数
    private Integer stockinHours; //采购入库小时数
    private Integer stockoutHours; //领料通知小时数
    private Integer saleoutHours; //销售出库小时数
    
    private String startTime;
    private String endTime;


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public Integer getBillCheckHours() {
    	if(porfqBillDate !=null && porfqBillCheckdate != null){
    		billCheckHours = WorkCalendar.diffWorkHours(porfqBillDate, porfqBillCheckdate);
    	}
		return billCheckHours;
	}


	public void setBillCheckHours(Integer billCheckHours) {
		this.billCheckHours = billCheckHours;
	}

	public Integer getFinCheckHours() {
		if(porfqBillCheckdate !=null && porfqFinCheckdate != null){
			finCheckHours = WorkCalendar.diffWorkHours(porfqBillCheckdate, porfqFinCheckdate);
    	}
		return finCheckHours;
	}

	public void setFinCheckHours(Integer finCheckHours) {
		this.finCheckHours = finCheckHours;
	}

	public Integer getOrderHours() {
		if(porfqFinCheckdate !=null && orderBillDate != null){
			orderHours = WorkCalendar.diffWorkHours(porfqFinCheckdate, orderBillDate);
    	}
		return (orderHours<0 ? 0 : orderHours);
	}


	public void setOrderHours(Integer orderHours) {
		this.orderHours = orderHours;
	}


	public Integer getOrderCheckHours() {
		if(orderBillDate !=null && orderBillCheckdate != null){
			orderCheckHours = WorkCalendar.diffWorkHours(orderBillDate, orderBillCheckdate);
    	}
		return orderCheckHours;
	}


	public void setOrderCheckHours(Integer orderCheckHours) {
		this.orderCheckHours = orderCheckHours;
	}


	public Integer getPoRequestHours() {
		if(orderBillCheckdate !=null && porequestBillDate != null){
			poRequestHours = WorkCalendar.diffWorkHours(
					orderBillCheckdate, porequestBillDate);
    	}
		return poRequestHours;
	}


	public void setPoRequestHours(Integer poRequestHours) {
		this.poRequestHours = poRequestHours;
	}


	public Integer getPorequestCheckHours() {
		if(porequestBillDate !=null && porequestBillCheckdate != null){
			poRequestCheckHours = WorkCalendar.diffWorkHours(
					porequestBillDate, porequestBillCheckdate);
    	}
		return poRequestCheckHours;
	}

	public void setPorequestCheckHours(Integer porequestCheckHours) {
		
		this.poRequestCheckHours = porequestCheckHours;
	}

	public Integer getPoOrderHours() {
		if(porequestBillCheckdate !=null && poorderBillDate != null){
			poOrderHours = WorkCalendar.diffWorkHours(
					porequestBillCheckdate, poorderBillDate);
    	}
		return poOrderHours;
		
	}


	public void setPoOrderHours(Integer poOrderHours) {
		this.poOrderHours = poOrderHours;
	}


	public Integer getPoOrderCheckHours() {
		if(poorderBillDate !=null && poorderBillCheckdate != null){
			poOrderCheckHours = WorkCalendar.diffWorkHours(
					poorderBillDate, poorderBillCheckdate);
    	}
		return poOrderCheckHours;
	}


	public void setPoOrderCheckHours(Integer poOrderCheckHours) {
		this.poOrderCheckHours = poOrderCheckHours;
	}


	public Integer getStockinHours() {
		if(poorderBillCheckdate !=null && stockinBillDate != null){
			stockinHours = WorkCalendar.diffWorkHours(poorderBillCheckdate, stockinBillDate);
    	}
		return stockinHours;
	}

	public void setStockinHours(Integer stockinHours) {
		this.stockinHours = stockinHours;
	}

	public Integer getStockoutHours() {
		if(stockoutBillDate !=null && stockinBillDate != null){
			stockoutHours = WorkCalendar.diffWorkHours(stockinBillDate, stockoutBillDate);
    	}
		return stockoutHours;
	}

	public void setStockoutHours(Integer stockoutHours) {
		this.stockoutHours = stockoutHours;
	}

	public Integer getSaleoutHours() {
		if(stockoutBillDate !=null && saleoutBillDate != null){
			saleoutHours = WorkCalendar.diffWorkHours(stockoutBillDate, saleoutBillDate);
    	}
		return saleoutHours;
	}

	public void setSaleoutHours(Integer saleoutHours) {
		this.saleoutHours = saleoutHours;
	}

	public String getPorfqBillNo() {
        return porfqBillNo;
    }

    public void setPorfqBillNo(String porfqBillNo) {
        this.porfqBillNo = porfqBillNo;
    }

    public Date getPorfqBillDate() {
        return porfqBillDate;
    }

    public void setPorfqBillDate(Date porfqBillDate) {
        this.porfqBillDate = porfqBillDate;
    }

    public Date getPorfqBillCheckdate() {
        return porfqBillCheckdate;
    }

    public void setPorfqBillCheckdate(Date porfqBillCheckdate) {
        this.porfqBillCheckdate = porfqBillCheckdate;
    }

    public Date getPorfqFinCheckdate() {
        return porfqFinCheckdate;
    }

    public void setPorfqFinCheckdate(Date porfqFinCheckdate) {
        this.porfqFinCheckdate = porfqFinCheckdate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderBillDate() {
        return orderBillDate;
    }

    public void setOrderBillDate(Date orderBillDate) {
        this.orderBillDate = orderBillDate;
    }

    public Date getOrderBillCheckdate() {
        return orderBillCheckdate;
    }

    public void setOrderBillCheckdate(Date orderBillCheckdate) {
        this.orderBillCheckdate = orderBillCheckdate;
    }

    public String getPorequestBillNo() {
        return porequestBillNo;
    }

    public void setPorequestBillNo(String porequestBillNo) {
        this.porequestBillNo = porequestBillNo;
    }

    public Date getPorequestBillDate() {
        return porequestBillDate;
    }

    public void setPorequestBillDate(Date porequestBillDate) {
        this.porequestBillDate = porequestBillDate;
    }

    public Date getPorequestBillCheckdate() {
        return porequestBillCheckdate;
    }

    public void setPorequestBillCheckdate(Date porequestBillCheckdate) {
        this.porequestBillCheckdate = porequestBillCheckdate;
    }

    public String getPoorderBillNo() {
        return poorderBillNo;
    }

    public void setPoorderBillNo(String poorderBillNo) {
        this.poorderBillNo = poorderBillNo;
    }

    public Date getPoorderBillDate() {
        return poorderBillDate;
    }

    public void setPoorderBillDate(Date poorderBillDate) {
        this.poorderBillDate = poorderBillDate;
    }

    public Date getPoorderBillCheckdate() {
        return poorderBillCheckdate;
    }

    public void setPoorderBillCheckdate(Date poorderBillCheckdate) {
        this.poorderBillCheckdate = poorderBillCheckdate;
    }

    public String getStockinBillNo() {
        return stockinBillNo;
    }

    public void setStockinBillNo(String stockinBillNo) {
        this.stockinBillNo = stockinBillNo;
    }

    public Date getStockinBillDate() {
        return stockinBillDate;
    }

    public void setStockinBillDate(Date stockinBillDate) {
        this.stockinBillDate = stockinBillDate;
    }

    public String getStockoutBillNo() {
        return stockoutBillNo;
    }

    public void setStockoutBillNo(String stockoutBillNo) {
        this.stockoutBillNo = stockoutBillNo;
    }

    public Date getStockoutBillDate() {
        return stockoutBillDate;
    }

    public void setStockoutBillDate(Date stockoutBillDate) {
        this.stockoutBillDate = stockoutBillDate;
    }

    public String getSaleoutBillNo() {
        return saleoutBillNo;
    }

    public void setSaleoutBillNo(String saleoutBillNo) {
        this.saleoutBillNo = saleoutBillNo;
    }

    public Date getSaleoutBillDate() {
        return saleoutBillDate;
    }

    public void setSaleoutBillDate(Date saleoutBillDate) {
        this.saleoutBillDate = saleoutBillDate;
    }
    

}