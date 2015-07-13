/**
 * 
 */
package com.sales.service;

import java.util.List;

import com.sales.model.SExpenseApplication;
import com.sales.model.SExpenseReimbursement;
import com.sales.model.STripApplication;
import com.sales.model.SUser;

/**
 * @author Leo
 *
 */
public interface STripApplicationService {
	
	public void addTripApplication(STripApplication ta,SUser loginUser);
	
	public void updateTripApplication(STripApplication ta);
	
    public List<STripApplication> getTripApplicationListPage(STripApplication ta);
	
	public Integer getTripApplicationListPageCount(STripApplication ta);
	
	public STripApplication getTripApplication(Integer tripId);
	
	public List<STripApplication> getTripApplicationListForReimbursement(Integer userId);
	
	public void updateTripApplicationById(STripApplication ta);

	public void deleteTrip(Integer tripId);
}
