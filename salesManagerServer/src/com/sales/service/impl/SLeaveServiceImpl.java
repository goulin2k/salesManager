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
import com.sales.dao.SLeaveDAO;
import com.sales.dao.SOvertimeCollectionDAO;
import com.sales.dao.SPositionDAO;
import com.sales.model.SAnnualLeave;
import com.sales.model.SDepartment;
import com.sales.model.SLeave;
import com.sales.model.SOvertimeCollection;
import com.sales.model.SPosition;
import com.sales.model.SProcess;
import com.sales.model.SUser;
import com.sales.service.SLeaveService;

/**
 * @author Leo
 * 
 */
public class SLeaveServiceImpl implements SLeaveService {

	private SLeaveDAO leaveDao;
	
	private SDepartmentDAO departmentDao;
	
	private SOvertimeCollectionDAO overtimeCollectionDao;
	
	private SPositionDAO positionDao;
	
	/**
	 * @param positionDao the positionDao to set
	 */
	public void setPositionDao(SPositionDAO positionDao) {
		this.positionDao = positionDao;
	}

	/**
	 * @param overtimeCollectionDao the overtimeCollectionDao to set
	 */
	public void setOvertimeCollectionDao(SOvertimeCollectionDAO overtimeCollectionDao) {
		this.overtimeCollectionDao = overtimeCollectionDao;
	}

	/**
	 * @param departmentDao the departmentDao to set
	 */
	public void setDepartmentDao(SDepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}

