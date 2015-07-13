package com.sales.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
 
import com.sales.model.SUser;
import com.sales.model.TOrfq;

public interface OrfqService {
	
	public TOrfq getOrfqById(Integer orfqId, String customerIds);
	
	public List getOrfqList(Integer pageNumber, Integer pageSize, TOrfq orfq, String customerIds);
	
	public Integer getOrfqCount(TOrfq orfq, String customerIds);
	
	@Transactional
	public String insertOrfq(TOrfq orfq, List orfqEntryList);
	
	@Transactional
	public String getDesc();
	
	public List getOrfqAllList(String billIds);
	
	public void deleteOrfqById(Integer fInterID, String customerIds, Integer userId);
	
	public List getSOrfqList(Integer pageNumber, Integer pageSize, TOrfq orfq, String customerIds);
	
	public Integer getSOrfqCount(TOrfq orfq, String customerIds);
	
	public void insertSOrfq(TOrfq orfq, List orfqEntryList, String userName);
	
	public TOrfq getSOrfqById(Integer orfqId, String customerIds);
	
	public void verifySOrfq(Integer fInterID, Integer fStatus, SUser sUser, String customerIds);
	
	public void deleteSOrfqById(Integer fInterID, String customerIds, Integer userId);
	
	public void dealSOrfq(TOrfq orfq, List orfqEntryList);

}
