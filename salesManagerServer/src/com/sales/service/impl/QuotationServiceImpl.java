package com.sales.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sales.common.Constants;
import com.sales.common.NormalFun;
import com.sales.dao.SInformationDAO;
import com.sales.dao.SLogDAO;
import com.sales.dao.SSalesQuotationCodeDAO;
import com.sales.dao.SSalesQuotationDAO;
import com.sales.dao.SSalesQuotationProductDAO;
import com.sales.dao.SUserDAO;
import com.sales.model.SInformation;
import com.sales.model.SLog;
import com.sales.model.SParameter;
import com.sales.model.SRole;
import com.sales.model.SSalesQuotation;
import com.sales.model.SSalesQuotationCode;
import com.sales.model.SSalesQuotationProduct;
import com.sales.model.SUser;
import com.sales.service.QuotationService;
import com.sales.service.RoleService;
import com.sales.service.SParameterService;

public class QuotationServiceImpl implements QuotationService {

	private SSalesQuotationDAO quotationDao;
	private SSalesQuotationProductDAO quotationProductDao;
	private SLogDAO logDao;
	private SSalesQuotationCodeDAO quotationCodeDao;
	private SInformationDAO informationDao;
	private SUserDAO sUserDao;
	private RoleService roleService;
	private SParameterService parameterService;

	public void setParameterService(SParameterService parameterService) {
		this.parameterService = parameterService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addQuotation(SSalesQuotation quotation, List<SSalesQuotationProduct> products) throws Exception{
		quotation.setQuotationCode(NormalFun.getNextQuotationCode(quotationDao.getMaxCode()));
		Integer quotationId = quotationDao.insertQuotation(quotation);
		for (int i = 0; i < products.size(); i++) {
			SSalesQuotationProduct quotationProduct = products.get(i);
			if (quotationProduct != null && quotationProduct.getProductCode() != null) {
				quotationProduct.setQuotationId(quotationId);
				quotationProductDao.insertQuotationProduct(quotationProduct);
			}
		}
		SLog log = new SLog();
		log.setLogContent("用户" + quotation.getQuotationUserId() + "新增询价单："
				+ quotation.getQuotationCode());
		log.setLogTime(new Date());
		log.setOperateUserId(quotation.getQuotationUserId());
		log.setTitle("询价单");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);
		// 给采购人员发送消息
		String title = "询价单";
		String content = "用户：" + quotation.getQuotationUserName() + "新增询价单："
				+ quotation.getQuotationCode() + "，请及时回复";
		String url = "quotation!reply?quotation.quotationId=" + quotationId;
		insertInfor(title, content, url, quotation.getPurchaseUserId(),quotationId);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateQuotation(SSalesQuotation quotation,
			List<SSalesQuotationProduct> products) {
		quotation.setReplyTime(new Date());
		quotationDao.updateQuotation(quotation);
		for (int i = 0; i < products.size(); i++) {
			SSalesQuotationProduct quotationProduct = products.get(i);
			quotationProductDao.updateQuotationProduct(quotationProduct);
		}
		SLog log = new SLog();
		log.setLogContent("用户" + quotation.getPurchaseUserName() + "回复询价单："
				+ quotation.getQuotationCode());
		log.setLogTime(new Date());
		log.setOperateUserId(quotation.getPurchaseUserId());
		log.setTitle("询价单");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);

		// 给询价人员回复消息
		SSalesQuotation quotationNew = quotationDao.getQuotationById(quotation
				.getQuotationId());
		String title = "询价单回复";
		String content = "采购人员：" + quotation.getPurchaseUserName() + "已回复询价单："
				+ quotation.getQuotationCode() + "，请查收";
		String url = "quotation!show?quotation.quotationId="
				+ quotation.getQuotationId();
		insertInfor(title, content, url, quotationNew.getQuotationUserId(),quotation.getQuotationId());

		// 给指定岗位的人员信息通知询价单已回复
		SParameter param = parameterService
				.getParameter(Constants.PARAM_QUOREPLY_SENDTO);
		if (param != null) {
			int positionId = Integer.parseInt(param.getValue());
			List<SUser> users = roleService.getUsers(positionId);
			for (SUser sUser : users) {
				insertInfor(title, content, url, sUser.getUserId(),quotation.getQuotationId());
			}
		}

	}

	public SSalesQuotation getQuotationById(Integer quotationId,
			String childUserIds, Integer purchaseUserId, Integer roleId) {
		Map queryMap = new HashMap();
		childUserIds = null;
		purchaseUserId = null;

		queryMap.put("childUserIds", childUserIds);
		queryMap.put("quotationId", quotationId);
		queryMap.put("purchaseUserId", purchaseUserId);
		SSalesQuotation quotation = quotationDao.getQuotationById(queryMap);
		return quotation;
	}

