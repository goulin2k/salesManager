/**
 * 
 */
package com.sales.service;

import java.util.List;

import com.sales.model.SCarRepairApplication;
import com.sales.model.SExpenseApplication;
import com.sales.model.SUser;

/**
 * @author Leo
 *
 */
public interface SCarRepairApplicationService {
	
	public void addCarRepairApplication(SCarRepairApplication ca, SUser loginUser);
	
	public void updateCarRepairApplication(SCarRepairApplication cra);
	
    public List<SCarRepairApplication> getCarApplicationListPage(SCarRepairApplication ca);
	
	public Integer getCarApplicationListPageCount(SCarRepairApplication ca);
	
	public SCarRepairApplication getCarApplication(Integer caId);
	
	public void updateCarApplicationById(SCarRepairApplication ca);
	
	public List<SCarRepairApplication> getCarApplicationListForReimbursement(Integer userId);
	
	public void deleteCa(Integer caId);

}
