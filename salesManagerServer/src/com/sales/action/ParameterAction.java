/**
 * 
 */
package com.sales.action;

import java.util.List;

import com.sales.common.Constants;
import com.sales.model.SParameter;
import com.sales.service.SParameterService;

/**
 * @author Leo
 * 
 */
public class ParameterAction extends BaseAction {

	private SParameterService parameterService;

	private SParameter parameter;

	private List<SParameter> parameterList;

	public String paramList() {
		if (this.parameter == null) {
			this.parameter = new SParameter();
		}
		if (this.parameter.getPageNo() == null) {
			this.parameter.setPageNo(1);
		}
		this.parameter.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		this.parameterList = this.parameterService.getParameterListPage(this.parameter);
		int totalCount = this.parameterService.getParameterListPageCount(this.parameter);
		this.parameter.setTotalCount(totalCount);
		return "parameterList";
	}
	
	public String toEdit() {
		if (this.request.getParameter("id") != null) {
			Integer id = Integer.valueOf(this.request.getParameter("id"));
			this.parameter = this.parameterService.getParameter(id);
		}
		return "parameterEdit";
	}

	public String edit() {
		if (this.parameter.getParameterId() == null) {
			this.parameterService.addParameter(this.parameter);
		}else {
			this.parameterService.updateParameter(this.parameter);
		}
		
		return "list";
	}

	public String show() {
		// TODO Auto-generated method stub
		return null;
	}

	public String delete() {
		this.parameterService.deleteParameter(Integer.valueOf(this.request.getParameter("id")));
		return "list";
	}

	/**
	 * @param parameterService
	 *            the parameterService to set
	 */
	public void setParameterService(SParameterService parameterService) {
		this.parameterService = parameterService;
	}

	/**
	 * @return the parameter
	 */
	public SParameter getParameter() {
		return parameter;
	}

	/**
	 * @param parameter
	 *            the parameter to set
	 */
	public void setParameter(SParameter parameter) {
		this.parameter = parameter;
	}

	/**
	 * @return the parameterList
	 */
	public List<SParameter> getParameterList() {
		return parameterList;
	}

	/**
	 * @param parameterList
	 *            the parameterList to set
	 */
	public void setParameterList(List<SParameter> parameterList) {
		this.parameterList = parameterList;
	}

}
