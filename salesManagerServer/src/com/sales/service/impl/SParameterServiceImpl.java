/**
 * 
 */
package com.sales.service.impl;

import java.util.List;

import com.sales.dao.SParameterDAO;
import com.sales.model.SParameter;
import com.sales.service.SParameterService;

/**
 * @author Leo
 *
 */
public class SParameterServiceImpl implements SParameterService {
	
	private SParameterDAO parameterDao;

	/**
	 * @param parameterDao the parameterDao to set
	 */
	public void setParameterDao(SParameterDAO parameterDao) {
		this.parameterDao = parameterDao;
	}

	@Override
	public void addParameter(SParameter param) {
		this.parameterDao.insert(param);
	}

	@Override
	public void deleteParameter(Integer id) {
		this.parameterDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<SParameter> getParameterListPage(SParameter param) {
		return this.parameterDao.getParameterListPage(param);
	}

	@Override
	public Integer getParameterListPageCount(SParameter param) {
		return this.parameterDao.getParameterListPageCount(param);
	}

	@Override
	public void updateParameter(SParameter param) {
		this.parameterDao.updateByPrimaryKeySelective(param);
	}

	@Override
	public SParameter getParameter(Integer id) {
		return this.parameterDao.selectByPrimaryKey(id);
	}

	@Override
	public SParameter getParameter(String name) {
		return this.parameterDao.selectByName(name);
	}

	

}
