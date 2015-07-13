package com.sales.dao.impl;

import com.sales.dao.SCustomerEvaluationDAO;
import com.sales.model.SCustomerEvaluation;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class SCustomerEvaluationDAOImpl extends SqlMapClientDaoSupport implements SCustomerEvaluationDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_cusromer_evaluation
     *
     * @abatorgenerated Thu Jun 27 16:52:56 CST 2013
     */
    public SCustomerEvaluationDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_cusromer_evaluation
     *
     * @abatorgenerated Thu Jun 27 16:52:56 CST 2013
     */
    public void insert(SCustomerEvaluation record) {
        getSqlMapClientTemplate().insert("s_cusromer_evaluation.abatorgenerated_insert", record);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_cusromer_evaluation
     *
     * @abatorgenerated Thu Jun 27 16:52:56 CST 2013
     */
    public int updateByPrimaryKey(SCustomerEvaluation record) {
        int rows = getSqlMapClientTemplate().update("s_cusromer_evaluation.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_cusromer_evaluation
     *
     * @abatorgenerated Thu Jun 27 16:52:56 CST 2013
     */
    public int updateByPrimaryKeySelective(SCustomerEvaluation record) {
        int rows = getSqlMapClientTemplate().update("s_cusromer_evaluation.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_cusromer_evaluation
     *
     * @abatorgenerated Thu Jun 27 16:52:56 CST 2013
     */
    public SCustomerEvaluation selectByPrimaryKey(Integer evaId) {
        SCustomerEvaluation key = new SCustomerEvaluation();
        key.setEvaId(evaId);
        SCustomerEvaluation record = (SCustomerEvaluation) getSqlMapClientTemplate().queryForObject("s_cusromer_evaluation.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_cusromer_evaluation
     *
     * @abatorgenerated Thu Jun 27 16:52:56 CST 2013
     */
    public int deleteByPrimaryKey(Integer evaId) {
        SCustomerEvaluation key = new SCustomerEvaluation();
        key.setEvaId(evaId);
        int rows = getSqlMapClientTemplate().delete("s_cusromer_evaluation.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }
}