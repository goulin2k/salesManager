/**
 * 
 */
package com.sales.service;

import java.util.List;

import com.sales.model.SLog;

/**
 * @author Leo
 *
 */
public interface SLogService {
	
	public void addLog(SLog log);
	
	public List<SLog> getLogListPage(SLog log);

	public Integer getLogListPageCount(SLog log);
}
