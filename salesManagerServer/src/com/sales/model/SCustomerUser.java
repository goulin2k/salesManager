package com.sales.model;

public class SCustomerUser {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_customer_user.customer_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    private Integer customerUserId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_customer_user.customer_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    private Integer customerId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_customer_user.user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    private Integer userId;
    private String customerName;

	private String userName;
	private Integer createUserId;
    private String createUserName;
	
    public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_customer_user.customer_user_id
     *
     * @return the value of s_customer_user.customer_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public Integer getCustomerUserId() {
        return customerUserId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_customer_user.customer_user_id
     *
     * @param customerUserId the value for s_customer_user.customer_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void setCustomerUserId(Integer customerUserId) {
        this.customerUserId = customerUserId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_customer_user.customer_id
     *
     * @return the value of s_customer_user.customer_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_customer_user.customer_id
     *
     * @param customerId the value for s_customer_user.customer_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_customer_user.user_id
     *
     * @return the value of s_customer_user.user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_customer_user.user_id
     *
     * @param userId the value for s_customer_user.user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}