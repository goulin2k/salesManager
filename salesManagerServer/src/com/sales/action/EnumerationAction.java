package com.sales.action;

import java.util.List;

import com.sales.model.SEnumeration;
import com.sales.service.EnumerationService;

/** 
 * @author  
 * @version 创建时间：2013-6-14 上午11:41:11 
 *  
 */
public class EnumerationAction extends BaseAction {
	
	private EnumerationService enumerationService;
	private List<SEnumeration> enumerationList;
	private List<SEnumeration> enumerationIndexList;
	private Integer enumerationType;
	private SEnumeration sEnumeration;
	
	public List<SEnumeration> getEnumerationIndexList() {
		return enumerationIndexList;
	}
	public void setEnumerationIndexList(List<SEnumeration> enumerationIndexList) {
		this.enumerationIndexList = enumerationIndexList;
	}
	
	public SEnumeration getsEnumeration() {
		return sEnumeration;
	}
	public void setsEnumeration(SEnumeration sEnumeration) {
		this.sEnumeration = sEnumeration;
	}
	public Integer getEnumerationType() {
		return enumerationType;
	}
	public void setEnumerationType(Integer enumerationType) {
		this.enumerationType = enumerationType;
	}
	public EnumerationService getEnumerationService() {
		return enumerationService;
	}
	public void setEnumerationService(EnumerationService enumerationService) {
		this.enumerationService = enumerationService;
	}
	public List<SEnumeration> getEnumerationList() {
		return enumerationList;
	}
	public void setEnumerationList(List<SEnumeration> enumerationList) {
		this.enumerationList = enumerationList;
	}
	
	public String getEnumList(){
		if(enumerationType==null){
			this.enumerationType = 3;
		}
		
		this.enumerationList = this.enumerationService.getEnumerationByType(enumerationType);
		this.enumerationIndexList = this.enumerationService.getEnumerationByType(2);
		return "getEnumList";
	}

}
