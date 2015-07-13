package com.sales.dao.impl;

import java.util.List;

import com.sales.dao.SSalesAssessDAO;
import com.sales.model.SSalesAssess;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class SSalesAssessDAOImpl extends SqlMapClientDaoSupport implements SSalesAssessDAO {
 
    public SSalesAssessDAOImpl() {
        super();
    }
 
    public Integer insertSalesAssess(SSalesAssess assess) {
    	Integer assessId = (Integer) getSqlMapClientTemplate().insert("s_sales_assess.insertSalesAssess", assess);
    	return assessId;
    }
  
    public SSalesAssess getSalesAssessById(Integer assessId) { 
        SSalesAssess assess = (SSalesAssess) getSqlMapClientTemplate().queryForObject("s_sales_assess.getSalesAssessById", assessId);
        return assess;
    }
 
    public void deleteSalesAssess(Integer assessId) { 
        getSqlMapClientTemplate().delete("s_sales_assess.deleteSalesAssess", assessId); 
    }
 
    public void updateSalesAssess(SSalesAssess assess) {
        getSqlMapClientTemplate().update("s_sales_assess.updateSalesAssess", assess);
    }

	/* (non-Javadoc)
	 * @see com.sales.dao.SSalesAssessDAO#getSaleAssessCountByPlan(java.lang.Integer)
	 */
	@Override
	public Integer getSaleAssessCountByPlan(Integer projectId) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("s_sales_assess.getSaleAssessCountByPlan", projectId);
    	return count;
	}

	/* (non-Javadoc)
	 * @see com.sales.dao.SSalesAssessDAO#getSaleAssessListByPlan(java.lang.Integer)
	 */
	@Override
	public List<SSalesAssess> getSaleAssessListByPlan(Integer projectId) {
		List list = getSqlMapClientTemplate().queryForList("s_sales_assess.getSalesAssessByProjectId", projectId);
		return list;
	}
    
    
}