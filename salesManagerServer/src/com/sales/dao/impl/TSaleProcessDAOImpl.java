/**
 * 
 */
package com.sales.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.TSaleProcessDAO;
import com.sales.model.SaleProcess;

/**
 * @author Administrator
 *
 */
public class TSaleProcessDAOImpl  extends SqlMapClientDaoSupport implements TSaleProcessDAO {

	/* (non-Javadoc)
	 * @see com.sales.dao.TSaleProcessDAO#refreshOrderDate()
	 */
	@Override
	public void refreshOrderData(Map<String, List<String>> userMap) {
		getSqlMapClientTemplate().update("t_saleProcess.sales_order_stat");		//同步K3销售订单统计表
		
		for (Iterator iterator = userMap.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String userName = key.substring(2);
			String childrenNameString = new String("(");
			List<String> children = userMap.get(key);
			if(children.size()>1) {
				for (Iterator iterator2 = children.iterator(); iterator2.hasNext();) {
					String childName = (String) iterator2.next();
					childrenNameString = childrenNameString + "'" + childName + "'";
					if(iterator2.hasNext()) childrenNameString+=",";
				}
				childrenNameString += ")";
				Map<String, String> map = new HashMap<String, String>();
				map.put("empName", userName);
				map.put("childrenNames", childrenNameString);
				getSqlMapClientTemplate().update("t_order_statistics.sumChildrenOrder", map);
			}
		}
	}



	/* (non-Javadoc)
	 * @see com.sales.dao.TSaleProcessDAO#refreshProcessData()
	 */
	@Override
	public void refreshProcessData() {
		getSqlMapClientTemplate().update("t_sale_bill_status.synBillRef"); 		//同步K3单据状态记录表
		getSqlMapClientTemplate().update("t_saleProcess.sales_process_syn"); 	//同步K3销售过程表
		
		
	}
	
	
	
	

	/* (non-Javadoc)
	 * @see com.sales.dao.TSaleProcessDAO#refreshRecieveBillDate(java.util.Map)
	 */
	@Override
	public void refreshRecieveBillData(Map<String, List<String>> userMap) {
		getSqlMapClientTemplate().update("t_saleProcess.sales_saleRecieveBil_stat");	//同步K3收款单据表
		
		for (Iterator iterator = userMap.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String userName = key.substring(2);
			String childrenNameString = new String("(");
			List<String> children = userMap.get(key);
			if(children.size()>1) {
				for (Iterator iterator2 = children.iterator(); iterator2.hasNext();) {
					String childName = (String) iterator2.next();
					childrenNameString = childrenNameString + "'" + childName + "'";
					if(iterator2.hasNext()) childrenNameString+=",";
				}
				childrenNameString += ")";
				Map<String, String> map = new HashMap<String, String>();
				map.put("empName", userName);
				map.put("childrenNames", childrenNameString);
				getSqlMapClientTemplate().update("t_recieveBill_statistics.sumChildrenBill", map);
			}
		}
	}



	/* (non-Javadoc)
	 * @see com.sales.dao.TSaleProcessDAO#queryAll()
	 */
	@Override
	public List<SaleProcess> queryAll() {
		// TODO Auto-generated method stub
		List<SaleProcess> list = getSqlMapClientTemplate().queryForList("t_saleProcess.selectAll");
		return list;
	}

}
