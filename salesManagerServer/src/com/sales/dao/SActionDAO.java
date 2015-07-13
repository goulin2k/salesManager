package com.sales.dao;

import java.util.List;

import com.sales.model.SAction;

public interface SActionDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_action
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    void insert(SAction record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_action
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    int updateByPrimaryKey(SAction record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_action
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    int updateByPrimaryKeySelective(SAction record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_action
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    SAction selectByPrimaryKey(Integer actionId);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_action
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    int deleteByPrimaryKey(Integer actionId);
    
    List<SAction> getActionList();
}