	public void setLeaveDao(SLeaveDAO leaveDao) {
		this.leaveDao = leaveDao;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void addLeaveReceipt(SLeave leave,SUser loginUser) {
		if (leave.getLeaveId() == null) {
			if (leave.getStatus().intValue() == 2) {
				leave.setUserId(loginUser.getUserId());
				Integer leaveId = this.leaveDao.insert(leave);
			}else {
				leave.setUserId(loginUser.getUserId());
				leave.setLeaveTime(new Date());
//				leave.setStatus(0);
				leave.setLeaveCode(NormalFun.getNextCode(Constants.LEAVE_PREFIX, leaveDao.getMaxCode()));
				Integer leaveId = this.leaveDao.insert(leave);
				//如果是调休，需减掉对应的加班时数
				if (leave.getTypeId().intValue() == 7) {
					SOvertimeCollection userOtCollection = this.overtimeCollectionDao.selectByPrimaryKey(loginUser.getUserId());
					userOtCollection.setHoursCollection(userOtCollection.getHoursCollection().floatValue() - leave.getLeaveDays().floatValue());
					this.overtimeCollectionDao.updateByPrimaryKey(userOtCollection);
				}
				//如果是年假，需减掉对应的请假时数
				if (leave.getTypeId().intValue() == 3) {
					SAnnualLeave sal = this.leaveDao.getAnnualLeave(loginUser.getUserId());
					sal.setAnnualHours(sal.getAnnualHours().intValue() - leave.getLeaveDays().intValue());
					this.leaveDao.updateAnnualLeaveHours(sal);
				}
				
				SDepartment dept = this.departmentDao.getSDepartmentById(loginUser.getDepartmentId());
				ProcessEngine processEngine = Configuration.getProcessEngine();
				Map<String,Object> instanceVariables = new HashMap<String,Object>();
				instanceVariables.put("userName", loginUser.getUserName());
				instanceVariables.put("userId", String.valueOf(loginUser.getUserId()));
				instanceVariables.put("typeName", "休假单");
				instanceVariables.put("deptName", dept.getName());
				
				instanceVariables.put("leaveDays", leave.getLeaveDays());
				SPosition sp = positionDao.selectByPrimaryKey(loginUser.getPositionOrgId());
				instanceVariables.put("orgPosition", sp.getName());
				

				// 启动流程实例		
				ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey("leave",instanceVariables, String.valueOf(leaveId));
//				processEngine.getExecutionService().setVariables(processInstance.getId(), instanceVariables);
				
				List<Task> myTaskList = processEngine.getTaskService().findPersonalTasks(String.valueOf(loginUser.getUserId()));
				for(Task task: myTaskList) {
					if (task.getExecutionId().equals(processInstance.getId())) {
						//processEngine.getTaskService().setVariables(task.getId(), instanceVariables);
						processEngine.getTaskService().completeTask(task.getId(), "申请");
						break;
					}
				}

			}
		} else {
            if (leave.getStatus().intValue() == 2) {
            	this.leaveDao.updateByPrimaryKeySelective(leave);
			}else {
				leave.setLeaveTime(new Date());
				leave.setLeaveCode(NormalFun.getNextCode(Constants.LEAVE_PREFIX, leaveDao.getMaxCode()));
				this.leaveDao.updateByPrimaryKeySelective(leave);
				//如果是调休，需减掉对应的加班时数
				if (leave.getTypeId().intValue() == 7) {
					SOvertimeCollection userOtCollection = this.overtimeCollectionDao.selectByPrimaryKey(loginUser.getUserId());
					userOtCollection.setHoursCollection(userOtCollection.getHoursCollection().floatValue() - leave.getLeaveDays().floatValue());
					this.overtimeCollectionDao.updateByPrimaryKey(userOtCollection);
				}
				//如果是年假，需减掉对应的请假时数
				if (leave.getTypeId().intValue() == 3) {
					SAnnualLeave sal = this.leaveDao.getAnnualLeave(loginUser.getUserId());
					sal.setAnnualHours(sal.getAnnualHours().intValue() - leave.getLeaveDays().intValue());
					this.leaveDao.updateAnnualLeaveHours(sal);
				}
				
				SDepartment dept = this.departmentDao.getSDepartmentById(loginUser.getDepartmentId());
				ProcessEngine processEngine = Configuration.getProcessEngine();
				Map<String,Object> instanceVariables = new HashMap<String,Object>();
				instanceVariables.put("userName", loginUser.getUserName());
				instanceVariables.put("userId", String.valueOf(loginUser.getUserId()));
				instanceVariables.put("typeName", "休假单");
				instanceVariables.put("deptName", dept.getName());
				
				instanceVariables.put("leaveDays", leave.getLeaveDays());
				SPosition sp = positionDao.selectByPrimaryKey(loginUser.getPositionOrgId());
				instanceVariables.put("orgPosition", sp.getName());
				

				// 启动流程实例		
				ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey("leave",instanceVariables, String.valueOf(leave.getLeaveId()));
//				processEngine.getExecutionService().setVariables(processInstance.getId(), instanceVariables);
				
				List<Task> myTaskList = processEngine.getTaskService().findPersonalTasks(String.valueOf(loginUser.getUserId()));
				for(Task task: myTaskList) {
					if (task.getExecutionId().equals(processInstance.getId())) {
						//processEngine.getTaskService().setVariables(task.getId(), instanceVariables);
						processEngine.getTaskService().completeTask(task.getId(), "申请");
						break;
					}
				}

			}
		}

	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void updateLeaveReceipt(SLeave leave) {
		this.leaveDao.updateByPrimaryKeySelective(leave);
		//如果是调休，需减掉对应的加班时数
		if (leave.getTypeId().intValue() == 7) {
			SOvertimeCollection userOtCollection = this.overtimeCollectionDao.selectByPrimaryKey(leave.getUserId());
			userOtCollection.setHoursCollection(userOtCollection.getHoursCollection().floatValue() - leave.getLeaveDays().floatValue()*8);
			this.overtimeCollectionDao.updateByPrimaryKey(userOtCollection);
		}
		//如果是年假，需减掉对应的请假时数
		if (leave.getTypeId().intValue() == 3) {
			SAnnualLeave sal = this.leaveDao.getAnnualLeave(leave.getUserId());
			sal.setAnnualHours(sal.getAnnualHours().intValue() - leave.getLeaveDays().intValue());
			this.leaveDao.updateAnnualLeaveHours(sal);
		}
		String instanceId = "leave."+leave.getLeaveId();
		ProcessEngine processEngine = Configuration.getProcessEngine();
		List<Task> myTaskList = processEngine.getTaskService().findPersonalTasks(String.valueOf(leave.getUserId()));
		for(Task task: myTaskList) {
			if (task.getExecutionId().equals(instanceId)) {
				processEngine.getTaskService().completeTask(task.getId(), "申请");
				break;
			}
		}
	}

	@Override
	public List<SLeave> getLeaveListPage(SLeave leave) {
		return this.leaveDao.getLeaveListPage(leave);
	}

	@Override
	public Integer getLeaveListPageCount(SLeave leave) {
		return this.leaveDao.getLeaveListPageCount(leave);
	}

	@Override
	public SLeave getLeaveReceipt(Integer leaveId) {
		return this.leaveDao.selectByPrimaryKey(leaveId);
	}

	@Override
	public void updateLeaveReceiptById(SLeave leave) {
		this.leaveDao.updateByPrimaryKeySelective(leave);
	}

	@Override
	public List<SProcess> getProcessListPage(SProcess process) {
		return this.leaveDao.getProcessListPage(process);
	}

	@Override
	public Integer getProcessListPageCount(SProcess process) {
		return this.leaveDao.getProcessListPageCount(process);
	}

	@Override
	public void insertProcessHistory(SProcess process) {
		this.leaveDao.insertProcessHistory(process);
	}

	@Override
	public List<SProcess> getProcessHistoryListPage(SProcess process) {
		return this.leaveDao.getProcessHistoryListPage(process);
	}

	@Override
	public Integer getProcessHistoryListPageCount(SProcess process) {
		return this.leaveDao.getProcessHistoryListPageCount(process);
	}

	@Override
	public void deleteLeaveReceipt(Integer leaveId) {
		this.leaveDao.deleteByPrimaryKey(leaveId);
	}

	@Override
	public Integer getAnnualLeaveHours(Integer userId) {
		Integer annual = leaveDao.getAnnualLeaveHours(userId);
		if(annual == null)
			annual = 0;
		return annual;
	}

	@Override
	public SAnnualLeave getAnnualLeave(Integer userId) {
		return leaveDao.getAnnualLeave(userId);
	}

	@Override
	public int updateAnnualLeaveHours(SAnnualLeave sal) {
		return leaveDao.updateAnnualLeaveHours(sal);
	}

	

}
