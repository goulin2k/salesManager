/**
 * 
 */
package com.sales.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.sales.dao.SaleProcessDAO;
import com.sales.dao.TCustomerDAO;
import com.sales.dao.TK3CustomerDAO;
import com.sales.dao.TSaleProcessDAO;
import com.sales.dao.impl.SaleProcessDAOImpl;
import com.sales.model.SUser;
import com.sales.model.SaleProcess;
import com.sales.model.TCustomer;
import com.sales.service.CustomerSyncService;
import com.sales.service.SUserService;

/**
 * @author Administrator
 *
 */
public class CustomerSyncServiceImpl implements CustomerSyncService {
	private TK3CustomerDAO k3CustomerDao;
	private TCustomerDAO customerDao;
	
	private TSaleProcessDAO tSaleProcessDAO;
	private SaleProcessDAO saleProcessDAO;
	
	private SUserService userService;
	
	protected static final Logger logger = (Logger) Logger
			.getLogger(CustomerSyncServiceImpl.class);

	/**
	 * @param k3cusCustomerDAO the k3cusCustomerDAO to set
	 */
	public void setK3CustomerDao(TK3CustomerDAO k3cusCustomerDAO) {
		this.k3CustomerDao = k3cusCustomerDAO;
	}



	/**
	 * @param customerDAO the customerDAO to set
	 */
	public void setCustomerDao(TCustomerDAO customerDAO) {
		this.customerDao = customerDAO;
	}


	/**
	 * @param tSaleProcessDAO the tSaleProcessDAO to set
	 */
	public void settSaleProcessDAO(TSaleProcessDAO tSaleProcessDAO) {
		this.tSaleProcessDAO = tSaleProcessDAO;
	}



	/**
	 * @param saleProcessDAO the saleProcessDAO to set
	 */
	public void setSaleProcessDAO(SaleProcessDAO saleProcessDAO) {
		this.saleProcessDAO = saleProcessDAO;
	}



	/**
	 * @param userService the userService to set
	 */
	public void setUserService(SUserService userService) {
		this.userService = userService;
	}



	/* (non-Javadoc)
	 * @see com.sales.service.CustomerSyncService#syncK3Customers()
	 * 未采用批处理模式处理，请不要在初始化使用
	 */
	@Override
	public int syncK3Customers() {
		List<TCustomer> newList = k3CustomerDao.getSyncCustomers();
		for (TCustomer customer : newList) {
			TCustomer crmCustomer = customerDao.getCustomerInfoById(customer.getCustomerId());
			if(crmCustomer == null)
				customerDao.insert(customer);
			else
				customerDao.updateByPrimaryKeySelective(customer);
		}
		return newList.size();
	}



	@Override
	public void syncK3ProcessData() {
		//调用存储过程执行K3单据状态、统计表数据
		tSaleProcessDAO.refreshProcessData();
		
		//调用存储过程执行K3订单统计数据
		Map<String, List<String>> userMap = getUsersWithParent();
		tSaleProcessDAO.refreshOrderData(userMap);
		
		//调用存储过程执行K3订单统计数据
		tSaleProcessDAO.refreshRecieveBillData(userMap);
		
		List<SaleProcess> list = tSaleProcessDAO.queryAll();
		logger.warn("同步K3订单流程数据：... ... ... ... ... ... ... ... ..." + list.size());
		
		saleProcessDAO.insertList(list);
	}
	
	/* (non-Javadoc)
	 * @see com.sales.service.CustomerSyncService#getUsersWithParent()
	 */
	public Map<String, List<String>> getUsersWithParent() {
		Map map = new TreeMap<String, List<String>>();
		List<SUser> parentUsers = userService.selectParentSaleUsers();
		String orderChar = "";
		Integer order = 1;
		for (Iterator iterator = parentUsers.iterator(); iterator.hasNext();) {
			SUser user = (SUser) iterator.next();
			String userName = user.getK3UserName();
			List<String> list = userService.getK3EmpNamesByParentName(userName);
			orderChar = order.toString();
			if(order<10) orderChar = "0" + orderChar;
			map.put(orderChar + userName, list);
			order++;
		}
//		map = mapSortByKey(map);
		return map;
	}
	
	private static SortedMap<String, List<String>> mapSortByKey(
			Map<String, List<String>> unsort_map) {
		TreeMap<String, List<String>> result = new TreeMap<String, List<String>>();

		Object[] unsort_key = unsort_map.keySet().toArray();
		Arrays.sort(unsort_key);

		for (int i = 0; i < unsort_key.length; i++) {
			result.put(unsort_key[i].toString(), unsort_map.get(unsort_key[i]));
		}
		return result.tailMap(result.firstKey());
	}

}
