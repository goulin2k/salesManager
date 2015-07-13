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
import com.sales.dao.SCarRepairApplicationDAO;
import com.sales.dao.SDepartmentDAO;
import com.sales.dao.SPositionDAO;
import com.sales.model.SCarRepairApplication;
import com.sales.model.SDepartment;
import com.sales.model.SPosition;
import com.sales.model.SUser;
import com.sales.service.SCarRepairApplicationService;

/**
 * @author Leo
 *
 */
public class SCarRepairApplicationServiceImpl implements SCarRepairApplicationService {
	
	private SCarRepairApplicationDAO carRepairApplicationDao;
	
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

	public void setCarRepairApplicationDao(SCarRepairApplicationDAO carRepairApplicationDao) {
		this.carRepairApplicationDao = carRepairApplicationDao;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void addCarRepairApplication(SCarRepairApplication ca, SUser loginUser) {
		if (ca.getCarApplicationId() == null) {
			if (ca.getStatus().intValue() == 2) {
				ca.setUserId(loginUser.getUserId());
				Integer caId = this.carRepairApplicationDao.insert(ca);
			} else {
				ca.setUserId(loginUser.getUserId());
				ca.setApplicationTime(new Date());
				ca.setCarApplicationCode(NormalFun.getNextCode(Constants.CAR_APP_PREFIX, carRepairApplicationDao.getMaxCode()));
				Integer caId = this.carRepairApplicationDao.insert(ca);
				SDepartment dept = this.departmentDao.getSDepartmentById(loginUser.getDepartmentId());
				ProcessEngine processEngine = Configuration.getProcessEngine();
				Map<String,Object> instanceVariables = new HashMap<String,Object>();
				instanceVariables.put("userName", loginUser.getUserName());
				instanceVariables.put("userId", String.valueOf(loginUser.getUserId()));
				instanceVariables.put("typeName", "车辆维修申请单");
				instanceVariables.put("deptName", dept.getName());
				
				SPosition sp = positionDao.selectByPrimaryKey(loginUser.getPositionOrgId());
				instanceVariables.put("orgPosition", sp.getName());

				// 启动流程实例		
				ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey("carRepairApp",instanceVariables, String.valueOf(caId));
			
				List<Task> myTaskList = processEngine.getTaskService().findPersonalTasks(String.valueOf(loginUser.getUserId()));
				for(Task task: myTaskList) {
					if (task.getExecutionId().equals(processInstance.getId())) {
						processEngine.getTaskService().completeTask(task.getId(), "申请");
						break;
					}
				}
			}
		} else {
            if (ca.getStatus().intValue() == 2) {
				this.carRepairApplicationDao.updateByPrimaryKeySelective(ca);
			} else {
				ca.setApplicationTime(new Date());
				ca.setCarApplicationCode(NormalFun.getNextCode(Constants.CAR_APP_PREFIX, carRepairApplicationDao.getMaxCode()));
				this.carRepairApplicationDao.updateByPrimaryKeySelective(ca);
				
				SDepartment dept = this.departmentDao.getSDepartmentById(loginUser.getDepartmentId());
				ProcessEngine processEngine = Configuration.getProcessEngine();
				Map<String,Object> instanceVariables = new HashMap<String,Object>();
				instanceVariables.put("userName", loginUser.getUserName());
				instanceVariables.put("userId", String.valueOf(loginUser.getUserId()));
				instanceVariables.put("typeName", "车辆维修申请单");
				instanceVariables.put("deptName", dept.getName());
				
				SPosition sp = positionDao.selectByPrimaryKey(loginUser.getPositionOrgId());
				instanceVariables.put("orgPosition", sp.getName());

				// 启动流程实例		
				ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey("carRepairApp",instanceVariables, String.valueOf(ca.getCarApplicationId()));
			
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

	@Transactional(propagation=Propagation.REQUIRED)
	public void updateCarRepairApplication(SCarRepairApplication ca) {
		this.carRepairApplicationDao.updateByPrimaryKeySelective(ca);
		String instanceId = "carRepairApp."+ca.getCarApplicationId();
		ProcessEngine processEngine = Configuration.getProcessEngine();
		List<Task> myTaskList = processEngine.getTaskService().findPersonalTasks(String.valueOf(ca.getUserId()));
		for(Task task: myTaskList) {
			if (task.getExecutionId().equals(instanceId)) {
				processEngine.getTaskService().completeTask(task.getId(), "申请");
				break;
			}
		}
	}

	@Override
	public SCarRepairApplication getCarApplication(Integer caId) {
		return this.carRepairApplicationDao.selectByPrimaryKey(caId);
	}

	@Override
	public List<SCarRepairApplication> getCarApplicationListPage(SCarRepairApplication ca) {
		return this.carRepairApplicationDao.getCarApplicationListPage(ca);
	}

	@Override
	public Integer getCarApplicationListPageCount(SCarRepairApplication ca) {
		return this.carRepairApplicationDao.getCarApplicationListPageCount(ca);
	}

	@Override
	public void updateCarApplicationById(SCarRepairApplication ca) {
		this.carRepairApplicationDao.updateByPrimaryKeySelective(ca);
	}

	@Override
	public List<SCarRepairApplication> getCarApplicationListForReimbursement(Integer userId) {
		return this.carRepairApplicationDao.getCarApplicationListForReimbursement(userId);
	}

	@Override
	public void deleteCa(Integer caId) {
		this.carRepairApplicationDao.deleteByPrimaryKey(caId);
	}

}
