package com.sales.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.SOvertimeDAO;
import com.sales.model.SOvertime;

public class SOvertimeDAOImpl extends SqlMapClientDaoSupport implements SOvertimeDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_overtime
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public SOvertimeDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_overtime
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public Integer insert(SOvertime record) {
        return (Integer) getSqlMapClientTemplate().insert("s_overtime.insert", record);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_overtime
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public int updateByPrimaryKey(SOvertime record) {
        int rows = getSqlMapClientTemplate().update("s_overtime.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_overtime
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public int updateByPrimaryKeySelective(SOvertime record) {
        int rows = getSqlMapClientTemplate().update("s_overtime.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_overtime
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public SOvertime selectByPrimaryKey(Integer overtimeId) {
        SOvertime key = new SOvertime();
        key.setOvertimeId(overtimeId);
        SOvertime record = (SOvertime) getSqlMapClientTemplate().queryForObject("s_overtime.selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_overtime
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public int deleteByPrimaryKey(Integer overtimeId) {
        SOvertime key = new SOvertime();
        key.setOvertimeId(overtimeId);
        int rows = getSqlMapClientTemplate().delete("s_overtime.deleteByPrimaryKey", key);
        return rows;
    }

	@Override
	public List<SOvertime> getOvertimeListPage(SOvertime overtime) {
		return getSqlMapClientTemplate().queryForList("s_overtime.getOvertimeListPage", overtime);
	}

	@Override
	public Integer getOvertimeListPageCount(SOvertime overtime) {
		return (Integer) getSqlMapClientTemplate().queryForObject("s_overtime.getOvertimeListPageCount", overtime);
	}

	@Override
	public String getMaxCode() {
		Object obj = getSqlMapClientTemplate().queryForObject("s_overtime.getMaxCode");
		if (obj != null) {
		    return (String) obj;
		}
		return null;
	}
}