	public SSalesQuotation getQuotationById(Integer quotationId) {
		SSalesQuotation quotation = quotationDao.getQuotationById(quotationId);
		return quotation;
	}

	public SSalesQuotation getReplyQuotationById(Integer quotationId) {
		Map queryMap = new HashMap();
		queryMap.put("quotationId", quotationId);
		queryMap.put("childUserIds", null);
		SSalesQuotation quotation = quotationDao.getQuotationById(queryMap);
		return quotation;
	}

	public List getQuotationList(SSalesQuotation quotation, Integer pageNumber,
			Integer pageSize, String childUserIds, Integer roleId) {
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
		queryMap.put("quotationUserId", quotation.getQuotationUserId());
		queryMap.put("purchaseUserId", quotation.getPurchaseUserId());
		queryMap.put("status", quotation.getStatus());
		queryMap.put("customerId", quotation.getCustomerId());
		queryMap.put("productCode", quotation.getProductCode());
		queryMap.put("quotationUserName", quotation.getQuotationUserName());
		Integer startNumber = (pageNumber - 1) * pageSize;
		queryMap.put("startNumber", startNumber);
		queryMap.put("pageSize", pageSize);
		queryMap.put("childUserIds", childUserIds);
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

	public Integer getQuotationCount(SSalesQuotation quotation, String childUserIds, Integer roleId) {
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
		queryMap.put("status", quotation.getStatus());
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

	@Transactional(propagation = Propagation.REQUIRED)
	public synchronized String getQuotationCode(Integer year) {
		SSalesQuotationCode code = quotationCodeDao.getCodeByYear(year);
		String tempCode = null;
		// 如果为空则新增一条code
		if (code == null) {
			code = new SSalesQuotationCode();
			code.setYear(year);
			code.setCode(1);
			tempCode = String.valueOf(1);
			quotationCodeDao.insertCode(code);
		} else {
			tempCode = String.valueOf(code.getCode());
			// code增加1
			code.setCode(code.getCode().intValue() + 1);
			quotationCodeDao.updateCode(code);
		}
		int codeLength = 6 - tempCode.length();
		if (tempCode.length() < 6) {
			for (int j = 0; j < codeLength; j++) {
				tempCode = "0" + tempCode;
			}
		}
		String quotationCode = "XJD" + String.valueOf(year) + tempCode;
		return quotationCode;
	}

	private void insertInfor(String title, String content, String url,
			Integer sendUserId, Integer quotationId) {
		SInformation infor = new SInformation();
		infor.setAddTime(new Timestamp(System.currentTimeMillis()));
		infor.setContent(content);
		infor.setSendUserId(sendUserId);
		infor.setStatus(Constants.INFO_STATUS_NEW);
		infor.setTitle(title);
		infor.setType(SInformation.BuzType.SALE_QUO.ordinal());
		infor.setBuzId(quotationId);
		infor.setImageUrl(url);
		informationDao.insert(infor);
	}
	 
	public SSalesQuotation toOrfq(Integer quotationId) {
		SSalesQuotation quotation = this.quotationDao.getQuotationById(quotationId);
		return quotation;
	}

	public SSalesQuotationProductDAO getQuotationProductDao() {
		return quotationProductDao;
	}

	public void setQuotationProductDao(
			SSalesQuotationProductDAO quotationProductDao) {
		this.quotationProductDao = quotationProductDao;
	}

	public SSalesQuotationDAO getQuotationDao() {
		return quotationDao;
	}

	public void setQuotationDao(SSalesQuotationDAO quotationDao) {
		this.quotationDao = quotationDao;
	}

	public SLogDAO getLogDao() {
		return logDao;
	}

	public void setLogDao(SLogDAO logDao) {
		this.logDao = logDao;
	}

	public SSalesQuotationCodeDAO getQuotationCodeDao() {
		return quotationCodeDao;
	}

	public void setQuotationCodeDao(SSalesQuotationCodeDAO quotationCodeDao) {
		this.quotationCodeDao = quotationCodeDao;
	}

	public SInformationDAO getInformationDao() {
		return informationDao;
	}

	public void setInformationDao(SInformationDAO informationDao) {
		this.informationDao = informationDao;
	}

	public SUserDAO getsUserDao() {
		return sUserDao;
	}

	public void setsUserDao(SUserDAO sUserDao) {
		this.sUserDao = sUserDao;
	}

}
