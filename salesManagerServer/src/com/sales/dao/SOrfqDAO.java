package com.sales.dao;

import java.util.List;
import java.util.Map;
 
import com.sales.model.TOrfq;
import com.sales.model.TOrfqEntry;

public interface SOrfqDAO {
	
	public TOrfq getOrfqById(Map queryMap);
	
	public List getOrfqList(TOrfq orfq);
	
	public Integer getOrfqCount(TOrfq orfq);
	
	public String getOrfqDesc();
	
	public Integer insertOrfq(TOrfq orfq);
	
	public void insertOrfqEntry(TOrfqEntry orfqEntry);
	
	public void insertOrfqCheckStatus(Integer fInterID);
	
	public void insertOrfqCheckRecords(Map insertMap);
	
	public void updateOrfqBillNo(String fDesc); 
	
	public Integer getMaxFInterID();
	
	public List getOrfqAllList(String billIds);
	
	public void deleteSOrfqEntryById(Integer fInterID); 
	
	public Integer deleteSOrfqById(Map deleteMap);
	
	public Integer verifySOrfq(Map verifyMap);
	
	public void updateSOrfq(TOrfq orfq);
	
	public void updateOrfqCheck(Map orfqMap);
	
	public void deleteOrfqs(String billNoes);
	
	public List getInterIdBybillNo(String fBillNo);
	
	public void deleteOrfqEntrys(String interIds);
	
	public void updateBillNoByInterId(TOrfq orfq);

}
