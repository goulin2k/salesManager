package com.sales.service;

import java.util.List;
import com.sales.model.SCustomerLinkman;

/** 
 * @author  
 * @version 创建时间：2013-6-25 下午04:06:46 
 *  
 */
public interface CustomerLinkmanService {
	
	public List<SCustomerLinkman> getLinkmanList(Long pageNumber, int pageSize, SCustomerLinkman linkman);
	
	public Integer getLinkmanCount(SCustomerLinkman linkman);
	
	public List<SCustomerLinkman> getLinkmanListByCustomerId(Integer customerId);
	
	public void addLinkman(SCustomerLinkman linkman);
	
	public void updateLinkman(SCustomerLinkman linkman);
	
	public void deleteLinkman(Integer customerLinkmanId);
	
	public SCustomerLinkman getCustomerLinkmanById(Integer linkmanId);
	public List<SCustomerLinkman> getLinkmanListByCustomerIds(Long pageNumber, int pageSize, String customerIds, SCustomerLinkman linkman);
	public Integer getLinkmanCountByCustomerIds(String customerIds, SCustomerLinkman linkman);

	/**
	 * 指定客户的联系人填写完整度
	 * @param customerId
	 * @return
	 */
	public double getLinkmanPerfection(Integer customerId);
}
