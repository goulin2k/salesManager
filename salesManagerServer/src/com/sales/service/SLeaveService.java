/**
 * 
 */
package com.sales.service;

import java.util.List;

import com.sales.model.SAnnualLeave;
import com.sales.model.SLeave;
import com.sales.model.SProcess;
import com.sales.model.SUser;

/**
 * @author Leo
 *
 */
public interface SLeaveService {

	public void addLeaveReceipt(SLeave leave,SUser loginUser);
	
	public void updateLeaveReceipt(SLeave leave);
	
	public List<SLeave> getLeaveListPage(SLeave leave);
	
	public Integer getLeaveListPageCount(SLeave leave);
	
	public SLeave getLeaveReceipt(Integer leaveId);
	
	public void updateLeaveReceiptById(SLeave leave);
	
    public List<SProcess> getProcessListPage(SProcess process);
	
	public Integer getProcessListPageCount(SProcess process);
	
	public void insertProcessHistory(SProcess process);
	
    public List<SProcess> getProcessHistoryListPage(SProcess process);
	
	public Integer getProcessHistoryListPageCount(SProcess process);
	
	public void deleteLeaveReceipt(Integer leaveId);
	
	public Integer getAnnualLeaveHours(Integer userId);
	
    public int updateAnnualLeaveHours(SAnnualLeave sal);
	
	public SAnnualLeave getAnnualLeave(Integer userId);
}
