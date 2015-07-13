/**
 * 
 */
package com.sales.service;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 *
 */
public interface CustomerSyncService {
	
	/**
	 * @return	同步的数据条数
	 */
	public int syncK3Customers();
	
	/**
	 * 同步K3销售过程统计数据
	 * @return 同步的数据条数
	 */
	public void syncK3ProcessData();
	
	/**
	 * @return
	 */
	public Map<String, List<String>> getUsersWithParent();

}
