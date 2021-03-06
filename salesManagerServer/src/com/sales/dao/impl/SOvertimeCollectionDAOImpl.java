package com.sales.dao.impl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.SOvertimeCollectionDAO;
import com.sales.model.SOvertimeCollection;

public class SOvertimeCollectionDAOImpl extends SqlMapClientDaoSupport implements SOvertimeCollectionDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_overtime_collection
     *
     * @abatorgenerated Thu Jul 11 20:43:32 CST 2013
     */
    public SOvertimeCollectionDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_overtime_collection
     *
     * @abatorgenerated Thu Jul 11 20:43:32 CST 2013
     */
    public void insert(SOvertimeCollection record) {
        getSqlMapClientTemplate().insert("s_overtime_collection.insert", record);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_overtime_collection
     *
     * @abatorgenerated Thu Jul 11 20:43:32 CST 2013
     */
    public int updateByPrimaryKey(SOvertimeCollection record) {
        int rows = getSqlMapClientTemplate().update("s_overtime_collection.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_overtime_collection
     *
     * @abatorgenerated Thu Jul 11 20:43:32 CST 2013
     */
    public int updateByPrimaryKeySelective(SOvertimeCollection record) {
        int rows = getSqlMapClientTemplate().update("s_overtime_collection.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_overtime_collection
     *
     * @abatorgenerated Thu Jul 11 20:43:32 CST 2013
     */
    public SOvertimeCollection selectByPrimaryKey(Integer id) {
        SOvertimeCollection key = new SOvertimeCollection();
        key.setUserId(id);
        SOvertimeCollection record = (SOvertimeCollection) getSqlMapClientTemplate().queryForObject("s_overtime_collection.selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_overtime_collection
     *
     * @abatorgenerated Thu Jul 11 20:43:32 CST 2013
     */
    public int deleteByPrimaryKey(Integer id) {
        SOvertimeCollection key = new SOvertimeCollection();
        key.setId(id);
        int rows = getSqlMapClientTemplate().delete("s_overtime_collection.deleteByPrimaryKey", key);
        return rows;
    }
}