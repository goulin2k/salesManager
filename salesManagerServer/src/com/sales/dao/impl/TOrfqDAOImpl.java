package com.sales.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.TOrfqDAO;
import com.sales.model.TOrfq;
import com.sales.model.TOrfqEntry;

public class TOrfqDAOImpl extends SqlMapClientDaoSupport implements TOrfqDAO {
	
	public TOrfq getOrfqById(Map queryMap){
		TOrfq orfq = (TOrfq) getSqlMapClientTemplate().queryForObject("t_orfq.getOrfqById", queryMap);
		return orfq;
	}
	
	public List getOrfqList(TOrfq orfq){
		List orfqList = (List) getSqlMapClientTemplate().queryForList("t_orfq.getOrfqList", orfq);
		return orfqList;
	}
	
	public Integer getOrfqCount(TOrfq orfq){
		Integer orfqCount = (Integer) getSqlMapClientTemplate().queryForObject("t_orfq.getOrfqCount", orfq);
		return orfqCount;
	}
	
	public String getOrfqDesc(){
		String fDesc = (String) getSqlMapClientTemplate().queryForObject("t_orfq.getOrfqDesc");
		return fDesc;
	}
	
	public void insertOrfq(TOrfq orfq){
		getSqlMapClientTemplate().insert("t_orfq.insertOrfq", orfq);  
	}
	
	public void insertOrfqEntry(TOrfqEntry orfqEntry){
		getSqlMapClientTemplate().insert("t_orfq.insertOrfqEntry", orfqEntry);
	}
	
	public void insertOrfqCheckStatus(Integer fInterID){
		getSqlMapClientTemplate().insert("t_orfq.insertOrfqCheckStatus", fInterID);
	}
	
	public void insertOrfqCheckRecords(Map insertMap){
		getSqlMapClientTemplate().insert("t_orfq.insertOrfqCheckRecords", insertMap);
	}
	
	public void updateOrfqBillNo(String fDesc){
		getSqlMapClientTemplate().update("t_orfq.updateOrfqBillNo", fDesc);
	} 
	
	public Integer getMaxFInterID(){
		Integer fInterID = (Integer) getSqlMapClientTemplate().queryForObject("t_orfq.getMaxFInterID");
		return fInterID;
	}
	
	public List getOrfqAllList(String billIds){
		List orfqList = (List) getSqlMapClientTemplate().queryForList("t_orfq.getOrfqAllList", billIds);
		return orfqList;
	}
	
	public void deleteOrfqEntryById(Integer fInterID){
		getSqlMapClientTemplate().delete("t_orfq.deleteOrfqEntryById", fInterID);
	}
	
	public void deleteICCCSById(Integer fInterID){
		getSqlMapClientTemplate().delete("t_orfq.deleteICCCSById", fInterID);
	}
	
	public void deleteICCCRById(Integer fInterID){
		getSqlMapClientTemplate().delete("t_orfq.deleteICCCRById", fInterID);
	}
	
	public Integer deleteOrfqById(Map deleteMap){
		Integer count = getSqlMapClientTemplate().delete("t_orfq.deleteOrfqById", deleteMap);
		return count;
	}
	
	public Integer getCurNo(){
		Integer curNo = (Integer) getSqlMapClientTemplate().queryForObject("t_orfq.getCurNo");
		return curNo;
	}
	
	public List getBillNo(){
		List billNoList = (List) getSqlMapClientTemplate().queryForList("t_orfq.getBillNo");
		return billNoList;
	}
	
	public Integer getInterId(String fBillNo){
		Integer interId = (Integer) getSqlMapClientTemplate().queryForObject("t_orfq.getInterId", fBillNo);
		return interId;
	}
	
	public void updateOrfqId(){
		getSqlMapClientTemplate().update("t_orfq.updateOrfqId"); 
	}
	
	public Integer getNewInterId(){
		Map insertMap = new HashMap(); 
		insertMap.put("TableName", "PORFQ");
		insertMap.put("FInterID", 0);
		insertMap.put("Increment", 1);
		getSqlMapClientTemplate().update("t_orfq.getNewInterId", insertMap);
		return (Integer) insertMap.get("FInterID");
	}

}
