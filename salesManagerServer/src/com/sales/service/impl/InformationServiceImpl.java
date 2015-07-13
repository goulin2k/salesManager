package com.sales.service.impl;

import java.util.List;

import com.sales.dao.SInformationDAO;
import com.sales.model.SInformation;
import com.sales.model.SimpleNameValue;
import com.sales.service.InformationService;

/** 
 * @author 
 * @version 创建时间：2013-5-2 下午10:34:18 
 *  
 */
public class InformationServiceImpl implements InformationService{
	
	private SInformationDAO informationDao;

	public SInformationDAO getInformationDao() {
		return informationDao;
	}

	public void setInformationDao(SInformationDAO informationDao) {
		this.informationDao = informationDao;
	}

	@Override
	public void deleteInfo(Integer infoId) {
		
		informationDao.deleteByPrimaryKey(infoId);
		
	}

	@Override
	public List<SInformation> getInfoListByUserId(Integer sendUserId, 
			Integer type, Integer status,
			Long pageNumber, int pageSize) {
		
		return informationDao.getInfoListByUserId(
				sendUserId,type, status, pageNumber,pageSize, false);
	}

	@Override
	public void insertInfo(SInformation info) {
		
		informationDao.insert(info);
		
	}

	@Override
	public void updateInfo(SInformation info) {
		
		informationDao.updateByPrimaryKeySelective(info);
		
	}
	

	@Override
	public void setAllReaded(Integer userId) {
		informationDao.updateStatusByUserId(userId, SInformation.STATUS_READED);
		
	}

	/* (non-Javadoc)
	 * @see com.sales.service.InformationService#updateInfo(java.lang.String, int)
	 */
	@Override
	public void updateInfo(String url, int userId, int status) {
		informationDao.updateStatusByUrl(url, userId, status);		
	}

	@Override
	public Integer getInfoCountByUserId(Integer sendUserId, 
			Integer type, Integer status) {
		
		Integer infoCount = this.informationDao.getInfoCountByUserId(sendUserId, type, status, false);
		return infoCount;
	}

	@Override
	public List<SInformation> getInfoListByStatus(Integer status, Integer sendUserId) {
		
		
		List infoList = this.informationDao.getInfoListByStatus(status, sendUserId);
		return infoList;
	}

	@Override
	public SInformation getInformationById(Integer infoId) {
		
		SInformation sInformation = this.informationDao.selectByPrimaryKey(infoId);
		return sInformation;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.InformationService#getProcInfoCountByUserId(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Integer getProcInfoCountByUserId(Integer sendUserId, Integer type,
			Integer status) {
		Integer infoCount = this.informationDao.getInfoCountByUserId(
				sendUserId, type, status, true);
		return infoCount;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.InformationService#getProcInfoListByStatus(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<SInformation> getProcInfoListByUserId(Integer sendUserId, 
			Integer type, Integer status,
			Long pageNumber, int pageSize) {
		return informationDao.getInfoListByUserId(
				sendUserId,type, status, pageNumber,pageSize, true);
	}

	/* (non-Javadoc)
	 * @see com.sales.service.InformationService#getUnreadedInfoGroupCounts(java.lang.Integer)
	 */
	@Override
	public List<SimpleNameValue> getUnreadedInfoGroupCounts(Integer userId) {
		return informationDao.getUnreadedInfoGroupCounts(userId);
		
	}
	
	

}
