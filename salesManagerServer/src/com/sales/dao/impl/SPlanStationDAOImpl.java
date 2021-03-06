package com.sales.dao.impl;

import com.sales.dao.SPlanStationDAO;
import com.sales.model.SPlanStation;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class SPlanStationDAOImpl extends SqlMapClientDaoSupport implements SPlanStationDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_plan_station
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public SPlanStationDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_plan_station
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void insert(SPlanStation record) {
        getSqlMapClientTemplate().insert("s_plan_station.abatorgenerated_insert", record);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_plan_station
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public int updateByPrimaryKey(SPlanStation record) {
        int rows = getSqlMapClientTemplate().update("s_plan_station.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_plan_station
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public int updateByPrimaryKeySelective(SPlanStation record) {
        int rows = getSqlMapClientTemplate().update("s_plan_station.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_plan_station
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public SPlanStation selectByPrimaryKey(Integer planStationId) {
        SPlanStation key = new SPlanStation();
        key.setPlanStationId(planStationId);
        SPlanStation record = (SPlanStation) getSqlMapClientTemplate().queryForObject("s_plan_station.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_plan_station
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public int deleteByPrimaryKey(Integer planStationId) {
        SPlanStation key = new SPlanStation();
        key.setPlanStationId(planStationId);
        int rows = getSqlMapClientTemplate().delete("s_plan_station.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }
}