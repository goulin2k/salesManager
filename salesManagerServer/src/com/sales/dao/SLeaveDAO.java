package com.sales.dao;

import java.util.List;

import com.sales.model.SAnnualLeave;
import com.sales.model.SLeave;
import com.sales.model.SProcess;

public interface SLeaveDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_leave
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    Integer insert(SLeave record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_leave
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    int updateByPrimaryKey(SLeave record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_leave
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    int updateByPrimaryKeySelective(SLeave record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_leave
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    SLeave selectByPrimaryKey(Integer leaveId);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_leave
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    int deleteByPrimaryKey(Integer leaveId);
    
    public List<SLeave> getLeaveListPage(SLeave leave);
	
	public Integer getLeaveListPageCount(SLeave leave);
	
    public List<SProcess> getProcessListPage(SProcess process);
	
	public Integer getProcessListPageCount(SProcess process);
	
	public void insertProcessHistory(SProcess process);
	
    public List<SProcess> getProcessHistoryListPage(SProcess process);
	
	public Integer getProcessHistoryListPageCount(SProcess process);
	
	public String getMaxCode();
	
	public Integer getAnnualLeaveHours(Integer userId);
	
	public int updateAnnualLeaveHours(SAnnualLeave sal);
	
	public SAnnualLeave getAnnualLeave(Integer userId);
}