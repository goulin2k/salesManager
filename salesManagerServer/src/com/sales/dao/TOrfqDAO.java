package com.sales.dao;

import java.util.List;
import java.util.Map;
 
import com.sales.model.TOrfq;
import com.sales.model.TOrfqEntry;

public interface TOrfqDAO {
	
	public TOrfq getOrfqById(Map queryMap);
	
	public List getOrfqList(TOrfq orfq);
	
	public Integer getOrfqCount(TOrfq orfq);
	
	public String getOrfqDesc();
	
	public void insertOrfq(TOrfq orfq);
	
	public void insertOrfqEntry(TOrfqEntry orfqEntry);
	
	public void insertOrfqCheckStatus(Integer fInterID);
	
	public void insertOrfqCheckRecords(Map insertMap);
	
	public void updateOrfqBillNo(String fDesc); 
	
	public Integer getMaxFInterID();
	
	public List getOrfqAllList(String billIds);
	
	public void deleteOrfqEntryById(Integer fInterID);
	
	public void deleteICCCSById(Integer fInterID);
	
	public void deleteICCCRById(Integer fInterID);
	
	public Integer deleteOrfqById(Map deleteMap);public Integer getCurNo();
	
	public List getBillNo();
	
	public Integer getInterId(String fBillNo);
	
	public void updateOrfqId();
	
	public Integer getNewInterId();

}
