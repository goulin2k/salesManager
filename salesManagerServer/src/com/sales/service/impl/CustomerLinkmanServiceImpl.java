package com.sales.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.sales.common.Constants;
import com.sales.dao.SCustomerLinkmanDAO;
import com.sales.dao.SLogDAO;
import com.sales.dao.TCustomerDAO;
import com.sales.model.CustomerLinkmanStatics;
import com.sales.model.SCustomerLinkman;
import com.sales.model.SLog;
import com.sales.service.CustomerLinkmanService;

/** 
 * @author  
 * @version 创建时间：2013-6-25 下午04:13:12 
 *  
 */
public class CustomerLinkmanServiceImpl implements CustomerLinkmanService{
	
	private SCustomerLinkmanDAO customerLinkmanDao; 
	private TCustomerDAO customerDao;
	private SLogDAO logDao;
	
	

	/**
	 * @param customerDao the customerDao to set
	 */
	public void setCustomerDao(TCustomerDAO customerDao) {
		this.customerDao = customerDao;
	}

	public SLogDAO getLogDao() {
		return logDao;
	}

	public void setLogDao(SLogDAO logDao) {
		this.logDao = logDao;
	}

	public SCustomerLinkmanDAO getCustomerLinkmanDao() {
		return this.customerLinkmanDao;
	}

	public void setCustomerLinkmanDao(SCustomerLinkmanDAO customerLinkmanDao) {
		this.customerLinkmanDao = customerLinkmanDao;
	}

	@Override
	public Integer getLinkmanCount(SCustomerLinkman linkman) {
		
		Integer temp = customerLinkmanDao.getLinkmanCount(linkman);
		return temp;
	}

	@Override
	public List<SCustomerLinkman> getLinkmanList(Long pageNumber, int pageSize, SCustomerLinkman linkman) {
		
		List linkmanList = customerLinkmanDao.getLinkmanList(pageNumber, pageSize, linkman);
		return linkmanList;
	}

	@Override
	public List<SCustomerLinkman> getLinkmanListByCustomerId(Integer customerId) {
		
		List linkmanList = customerLinkmanDao.getLinkmanListByCustomerId(customerId);
		return linkmanList;
	}

	@Override
	public void addLinkman(SCustomerLinkman linkman) {
		
		customerLinkmanDao.insert(linkman);
		
		updateCustomerLinkManStatus(linkman);
		
		//添加日志
		SLog log = new SLog();
		log.setLogContent("用户" + linkman.getCreateUserName() + "新增客户联系人" + linkman.getName());
		log.setLogTime(new Date());
		log.setOperateUserId(linkman.getCreateUserId());
		log.setTitle("新增客户联系人");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);
	}

	/**
	 * @param linkman
	 */
	private void updateCustomerLinkManStatus(SCustomerLinkman linkman) {
		Double completely = getLinkmanPerfection(linkman.getCustomerId());
		customerDao.updateLinkmanCompletely(linkman.getCustomerId(), completely);
	}

	@Override
	public void deleteLinkman(Integer customerLinkmanId) {
		SCustomerLinkman linkman = getCustomerLinkmanById(customerLinkmanId);
		customerLinkmanDao.deleteByPrimaryKey(customerLinkmanId);
		updateCustomerLinkManStatus(linkman);
	}

	@Override
	public void updateLinkman(SCustomerLinkman linkman) {
		
		customerLinkmanDao.updateByPrimaryKeySelective(linkman);
		updateCustomerLinkManStatus(linkman);
		
		//添加日志
		SLog log = new SLog();
		log.setLogContent("用户" + linkman.getCreateUserName() + "修改客户联系人" + linkman.getName());
		log.setLogTime(new Date());
		log.setOperateUserId(linkman.getCreateUserId());
		log.setTitle("修改客户联系人");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);
	}

	@Override
	public SCustomerLinkman getCustomerLinkmanById(Integer linkmanId) {
		
		return customerLinkmanDao.selectByPrimaryKey(linkmanId);
	}

	@Override
	public List<SCustomerLinkman> getLinkmanListByCustomerIds(Long pageNumber,
			int pageSize, String customerIds, SCustomerLinkman linkman) {
		
		return customerLinkmanDao.getLinkmanListByCustomerIds(pageNumber, pageSize, customerIds, linkman);
	}

	@Override
	public Integer getLinkmanCountByCustomerIds(String customerIds, SCustomerLinkman linkman) {
		
		return customerLinkmanDao.getLinkmanCountByCustomerIds(customerIds, linkman);
	}

	/* (non-Javadoc)
	 * @see com.sales.service.CustomerLinkmanService#getLinkmanPerfection(java.lang.Integer)
	 */
	@Override
	public double getLinkmanPerfection(Integer customerId) {
		List<CustomerLinkmanStatics> list =  customerLinkmanDao.
				getCustomerLinkmanStatis(customerId);
		double perfection = 0d;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			CustomerLinkmanStatics customerLinkmanStatics = (CustomerLinkmanStatics) iterator
					.next();
			if(customerLinkmanStatics.getLinkType() == 1 && customerLinkmanStatics.getCount()>0)
				perfection += 0.5;
			if(customerLinkmanStatics.getLinkType() == 2 && customerLinkmanStatics.getCount()>0)
				perfection += 0.5;
		}
		return perfection;
	}
	
	

}
