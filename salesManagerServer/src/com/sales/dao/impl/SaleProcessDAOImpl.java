package com.sales.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sales.dao.SaleProcessDAO;
import com.sales.model.SaleProcess;

public class SaleProcessDAOImpl extends SqlMapClientDaoSupport implements SaleProcessDAO {
	protected static final Logger logger = (Logger) Logger
			.getLogger(SaleProcessDAOImpl.class);
	@Override
	public int insert(SaleProcess record) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.sales.dao.SaleProcessDAO#insertList(java.util.List)
	 *  TODO 该处有性能隐患
	 */
	@Override
	public int insertList(List<SaleProcess> listSaleProcess) {
		SqlMapClient sqlMapClient = getSqlMapClientTemplate().getSqlMapClient();
		
		int size = 0;
		try {
			sqlMapClient.update("s_saleProcess.clear");
			
			// 开始事务  
	        sqlMapClient.startTransaction();  
	        // 开始批处理  
	        sqlMapClient.startBatch();  
	  
	        for (SaleProcess sp: listSaleProcess) {  
	            // 插入操作  
	            sqlMapClient.insert("s_saleProcess.insert", sp);  
	        }  
	        // 执行批处理  
	        size = sqlMapClient.executeBatch();  
	  
	        // 提交事务  
	        sqlMapClient.commitTransaction();  
	        
	        logger.info("成功更新销售过程统计数据[" + size + "]条.");
	        
	        int updateSize = sqlMapClient.update("s_saleProcess.updatePorfqDate");
	        logger.info("成功更新销售报价单日期数据[" + updateSize + "]条.");
		}catch(SQLException sqlException){
			logger.error(sqlException.getMessage());
		}
		return size;
	}

	@Override
	public int getSaleProCount(Map queryMap) {
		// TODO Auto-generated method stub
		Integer rows = (Integer) getSqlMapClientTemplate().queryForObject("s_saleProcess.getSaleProCount",queryMap);
		return rows;
	}

	@Override
	public List<SaleProcess> getSaleProList(Map queryMap) {
		// TODO Auto-generated method stub
		List SaleProList = getSqlMapClientTemplate().queryForList("s_saleProcess.getSaleProList",queryMap);
		return SaleProList;
	}

	@Override
	public List<SaleProcess> getSaleProPageList(Map queryMap) {
		// TODO Auto-generated method stub
		List SaleProPageList = getSqlMapClientTemplate().queryForList("s_saleProcess.getSaleProPageList",queryMap);
		return SaleProPageList;
	}

}
