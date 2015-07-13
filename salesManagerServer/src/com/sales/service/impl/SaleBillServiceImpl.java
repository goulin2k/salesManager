package com.sales.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import com.sales.dao.SaleBillDAO;
import com.sales.model.SaleBillStatus;
import com.sales.service.SaleBillService;

public class SaleBillServiceImpl implements SaleBillService {
	
	private SaleBillDAO saleBillDao;
	
	private static Logger logger = Logger.getLogger(SaleBillService.class);
  
    public SaleBillDAO getSaleBillDao() {
		return saleBillDao;
	}

	public void setSaleBillDao(SaleBillDAO saleBillDao) {
		this.saleBillDao = saleBillDao;
	}

	public List getSaleBillStatusByOrderId(Integer orderId){
		List saleBillList = new ArrayList();
    	List list = saleBillDao.getSaleBillStatusByOrderId(orderId);
    	for (int i = 0; i < list.size(); i++) {
    		SaleBillStatus saleBill = (SaleBillStatus) list.get(i);
    		boolean addFlag = true; 
			for (int j = 0; j < saleBillList.size(); j++) { 
	    		SaleBillStatus saleBillStatus = (SaleBillStatus) saleBillList.get(j);
	    		if(saleBill.getRelationType().intValue()==saleBillStatus.getRelationType().intValue()){
	    			if(saleBill.getBillId()==null || saleBill.getBillId().intValue()==0){
	    				saleBillStatus.setBillIdStr(null);
	    			}
	    			else{
		    			saleBillStatus.setBillIdStr(saleBillStatus.getBillIdStr() + "," + String.valueOf(saleBill.getBillId()));
	    			}
	    			addFlag = false;
	    		}
	    		else{
	    			if(saleBill.getBillId()==null || saleBill.getBillId().intValue()==0){
	    				saleBill.setBillIdStr(null);
	    			}
	    			else{
	    				saleBill.setBillIdStr(String.valueOf(saleBill.getBillId()));
	    			}
	    		}
			}
			if(addFlag){
				if(saleBillList.size() == 0){
					saleBill.setBillIdStr(String.valueOf(saleBill.getBillId()));
				}
				saleBillList.add(saleBill);
			}
		}
    	
    	return saleBillList;
    }
    
    public SaleBillStatus getSaleBillStatusByBillId(Integer relationType, Integer billId){
    	Map queryMap = new HashMap();
		queryMap.put("relationType", relationType); 
		queryMap.put("billId", billId);
    	SaleBillStatus saleBill = saleBillDao.getSaleBillStatusByBillId(queryMap);
    	return saleBill;
    }
    
    public SaleBillStatus getSaleBillListByBillId(Integer relationType, Integer billId){
    	Map queryMap = new HashMap();
		queryMap.put("relationType", relationType); 
		queryMap.put("billId", billId);
    	List saleBillList = saleBillDao.getSaleBillListByBillId(queryMap);
    	SaleBillStatus saleBill = new SaleBillStatus();
    	for (int i = 0; i < saleBillList.size(); i++) {
    		SaleBillStatus saleBill1 = (SaleBillStatus) saleBillList.get(i);
    		if(saleBill.getOrderIdStr() == null){
    			saleBill.setOrderIdStr(String.valueOf(saleBill1.getOrderId()));
    		}
    		else{
    			saleBill.setOrderIdStr(saleBill.getOrderIdStr() + "," + String.valueOf(saleBill1.getOrderId()));
    		}
		}
    	return saleBill;
    }

	/* (non-Javadoc)
	 * @see com.sales.service.SaleBillService#synK3BillStatus()
	 */
	@Override
	public void synK3BillStatus() {
		logger.warn("***************��ʼִ��K3���ݹ�������*******************");
		saleBillDao.synK3BillStatus();	
		logger.warn("***************����ִ��K3���ݹ�������*******************");
	}
    
    

}
