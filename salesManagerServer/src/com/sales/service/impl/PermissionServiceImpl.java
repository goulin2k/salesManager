package com.sales.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @author  
 * @version 创建时间：2013-4-24 下午10:22:41 
 *  
 */

import com.sales.common.Constants;
import com.sales.dao.SCustomerOwnerUserDAO;
import com.sales.dao.SCustomerUserDAO;
import com.sales.dao.SDepartmentDAO;
import com.sales.dao.SRoleActionDAO;
import com.sales.dao.SUserDAO;
import com.sales.model.SCustomerOwnerUser;
import com.sales.model.SCustomerUser;
import com.sales.model.SDepartment;
import com.sales.model.SRole;
import com.sales.model.SRoleAction;
import com.sales.model.SUser;
import com.sales.service.PermissionService;
import com.sales.service.RoleService;

public class PermissionServiceImpl implements PermissionService {
	
	private RoleService roleService;
	
	private SDepartmentDAO departmentDao;
	private SCustomerUserDAO customerUserDao;
	private SCustomerOwnerUserDAO customerOwnerUserDao;
	private SRoleActionDAO roleActionDao;
	private SUserDAO sUserDao;

	public SUserDAO getsUserDao() {
		return sUserDao;
	}

	public void setsUserDao(SUserDAO sUserDao) {
		this.sUserDao = sUserDao;
	}

	public SCustomerUserDAO getCustomerUserDao() {
		return customerUserDao;
	}

	public void setCustomerUserDao(SCustomerUserDAO customerUserDao) {
		this.customerUserDao = customerUserDao;
	}

	public SCustomerOwnerUserDAO getCustomerOwnerUserDao() {
		return customerOwnerUserDao;
	}

	public void setCustomerOwnerUserDao(SCustomerOwnerUserDAO customerOwnerUserDao) {
		this.customerOwnerUserDao = customerOwnerUserDao;
	}

	public SRoleActionDAO getRoleActionDao() {
		return roleActionDao;
	}

	public void setRoleActionDao(SRoleActionDAO roleActionDao) {
		this.roleActionDao = roleActionDao;
	}

