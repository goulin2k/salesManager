package com.sales.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sales.common.Constants;
import com.sales.common.NormalFun;
import com.sales.dao.SSalesQuotationDAO;
import com.sales.dao.SaleProcessDAO;
import com.sales.model.SRole;
import com.sales.model.SSalesQuotation;
import com.sales.model.SaleProcess;
import com.sales.service.ReportService;
import com.sales.service.RoleService;

/** 
 * @author  
 * @version 创建时间：2014-3-1 下午04:44:32 
 *  
 */
public class ReportServiceImpl implements ReportService{
	
	private SSalesQuotationDAO quotationDao;
	private RoleService roleService;
	private SaleProcessDAO saleProcessDAO;

	public SaleProcessDAO getSaleProcessDAO() {
		return saleProcessDAO;
	}

	public void setSaleProcessDAO(SaleProcessDAO saleProcessDAO) {
		this.saleProcessDAO = saleProcessDAO;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public SSalesQuotationDAO getQuotationDao() {
		return quotationDao;
	}

	public void setQuotationDao(SSalesQuotationDAO quotationDao) {
		this.quotationDao = quotationDao;
	}

	@Override
	public Integer getQuotationCount(SSalesQuotation quotation,
			String childUserIds, Integer roleId) {
		// TODO Auto-generated method stub
		Map queryMap = new HashMap();

		// 角色不控制客户访问权限则不过滤
		SRole thisRole = roleService.getRole(roleId);
		if (thisRole.getCustomerChecked() == false) {
			childUserIds = null;
			quotation.setPurchaseUserId(null);
		} 
		else {
			if (childUserIds == null || childUserIds.trim().equals("")) {
				childUserIds = "(-1)";
				quotation.setPurchaseUserId(null);
			} 
			else if (Constants.USER_FILTER_MARK.equals(childUserIds)) {
				childUserIds = null;
				quotation.setPurchaseUserId(null);
			}
			else if(Constants.ROLE_PURCHARSE.intValue() == roleId.intValue()){
				childUserIds = null; 
			}
		}
		queryMap.put("quotationUserId", quotation.getQuotationUserId());
		queryMap.put("purchaseUserId", quotation.getPurchaseUserId());
		queryMap.put("status", 1);
		queryMap.put("customerId", quotation.getCustomerId());
		queryMap.put("productCode", quotation.getProductCode());
		queryMap.put("quotationUserName", quotation.getQuotationUserName());
		queryMap.put("childUserIds", childUserIds);
		if (quotation.getStartTime() != null && !quotation.getStartTime().trim().equals("")) {
			queryMap.put("startTime",
					NormalFun.formatStringDateTime(quotation.getStartTime() + " 00:00:00"));
		}
		if (quotation.getEndTime() != null && !quotation.getEndTime().trim().equals("")) {
			queryMap.put("endTime",
					NormalFun.formatStringDateTime(quotation.getEndTime() + " 23:59:59"));
		}
		Integer quotationCount = (Integer) quotationDao
				.getQuotationCount(queryMap);
		return quotationCount;
	}

	@Override
	public List getQuotationList(SSalesQuotation quotation, Integer pageNumber,
			Integer pageSize, String childUserIds, Integer roleId) {
		// TODO Auto-generated method stub
		Map queryMap = new HashMap();
		//
		// 角色不控制客户访问权限则不过滤
		SRole thisRole = roleService.getRole(roleId);
		if (thisRole.getCustomerChecked() == false) {
			childUserIds = null;
			quotation.setPurchaseUserId(null);
		} 
		else {
			if (childUserIds == null || childUserIds.trim().equals("")) {
				childUserIds = "(-1)";
				quotation.setPurchaseUserId(null);
			} 
			else if (Constants.USER_FILTER_MARK.equals(childUserIds)) {
				childUserIds = null;
				quotation.setPurchaseUserId(null);
			}
			else if(Constants.ROLE_PURCHARSE.intValue() == roleId.intValue()){
				childUserIds = null; 
			}
		}
		
		queryMap.put("status", 1);
		
		Integer startNumber = (pageNumber - 1) * pageSize;
		queryMap.put("startNumber", startNumber);
		queryMap.put("pageSize", pageSize);
		
		if (quotation.getStartTime() != null && !quotation.getStartTime().trim().equals("")) {
			queryMap.put("startTime",
					NormalFun.formatStringDateTime(quotation.getStartTime() + " 00:00:00"));
		}
		if (quotation.getEndTime() != null && !quotation.getEndTime().trim().equals("")) {
			queryMap.put("endTime",
					NormalFun.formatStringDateTime(quotation.getEndTime() + " 23:59:59"));
		}
		List quotationList = quotationDao.getQuotationList(queryMap);
		return quotationList;
	}
	
	@Override
	public List getSaleProcPageList(SaleProcess saleProcess,Integer pageNumber,Integer pageSize) {
		// TODO Auto-generated method stub
		Map queryMap = new HashMap();
		Integer startNumber = (pageNumber - 1) * pageSize;
		queryMap.put("startNumber", startNumber);
		queryMap.put("pageSize", pageSize);
		if (saleProcess.getStartTime() != null && !saleProcess.getStartTime().trim().equals("")) {
			queryMap.put("startTime",
					NormalFun.formatStringDateTime(saleProcess.getStartTime() + " 00:00:00"));
		}
		if (saleProcess.getEndTime() != null && !saleProcess.getEndTime().trim().equals("")) {
			queryMap.put("endTime",
					NormalFun.formatStringDateTime(saleProcess.getEndTime() + " 23:59:59"));
		}
		List saleProcList = saleProcessDAO.getSaleProPageList(queryMap);
		return saleProcList;
	}

	@Override
	public Integer getSaleProcCount(SaleProcess saleProcess) {
		// TODO Auto-generated method stub
		Map queryMap = new HashMap();
		if (saleProcess.getStartTime() != null && !saleProcess.getStartTime().trim().equals("")) {
			queryMap.put("startTime",
					NormalFun.formatStringDateTime(saleProcess.getStartTime() + " 00:00:00"));
		}
		if (saleProcess.getEndTime() != null && !saleProcess.getEndTime().trim().equals("")) {
			queryMap.put("endTime",
					NormalFun.formatStringDateTime(saleProcess.getEndTime() + " 23:59:59"));
		}
		Integer procCount = (Integer) saleProcessDAO.getSaleProCount(queryMap);
		return procCount;
	}

	@Override
	public List getAllQuotationListReplied(SSalesQuotation quotation) {
		// TODO Auto-generated method stub
		Map queryMap = new HashMap();
		queryMap.put("quotationUserId", quotation.getQuotationUserId());
		queryMap.put("purchaseUserId", quotation.getPurchaseUserId());
		queryMap.put("status", 1);  //设置条件为已经审核的询价单
		queryMap.put("customerId", quotation.getCustomerId());
		queryMap.put("productCode", quotation.getProductCode());
		queryMap.put("quotationUserName", quotation.getQuotationUserName());
		if (quotation.getStartTime() != null && !quotation.getStartTime().trim().equals("")) {
			queryMap.put("startTime",
					NormalFun.formatStringDateTime(quotation.getStartTime() + " 00:00:00"));
		}
		if (quotation.getEndTime() != null && !quotation.getEndTime().trim().equals("")) {
			queryMap.put("endTime",
					NormalFun.formatStringDateTime(quotation.getEndTime() + " 23:59:59"));
		}
		List quotationList = quotationDao.getAllQuotationList(queryMap);
		return quotationList;
	}

	@Override
	public List getSaleProcList(SaleProcess saleProcess) {
		// TODO Auto-generated method stub
		Map queryMap = new HashMap();
		if (saleProcess.getStartTime() != null && !saleProcess.getStartTime().trim().equals("")) {
			queryMap.put("startTime",
					NormalFun.formatStringDateTime(saleProcess.getStartTime() + " 00:00:00"));
		}
		if (saleProcess.getEndTime() != null && !saleProcess.getEndTime().trim().equals("")) {
			queryMap.put("endTime",
					NormalFun.formatStringDateTime(saleProcess.getEndTime() + " 23:59:59"));
		}
		List saleProcList = saleProcessDAO.getSaleProList(queryMap);
		return saleProcList;
	}

}
