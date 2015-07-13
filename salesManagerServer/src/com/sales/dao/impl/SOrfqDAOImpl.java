package com.sales.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.SOrfqDAO;
import com.sales.model.TOrfq;
import com.sales.model.TOrfqEntry;

public class SOrfqDAOImpl extends SqlMapClientDaoSupport implements SOrfqDAO {
	
	public TOrfq getOrfqById(Map queryMap){
		TOrfq orfq = (TOrfq) getSqlMapClientTemplate().queryForObject("t_orfq.getSOrfqById", queryMap);
		return orfq;
	}
	
	public List getOrfqList(TOrfq orfq){
		List orfqList = (List) getSqlMapClientTemplate().queryForList("t_orfq.getSOrfqList", orfq);
		return orfqList;
	}
	
	public Integer getOrfqCount(TOrfq orfq){
		Integer orfqCount = (Integer) getSqlMapClientTemplate().queryForObject("t_orfq.getSOrfqCount", orfq);
		return orfqCount;
	}
	
	public String getOrfqDesc(){
		String fDesc = (String) getSqlMapClientTemplate().queryForObject("t_orfq.getOrfqDesc");
		return fDesc;
	}
	
	public Integer insertOrfq(TOrfq orfq){
		Integer fInterId = (Integer) getSqlMapClientTemplate().insert("t_orfq.insertSOrfq", orfq);
		return fInterId;
	}
	
	public void insertOrfqEntry(TOrfqEntry orfqEntry){
		getSqlMapClientTemplate().insert("t_orfq.insertSOrfqEntry", orfqEntry);
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
	
	public void deleteSOrfqEntryById(Integer fInterID){
		getSqlMapClientTemplate().delete("t_orfq.deleteSOrfqEntryById", fInterID);
	}
	 
	public Integer deleteSOrfqById(Map deleteMap){
		Integer count = getSqlMapClientTemplate().delete("t_orfq.deleteSOrfqById", deleteMap);
		return count;
	}
	
	public Integer verifySOrfq(Map verifyMap){
		Integer count = getSqlMapClientTemplate().update("t_orfq.verifySOrfq", verifyMap);
		return count;
	}
	
	public void updateSOrfq(TOrfq orfq){
		getSqlMapClientTemplate().update("t_orfq.updateSOrfq", orfq);
	}
	
	public void updateOrfqCheck(Map orfqMap){
		getSqlMapClientTemplate().update("t_orfq.updateOrfqByBillNoes", orfqMap);
	}
	
	public void deleteOrfqs(String billNoes){
		getSqlMapClientTemplate().delete("t_orfq.deleteOrfqByBillNoes", billNoes);
	}
	
	public List getInterIdBybillNo(String fBillNo){
		List interIds = getSqlMapClientTemplate().queryForList("t_orfq.getInterIdBybillNo", fBillNo);
		return interIds;
	}
	
	public void deleteOrfqEntrys(String interIds){
		getSqlMapClientTemplate().delete("t_orfq.deleteOrfqEntrys", interIds);
	}
	
	public void updateBillNoByInterId(TOrfq orfq){
		getSqlMapClientTemplate().update("t_orfq.updateBillNoByInterId", orfq);
	}

}
