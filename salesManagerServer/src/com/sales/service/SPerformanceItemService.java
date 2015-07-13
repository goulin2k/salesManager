/**
 * 
 */
package com.sales.service;

import java.util.List;

import com.sales.model.SPerformanceItem;

/**
 * @author Leo
 *
 */
public interface SPerformanceItemService {
	
    public List<SPerformanceItem> getItemListPage(SPerformanceItem spi);
    
    public Integer getItemListPageCount(SPerformanceItem spi);
    
    public void addItem(SPerformanceItem spi);
    
    public SPerformanceItem getItem(Integer id);
    
    public void deleteItem(Integer id);
    
    public List<SPerformanceItem> getPlanItemList(Integer planId);
}
