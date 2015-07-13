/**
 * 
 */
package com.sales.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sales.dao.SPerformancePlanDAO;
import com.sales.dao.SPerformanceSqlDAO;
import com.sales.dao.SPlanItemDAO;
import com.sales.dao.SPositionDAO;
import com.sales.dao.SUserDAO;
import com.sales.model.SPerformancePlan;
import com.sales.model.SPerformanceSql;
import com.sales.model.SPlanItem;
import com.sales.model.SPosition;
import com.sales.model.SUser;
import com.sales.model.SUserJson;
import com.sales.service.SPerformancePlanService;

/**
 * @author Leo
 *
 */
public class SPerformancePlanServiceImpl implements SPerformancePlanService {
	
	private SPerformancePlanDAO performancePlanDao;
	
	private SPlanItemDAO planItemDao;
	
	private SPerformanceSqlDAO performanceSqlDao;
	
	private SPositionDAO positionDao;
	
	private SUserDAO userDao;

	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(SUserDAO userDao) {
		this.userDao = userDao;
	}

	/**
	 * @param positionDao the positionDao to set
	 */
	public void setPositionDao(SPositionDAO positionDao) {
		this.positionDao = positionDao;
	}

	/**
	 * @param performanceSqlDao the performanceSqlDao to set
	 */
	public void setPerformanceSqlDao(SPerformanceSqlDAO performanceSqlDao) {
		this.performanceSqlDao = performanceSqlDao;
	}

	/**
	 * @param planItemDao the planItemDao to set
	 */
	public void setPlanItemDao(SPlanItemDAO planItemDao) {
		this.planItemDao = planItemDao;
	}

	/**
	 * @param performancePlanDao the performancePlanDao to set
	 */
	public void setPerformancePlanDao(SPerformancePlanDAO performancePlanDao) {
		this.performancePlanDao = performancePlanDao;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.SPerformancePlanService#addPerformancePlan(com.sales.model.SPerformancePlan)
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public int addPerformancePlan(SPerformancePlan spp) {
		spp.setCreateTime(new Date());
		int planId = this.performancePlanDao.insert(spp);
		for (SPlanItem pi : spp.getPlanItemList()) {
			if (pi != null) {
				pi.setPlanId(planId);
				this.planItemDao.insert(pi);
			}
		}
		return 0;
	}

	@Override
	public List<SPerformancePlan> getPlanListPage(SPerformancePlan spp) {
		return this.performancePlanDao.getPlanListPage(spp);
	}

	@Override
	public Integer getPlanListPageCount(SPerformancePlan spp) {
		return this.performancePlanDao.getPlanListPageCount(spp);
	}

	@Override
	public List<SPlanItem> getPlanItemListByConditions(SPlanItem spi) {
		return this.planItemDao.getPlanItemListByConditions(spi);
	}

	@Override
	public List<SPerformanceSql> getSqlList() {
		return this.performanceSqlDao.getSqlList();
	}

	@Override
	public List<SPosition> getPostList(Integer postType) {
		List<SPosition> list = this.positionDao.getPositionListByType(postType);
		return list;
	}

	@Override
	public SPerformancePlan getPerformancePlan(Integer planId) {
		return this.performancePlanDao.selectByPrimaryKey(planId);
	}

	@Override
	public void deletePlan(Integer planId) {
		this.performancePlanDao.deleteByPrimaryKey(planId);
		this.planItemDao.deleteByPlanId(planId);
	}

	@Override
	public List<SUser> getUserList(Integer postType,Integer postId) {
		return this.userDao.getPerformanceUserList(postType,postId);
	}

	@Override
	public List<SPerformancePlan> getPlanListByUserPosition(Integer userId) {
		List<SPerformancePlan> orgPlanList = null;
		SUser user = this.userDao.getSUserById(userId);
		SPerformancePlan spp = new SPerformancePlan();
		if (user.getPositionOrgId() == null) {
			orgPlanList = new ArrayList<SPerformancePlan>();
		} else {
			spp.setSuitablePostId(user.getPositionOrgId());
			orgPlanList = this.performancePlanDao.getPlanListPage(spp);
		}
		List<SPerformancePlan> bsPlanList = null;
		if (user.getPositionBsId() == null) {
			bsPlanList = new ArrayList<SPerformancePlan>();
		} else {
			spp.setSuitablePostId(user.getPositionBsId());
			bsPlanList = this.performancePlanDao.getPlanListPage(spp);
		}
		orgPlanList.addAll(bsPlanList);
		return orgPlanList;
	}

	@Override
	public List<SUserJson> getChildrenUserList(Integer parentId) {
		return this.userDao.getSUserByParentId(parentId);
	}

}
