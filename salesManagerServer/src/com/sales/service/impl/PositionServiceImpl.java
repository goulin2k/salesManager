package com.sales.service.impl;

import java.util.List;

import com.sales.dao.SPositionDAO;
import com.sales.model.SPosition;
import com.sales.service.PositionService;

/** 
 * @author  
 * @version 创建时间：2013-6-4 下午10:35:50 
 *  
 */
public class PositionServiceImpl implements PositionService {
	
	private SPositionDAO positionDao;

	public SPositionDAO getPositionDao() {
		return positionDao;
	}

	public void setPositionDao(SPositionDAO positionDao) {
		this.positionDao = positionDao;
	}

	@Override
	public List<SPosition> positionListByType(Integer type) {
		// TODO Auto-generated method stub
		List<SPosition> positionList = this.positionDao.getPositionListByType(type);
		return positionList;
	}

}
