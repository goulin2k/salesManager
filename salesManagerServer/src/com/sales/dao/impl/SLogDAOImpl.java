package com.sales.dao.impl;

import java.util.List;

import com.sales.dao.SLogDAO;
import com.sales.model.SLog;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class SLogDAOImpl extends SqlMapClientDaoSupport implements SLogDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_log
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public SLogDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_log
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void insert(SLog record) {
        getSqlMapClientTemplate().insert("s_log.insert", record);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_log
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public int updateByPrimaryKey(SLog record) {
        int rows = getSqlMapClientTemplate().update("s_log.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_log
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public int updateByPrimaryKeySelective(SLog record) {
        int rows = getSqlMapClientTemplate().update("s_log.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_log
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public SLog selectByPrimaryKey(Integer logId) {
        SLog key = new SLog();
        key.setLogId(logId);
        SLog record = (SLog) getSqlMapClientTemplate().queryForObject("s_log.selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_log
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public int deleteByPrimaryKey(Integer logId) {
        SLog key = new SLog();
        key.setLogId(logId);
        int rows = getSqlMapClientTemplate().delete("s_log.deleteByPrimaryKey", key);
        return rows;
    }

	@Override
	public List<SLog> getLogListPage(SLog log) {
		return getSqlMapClientTemplate().queryForList("s_log.getLogListPage", log);
	}

	@Override
	public Integer getLogListPageCount(SLog log) {
		return (Integer) getSqlMapClientTemplate().queryForObject("s_log.getLogListPageCount", log);
	}
}