package com.sales.service.impl;

import java.util.List;

import com.sales.dao.SActionDAO;
import com.sales.model.SAction;
import com.sales.service.ActionService;

/** 
 * @author 
 * @version 创建时间：2013-5-4 上午09:50:02 
 *  
 */
public class ActionServiceImpl implements ActionService{
	
	private SActionDAO actionDao;

	public SActionDAO getActionDao() {
		return actionDao;
	}

	public void setActionDao(SActionDAO actionDao) {
		this.actionDao = actionDao;
	}

	@Override
	public List<SAction> getActionList() {
		// TODO Auto-generated method stub
		return actionDao.getActionList();
	}

}
