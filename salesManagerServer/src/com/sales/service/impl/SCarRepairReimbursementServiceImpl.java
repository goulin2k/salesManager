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
import com.sales.dao.SCarRepairReimbursementDAO;
import com.sales.dao.SDepartmentDAO;
import com.sales.dao.SPositionDAO;
import com.sales.model.SCarRepairReimbursement;
import com.sales.model.SDepartment;
import com.sales.model.SPosition;
import com.sales.model.SUser;
import com.sales.service.SCarRepairReimbursementService;

/**
 * @author Leo
 *
 */
public class SCarRepairReimbursementServiceImpl implements SCarRepairReimbursementService {
	
	private SCarRepairReimbursementDAO carRepairReimbursementDao;
	
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

	/**
	 * @param carRepairReimbursementDao the carRepairReimbursementDao to set
	 */
	public void setCarRepairReimbursementDao(SCarRepairReimbursementDAO carRepairReimbursementDao) {
		this.carRepairReimbursementDao = carRepairReimbursementDao;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.SCarRepairReimbursementService#addCarRepairReimbursement(com.sales.model.SCarRepairReimbursement)
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void addCarRepairReimbursement(SCarRepairReimbursement cr,SUser loginUser) {
		if (cr.getCarReimbursementId() == null) {
			if (cr.getStatus().intValue() == 2) {
				cr.setUserId(loginUser.getUserId());
				Integer crId = this.carRepairReimbursementDao.insert(cr);
			} else {
				cr.setUserId(loginUser.getUserId());
				cr.setReimbursementTime(new Date());
				cr.setCarReimbursementCode(NormalFun.getNextCode(Constants.CAR_REI_PREFIX, carRepairReimbursementDao.getMaxCode()));
				Integer crId = this.carRepairReimbursementDao.insert(cr);
				SDepartment dept = this.departmentDao.getSDepartmentById(loginUser.getDepartmentId());
				ProcessEngine processEngine = Configuration.getProcessEngine();
				Map<String,Object> instanceVariables = new HashMap<String,Object>();
				instanceVariables.put("userName", loginUser.getUserName());
				instanceVariables.put("userId", String.valueOf(loginUser.getUserId()));
				instanceVariables.put("typeName", "车辆维修报销单");
				instanceVariables.put("deptName", dept.getName());
				
				SPosition sp = positionDao.selectByPrimaryKey(loginUser.getPositionOrgId());
				instanceVariables.put("orgPosition", sp.getName());

				// 启动流程实例		
				ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey("carRepairRei",instanceVariables, String.valueOf(crId));
			
				List<Task> myTaskList = processEngine.getTaskService().findPersonalTasks(String.valueOf(loginUser.getUserId()));
				for(Task task: myTaskList) {
					if (task.getExecutionId().equals(processInstance.getId())) {
						processEngine.getTaskService().completeTask(task.getId(), "申请");
						break;
					}
				}
			}
		} else {
            if (cr.getStatus().intValue() == 2) {
            	this.carRepairReimbursementDao.updateByPrimaryKeySelective(cr);
			} else {
				cr.setReimbursementTime(new Date());
				cr.setCarReimbursementCode(NormalFun.getNextCode(Constants.CAR_REI_PREFIX, carRepairReimbursementDao.getMaxCode()));
				this.carRepairReimbursementDao.updateByPrimaryKeySelective(cr);
				
				SDepartment dept = this.departmentDao.getSDepartmentById(loginUser.getDepartmentId());
				ProcessEngine processEngine = Configuration.getProcessEngine();
				Map<String,Object> instanceVariables = new HashMap<String,Object>();
				instanceVariables.put("userName", loginUser.getUserName());
				instanceVariables.put("userId", String.valueOf(loginUser.getUserId()));
				instanceVariables.put("typeName", "车辆维修报销单");
				instanceVariables.put("deptName", dept.getName());
				
				SPosition sp = positionDao.selectByPrimaryKey(loginUser.getPositionOrgId());
				instanceVariables.put("orgPosition", sp.getName());

				// 启动流程实例		
				ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey("carRepairRei",instanceVariables, String.valueOf(cr.getCarReimbursementId()));
			
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
	 * @see com.sales.service.SCarRepairReimbursementService#updateCarRepairReimbursement(com.sales.model.SCarRepairReimbursement)
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateCarRepairReimbursement(SCarRepairReimbursement cr) {
		this.carRepairReimbursementDao.updateByPrimaryKeySelective(cr);
		String instanceId = "carRepairRei."+cr.getCarReimbursementId();
		ProcessEngine processEngine = Configuration.getProcessEngine();
		List<Task> myTaskList = processEngine.getTaskService().findPersonalTasks(String.valueOf(cr.getUserId()));
		for(Task task: myTaskList) {
			if (task.getExecutionId().equals(instanceId)) {
				processEngine.getTaskService().completeTask(task.getId(), "申请");
				break;
			}
		}
	}

	@Override
	public SCarRepairReimbursement getCarReimbursement(Integer crId) {
		return this.carRepairReimbursementDao.selectByPrimaryKey(crId);
	}

	@Override
	public List<SCarRepairReimbursement> getCarReimbursementListPage(SCarRepairReimbursement cr) {
		return this.carRepairReimbursementDao.getCarReimbursementListPage(cr);
	}

	@Override
	public Integer getCarReimbursementListPageCount(SCarRepairReimbursement cr) {
		return this.carRepairReimbursementDao.getCarReimbursementListPageCount(cr);
	}

	@Override
	public void updateCarReimbursementById(SCarRepairReimbursement cr) {
		this.carRepairReimbursementDao.updateByPrimaryKeySelective(cr);
	}
	@Override
	public void deleteCr(Integer crId) {
		this.carRepairReimbursementDao.deleteByPrimaryKey(crId);
	}

}
