package com.sales.model;

import java.util.Date;

import com.sales.common.NormalFun;

public class SSalesAssess {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_assess.assess_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    private Integer assessId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_assess.project_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    private Integer projectId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_assess.activity_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    private Integer activityId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_assess.comment
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    private String comment;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_assess.comment_time
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    private Date commentTime;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_assess.assess_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    private Integer assessUserId;
    
    private String assessUserName;

    public String getAssessUserName() {
		return assessUserName;
	}

	public void setAssessUserName(String assessUserName) {
		this.assessUserName = assessUserName;
	}

	/**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_assess.assess_id
     *
     * @return the value of s_sales_assess.assess_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public Integer getAssessId() {
        return assessId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_assess.assess_id
     *
     * @param assessId the value for s_sales_assess.assess_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setAssessId(Integer assessId) {
        this.assessId = assessId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_assess.project_id
     *
     * @return the value of s_sales_assess.project_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_assess.project_id
     *
     * @param projectId the value for s_sales_assess.project_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_assess.activity_id
     *
     * @return the value of s_sales_assess.activity_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public Integer getActivityId() {
        return activityId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_assess.activity_id
     *
     * @param activityId the value for s_sales_assess.activity_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_assess.comment
     *
     * @return the value of s_sales_assess.comment
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_assess.comment
     *
     * @param comment the value for s_sales_assess.comment
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_assess.comment_time
     *
     * @return the value of s_sales_assess.comment_time
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public Date getCommentTime() {
        return commentTime;
    }
    
    public String getCommentTimeString() {
        if( commentTime != null)
        	return NormalFun.formatDateString(commentTime);
        return null;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_assess.comment_time
     *
     * @param commentTime the value for s_sales_assess.comment_time
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_assess.assess_user_id
     *
     * @return the value of s_sales_assess.assess_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public Integer getAssessUserId() {
        return assessUserId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_assess.assess_user_id
     *
     * @param assessUserId the value for s_sales_assess.assess_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setAssessUserId(Integer assessUserId) {
        this.assessUserId = assessUserId;
    }
}