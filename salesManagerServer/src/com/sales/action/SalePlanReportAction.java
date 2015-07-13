/**
 * 
 */
package com.sales.action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.sales.model.SUser;
import com.sales.model.SalePlanStatistics;
import com.sales.model.SalePlanWithActivityVO;
import com.sales.service.ProjectService;

/**
 * 销售计划完成情况统计
 * @author Administrator
 *
@SuppressWarnings("serial")
 */
public class SalePlanReportAction extends BaseAction {

	
	private static final long serialVersionUID = -1367798589050554425L;
	private ProjectService projectService;
	
	private Integer userId;
	private Date startTime;
	private Date endTime;
	private List<SalePlanStatistics> planList;
	private List<SalePlanWithActivityVO> planWithActivityVOs;
	
	private String userName;
	
	
	/**
	 * @param projectService the projectService to set
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	

	/**
	 * @return the planWithActivityVOs
	 */
	public List<SalePlanWithActivityVO> getPlanWithActivityVOs() {
		return planWithActivityVOs;
	}

	/**
	 * @return the planList
	 */
	public List<SalePlanStatistics> getPlanList() {
		return planList;
	}


	/**
	 * @param planList the planList to set
	 */
	public void setPlanList(List<SalePlanStatistics> planList) {
		this.planList = planList;
	}


	/**
	 * @param planWithActivityVOs the planWithActivityVOs to set
	 */
	public void setPlanWithActivityVOs(
			List<SalePlanWithActivityVO> planWithActivityVOs) {
		this.planWithActivityVOs = planWithActivityVOs;
	}

	

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}


	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}


	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


	/**
	 * @return the projectService
	 */
	public ProjectService getProjectService() {
		return projectService;
	}



	/**
	 * 周计划统计
	 * @return
	 */
	public String reportWeekly() {
		SUser sUser = getLoginUser();
		userId = sUser.getUserId();
		String childrenUserIds = getChildrenUserIds();
		
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(new Date());
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		if(startTime == null) {
			cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
			startTime = cal.getTime();
		}
		if(endTime == null) {
			cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek() + 6);
			endTime = cal.getTime();
		}
		
		planList = projectService.statisPlanReport(
				childrenUserIds, startTime, endTime);
		return "reportWeekly";
	}
	
	/**
	 * 个人周计划完成情况详情
	 * @return
	 */
	public String detailWeekly() {
		if(userId == null) {
			SUser sUser = getLoginUser();
			userId = sUser.getUserId();
		}
		try{
			if(request.getQueryString() != null && request.getQueryString().indexOf("userName") > 0) {
				String strPtname = request.getParameter("userName");
				userName = new String(strPtname.getBytes("ISO-8859-1"), "UTF-8"); 
			}
		}catch(UnsupportedEncodingException unse) {
			actionLog.error(unse.getMessage());
		}
		
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(new Date());
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		if(startTime == null) {
			cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
			startTime = cal.getTime();
		}
		if(endTime == null) {
			cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek() + 6);
			endTime = cal.getTime();
		}
		
		planWithActivityVOs = projectService.statisPersonPlanReport(
				userId, startTime, endTime);
		return "detailWeekly";
	}

}
