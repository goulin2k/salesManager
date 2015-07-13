/**
 * 
 */
package com.sales.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sales.common.Constants;
import com.sales.common.NormalFun;
import com.sales.common.WorkCalendar;
import com.sales.dao.SPerformancePlanDAO;
import com.sales.dao.SPerformanceSqlDAO;
import com.sales.dao.SUserPerformanceItemDAO;
import com.sales.dao.SUserPerformancePlanDAO;
import com.sales.model.K3SalerQuota;
import com.sales.model.SPerformancePlan;
import com.sales.model.SPerformanceSql;
import com.sales.model.SUserPerformanceItem;
import com.sales.model.SUserPerformancePlan;
import com.sales.service.K3ICSaleStatisticsService;
import com.sales.service.K3OrderStatisticsService;
import com.sales.service.K3RecieveBillStatisticsService;
import com.sales.service.K3SalerQuotaService;
import com.sales.service.SUserPerformancePlanService;

/**
 * @author Leo
 *
 */
public class SUserPerformancePlanServiceImpl implements SUserPerformancePlanService {
	
	private SUserPerformancePlanDAO userPerformancePlanDao;
	
	private SUserPerformanceItemDAO userPerformanceItemDao;
	
	private SPerformancePlanDAO performancePlanDao;
	
	private SPerformanceSqlDAO performanceSqlDao;
	
	private K3OrderStatisticsService statisticsOrderService;
	private K3RecieveBillStatisticsService statisticsRecieveService;
	private K3ICSaleStatisticsService statisticsICSaleService;
	private K3SalerQuotaService quotaService;
	

	/**
	 * @param statisticsICSaleService the statisticsICSaleService to set
	 */
	public void setStatisticsICSaleService(
			K3ICSaleStatisticsService statisticsICSaleService) {
		this.statisticsICSaleService = statisticsICSaleService;
	}

	/**
	 * @param statisticsOrderService the statisticsOrderService to set
	 */
	public void setStatisticsOrderService(
			K3OrderStatisticsService statisticsOrderService) {
		this.statisticsOrderService = statisticsOrderService;
	}

	/**
	 * @param statisticsRecieveService the statisticsRecieveService to set
	 */
	public void setStatisticsRecieveService(
			K3RecieveBillStatisticsService statisticsRecieveService) {
		this.statisticsRecieveService = statisticsRecieveService;
	}

	/**
	 * @param quotaService the quotaService to set
	 */
	public void setQuotaService(K3SalerQuotaService quotaService) {
		this.quotaService = quotaService;
	}

	/**
	 * @param performanceSqlDao the performanceSqlDao to set
	 */
	public void setPerformanceSqlDao(SPerformanceSqlDAO performanceSqlDao) {
		this.performanceSqlDao = performanceSqlDao;
	}

	/**
	 * @param performancePlanDao the performancePlanDao to set
	 */
	public void setPerformancePlanDao(SPerformancePlanDAO performancePlanDao) {
		this.performancePlanDao = performancePlanDao;
	}

	/**
	 * @param userPerformanceItemDao the userPerformanceItemDao to set
	 */
	public void setUserPerformanceItemDao(SUserPerformanceItemDAO userPerformanceItemDao) {
		this.userPerformanceItemDao = userPerformanceItemDao;
	}

