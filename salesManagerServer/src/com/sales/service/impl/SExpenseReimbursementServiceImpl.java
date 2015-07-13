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
import com.sales.dao.SExpenseReimbursementDAO;
import com.sales.dao.SPositionDAO;
import com.sales.model.SDepartment;
import com.sales.model.SExpenseReimbursement;
import com.sales.model.SPosition;
import com.sales.model.SUser;
import com.sales.service.SExpenseReimbursementService;

/**
 * @author Leo
 *
 */
public class SExpenseReimbursementServiceImpl implements SExpenseReimbursementService {
	
	private SExpenseReimbursementDAO expenseReimbursementDao;	
	
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

	/* (non-Javadoc)
	 * @see com.sales.service.SExpenseReimbursementService#addExpenseReimbursement(com.sales.model.SExpenseReimbursement)
	 */
	public void setExpenseReimbursementDao(SExpenseReimbursementDAO expenseReimbursementDao) {
		this.expenseReimbursementDao = expenseReimbursementDao;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void addExpenseReimbursement(SExpenseReimbursement er, SUser loginUser) {
		if (er.getReimbursementId() == null) {
			if (er.getStatus().intValue() == 2) {
				er.setReimbursementUserId(loginUser.getUserId());
				Integer erId = this.expenseReimbursementDao.insert(er);
			} else {
				er.setReimbursementUserId(loginUser.getUserId());
				er.setReimbursementTime(new Date());
				er.setReimbursementCode(NormalFun.getNextCode(Constants.EXPENSE_REI_PREFIX, expenseReimbursementDao.getMaxCode()));
				Integer erId = this.expenseReimbursementDao.insert(er);
				
				SDepartment dept = this.departmentDao.getSDepartmentById(loginUser.getDepartmentId());
				ProcessEngine processEngine = Configuration.getProcessEngine();
				Map<String,Object> instanceVariables = new HashMap<String,Object>();
				instanceVariables.put("userName", loginUser.getUserName());
				instanceVariables.put("userId", String.valueOf(loginUser.getUserId()));
				instanceVariables.put("typeName", "费用报销单");
				instanceVariables.put("deptName", dept.getName());
				
				SPosition sp = positionDao.selectByPrimaryKey(loginUser.getPositionOrgId());
				instanceVariables.put("orgPosition", sp.getName());

				// 启动流程实例		
				ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey("expenseRei",instanceVariables, String.valueOf(erId));
			
				List<Task> myTaskList = processEngine.getTaskService().findPersonalTasks(String.valueOf(loginUser.getUserId()));
				for(Task task: myTaskList) {
					if (task.getExecutionId().equals(processInstance.getId())) {
						processEngine.getTaskService().completeTask(task.getId(), "申请");
						break;
					}
				}
			}
		} else {
            if (er.getStatus().intValue() == 2) {
            	this.expenseReimbursementDao.updateByPrimaryKeySelective(er);
			} else {
				er.setReimbursementTime(new Date());
				er.setReimbursementCode(NormalFun.getNextCode(Constants.EXPENSE_REI_PREFIX, expenseReimbursementDao.getMaxCode()));
				this.expenseReimbursementDao.updateByPrimaryKeySelective(er);
				
				SDepartment dept = this.departmentDao.getSDepartmentById(loginUser.getDepartmentId());
				ProcessEngine processEngine = Configuration.getProcessEngine();
				Map<String,Object> instanceVariables = new HashMap<String,Object>();
				instanceVariables.put("userName", loginUser.getUserName());
				instanceVariables.put("userId", String.valueOf(loginUser.getUserId()));
				instanceVariables.put("typeName", "费用报销单");
				instanceVariables.put("deptName", dept.getName());
				
				SPosition sp = positionDao.selectByPrimaryKey(loginUser.getPositionOrgId());
				instanceVariables.put("orgPosition", sp.getName());

				// 启动流程实例		
				ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey("expenseRei",instanceVariables, String.valueOf(er.getReimbursementId()));
			
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
	 * @see com.sales.service.SExpenseReimbursementService#updateExpenseReimbursement(com.sales.model.SExpenseReimbursement)
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateExpenseReimbursement(SExpenseReimbursement er) {
		this.expenseReimbursementDao.updateByPrimaryKeySelective(er);
		String instanceId = "expenseRei."+er.getReimbursementId();
		ProcessEngine processEngine = Configuration.getProcessEngine();
		List<Task> myTaskList = processEngine.getTaskService().findPersonalTasks(String.valueOf(er.getReimbursementUserId()));
		for(Task task: myTaskList) {
			if (task.getExecutionId().equals(instanceId)) {
				processEngine.getTaskService().completeTask(task.getId(), "申请");
				break;
			}
		}
	}

	@Override
	public SExpenseReimbursement getExpenseReimbursement(Integer erId) {
		return this.expenseReimbursementDao.selectByPrimaryKey(erId);
	}

	@Override
	public List<SExpenseReimbursement> getExpenseReimbursementListPage(SExpenseReimbursement er) {
		return this.expenseReimbursementDao.getExpenseReimbursementListPage(er);
	}

	@Override
	public Integer getExpenseReimbursementListPageCount(SExpenseReimbursement er) {
		return this.expenseReimbursementDao.getExpenseReimbursementListPageCount(er);
	}

	@Override
	public void updateExpenseReimbursementById(SExpenseReimbursement er) {
		this.expenseReimbursementDao.updateByPrimaryKeySelective(er);
	}
	@Override
	public void deleteEr(Integer erId) {
		this.expenseReimbursementDao.deleteByPrimaryKey(erId);
	}

}