	public SDepartmentDAO getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(SDepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Override
	public Map getPermissionByUserId(SUser userInfo) {
		
		
		List dpList = departmentDao.getDepAndChildById(userInfo.getDepartmentId());
		List cusUserList = customerUserDao.getCustomerUserListByUserId(userInfo.getUserId());
		List cusOwnerUserList = customerOwnerUserDao.getCOUserListByUserId(userInfo.getUserId());
		List rActionList = roleActionDao.getRoleActionListByRoleId(userInfo.getRoleId());
		List childUserList = sUserDao.getChildUsersById(userInfo.getUserId());
		
		StringBuffer sb = new StringBuffer();
		String departFilter = null;
		String customerUserFilter = null;
		String customerOwnerFilter = null;
		String roleModuleFilter = null;
		String childUserFilter = null;
		for (int i = 0; i < dpList.size(); i++) {
			if (i == 0) {
				sb.append("(");
			}
			SDepartment resultDepart = (SDepartment) dpList.get(i);
			sb.append(resultDepart.getDepartmentId()).append(",");
			if (i == dpList.size() - 1) {
				if (sb != null && sb.length() > 0) {
					departFilter = sb.substring(0, sb.length() - 1);
					departFilter = departFilter + ")";
					sb = null;
				}
			}
		}
		StringBuffer cusb = new StringBuffer();
		for (int i = 0; i < cusUserList.size(); i++) {
			if (i == 0) {
				cusb.append("(");
			}
			SCustomerUser resultcusUser = (SCustomerUser) cusUserList.get(i);
			cusb.append(resultcusUser.getCustomerId()).append(",");
			if (i == cusUserList.size() - 1) {
				if (cusb != null && cusb.length() > 0) {
					customerUserFilter = cusb.substring(0, cusb.length() - 1);
					customerUserFilter = customerUserFilter + ")";
					cusb = null;
				}
			}
		}
		StringBuffer cousb = new StringBuffer();
		for (int i = 0; i < cusOwnerUserList.size(); i++) {
			if (i == 0) {
				cousb.append("(");
			}
			SCustomerOwnerUser resultCustomerOwnerUser = (SCustomerOwnerUser) cusOwnerUserList.get(i);
			cousb.append(resultCustomerOwnerUser.getCustomerId()).append(",");
			if (i == cusOwnerUserList.size() - 1) {
				if (cousb != null && cousb.length() > 0) {
					customerOwnerFilter = cousb.substring(0, cousb.length() - 1);
					customerOwnerFilter = customerOwnerFilter + ")";
					cousb = null;
				}
			}
		}
		StringBuffer asb = new StringBuffer();
		for (int i = 0; i < rActionList.size(); i++) {
			if (i == 0) {
				asb.append("(");
			}
			SRoleAction resultRoleAction = (SRoleAction) rActionList.get(i);
			asb.append(resultRoleAction.getMethod()).append(",");
			if (i == rActionList.size() - 1) {
				if (asb != null && asb.length() > 0) {
					roleModuleFilter = asb.substring(0, asb.length() - 1);
					roleModuleFilter = roleModuleFilter + ")";
					asb = null;
				}
			}
		}
		
		StringBuffer usb = new StringBuffer();
		for (int i = 0; i < childUserList.size(); i++) {
			if (i == 0) {
				usb.append("(");
			}
			SUser sUser = (SUser) childUserList.get(i);
			usb.append(sUser.getUserId()).append(",");
			if (i == childUserList.size() - 1) {
				if (usb != null && usb.length() > 0) {
					childUserFilter = usb.substring(0, usb.length() - 1);
					childUserFilter = childUserFilter + ")";
					usb = null;
				}
			}
		}
		
		Map permissionMap = new HashMap();
		
		//设置用户客户访问权限
		SRole thisRole = roleService.getRole(userInfo.getRoleId());
		if(thisRole.getCustomerChecked() == false)
			permissionMap.put("customerOwnerFilter", Constants.USER_FILTER_MARK);
		else
			permissionMap.put("customerOwnerFilter", customerOwnerFilter);
		
		//设置其它权限数据
		if(userInfo.getRoleId()==ROLE_ID_ADMIN ){								//系统管理员
			permissionMap.put("departFilter", Constants.USER_FILTER_MARK);
			permissionMap.put("customerUserFilter", Constants.USER_FILTER_MARK);
			
			permissionMap.put("roleModuleFilter", Constants.USER_FILTER_MARK);
			permissionMap.put("childUserFilter", Constants.USER_FILTER_MARK);
		} else if( userInfo.getRoleId() == ROLE_ID_GENERATOR ) {				//总经理
			permissionMap.put("departFilter", Constants.USER_FILTER_MARK);
			permissionMap.put("customerUserFilter", Constants.USER_FILTER_MARK);
			
			permissionMap.put("roleModuleFilter", roleModuleFilter);
			permissionMap.put("childUserFilter", childUserFilter);
		}else if( userInfo.getRoleId() == ROLE_ID_SALEAGENT
				|| userInfo.getRoleId() == ROLE_ID_PURCHASEMANAGER || userInfo.getRoleId() == ROLE_ID_PURCHASEMANAGER
				|| userInfo.getRoleId() == ROLE_ID_FINMANAGER || userInfo.getRoleId() == ROLE_ID_WAREHOUSEMANAGER
				|| userInfo.getRoleId() == ROLE_ID_INTEGRATEDEPMGR) {
			permissionMap.put("departFilter", departFilter);
			permissionMap.put("customerUserFilter", Constants.USER_FILTER_MARK);
			
			permissionMap.put("roleModuleFilter", roleModuleFilter);
			permissionMap.put("childUserFilter", childUserFilter);
		}else{
			permissionMap.put("departFilter", departFilter);
			permissionMap.put("customerUserFilter", customerUserFilter);
			
			permissionMap.put("roleModuleFilter", roleModuleFilter);
			permissionMap.put("childUserFilter", childUserFilter);
		}
		
		if(userInfo.getRoleId() == ROLE_ID_SALEAGENT)
			permissionMap.put("childUserFilter", Constants.USER_FILTER_MARK);
		
		return permissionMap;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

}
