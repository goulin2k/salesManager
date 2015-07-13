package com.sales.dao.impl;
 
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.SaleBillDAO; 
import com.sales.model.SaleBillStatus;

public class SaleBillDAOImpl extends SqlMapClientDaoSupport implements SaleBillDAO {
	
	public SaleBillDAOImpl() {
        super();
    }
    
    public List getSaleBillStatusByOrderId(Integer orderId){
    	List saleBillList = getSqlMapClientTemplate().queryForList("t_sale_bill_status.getSaleBillStatusByOrderId", orderId);
    	return saleBillList;
    }
    
    public SaleBillStatus getSaleBillStatusByBillId(Map queryMap){
    	SaleBillStatus saleBill = (SaleBillStatus) getSqlMapClientTemplate().queryForObject("t_sale_bill_status.getSaleBillStatusByBillId", queryMap);
    	return saleBill;
    }
    
    public List getSaleBillListByBillId(Map queryMap){
    	List saleBillList = getSqlMapClientTemplate().queryForList("t_sale_bill_status.getSaleBillListByBillId", queryMap);
    	return saleBillList;
    }
    
    public List getSaleBillListNotRead(){
		List statusList = getSqlMapClientTemplate().queryForList("t_sale_bill_status.getSaleBillListNotRead");
		return statusList;
	}
	
	public void updateSaleBillRead(String billStatusIds){
		getSqlMapClientTemplate().update("t_sale_bill_status.updateSaleBillRead", billStatusIds);
	}
	
	public List getSaleCheck(String checkDate){
		List checkList = getSqlMapClientTemplate().queryForList("t_sale_bill_status.getSaleCheck", checkDate);
		return checkList;
	}
	
	public List getSaleDelete(String deleteDate){
		List deleteList = getSqlMapClientTemplate().queryForList("t_sale_bill_status.getSaleDelete", deleteDate);
		return deleteList;
	}
	
	public Date getOrfqDealDate(String billType){
		Date dealDate = (Date) getSqlMapClientTemplate().queryForObject("t_sale_bill_status.getOrfqDealDate", billType);
		return dealDate;
	}
	
	public void updateOrfqDealDate(Map orfqMap){
		getSqlMapClientTemplate().update("t_sale_bill_status.updateOrfqDealDate", orfqMap);
	}

	/* (non-Javadoc)
	 * @see com.sales.dao.SaleBillDAO#synK3BillStatus()
	 */
	@Override
	public void synK3BillStatus() {
		getSqlMapClientTemplate().update("t_sale_bill_status.synBillRef");
		getSqlMapClientTemplate().update("t_sale_bill_status.synBillStatus");
		
	}
	
	

}
