package com.sales.model;

public class SPosition {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_position.position_id
     *
     * @abatorgenerated Mon Jun 03 22:16:57 CST 2013
     */
    private Integer positionId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_position.name
     *
     * @abatorgenerated Mon Jun 03 22:16:57 CST 2013
     */
    private String name;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_position.type
     *
     * @abatorgenerated Mon Jun 03 22:16:57 CST 2013
     */
    private Integer type;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_position.note
     *
     * @abatorgenerated Mon Jun 03 22:16:57 CST 2013
     */
    private String note;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column s_position.status
     *
     * @abatorgenerated Mon Jun 03 22:16:57 CST 2013
     */
    private Integer status;
    
    private String isSelect;

    public String getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(String isSelect) {
		this.isSelect = isSelect;
	}

	/**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_position.position_id
     *
     * @return the value of s_position.position_id
     *
     * @abatorgenerated Mon Jun 03 22:16:57 CST 2013
     */
    public Integer getPositionId() {
        return positionId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_position.position_id
     *
     * @param positionId the value for s_position.position_id
     *
     * @abatorgenerated Mon Jun 03 22:16:57 CST 2013
     */
    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_position.name
     *
     * @return the value of s_position.name
     *
     * @abatorgenerated Mon Jun 03 22:16:57 CST 2013
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_position.name
     *
     * @param name the value for s_position.name
     *
     * @abatorgenerated Mon Jun 03 22:16:57 CST 2013
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_position.type
     *
     * @return the value of s_position.type
     *
     * @abatorgenerated Mon Jun 03 22:16:57 CST 2013
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_position.type
     *
     * @param type the value for s_position.type
     *
     * @abatorgenerated Mon Jun 03 22:16:57 CST 2013
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_position.note
     *
     * @return the value of s_position.note
     *
     * @abatorgenerated Mon Jun 03 22:16:57 CST 2013
     */
    public String getNote() {
        return note;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_position.note
     *
     * @param note the value for s_position.note
     *
     * @abatorgenerated Mon Jun 03 22:16:57 CST 2013
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column s_position.status
     *
     * @return the value of s_position.status
     *
     * @abatorgenerated Mon Jun 03 22:16:57 CST 2013
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column s_position.status
     *
     * @param status the value for s_position.status
     *
     * @abatorgenerated Mon Jun 03 22:16:57 CST 2013
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}