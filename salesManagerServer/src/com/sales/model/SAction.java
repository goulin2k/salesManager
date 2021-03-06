package com.sales.model;

import java.util.List;

public class SAction {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_action.action_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    private Integer actionId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_action.name
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    private String name;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_action.url
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    private String url;
    
    private String isCheck;
    
    private List<SModule> moduleList;

	public String getIsCheck() {
		return isCheck;
	}

	public List<SModule> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<SModule> moduleList) {
		this.moduleList = moduleList;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	/**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_action.action_id
     *
     * @return the value of s_action.action_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public Integer getActionId() {
        return actionId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_action.action_id
     *
     * @param actionId the value for s_action.action_id
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_action.name
     *
     * @return the value of s_action.name
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_action.name
     *
     * @param name the value for s_action.name
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_action.url
     *
     * @return the value of s_action.url
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_action.url
     *
     * @param url the value for s_action.url
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void setUrl(String url) {
        this.url = url;
    }
}