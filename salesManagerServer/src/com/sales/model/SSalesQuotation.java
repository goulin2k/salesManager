package com.sales.model;

import java.util.Date;
import java.util.List;

import com.sales.common.DisplayAnnotation;
import com.sales.common.WorkCalendar;

public class SSalesQuotation extends BaseObject {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_quotation.quotation_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    private Integer quotationId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_quotation.quotation_code
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    @DisplayAnnotation(name="询价单号")
    private String quotationCode;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_quotation.quotation_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    private Integer quotationUserId;
    
    @DisplayAnnotation(name="询价人员")
    private String quotationUserName;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_quotation.purchase_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    private Integer purchaseUserId;
    
    @DisplayAnnotation(name="采购回复")
    private String purchaseUserName;
    
    private String startTime;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_project.end_time
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    private String endTime;
    
    private String issued;
    
    private String productCode;
    
    private String productName;

    public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getIssued() {
		return issued;
	}

	public void setIssued(String issued) {
		this.issued = issued;
	}

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

	public String getQuotationUserName() {
		return quotationUserName;
	}

	public void setQuotationUserName(String quotationUserName) {
		this.quotationUserName = quotationUserName;
	}

	public String getPurchaseUserName() {
		return purchaseUserName;
	}

	public void setPurchaseUserName(String purchaseUserName) {
		this.purchaseUserName = purchaseUserName;
	}

	/**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_quotation.quotation_time
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
	@DisplayAnnotation(name="询价时间")
    private Date quotationTime;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_quotation.reply_time
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
	@DisplayAnnotation(name="询价回复时间")
    private Date replyTime;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_quotation.status
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    private Integer status;
    
    private String statusName;
    private int replyHours;
    
    private Integer orfqId; //关联报价单id

    public int getReplyHours() {
    	if(quotationTime != null && replyTime != null){
    		replyHours = WorkCalendar.diffWorkHours(quotationTime, replyTime);
    	}
		return replyHours;
	}

	public void setReplyHours(int replyHours) {
		this.replyHours = replyHours;
	}

	public String getStatusName() {
		if(status!=null && status==1){
			return "已回复";
		}
		else{
			return "未回复";
		}
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_quotation.comment
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
	@DisplayAnnotation(name="备注")
    private String comment;
	
	@DisplayAnnotation(isChildrenList=true, name="询价产品列表")
    private List quotationProductList; 
    
    private Integer customerId;
    
    @DisplayAnnotation(name="客户名称")
    private String customerName;
    
    public String getCustomerName() {
    	if(customerName == null)
    		return "";
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public List getQuotationProductList() {
		return quotationProductList;
	}

	public void setQuotationProductList(List quotationProductList) {
		this.quotationProductList = quotationProductList;
	}

	/**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_quotation.quotation_id
     *
     * @return the value of s_sales_quotation.quotation_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public Integer getQuotationId() {
        return quotationId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_quotation.quotation_id
     *
     * @param quotationId the value for s_sales_quotation.quotation_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setQuotationId(Integer quotationId) {
        this.quotationId = quotationId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_quotation.quotation_code
     *
     * @return the value of s_sales_quotation.quotation_code
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public String getQuotationCode() {
        return quotationCode;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_quotation.quotation_code
     *
     * @param quotationCode the value for s_sales_quotation.quotation_code
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setQuotationCode(String quotationCode) {
        this.quotationCode = quotationCode;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_quotation.quotation_user_id
     *
     * @return the value of s_sales_quotation.quotation_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public Integer getQuotationUserId() {
        return quotationUserId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_quotation.quotation_user_id
     *
     * @param quotationUserId the value for s_sales_quotation.quotation_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setQuotationUserId(Integer quotationUserId) {
        this.quotationUserId = quotationUserId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_quotation.purchase_user_id
     *
     * @return the value of s_sales_quotation.purchase_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public Integer getPurchaseUserId() {
        return purchaseUserId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_quotation.purchase_user_id
     *
     * @param purchaseUserId the value for s_sales_quotation.purchase_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setPurchaseUserId(Integer purchaseUserId) {
        this.purchaseUserId = purchaseUserId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_quotation.quotation_time
     *
     * @return the value of s_sales_quotation.quotation_time
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public Date getQuotationTime() {
        return quotationTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_quotation.quotation_time
     *
     * @param quotationTime the value for s_sales_quotation.quotation_time
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setQuotationTime(Date quotationTime) {
        this.quotationTime = quotationTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_quotation.reply_time
     *
     * @return the value of s_sales_quotation.reply_time
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public Date getReplyTime() {
        return replyTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_quotation.reply_time
     *
     * @param replyTime the value for s_sales_quotation.reply_time
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_quotation.status
     *
     * @return the value of s_sales_quotation.status
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_quotation.status
     *
     * @param status the value for s_sales_quotation.status
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_quotation.comment
     *
     * @return the value of s_sales_quotation.comment
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_quotation.comment
     *
     * @param comment the value for s_sales_quotation.comment
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

	/**
	 * 关联报价单id
	 * @return the qrfqId
	 */
	public Integer getOrfqId() {
		return orfqId;
	}

	/**
	 * 关联报价单id
	 * @param qrfqId the qrfqId to set
	 */
	public void setOrfqId(Integer orfqId) {
		this.orfqId = orfqId;
	}
}