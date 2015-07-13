/**
 * 
 */
package com.sales.service;

import java.util.List;

import com.sales.model.SCarRepairReimbursement;
import com.sales.model.SUser;

/**
 * @author Leo
 *
 */
public interface SCarRepairReimbursementService {
	
	public void addCarRepairReimbursement(SCarRepairReimbursement crr,SUser loginUser);
	
	public void updateCarRepairReimbursement(SCarRepairReimbursement crr);
	
	public List<SCarRepairReimbursement> getCarReimbursementListPage(SCarRepairReimbursement cr);
    
	public Integer getCarReimbursementListPageCount(SCarRepairReimbursement cr);
	
    public SCarRepairReimbursement getCarReimbursement(Integer crId);
	
	public void updateCarReimbursementById(SCarRepairReimbursement cr);
	
	public void deleteCr(Integer crId);

}
