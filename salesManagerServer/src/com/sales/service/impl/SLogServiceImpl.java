/**
 * 
 */
package com.sales.service.impl;

import java.util.List;

import com.sales.dao.SLogDAO;
import com.sales.model.SLog;
import com.sales.service.SLogService;

/**
 * @author Leo
 *
 */
public class SLogServiceImpl implements SLogService {
	
	private SLogDAO logDao;

	/**
	 * @param logDao the logDao to set
	 */
	public void setLogDao(SLogDAO logDao) {
		this.logDao = logDao;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.SLogService#addLog(com.sales.model.SLog)
	 */
	@Override
	public void addLog(SLog log) {
		this.logDao.insert(log);
	}

	/* (non-Javadoc)
	 * @see com.sales.service.SLogService#getLogListPage(com.sales.model.SLog)
	 */
	@Override
	public List<SLog> getLogListPage(SLog log) {
		return this.logDao.getLogListPage(log);
	}

	/* (non-Javadoc)
	 * @see com.sales.service.SLogService#getLogListPageCount(com.sales.model.SLog)
	 */
	@Override
	public Integer getLogListPageCount(SLog log) {
		return this.logDao.getLogListPageCount(log);
	}

}
