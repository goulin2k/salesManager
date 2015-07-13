/**
 * 
 */
package com.sales.action;

import java.util.Date;
import java.util.List;

import com.sales.model.SSalesProject;
import com.sales.service.ProjectService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.sales.model.CalendarTask;
import com.sales.model.SModule;
import com.sales.model.SSalesProject;
import com.sales.service.ProjectService;

/**
 * @author Administrator
 * 
 */
public class WorkCalendaAction extends BaseAction {
	private ProjectService projectService;
	private String startTime;
	private String endTime;
	private int userId;

	
	/**
	 * @return the projectService
	 */
	public ProjectService getProjectService() {
		return projectService;
	}

	/**
	 * @param projectService the projectService to set
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String view() {
		return "view";
	}
	
	/**
	 * @return
	 */
	public String getProjectsJson() {
		
		return null;
	}

	/**
	 * 查询获取Json格式的用户任务列表
	 * @return
	 */
	public String getProjectsList() {
		try {
			List<SSalesProject> projList = new ArrayList<SSalesProject>();
			userId = this.getLoginUser().getUserId();
			projList = this.projectService.getProjectList(userId, startTime,
					endTime, false);
			List<CalendarTask> taskList = new ArrayList<CalendarTask>();
			for (Iterator iterator = projList.iterator(); iterator.hasNext();) {
				SSalesProject project = (SSalesProject) iterator.next();
				CalendarTask task = new CalendarTask();
				task.setId(project.getProjectId());
				task.setStart(project.getStartTime());
				task.setEnd(project.getEndTime());
				task.setTitle(project.getGoal());
				task.setUrl("/salesManager/project!show?project.projectId=" + project.getProjectId());
				task.setAllDay(true);
				task.setCompleted(!(project.getCompletionTime() == null));
				taskList.add(task);
			}
			JSONArray jsonArray = JSONArray.fromObject(taskList);
			response = (HttpServletResponse) ActionContext.getContext().get(
					ServletActionContext.HTTP_RESPONSE);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
