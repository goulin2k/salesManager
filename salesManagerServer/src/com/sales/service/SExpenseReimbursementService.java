/**
 * 
 */
package com.sales.service;

import java.util.List;

import com.sales.model.SExpenseReimbursement;
import com.sales.model.SUser;

/**
 * @author Leo
 *
 */
public interface SExpenseReimbursementService {
	
	public void addExpenseReimbursement(SExpenseReimbursement er, SUser loginUser);
	
	public void updateExpenseReimbursement(SExpenseReimbursement er);
	
    public List<SExpenseReimbursement> getExpenseReimbursementListPage(SExpenseReimbursement er);
	
	public Integer getExpenseReimbursementListPageCount(SExpenseReimbursement er);
	
	public SExpenseReimbursement getExpenseReimbursement(Integer erId);
	
	public void updateExpenseReimbursementById(SExpenseReimbursement er);
	
	public void deleteEr(Integer erId);

}
