/**
 * 
 */
package com.sales.service;

import java.util.List;

import com.sales.model.SOvertime;
import com.sales.model.SOvertimeCollection;
import com.sales.model.SUser;

/**
 * @author Leo
 * 
 */
public interface SOvertimeService {

	public void addOvertimeReceipt(SOvertime overtime,SUser loginUser);

	public void updateOvertimeReceipt(SOvertime overtime);

	public List<SOvertime> getOvertimeListPage(SOvertime overtime);

	public Integer getOvertimeListPageCount(SOvertime overtime);

	public SOvertime getOvertimeReceipt(Integer otId);
	
	public void updateOvertimeReceiptById(SOvertime overtime);
	
	public void updateUserOvertimeCollection(SOvertimeCollection otCollection);
	
	public SOvertimeCollection getOtCollection(Integer userId);
	
	public void updateOvertimeCollection(SOvertimeCollection otCollection);
	
	public void deleteOvertime(Integer otId);

}
