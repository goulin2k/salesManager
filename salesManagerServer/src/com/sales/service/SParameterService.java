/**
 * 
 */
package com.sales.service;

import java.util.List;

import com.sales.model.SParameter;

/**
 * @author Leo
 *
 */
public interface SParameterService {
	
	public List<SParameter> getParameterListPage(SParameter param);
	
	public Integer getParameterListPageCount(SParameter param);
	
	public void addParameter(SParameter param);
	
	public void updateParameter(SParameter param);
	
	public void deleteParameter(Integer id);
	
	public SParameter getParameter(Integer id);
	
	public SParameter getParameter(String name);

}
