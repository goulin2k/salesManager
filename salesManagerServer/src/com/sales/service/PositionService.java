package com.sales.service;

import java.util.List;

import com.sales.model.SPosition;
/** 
 * @author  
 * @version 创建时间：2013-6-4 下午10:35:18 
 *  
 */
public interface PositionService {
	
	public List<SPosition> positionListByType(Integer type);

}
