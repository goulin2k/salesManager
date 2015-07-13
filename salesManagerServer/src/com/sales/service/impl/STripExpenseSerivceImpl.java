/**
 * 
 */
package com.sales.service.impl;

import com.sales.dao.STripExpenseDAO;
import com.sales.model.STripExpense;
import com.sales.service.STripExpenseSerivce;

/**
 * @author Leo
 *
 */
public class STripExpenseSerivceImpl implements STripExpenseSerivce {
	
	private STripExpenseDAO tripExpenseDao;

	public void setTripExpenseDao(STripExpenseDAO tripExpenseDao) {
		this.tripExpenseDao = tripExpenseDao;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.STripExpenseSerivce#addTripExpense(com.sales.model.STripExpense)
	 */
	@Override
	public void addTripExpense(STripExpense te) {
		this.tripExpenseDao.insert(te);
	}

	/* (non-Javadoc)
	 * @see com.sales.service.STripExpenseSerivce#updateTripExpense(com.sales.model.STripExpense)
	 */
	@Override
	public void updateTripExpense(STripExpense te) {
		this.tripExpenseDao.updateByPrimaryKeySelective(te);
	}

}
