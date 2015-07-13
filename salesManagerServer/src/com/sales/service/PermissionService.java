package com.sales.service;

import java.util.Map;

import com.sales.model.SUser;

/** 
 * @author  
 * @version 创建时间：2013-4-24 下午10:22:41 
 *  
 */
public interface PermissionService {
	
	public static final int ROLE_ID_ADMIN = 1;			  //管理员角
	public static final int ROLE_ID_GENERATOR = 2;		//总经理角色
	public static final int ROLE_ID_SALEAGENT = 6;		//销售助理角色
	public static final int ROLE_ID_PURCHASEMANAGER = 7;		//采购主管角色
	public static final int ROLE_ID_FINMANAGER = 8;		//财务主管角色
	public static final int ROLE_ID_WAREHOUSEMANAGER = 12;		//库管角色
	public static final int ROLE_ID_INTEGRATEDEPMGR = 13;		//综合部经理角色
	
	/**
	 * 获取用户权限
	 * @param userId
	 * @return
	 */
	public Map getPermissionByUserId(SUser userInfo);

}
