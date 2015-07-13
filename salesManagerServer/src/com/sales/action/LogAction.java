/**
 * 
 */
package com.sales.action;

import java.util.List;

import com.sales.common.Constants;
import com.sales.model.SLog;
import com.sales.service.SLogService;

/**
 * @author Leo
 *
 */
public class LogAction extends BaseAction {
	
	private SLogService logService;
	
	private List<SLog> logList;
	
	private SLog log;
	

	public String logList() {
		if (this.log == null) {
			this.log = new SLog();
		}
		if (this.log.getPageNo() == null) {
			this.log.setPageNo(1);
		}
		this.log.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		this.logList = this.logService.getLogListPage(this.log);
		int totalCount = this.logService.getLogListPageCount(this.log);
		this.log.setTotalCount(totalCount);
		return "logList";
	}

	/**
	 * @param logService the logService to set
	 */
	public void setLogService(SLogService logService) {
		this.logService = logService;
	}

	/**
	 * @return the logList
	 */
	public List<SLog> getLogList() {
		return logList;
	}

	/**
	 * @param logList the logList to set
	 */
	public void setLogList(List<SLog> logList) {
		this.logList = logList;
	}

	/**
	 * @return the log
	 */
	public SLog getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(SLog log) {
		this.log = log;
	}

}
