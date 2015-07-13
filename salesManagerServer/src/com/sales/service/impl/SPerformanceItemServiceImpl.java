/**
 * 
 */
package com.sales.service.impl;

import java.util.List;

import com.sales.dao.SPerformanceItemDAO;
import com.sales.model.SPerformanceItem;
import com.sales.service.SPerformanceItemService;

/**
 * @author Leo
 *
 */
public class SPerformanceItemServiceImpl implements SPerformanceItemService {
	
	private SPerformanceItemDAO performanceItemDao;

	/**
	 * @param performanceItemDao the performanceItemDao to set
	 */
	public void setPerformanceItemDao(SPerformanceItemDAO performanceItemDao) {
		this.performanceItemDao = performanceItemDao;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.SPerformanceItemService#addItem(com.sales.model.SPerformanceItem)
	 */
	@Override
	public void addItem(SPerformanceItem spi) {
		this.performanceItemDao.insert(spi);
	}

	/* (non-Javadoc)
	 * @see com.sales.service.SPerformanceItemService#deleteItem(java.lang.Integer)
	 */
	@Override
	public void deleteItem(Integer id) {
		this.performanceItemDao.deleteByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.sales.service.SPerformanceItemService#getItem(java.lang.Integer)
	 */
	@Override
	public SPerformanceItem getItem(Integer id) {
		return this.performanceItemDao.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.sales.service.SPerformanceItemService#getItemListPage(com.sales.model.SPerformanceItem)
	 */
	@Override
	public List<SPerformanceItem> getItemListPage(SPerformanceItem spi) {
		if (spi == null) {
			spi = new SPerformanceItem();
		}
		return this.performanceItemDao.getItemListPage(spi);
	}

	/* (non-Javadoc)
	 * @see com.sales.service.SPerformanceItemService#getItemistPageCount(com.sales.model.SPerformanceItem)
	 */
	@Override
	public Integer getItemListPageCount(SPerformanceItem spi) {
		return this.performanceItemDao.getItemListPageCount(spi);
	}

	@Override
	public List<SPerformanceItem> getPlanItemList(Integer planId) {
		return this.performanceItemDao.getPlanItemList(planId);
	}

}
