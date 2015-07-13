package com.sales.model;

import java.util.Date;

import com.sales.common.Constants;

public class SLog extends Page{
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_log.log_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    private Integer logId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_log.type_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    private Integer typeId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_log.title
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    private String title;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_log.operate_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    private Integer operateUserId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_log.log_time
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    private Date logTime;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_log.log_content
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    private String logContent;
    
    private String userName;
    
    private String typeName;

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_log.log_id
     *
     * @return the value of s_log.log_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public Integer getLogId() {
        return logId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_log.log_id
     *
     * @param logId the value for s_log.log_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_log.type_id
     *
     * @return the value of s_log.type_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_log.type_id
     *
     * @param typeId the value for s_log.type_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_log.title
     *
     * @return the value of s_log.title
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_log.title
     *
     * @param title the value for s_log.title
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_log.operate_user_id
     *
     * @return the value of s_log.operate_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public Integer getOperateUserId() {
        return operateUserId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_log.operate_user_id
     *
     * @param operateUserId the value for s_log.operate_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void setOperateUserId(Integer operateUserId) {
        this.operateUserId = operateUserId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_log.log_time
     *
     * @return the value of s_log.log_time
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public Date getLogTime() {
        return logTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_log.log_time
     *
     * @param logTime the value for s_log.log_time
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_log.log_content
     *
     * @return the value of s_log.log_content
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public String getLogContent() {
        return logContent;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_log.log_content
     *
     * @param logContent the value for s_log.log_content
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void setLogContent(String logContent) {
        this.logContent = logContent;
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

	public String getTypeName() {
		if (this.getTypeId().intValue() == Constants.LOG_TYPE_SYNCHRONOUS) 
			typeName = "用户登录";
		else 
			typeName = "业务操作";
		return typeName;
	}
}