/**
 * 
 */
package com.sales.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sales.common.Constants;
import com.sales.common.NormalFun;
import com.sales.dao.SDepartmentDAO;
import com.sales.dao.SPositionDAO;
import com.sales.dao.STripApplicationDAO;
import com.sales.model.SDepartment;
import com.sales.model.SPosition;
import com.sales.model.STripApplication;
import com.sales.model.SUser;
import com.sales.service.STripApplicationService;

/**
 * @author Leo
 *
 */
public class STripApplicationServiceImpl implements STripApplicationService {
	
	private STripApplicationDAO tripApplicationDao;
	
	private SDepartmentDAO departmentDao;

	private SPositionDAO positionDao;
	
	/**
	 * @param positionDao the positionDao to set
	 */
	public void setPositionDao(SPositionDAO positionDao) {
		this.positionDao = positionDao;
	}
	/**
	 * @param departmentDao the departmentDao to set
	 */
	public void setDepartmentDao(SDepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}

	public void setTripApplicationDao(STripApplicationDAO tripApplicationDao) {
		this.tripApplicationDao = tripApplicationDao;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.STripApplicationService#addTripApplication(com.sales.model.STripApplication)
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void addTripApplication(STripApplication ta,SUser loginUser) {
		if(ta.getTripId() == null) {
			if (ta.getStatus().intValue() == 2) {
				ta.setUserId(loginUser.getUserId());
				Integer tripId = this.tripApplicationDao.insert(ta);
			} else {
				ta.setUserId(loginUser.getUserId());
				ta.setTripTime(new Date());
				ta.setTripCode(NormalFun.getNextCode(Constants.TRIP_APP_PREFIX, tripApplicationDao.getMaxCode()));
				Integer tripId = this.tripApplicationDao.insert(ta);
				SDepartment dept = this.departmentDao.getSDepartmentById(loginUser.getDepartmentId());
				ProcessEngine processEngine = Configuration.getProcessEngine();
				Map<String,Object> instanceVariables = new HashMap<String,Object>();
				instanceVariables.put("userName", loginUser.getUserName());
				instanceVariables.put("userId", String.valueOf(loginUser.getUserId()));
				instanceVariables.put("typeName", "出差申请单");
				instanceVariables.put("deptName", dept.getName());
				
				SPosition sp = positionDao.selectByPrimaryKey(loginUser.getPositionOrgId());
				instanceVariables.put("orgPosition", sp.getName());
				

				// 启动流程实例		
				ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey("tripApp",instanceVariables, String.valueOf(tripId));
			
				List<Task> myTaskList = processEngine.getTaskService().findPersonalTasks(String.valueOf(loginUser.getUserId()));
				for(Task task: myTaskList) {
					if (task.getExecutionId().equals(processInstance.getId())) {
						processEngine.getTaskService().completeTask(task.getId(), "申请");
						break;
					}
				}
			}
		}else {
            if (ta.getStatus().intValue() == 2) {
				this.tripApplicationDao.updateByPrimaryKeySelective(ta);
			} else {
				ta.setTripTime(new Date());
				ta.setTripCode(NormalFun.getNextCode(Constants.TRIP_APP_PREFIX, tripApplicationDao.getMaxCode()));
				this.tripApplicationDao.updateByPrimaryKeySelective(ta);
				
				SDepartment dept = this.departmentDao.getSDepartmentById(loginUser.getDepartmentId());
				ProcessEngine processEngine = Configuration.getProcessEngine();
				Map<String,Object> instanceVariables = new HashMap<String,Object>();
				instanceVariables.put("userName", loginUser.getUserName());
				instanceVariables.put("userId", String.valueOf(loginUser.getUserId()));
				instanceVariables.put("typeName", "出差申请单");
				instanceVariables.put("deptName", dept.getName());
				
				SPosition sp = positionDao.selectByPrimaryKey(loginUser.getPositionOrgId());
				instanceVariables.put("orgPosition", sp.getName());
				

				// 启动流程实例		
				ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey("tripApp",instanceVariables, String.valueOf(ta.getTripId()));
			
				List<Task> myTaskList = processEngine.getTaskService().findPersonalTasks(String.valueOf(loginUser.getUserId()));
				for(Task task: myTaskList) {
					if (task.getExecutionId().equals(processInstance.getId())) {
						processEngine.getTaskService().completeTask(task.getId(), "申请");
						break;
					}
				}
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see com.sales.service.STripApplicationService#updateTripApplication(com.sales.model.STripApplication)
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateTripApplication(STripApplication ta) {
		this.tripApplicationDao.updateByPrimaryKeySelective(ta);
		String instanceId = "tripApp."+ta.getTripId();
		ProcessEngine processEngine = Configuration.getProcessEngine();
		List<Task> myTaskList = processEngine.getTaskService().findPersonalTasks(String.valueOf(ta.getUserId()));
		for(Task task: myTaskList) {
			if (task.getExecutionId().equals(instanceId)) {
				processEngine.getTaskService().completeTask(task.getId(), "申请");
				break;
			}
		}
	}

	@Override
	public STripApplication getTripApplication(Integer tripId) {
		return this.tripApplicationDao.selectByPrimaryKey(tripId);
	}

	@Override
	public List<STripApplication> getTripApplicationListPage(STripApplication ta) {
		return this.tripApplicationDao.getTripApplicationListPage(ta);
	}

	@Override
	public Integer getTripApplicationListPageCount(STripApplication ta) {
		return this.tripApplicationDao.getTripApplicationListPageCount(ta);
	}

	@Override
	public List<STripApplication> getTripApplicationListForReimbursement(Integer userId) {
		return this.tripApplicationDao.getTripApplicationListForReimbursement(userId);
	}

	@Override
	public void updateTripApplicationById(STripApplication ta) {
		this.tripApplicationDao.updateByPrimaryKeySelective(ta);
	}
	@Override
	public void deleteTrip(Integer tripId) {
		this.tripApplicationDao.deleteByPrimaryKey(tripId);
	}

}
