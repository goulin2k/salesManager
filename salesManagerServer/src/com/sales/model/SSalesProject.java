package com.sales.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.sales.common.DisplayAnnotation;

public class SSalesProject extends BaseObject {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_project.project_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    private Integer projectId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_project.topical
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    @DisplayAnnotation(name="计划主题")
    private String topical;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_project.goal
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    private String goal;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_project.start_time
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    private String startTime;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_project.end_time
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    private String endTime;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_project.response_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    private Integer responseUserId;
    private String responseUserName;
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_project.create_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    private Integer createUserId;
    private String createUserName;
    private Integer enumerationId;
    private String enumerationName;

	/**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_project.comment
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    private String comment;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_project.completion_rate
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    private String completionRate;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_project.customer_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    private Integer customerId;
    
    private String customerName;
    private String customerLevel;

	/**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_sales_project.completion_time
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    private Date completionTime; 
    
    private Timestamp createTime;
    
    private List activityList;
    
    private List attentionUserList;
    
    private List assessList;
    
    private Integer assessListCount;
    
    private Integer customerProjectId;
    
    private String customerProjectName;
    
    private Integer projectRole;
    
    private Timestamp commitTime;  //计划提交时间
    private int status;			//计划提交状态
    private Integer activityCount;		//计划关联的销售活动数
    
	public Integer getProjectRole() {
		return projectRole;
	}

	public void setProjectRole(Integer projectRole) {
		this.projectRole = projectRole;
	}

	public Integer getCustomerProjectId() {
		return customerProjectId;
	}

	public void setCustomerProjectId(Integer customerProjectId) {
		this.customerProjectId = customerProjectId;
	}

	public String getCustomerProjectName() {
		return customerProjectName;
	}

	public void setCustomerProjectName(String customerProjectName) {
		this.customerProjectName = customerProjectName;
	}

	public List getAssessList() {
		return assessList;
	}

	public void setAssessList(List assessList) {
		this.assessList = assessList;
	}

	public List getAttentionUserList() {
		return attentionUserList;
	}

	public void setAttentionUserList(List attentionUserList) {
		this.attentionUserList = attentionUserList;
	}

	public List getActivityList() {
		return activityList;
	}

	public void setActivityList(List activityList) {
		this.activityList = activityList;
	}
	
	

	/**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_project.project_id
     *
     * @return the value of s_sales_project.project_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_project.project_id
     *
     * @param projectId the value for s_sales_project.project_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_project.topical
     *
     * @return the value of s_sales_project.topical
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public String getTopical() {
        return topical;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_project.topical
     *
     * @param topical the value for s_sales_project.topical
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setTopical(String topical) {
        this.topical = topical;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_project.goal
     *
     * @return the value of s_sales_project.goal
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public String getGoal() {
        return goal;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_project.goal
     *
     * @param goal the value for s_sales_project.goal
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setGoal(String goal) {
        this.goal = goal;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_project.start_time
     *
     * @return the value of s_sales_project.start_time
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_project.start_time
     *
     * @param startTime the value for s_sales_project.start_time
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_project.end_time
     *
     * @return the value of s_sales_project.end_time
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_project.end_time
     *
     * @param endTime the value for s_sales_project.end_time
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_project.response_user_id
     *
     * @return the value of s_sales_project.response_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public Integer getResponseUserId() {
        return responseUserId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_project.response_user_id
     *
     * @param responseUserId the value for s_sales_project.response_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setResponseUserId(Integer responseUserId) {
        this.responseUserId = responseUserId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_project.create_user_id
     *
     * @return the value of s_sales_project.create_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_project.create_user_id
     *
     * @param createUserId the value for s_sales_project.create_user_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_project.comment
     *
     * @return the value of s_sales_project.comment
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_project.comment
     *
     * @param comment the value for s_sales_project.comment
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_project.completion_rate
     *
     * @return the value of s_sales_project.completion_rate
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public String getCompletionRate() {
        return completionRate;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_project.completion_rate
     *
     * @param completionRate the value for s_sales_project.completion_rate
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setCompletionRate(String completionRate) {
        this.completionRate = completionRate;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_project.customer_id
     *
     * @return the value of s_sales_project.customer_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_project.customer_id
     *
     * @param customerId the value for s_sales_project.customer_id
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_sales_project.completion_time
     *
     * @return the value of s_sales_project.completion_time
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public Date getCompletionTime() {
        return completionTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_sales_project.completion_time
     *
     * @param completionTime the value for s_sales_project.completion_time
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }

    public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

    public Integer getEnumerationId() {
		return enumerationId;
	}

	public void setEnumerationId(Integer enumerationId) {
		this.enumerationId = enumerationId;
	}

	public String getEnumerationName() {
		return enumerationName;
	}

	public void setEnumerationName(String enumerationName) {
		this.enumerationName = enumerationName;
	}

	public String getResponseUserName() {
		return responseUserName;
	}

	public void setResponseUserName(String responseUserName) {
		this.responseUserName = responseUserName;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	/**
	 * @return the 对应的客户等级
	 */
	public String getCustomerLevel() {
		return customerLevel;
	}

	/**
	 * @return the 计划创建时间
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}

	/**
	 * @param 客户等级 to set
	 */
	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}

	/**
	 * @param 计划创建时间 to set
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the assessListCount
	 */
	public Integer getAssessListCount() {
		return assessListCount;
	}

	/**
	 * @param assessListCount the assessListCount to set
	 */
	public void setAssessListCount(Integer assessListCount) {
		this.assessListCount = assessListCount;
	}

	/**
	 * @return the commitTime
	 */
	public Timestamp getCommitTime() {
		return commitTime;
	}

	/**
	 * @param commitTime the commitTime to set
	 */
	public void setCommitTime(Timestamp commitTime) {
		this.commitTime = commitTime;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the activityCount
	 */
	public Integer getActivityCount() {
		return activityCount;
	}

	/**
	 * @param activityCount the activityCount to set
	 */
	public void setActivityCount(Integer activityCount) {
		this.activityCount = activityCount;
	}

}