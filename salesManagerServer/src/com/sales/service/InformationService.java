package com.sales.service;
/** 
 * @author  
 * @version 创建时间：2013-5-2 下午10:33:42 
 *  
 */

import java.util.List;

import com.sales.model.SInformation;
import com.sales.model.SimpleNameValue;

public interface InformationService {
	
	public static final int INFO_TYPE_SALEPLAN = 1;			//销售活动计划通知
	public static final int INFO_TYPE_SALEPROCESS = 2;		//销售单据审核通知
	public static final int INFO_TYPE_OAPROCESS = 3;		//oa审核通知
	public static final int INFO_TYPE_BILLMSG = 4;			//销售单据通知
	public static final int INFO_TYPE_CORPINFO = 5;			//公司新闻公告通知
	
	public List<SInformation> getInfoListByUserId(Integer sendUserId, 
			Integer type, Integer status,
			Long pageNumber, int pageSize);
	
	public void updateInfo(SInformation info);
	
	/**
	 * 修改指定的url消息状态
	 * @param url
	 * @param status
	 */
	public void updateInfo(String url, int userId, int status);
	
	/**
	 * 删除指定id的消息
	 * @param infoId
	 */
	public void deleteInfo(Integer infoId);
	
	public void insertInfo(SInformation info);
	
	public Integer getInfoCountByUserId(Integer sendUserId, Integer type, Integer status);
	
	public List<SInformation> getInfoListByStatus(Integer status,Integer sendUserId);
	
	/**
	 * 查询流程通知消息数
	 * @param sendUserId
	 * @param type
	 * @param status
	 * @return
	 */
	public Integer getProcInfoCountByUserId(Integer sendUserId, Integer type, Integer status);
	
	/**
	 * 查询流程通知消息列表
	 * @param status
	 * @param sendUserId
	 * @return
	 */
	public List<SInformation> getProcInfoListByUserId(Integer sendUserId, 
			Integer type, Integer status,
			Long pageNumber, int pageSize);
	
	public SInformation getInformationById(Integer infoId);
	
	/**
	 * 设置指定用户所有消息为已读
	 * @param userId
	 */
	public void setAllReaded(Integer userId);
	
	/**
	 * 根据消息类型获取未读消息数
	 */
	public List<SimpleNameValue> getUnreadedInfoGroupCounts(Integer userId);

}
