package com.sales.model;

import java.util.Date;

import com.sales.common.DisplayAnnotation;

public class SExpenseApplication extends Page {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_expense_application.expense_application_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    private Integer expenseApplicationId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_expense_application.type_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    private Integer typeId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_expense_application.status
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    private Integer status;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_expense_application.expense_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    private Integer expenseUserId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_expense_application.expense_time
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    @DisplayAnnotation(name="申请日期")
    private Date expenseTime;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_expense_application.comment
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    @DisplayAnnotation(name="备注")
    private String comment;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_expense_application.loan_sum
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    @DisplayAnnotation(name="借款金额")
    private Float loanSum;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_expense_application.uppercase
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    private String uppercase;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_expense_application.expense_application_code
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    private String expenseApplicationCode;
    
    @DisplayAnnotation(name="申请人")
    private String userName;

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_expense_application.expense_application_id
     *
     * @return the value of s_expense_application.expense_application_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public Integer getExpenseApplicationId() {
        return expenseApplicationId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_expense_application.expense_application_id
     *
     * @param expenseApplicationId the value for s_expense_application.expense_application_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void setExpenseApplicationId(Integer expenseApplicationId) {
        this.expenseApplicationId = expenseApplicationId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_expense_application.type_id
     *
     * @return the value of s_expense_application.type_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_expense_application.type_id
     *
     * @param typeId the value for s_expense_application.type_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_expense_application.status
     *
     * @return the value of s_expense_application.status
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_expense_application.status
     *
     * @param status the value for s_expense_application.status
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_expense_application.expense_user_id
     *
     * @return the value of s_expense_application.expense_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public Integer getExpenseUserId() {
        return expenseUserId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_expense_application.expense_user_id
     *
     * @param expenseUserId the value for s_expense_application.expense_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void setExpenseUserId(Integer expenseUserId) {
        this.expenseUserId = expenseUserId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_expense_application.expense_time
     *
     * @return the value of s_expense_application.expense_time
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public Date getExpenseTime() {
        return expenseTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_expense_application.expense_time
     *
     * @param expenseTime the value for s_expense_application.expense_time
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void setExpenseTime(Date expenseTime) {
        this.expenseTime = expenseTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_expense_application.comment
     *
     * @return the value of s_expense_application.comment
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_expense_application.comment
     *
     * @param comment the value for s_expense_application.comment
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_expense_application.loan_sum
     *
     * @return the value of s_expense_application.loan_sum
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public Float getLoanSum() {
        return loanSum;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_expense_application.loan_sum
     *
     * @param loanSum the value for s_expense_application.loan_sum
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void setLoanSum(Float loanSum) {
        this.loanSum = loanSum;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_expense_application.uppercase
     *
     * @return the value of s_expense_application.uppercase
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public String getUppercase() {
        return uppercase;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_expense_application.uppercase
     *
     * @param uppercase the value for s_expense_application.uppercase
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void setUppercase(String uppercase) {
        this.uppercase = uppercase;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_expense_application.expense_application_code
     *
     * @return the value of s_expense_application.expense_application_code
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public String getExpenseApplicationCode() {
        return expenseApplicationCode;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_expense_application.expense_application_code
     *
     * @param expenseApplicationCode the value for s_expense_application.expense_application_code
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void setExpenseApplicationCode(String expenseApplicationCode) {
        this.expenseApplicationCode = expenseApplicationCode;
    }

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}