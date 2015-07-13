package com.sales.service.impl;

import java.util.List;

import com.sales.dao.SEnumerationDAO;
import com.sales.service.EnumerationService;

public class EnumerationServiceImpl implements EnumerationService {
	
	private SEnumerationDAO enumerationDao;
	
	public List getEnumerationByType(Integer enumerationType){
		List enumerationList = enumerationDao.selectEnumerationByType(enumerationType);
		return enumerationList;
	}

	public SEnumerationDAO getEnumerationDao() {
		return enumerationDao;
	}

	public void setEnumerationDao(SEnumerationDAO enumerationDao) {
		this.enumerationDao = enumerationDao;
	}

}
