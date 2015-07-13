package com.sales.dao.impl;

import java.util.List;

import com.sales.dao.SActionDAO;
import com.sales.model.SAction;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class SActionDAOImpl extends SqlMapClientDaoSupport implements SActionDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_action
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public SActionDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_action
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void insert(SAction record) {
        getSqlMapClientTemplate().insert("s_action.abatorgenerated_insert", record);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_action
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public int updateByPrimaryKey(SAction record) {
        int rows = getSqlMapClientTemplate().update("s_action.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_action
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public int updateByPrimaryKeySelective(SAction record) {
        int rows = getSqlMapClientTemplate().update("s_action.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_action
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public SAction selectByPrimaryKey(Integer actionId) {
        SAction key = new SAction();
        key.setActionId(actionId);
        SAction record = (SAction) getSqlMapClientTemplate().queryForObject("s_action.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_action
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public int deleteByPrimaryKey(Integer actionId) {
        SAction key = new SAction();
        key.setActionId(actionId);
        int rows = getSqlMapClientTemplate().delete("s_action.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

	@Override
	public List<SAction> getActionList() {
		// TODO Auto-generated method stub
		List actionList = getSqlMapClientTemplate().queryForList("s_action.getActionList");
		return actionList;
	}
}