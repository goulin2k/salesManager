package com.sales.service.impl;
 
import java.util.HashMap;
import java.util.List; 
import java.util.Map;
 
import com.sales.common.Constants;
import com.sales.dao.TOrderDAO;
import com.sales.model.K3OrderCustomerStatistics;
import com.sales.model.TOrder;
import com.sales.model.TOrderEntry;
import com.sales.service.OrderService;

public class OrderServiceImpl implements OrderService {
	
	private TOrderDAO orderDao;
	
	
	public TOrder getOrderById(Integer orderId, String customerIds){
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		Map queryMap = new HashMap();
		queryMap.put("orderId", orderId); 
		queryMap.put("customerIds", customerIds); 
		TOrder order = orderDao.getOrderById(queryMap);
		return order;
	}
	
	public List getOrderList(Integer pageNumber, Integer pageSize, TOrder order, String customerIds){ 
		Integer startNumber = (pageNumber - 1) * pageSize;
		order.setStartNumber(startNumber);
		order.setPageSize(pageSize);
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		order.setCustomerIds(customerIds);
		List orderList = orderDao.getOrderList(order);
		return orderList;
	}
	
	public Integer getOrderCount(TOrder order, String customerIds){
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		order.setCustomerIds(customerIds);
		Integer orderCount = orderDao.getOrderCount(order);
		return orderCount;
	}
	
	public List getOrderAllList(String orderIds){
		List orderList = orderDao.getOrderAllList("(" + orderIds + ")");
		return orderList;
	}
	
	public List getOrderByCustomerId(Integer customerId){  
		List orderList = orderDao.getOrderByCustomerId(customerId);
		for (int i = 0; i < orderList.size(); i++) {
			TOrder order = (TOrder) orderList.get(i);
			List entryList = order.getOrderEntryList();
			for (int j = 0; j < entryList.size(); j++) {
				TOrderEntry te = (TOrderEntry) entryList.get(j);
				if(order.getAmount() == null){
					order.setAmount(te.getFAmount().doubleValue());
				}
				else{
					order.setAmount(order.getAmount().doubleValue() + te.getFAmount().doubleValue());
				}
			}
		}
		return orderList;
	}
	
	/* (non-Javadoc)
	 * @see com.sales.service.CustomerService#getOrderProductStatisticsList(java.lang.Integer)
	 */
	@Override
	public List<K3OrderCustomerStatistics> getOrderProductStatisticsList(
			Integer customerId) {
		List<K3OrderCustomerStatistics> list = orderDao.
				getOrderProductStatisticsList(customerId, STATISTICS_TOP, STATISTICS_MONTHS);
		return list;
	}

	public TOrderDAO getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(TOrderDAO orderDao) {
		this.orderDao = orderDao;
	}

}
