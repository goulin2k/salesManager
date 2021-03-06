package com.sales.dao.impl;

import java.util.List;

import com.sales.dao.SCarRepairReimbursementDAO;
import com.sales.model.SCarRepairReimbursement;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class SCarRepairReimbursementDAOImpl extends SqlMapClientDaoSupport implements SCarRepairReimbursementDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_car_repair_reimbursement
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public SCarRepairReimbursementDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_car_repair_reimbursement
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public Integer insert(SCarRepairReimbursement record) {
        return (Integer) getSqlMapClientTemplate().insert("s_car_repair_reimbursement.insert", record);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_car_repair_reimbursement
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public int updateByPrimaryKey(SCarRepairReimbursement record) {
        int rows = getSqlMapClientTemplate().update("s_car_repair_reimbursement.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_car_repair_reimbursement
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public int updateByPrimaryKeySelective(SCarRepairReimbursement record) {
        int rows = getSqlMapClientTemplate().update("s_car_repair_reimbursement.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_car_repair_reimbursement
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public SCarRepairReimbursement selectByPrimaryKey(Integer carReimbursementId) {
        SCarRepairReimbursement key = new SCarRepairReimbursement();
        key.setCarReimbursementId(carReimbursementId);
        SCarRepairReimbursement record = (SCarRepairReimbursement) getSqlMapClientTemplate().queryForObject("s_car_repair_reimbursement.selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_car_repair_reimbursement
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public int deleteByPrimaryKey(Integer carReimbursementId) {
        SCarRepairReimbursement key = new SCarRepairReimbursement();
        key.setCarReimbursementId(carReimbursementId);
        int rows = getSqlMapClientTemplate().delete("s_car_repair_reimbursement.deleteByPrimaryKey", key);
        return rows;
    }

	@Override
	public List<SCarRepairReimbursement> getCarReimbursementListPage(SCarRepairReimbursement cr) {
		return getSqlMapClientTemplate().queryForList("s_car_repair_reimbursement.getCarReimbursementListPage", cr);
	}

	@Override
	public Integer getCarReimbursementListPageCount(SCarRepairReimbursement cr) {
		return (Integer) getSqlMapClientTemplate().queryForObject("s_car_repair_reimbursement.getCarReimbursementListPageCount", cr);
	}

	@Override
	public String getMaxCode() {
		Object obj = getSqlMapClientTemplate().queryForObject("s_car_repair_reimbursement.getMaxCode");
		if (obj != null) {
		    return (String) obj;
		}
		return null;
	}
}