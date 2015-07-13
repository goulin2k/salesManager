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
import com.sales.dao.SOvertimeCollectionDAO;
import com.sales.dao.SOvertimeDAO;
import com.sales.dao.SPositionDAO;
import com.sales.model.SDepartment;
import com.sales.model.SOvertime;
import com.sales.model.SOvertimeCollection;
import com.sales.model.SPosition;
import com.sales.model.SUser;
import com.sales.service.SOvertimeService;

/**
 * @author Leo
 *
 */
public class SOvertimeServiceImpl implements SOvertimeService {
	
	private SOvertimeDAO overtimeDao;
	
	private SOvertimeCollectionDAO overtimeCollectionDao;
	
	private SDepartmentDAO departmentDao;
	
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

	public void setOvertimeDao(SOvertimeDAO overtimeDao) {
		this.overtimeDao = overtimeDao;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.SOvertimeService#addOvertimeReceipt()
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void addOvertimeReceipt(SOvertime overtime,SUser loginUser) {
		if (overtime.getOvertimeId() == null) {
			if (overtime.getStatus().intValue() == 2) {
				overtime.setUserId(loginUser.getUserId());
				Integer otId = this.overtimeDao.insert(overtime);
			}else {
				overtime.setUserId(loginUser.getUserId());
				overtime.setOvertimeTime(new Date());
//				overtime.setStatus(0);
				overtime.setOvertimeCode(NormalFun.getNextCode(Constants.OVERTIME_PREFIX, overtimeDao.getMaxCode()));
				Integer otId = this.overtimeDao.insert(overtime);
				
				SDepartment dept = this.departmentDao.getSDepartmentById(loginUser.getDepartmentId());
				ProcessEngine processEngine = Configuration.getProcessEngine();
				Map<String,Object> instanceVariables = new HashMap<String,Object>();
				instanceVariables.put("userName", loginUser.getUserName());
				instanceVariables.put("userId", String.valueOf(loginUser.getUserId()));
				instanceVariables.put("typeName", "加班单");
				instanceVariables.put("deptName", dept.getName());
				
				SPosition sp = positionDao.selectByPrimaryKey(loginUser.getPositionOrgId());
				instanceVariables.put("orgPosition", sp.getName());
				

				// 启动流程实例		
				ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey("overtime",instanceVariables, String.valueOf(otId));

				List<Task> myTaskList = processEngine.getTaskService().findPersonalTasks(String.valueOf(loginUser.getUserId()));
				for(Task task: myTaskList) {
					if (task.getExecutionId().equals(processInstance.getId())) {
						processEngine.getTaskService().completeTask(task.getId(), "申请");
						break;
					}
				}
			}
		} else {
            if (overtime.getStatus().intValue() == 2) {
				this.overtimeDao.updateByPrimaryKeySelective(overtime);
			}else {
				overtime.setOvertimeTime(new Date());
				overtime.setOvertimeCode(NormalFun.getNextCode(Constants.OVERTIME_PREFIX, overtimeDao.getMaxCode()));
				this.overtimeDao.updateByPrimaryKeySelective(overtime);
				
				SDepartment dept = this.departmentDao.getSDepartmentById(loginUser.getDepartmentId());
				ProcessEngine processEngine = Configuration.getProcessEngine();
				Map<String,Object> instanceVariables = new HashMap<String,Object>();
				instanceVariables.put("userName", loginUser.getUserName());
				instanceVariables.put("userId", String.valueOf(loginUser.getUserId()));
				instanceVariables.put("typeName", "加班单");
				instanceVariables.put("deptName", dept.getName());
				
				SPosition sp = positionDao.selectByPrimaryKey(loginUser.getPositionOrgId());
				instanceVariables.put("orgPosition", sp.getName());
				

				// 启动流程实例		
				ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey("overtime",instanceVariables, String.valueOf(overtime.getOvertimeId()));

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
	 * @see com.sales.service.SOvertimeService#updateOvertimeReceipt()
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateOvertimeReceipt(SOvertime overtime) {
		this.overtimeDao.updateByPrimaryKeySelective(overtime);
		String instanceId = "overtime."+overtime.getOvertimeId();
		ProcessEngine processEngine = Configuration.getProcessEngine();
		List<Task> myTaskList = processEngine.getTaskService().findPersonalTasks(String.valueOf(overtime.getUserId()));
		for(Task task: myTaskList) {
			if (task.getExecutionId().equals(instanceId)) {
				processEngine.getTaskService().completeTask(task.getId(), "申请");
				break;
			}
		}
	}

	@Override
	public List<SOvertime> getOvertimeListPage(SOvertime overtime) {
		return this.overtimeDao.getOvertimeListPage(overtime);
	}

	@Override
	public Integer getOvertimeListPageCount(SOvertime overtime) {
		return this.overtimeDao.getOvertimeListPageCount(overtime);
	}

	@Override
	public SOvertime getOvertimeReceipt(Integer otId) {
		return this.overtimeDao.selectByPrimaryKey(otId);
	}

	/**
	 * @param departmentDao the departmentDao to set
	 */
	public void setDepartmentDao(SDepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Override
	public void updateOvertimeReceiptById(SOvertime overtime) {
		this.overtimeDao.updateByPrimaryKeySelective(overtime);
	}

	@Override
	public void updateUserOvertimeCollection(SOvertimeCollection otCollection) {
		SOvertimeCollection userOtCollection = this.overtimeCollectionDao.selectByPrimaryKey(otCollection.getUserId());
		if (userOtCollection == null) {
			this.overtimeCollectionDao.insert(otCollection);
		} else {
			userOtCollection.setHoursCollection(userOtCollection.getHoursCollection().floatValue() + otCollection.getHoursCollection().floatValue());
			this.overtimeCollectionDao.updateByPrimaryKey(userOtCollection);
		}
	}

	@Override
	public SOvertimeCollection getOtCollection(Integer userId) {
		SOvertimeCollection userOtCollection = this.overtimeCollectionDao.selectByPrimaryKey(userId);
		return userOtCollection;
	}

	@Override
	public void updateOvertimeCollection(SOvertimeCollection otCollection) {
		this.overtimeCollectionDao.updateByPrimaryKey(otCollection);
	}

	@Override
	public void deleteOvertime(Integer otId) {
		this.overtimeDao.deleteByPrimaryKey(otId);
	}

}