	/**
	 * @param userPerformancePlanDao the userPerformancePlanDao to set
	 */
	public void setUserPerformancePlanDao(SUserPerformancePlanDAO userPerformancePlanDao) {
		this.userPerformancePlanDao = userPerformancePlanDao;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.SUserPerformancePlanService#getUserPlanListPage(com.sales.model.SUserPerformancePlan)
	 */
	@Override
	public List<SUserPerformancePlan> getUserPlanListPage(SUserPerformancePlan upp) {
		return this.userPerformancePlanDao.getUserPlanListPage(upp);
	}

	/* (non-Javadoc)
	 * @see com.sales.service.SUserPerformancePlanService#getUserPlanListPageCount(com.sales.model.SUserPerformancePlan)
	 */
	@Override
	public Integer getUserPlanListPageCount(SUserPerformancePlan upp) {
		return this.userPerformancePlanDao.getUserPlanListPageCount(upp);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void addUserPlan(SUserPerformancePlan upp) {
		SPerformancePlan pp = this.performancePlanDao.selectByPrimaryKey(upp.getPlanId());
		if (pp.getCycleId().intValue() == Constants.YEAR_CYCLE_TYPE) {
		    upp.setSeason(null);
		    upp.setMonth(null);
		} else if (pp.getCycleId().intValue() == Constants.SEASON_CYCLE_TYPE) {
			upp.setMonth(null);
		} else {
			upp.setSeason(null);
		}
		upp.setCreateTime(new Date());
		upp.setStatus(0);
		Integer upid = this.userPerformancePlanDao.insert(upp);
		for(SUserPerformanceItem upi: upp.getUserItemList()) {
			upi.setPerformancePlanId(upid);
			if (upi.getCheckBoxStatus() != null && "on".equals(upi.getCheckBoxStatus())) {
				upi.setStatistics(1);
			}
			this.userPerformanceItemDao.insert(upi);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteUserPlan(Integer upid) {
		this.userPerformancePlanDao.deleteByPrimaryKey(upid);
		this.userPerformanceItemDao.deleteByUserPlanId(upid);
	}

	@Override
	public SUserPerformancePlan getUserPlan(Integer upid) {
		return this.userPerformancePlanDao.selectByPrimaryKey(upid);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void updateUserPlanItemScore(SUserPerformancePlan upp) {
		int totalScore = 0;
//		SUserPerformancePlan up = this.userPerformancePlanDao.selectByPrimaryKey(upp.getPerformancePlanId());
//		if (up.getTotalScore() != null) {
//			totalScore = up.getTotalScore();
//		}
		List<SUserPerformanceItem> userItemList = upp.getUserItemList();
		for(SUserPerformanceItem upi : userItemList) {
			if (upi.getScore() != null && !"".equals(upi.getScore())) {
			    this.userPerformanceItemDao.updateByPrimaryKeySelective(upi);
			    totalScore = totalScore + Integer.parseInt(upi.getScore());
			}
		}
		if (upp.getStatus().intValue() == 1) {
			upp.setTotalScore(totalScore);
			this.userPerformancePlanDao.updateByPrimaryKeySelective(upp);
		}
 	}

	@Override
	public List<SUserPerformanceItem> getUserItemListPage(SUserPerformanceItem upi) {
		return this.userPerformanceItemDao.getUserItemListPage(upi);
	}

	@Override
	public Integer getUserItemListPageCount(SUserPerformanceItem upi) {
		return this.userPerformanceItemDao.getUserItemListPageCount(upi);
	}

	@Override
	public SUserPerformanceItem getUserItem(Integer userItemId) {
		return this.userPerformanceItemDao.selectByPrimaryKey(userItemId);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void updateUserItemScore(SUserPerformanceItem upi) {
		this.userPerformanceItemDao.updateByPrimaryKeySelective(upi);
		int totalScore = 0;
		SUserPerformancePlan up = this.userPerformancePlanDao.selectByPrimaryKey(upi.getUserPlan().getPerformancePlanId());
		if (up.getTotalScore() != null) {
			totalScore = up.getTotalScore();
		}
		totalScore = totalScore + Integer.parseInt(upi.getScore());
		SUserPerformancePlan upp = upi.getUserPlan();
		upp.setTotalScore(totalScore);
		this.userPerformancePlanDao.updateByPrimaryKeySelective(upp);
	}

	@Override
	public double calSqlScore(Integer userItemId,Integer userId, String userIds) {
		double score = 0;
		SUserPerformanceItem upi = this.userPerformanceItemDao.selectByPrimaryKey(userItemId);
		
		SPerformanceSql ps = this.performanceSqlDao.selectByPrimaryKey(upi.getItem().getSqlId());
		if(ps == null)
			throw(new RuntimeException("Not Setted Calutor Sql Yet: userItemId is [" + userItemId + "]."));
		
		if(ps.getType().equalsIgnoreCase("sql")) {
			Date startDate = null;
			Date endDate = null;
			if (upi.getUserPlan().getPlan().getCycleId().intValue() == Constants.YEAR_CYCLE_TYPE) {
				startDate = new Date(upi.getUserPlan().getYear()-1900,0,1);
				endDate = new Date(upi.getUserPlan().getYear()-1900,11,31);
			} else if (upi.getUserPlan().getPlan().getCycleId().intValue() == Constants.SEASON_CYCLE_TYPE) {
				startDate = new Date(upi.getUserPlan().getYear()-1900,(upi.getUserPlan().getSeason()-1)*3,1);
				endDate = NormalFun.getLastDayOfQuarter(startDate);
			} else {
				startDate = new Date(upi.getUserPlan().getYear()-1900,upi.getUserPlan().getMonth()-1,1);
				endDate = NormalFun.getLastDayOfMonth(startDate);
			}
			if (upi.getStatistics() != null && upi.getStatistics().intValue() == 1) {
				//获取session中所有下属
			    upi.getUserPlan().setUserIds(userIds);
			} else {
				upi.getUserPlan().setUserIds(String.valueOf(upi.getUserPlan().getUserId()));
			}
			score = this.performanceSqlDao.calSqlScore(ps.getSqlContent(), upi.getUserPlan().getUserIds(), startDate, endDate);
			return score;
		}else if(ps.getType().equalsIgnoreCase("cal")) {
			Date startDate = new Date(upi.getUserPlan().getYear()-1900,upi.getUserPlan().getMonth()-1,1);
			Date endDate = NormalFun.getLastDayOfMonth(startDate);
			score = calCalutorScore(ps, userId, startDate, endDate);
			if(upi.getItem().getMeasurementUnit().equalsIgnoreCase("%"))
				score = score * 100;
			return score;
		}else if(ps.getType().equalsIgnoreCase("ben")) {
			return score;
		}else {
			throw(new RuntimeException("Not support performanceSql type [" + ps.getType() + "]."));
		}
	}
	
	/**
	 * @param userId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	private Double calCalutorScore(SPerformanceSql ps, Integer userId, 
			Date beginDate, Date endDate) {
		String script = ps.getSqlContent();
		List<String> varList = new ArrayList<String>();
		List<Double> values = new ArrayList<Double>();
		boolean isVarBegin = false;
		String var="";
		String cals = "";
		for (int i = 0; i < script.length(); i++) {
			char c = script.charAt(i);
			if(c == '#' && isVarBegin==false) {
				isVarBegin = true;
			}else if(c == '#' && isVarBegin==true) {
				isVarBegin = false;
				varList.add(var);
				var = "";
			}else if(c != '#' && isVarBegin==true) {
				var = var + c;
			}else if(c != '#' && isVarBegin==false) {
				cals = cals + c;
			}
		}
		
		for (Iterator iterator = varList.iterator(); iterator.hasNext();) {
			String name = (String) iterator.next();
			SPerformanceSql psql = performanceSqlDao.selectByPrimaryName(name);
			Double val = calBeanScore(psql.getSqlContent(), userId, beginDate, endDate);
			
			values.add(val);
		}
		
		if(cals.equalsIgnoreCase("/") && values.get(1) != 0) {
			return values.get(0)/values.get(1);
		}else if(values.get(1) == 0)		//分母为零，指标值100%
			return -1.0;
		
		return 0.0;
	}
	
	/**
	 * @param userId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	private double calBeanScore(String beanCal, Integer userId, 
			Date beginDate, Date endDate) {
		double value = 0.0;
		
		if(beanCal.equalsIgnoreCase("k3OrderStatisticsService.statBetweenDate")) {
			value = statisticsOrderService.statBetweenDate(beginDate, endDate, userId);
		}
		if(beanCal.equalsIgnoreCase("quotaService.queryThisMonth")) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(beginDate);
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			Double dv = quotaService.queryThisMonth(year, month, userId, K3SalerQuota.QUOTA_CLASS_ORDER);
			if(dv != null)
				value = dv.doubleValue();
		}
		if(beanCal.equalsIgnoreCase("statisticsRecieveService.statBetweenDate")) {
			Double dv = statisticsRecieveService.statBetweenDate(beginDate, endDate, userId);
			if(dv != null)
				value = dv.doubleValue();
		}
		if(beanCal.equalsIgnoreCase("statisticsICSaleService.statBetweenDate")) {
			Double dv = statisticsICSaleService.statBetweenDate(beginDate, endDate, userId);
			if(dv != null)
				value = dv.doubleValue();
		}
		
		return value;
	}
	
	@Override
	public List<SUserPerformancePlan> getUserPlanListByCircle(SUserPerformancePlan upp) {
		SPerformancePlan pp = this.performancePlanDao.selectByPrimaryKey(upp.getPlanId());
		if (pp.getCycleId().intValue() == Constants.YEAR_CYCLE_TYPE) {
		    upp.setSeason(null);
		    upp.setMonth(null);
		} else if (pp.getCycleId().intValue() == Constants.SEASON_CYCLE_TYPE) {
			upp.setMonth(null);
		} else {
			upp.setSeason(null);
		}
		return this.userPerformancePlanDao.getUserPlanListPage(upp);
	}
	
	

}
