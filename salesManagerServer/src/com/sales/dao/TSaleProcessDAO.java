package com.sales.dao;

import java.util.List;
import java.util.Map;

import com.sales.model.SaleProcess;

public interface TSaleProcessDAO {
	
	/**
	 * 刷新K3销售过程统计数据
	 */
	public void refreshProcessData();
	
	/**
	 * 刷新K3销售订单统计数据
	 * @param userMap<K3 userName, 下属用户名List>
	 */
	public void refreshOrderData(Map<String, List<String>> userMap);
	
	/**
	 * 刷新K3收款单统计数据
	 * @param userMap<K3 userName, 下属用户名List>
	 */
	public void refreshRecieveBillData(Map<String, List<String>> userMap);
	
	/**
	 * 从K3查询所有销售过程报表数据
	 * @return
	 */
	public List<SaleProcess> queryAll();
	
}