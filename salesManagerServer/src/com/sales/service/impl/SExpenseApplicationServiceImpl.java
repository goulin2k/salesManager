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
import com.sales.dao.SExpenseApplicationDAO;
import com.sales.dao.SPositionDAO;
import com.sales.model.SDepartment;
import com.sales.model.SExpenseApplication;
import com.sales.model.SPosition;
import com.sales.model.SUser;
import com.sales.service.SExpenseApplicationService;

/**
 * @author Leo
 *
 */
public class SExpenseApplicationServiceImpl implements SExpenseApplicationService {
	
	private SExpenseApplicationDAO expenseApplicationDao;
	
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

	public void setExpenseApplicationDao(SExpenseApplicationDAO expenseApplicationDao) {
		this.expenseApplicationDao = expenseApplicationDao;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.SExpenseApplicationService#addExpenseApplication(com.sales.model.SExpenseApplication)
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void addExpenseApplication(SExpenseApplication ea, SUser loginUser) {
		if(ea.getExpenseApplicationId() == null) {
			if (ea.getStatus().intValue() == 2) {
				ea.setExpenseUserId(loginUser.getUserId());
				Integer eaId = this.expenseApplicationDao.insert(ea);
			}else {
				ea.setExpenseUserId(loginUser.getUserId());
				ea.setExpenseTime(new Date());
				ea.setExpenseApplicationCode(NormalFun.getNextCode(Constants.EXPENSE_APP_PREFIX, expenseApplicationDao.getMaxCode()));
				Integer eaId = this.expenseApplicationDao.insert(ea);
				SDepartment dept = this.departmentDao.getSDepartmentById(loginUser.getDepartmentId());
				ProcessEngine processEngine = Configuration.getProcessEngine();
				Map<String,Object> instanceVariables = new HashMap<String,Object>();
				instanceVariables.put("userName", loginUser.getUserName());
				instanceVariables.put("userId", String.valueOf(loginUser.getUserId()));
				instanceVariables.put("typeName", "费用申请单");
				instanceVariables.put("deptName", dept.getName());
				
				SPosition sp = positionDao.selectByPrimaryKey(loginUser.getPositionOrgId());
				instanceVariables.put("orgPosition", sp.getName());

				// 启动流程实例		
				ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey("expenseApp",instanceVariables, String.valueOf(eaId));
			
				List<Task> myTaskList = processEngine.getTaskService().findPersonalTasks(String.valueOf(loginUser.getUserId()));
				for(Task task: myTaskList) {
					if (task.getExecutionId().equals(processInstance.getId())) {
						processEngine.getTaskService().completeTask(task.getId(), "申请");
						break;
					}
				}

			}
		} else {
            if (ea.getStatus().intValue() == 2) {
				this.expenseApplicationDao.updateByPrimaryKeySelective(ea);
			}else {
				ea.setExpenseTime(new Date());
				ea.setExpenseApplicationCode(NormalFun.getNextCode(Constants.EXPENSE_APP_PREFIX, expenseApplicationDao.getMaxCode()));
				this.expenseApplicationDao.updateByPrimaryKeySelective(ea);
				
				SDepartment dept = this.departmentDao.getSDepartmentById(loginUser.getDepartmentId());
				ProcessEngine processEngine = Configuration.getProcessEngine();
				Map<String,Object> instanceVariables = new HashMap<String,Object>();
				instanceVariables.put("userName", loginUser.getUserName());
				instanceVariables.put("userId", String.valueOf(loginUser.getUserId()));
				instanceVariables.put("typeName", "费用申请单");
				instanceVariables.put("deptName", dept.getName());
				
				SPosition sp = positionDao.selectByPrimaryKey(loginUser.getPositionOrgId());
				instanceVariables.put("orgPosition", sp.getName());

				// 启动流程实例		
				ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey("expenseApp",instanceVariables, String.valueOf(ea.getExpenseApplicationId()));
			
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
	 * @see com.sales.service.SExpenseApplicationService#updateExpenseApplication(com.sales.model.SExpenseApplication)
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateExpenseApplication(SExpenseApplication ea) {
		this.expenseApplicationDao.updateByPrimaryKeySelective(ea);
		String instanceId = "expenseApp."+ea.getExpenseApplicationId();
		ProcessEngine processEngine = Configuration.getProcessEngine();
		List<Task> myTaskList = processEngine.getTaskService().findPersonalTasks(String.valueOf(ea.getExpenseUserId()));
		for(Task task: myTaskList) {
			if (task.getExecutionId().equals(instanceId)) {
				processEngine.getTaskService().completeTask(task.getId(), "申请");
				break;
			}
		}
	}

	@Override
	public List<SExpenseApplication> getExpenseApplicationListPage(SExpenseApplication ea) {
		
		return this.expenseApplicationDao.getExpenseApplicationListPage(ea);
	}

	@Override
	public Integer getExpenseApplicationListPageCount(SExpenseApplication ea) {
		
		return this.expenseApplicationDao.getExpenseApplicationListPageCount(ea);
	}

	@Override
	public SExpenseApplication getExpenseApplication(Integer eaId) {
		return this.expenseApplicationDao.selectByPrimaryKey(eaId);
	}

	@Override
	public List<SExpenseApplication> getExpenseApplicationListForReimbursement(Integer userId) {
		return this.expenseApplicationDao.getExpenseApplicationListForReimbursement(userId);
	}

	@Override
	public void updateExpenseApplicationById(SExpenseApplication ea) {
		this.expenseApplicationDao.updateByPrimaryKeySelective(ea);
	}
	@Override
	public void deleteEa(Integer eaId) {
		this.expenseApplicationDao.deleteByPrimaryKey(eaId);
	}

}
