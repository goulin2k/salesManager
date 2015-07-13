/**
 * 
 */
package com.sales.action;

import java.util.List;

import com.sales.common.Constants;
import com.sales.model.SExpenseApplication;
import com.sales.model.SExpenseReimbursement;
import com.sales.model.STripApplication;
import com.sales.service.SExpenseApplicationService;
import com.sales.service.SExpenseReimbursementService;
import com.sales.service.STripApplicationService;

/**
 * @author Leo
 *
 */
public class ExpenseAction extends BaseAction {
	
	private SExpenseApplicationService expenseApplicationService;
	
	private SExpenseReimbursementService expenseReimbursementService;
	
	private STripApplicationService tripApplicationService;
	
	private SExpenseApplication ea;
	
	private SExpenseReimbursement er;
	
	private List<SExpenseApplication> eaList;
	
	private List<SExpenseReimbursement> erList;
	
	private List<STripApplication> tripList;
	
	private Integer eaId;
	
	private Integer erId;
	
	public String eaList() {
		if (this.ea == null) {
			this.ea = new SExpenseApplication();
		}
		if (this.ea.getPageNo() == null) {
			this.ea.setPageNo(1);
		}
		this.ea.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		this.ea.setExpenseUserId(this.getLoginUser().getUserId());
		this.eaList = this.expenseApplicationService.getExpenseApplicationListPage(this.ea);
		int totalCount = this.expenseApplicationService.getExpenseApplicationListPageCount(this.ea);
		this.ea.setTotalCount(totalCount);
		return "eaList";
	}
	
	public String toEditEa() {
		if (this.eaId != null) {
			this.ea = this.expenseApplicationService.getExpenseApplication(this.eaId);
		}
		return "expenseApplicationEdit";
	}
	
	public String editEa() {
		this.expenseApplicationService.addExpenseApplication(this.ea,this.getLoginUser());
		return "toEaList";
	}
	
	public String deleteEa() {
		this.expenseApplicationService.deleteEa(this.eaId);
		return "toEaList";
	}
	
	public String eaDetail() {
		this.ea = this.expenseApplicationService.getExpenseApplication(this.eaId);
		return "eaDetail";
	}
	
	public String toReSubmitEa() {
		this.ea = this.expenseApplicationService.getExpenseApplication(this.eaId);
		return "eaReSubmit";
	}
	
	public String reSubmitEa() {
		this.ea.setExpenseUserId(this.getLoginUser().getUserId());
		this.expenseApplicationService.updateExpenseApplication(ea);
		return "toEaList";
	}
	
	public String erList() {
		if (this.er == null) {
			this.er = new SExpenseReimbursement();
		}
		if (this.er.getPageNo() == null) {
			this.er.setPageNo(1);
		}
		this.er.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		this.er.setReimbursementUserId(this.getLoginUser().getUserId());
		this.erList = this.expenseReimbursementService.getExpenseReimbursementListPage(this.er);
		int totalCount = this.expenseReimbursementService.getExpenseReimbursementListPageCount(this.er);
		this.er.setTotalCount(totalCount);
		return "erList";
	}
	
	public String erDetail() {
		this.er = this.expenseReimbursementService.getExpenseReimbursement(this.erId);
		return "erDetail";
	}
	
	public String toEditEr() {
		if (this.erId != null) {
			this.er = this.expenseReimbursementService.getExpenseReimbursement(this.erId);
		}
		this.eaList = this.expenseApplicationService.getExpenseApplicationListForReimbursement(this.getLoginUser().getUserId());
		this.tripList = this.tripApplicationService.getTripApplicationListForReimbursement(this.getLoginUser().getUserId());
		return "expenseReimbursementEdit";
	}
	
	public String editEr() {
		this.expenseReimbursementService.addExpenseReimbursement(this.er,this.getLoginUser());
		return "toErList";
	}
	
	public String deleteEr() {
		this.expenseReimbursementService.deleteEr(this.erId);
		return "toErList";
	}
	
	public String toReSubmitEr() {
		this.er = this.expenseReimbursementService.getExpenseReimbursement(erId);
		this.eaList = this.expenseApplicationService.getExpenseApplicationListForReimbursement(this.getLoginUser().getUserId());
		this.tripList = this.tripApplicationService.getTripApplicationListForReimbursement(this.getLoginUser().getUserId());
		return "erReSubmit";
	}
	
	public String reSubmitEr() {
		this.er.setReimbursementUserId(this.getLoginUser().getUserId());
		this.expenseReimbursementService.updateExpenseReimbursement(er);
		return "toErList";
	}

	/**
	 * @param expenseApplicationService the expenseApplicationService to set
	 */
	public void setExpenseApplicationService(SExpenseApplicationService expenseApplicationService) {
		this.expenseApplicationService = expenseApplicationService;
	}

	/**
	 * @param expenseReimbursementService the expenseReimbursementService to set
	 */
	public void setExpenseReimbursementService(SExpenseReimbursementService expenseReimbursementService) {
		this.expenseReimbursementService = expenseReimbursementService;
	}

	/**
	 * @return the ea
	 */
	public SExpenseApplication getEa() {
		return ea;
	}

	/**
	 * @param ea the ea to set
	 */
	public void setEa(SExpenseApplication ea) {
		this.ea = ea;
	}

	/**
	 * @return the er
	 */
	public SExpenseReimbursement getEr() {
		return er;
	}

	/**
	 * @param er the er to set
	 */
	public void setEr(SExpenseReimbursement er) {
		this.er = er;
	}

	/**
	 * @return the eaList
	 */
	public List<SExpenseApplication> getEaList() {
		return eaList;
	}

	/**
	 * @param eaList the eaList to set
	 */
	public void setEaList(List<SExpenseApplication> eaList) {
		this.eaList = eaList;
	}

	/**
	 * @return the erList
	 */
	public List<SExpenseReimbursement> getErList() {
		return erList;
	}

	/**
	 * @param erList the erList to set
	 */
	public void setErList(List<SExpenseReimbursement> erList) {
		this.erList = erList;
	}

	/**
	 * @return the eaId
	 */
	public Integer getEaId() {
		return eaId;
	}

	/**
	 * @param eaId the eaId to set
	 */
	public void setEaId(Integer eaId) {
		this.eaId = eaId;
	}

	/**
	 * @return the erId
	 */
	public Integer getErId() {
		return erId;
	}

	/**
	 * @param erId the erId to set
	 */
	public void setErId(Integer erId) {
		this.erId = erId;
	}

	public List<STripApplication> getTripList() {
		return tripList;
	}

	public void setTripList(List<STripApplication> tripList) {
		this.tripList = tripList;
	}

	public void setTripApplicationService(
			STripApplicationService tripApplicationService) {
		this.tripApplicationService = tripApplicationService;
	}

}
