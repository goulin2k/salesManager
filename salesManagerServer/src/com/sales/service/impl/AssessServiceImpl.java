package com.sales.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sales.common.Constants;
import com.sales.dao.SInformationDAO;
import com.sales.dao.SLogDAO;
import com.sales.dao.SSalesAssessDAO;
import com.sales.dao.SSalesProjectDAO;
import com.sales.model.SInformation;
import com.sales.model.SLog;
import com.sales.model.SSalesAssess;
import com.sales.model.SSalesProject;
import com.sales.model.SUser;
import com.sales.service.AssessService;

/**
 * 销售活动督办
 * @author apple
 *
 */
public class AssessServiceImpl implements AssessService {
	
	private SSalesAssessDAO assessDao;
	private SSalesProjectDAO projectDao;
	private SInformationDAO informationDao;
	private SLogDAO logDao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void addSalesAssess(SSalesAssess assess, String childUserIds) {
		Integer assessId = assessDao.insertSalesAssess(assess);
		if(assess.getProjectId()!=null && assess.getProjectId()>0){ 
			Map queryMap = new HashMap();
			queryMap.put("projectId", assess.getProjectId()); 
			if(childUserIds==null || childUserIds.trim().equals("")){
				childUserIds = "(-1)";
			}
			else if(Constants.USER_FILTER_MARK.equals(childUserIds)){
				childUserIds = null;
			}
			queryMap.put("childUserIds", childUserIds);
			SSalesProject project = projectDao.getProjectById(queryMap);
			if(project != null){  		
				//评价人与计划创建人不是同一个人，通知计划创建人
				if(project.getCreateUserId().intValue() != assess.getAssessUserId().intValue()){
					insertInfor("用户:" + assess.getAssessUserName() + "为销售计划：" + project.getTopical() + "新增评价" + assess.getComment(), 
							project.getCreateUserId(), "销售评价", "project!get?project.projectId=" + String.valueOf(assess.getProjectId()),
							assessId);
				}
				//活动负责人与计划负责人不是同一个人，通知计划负责人
				if(project.getResponseUserId()!=null && project.getResponseUserId().intValue()!=assess.getAssessUserId().intValue()){
					insertInfor("用户:" + assess.getAssessUserName() + "为销售计划：" + project.getTopical() + "新增评价" + assess.getComment(), 
							project.getResponseUserId(), "销售评价", "project!get?project.projectId=" + String.valueOf(assess.getProjectId()),
							assessId);
				}
				//通知计划关注人
				List attentionList = projectDao.getAttentionUserByProjectId(assess.getProjectId());
				for (int i = 0; i < attentionList.size(); i++) {
					SUser attentionUser = (SUser) attentionList.get(i);
					insertInfor("用户:" + assess.getAssessUserName() + "为销售计划：" + project.getTopical() + "新增活动：" + assess.getComment(), 
							attentionUser.getUserId(), "销售评价", "project!get?project.projectId=" + String.valueOf(assess.getProjectId()),
							assessId);
				}
			}
		} 
		//插入消息
		SLog log = new SLog();
		String logStr = "";
		if(assess.getComment().length() > 30){
			logStr = assess.getComment().substring(0, 30) + "...";
		}
		else{
			logStr = assess.getComment().substring(0, assess.getComment().length());
		}
		log.setLogContent("用户" + assess.getAssessUserId() + "新增销售评价：" + logStr);
		log.setLogTime(new Date());
		log.setOperateUserId(assess.getAssessUserId());
		log.setTitle("销售评价");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);
    }
  
    public SSalesAssess getSalesAssessById(Integer assessId) { 
        SSalesAssess assess = assessDao.getSalesAssessById(assessId);
        return assess;
    }
    
    
	
    /* (non-Javadoc)
	 * @see com.sales.service.AssessService#getSaleAssessCountByPlan(java.lang.Integer)
	 */
	@Override
	public Integer getSaleAssessCountByPlan(Integer projectId) {
		return assessDao.getSaleAssessCountByPlan(projectId);
	}

	/* (non-Javadoc)
	 * @see com.sales.service.AssessService#getSaleAssessListByPlan(java.lang.Integer)
	 */
	@Override
	public List<SSalesAssess> getSaleAssessListByPlan(Integer projectId) {
		return assessDao.getSaleAssessListByPlan(projectId);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void updateSalesAssess(SSalesAssess assess) {
		assess.setCommentTime(new Date());
		assessDao.updateSalesAssess(assess);
		SLog log = new SLog();
		String logStr = "";
		if(assess.getComment().length() > 30){
			logStr = assess.getComment().substring(0, 30) + "...";
		}
		else{
			logStr = assess.getComment().substring(0, assess.getComment().length());
		}
		log.setLogContent("用户" + assess.getAssessUserId() + "修改销售评价：" + logStr);
		log.setLogTime(new Date());
		log.setOperateUserId(assess.getAssessUserId());
		log.setTitle("销售评价");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);
    }
	
	private void insertInfor(String content, Integer sendUserId, String title, String url, int assessId){
		SInformation infor = new SInformation();
		infor.setAddTime(new Timestamp(System.currentTimeMillis()));
		infor.setContent(content);
		infor.setSendUserId(sendUserId);
		infor.setStatus(Constants.INFO_STATUS_NEW);
		infor.setTitle(title);
		infor.setType(SInformation.BuzType.PLAN.ordinal());
		infor.setImageUrl(url);
		infor.setBuzId(assessId);
		informationDao.insert(infor);
	}

	public SSalesAssessDAO getAssessDao() {
		return assessDao;
	}

	public void setAssessDao(SSalesAssessDAO assessDao) {
		this.assessDao = assessDao;
	}

	public SLogDAO getLogDao() {
		return logDao;
	}

	public void setLogDao(SLogDAO logDao) {
		this.logDao = logDao;
	}

	public SSalesProjectDAO getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(SSalesProjectDAO projectDao) {
		this.projectDao = projectDao;
	}

	public SInformationDAO getInformationDao() {
		return informationDao;
	}

	public void setInformationDao(SInformationDAO informationDao) {
		this.informationDao = informationDao;
	}

}
