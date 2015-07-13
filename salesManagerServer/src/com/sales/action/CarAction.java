/**
 * 
 */
package com.sales.action;

import java.util.List;

import net.sf.json.JSONObject;

import com.sales.common.Constants;
import com.sales.common.NormalFun;
import com.sales.common.Result;
import com.sales.model.SCarRepairApplication;
import com.sales.model.SCarRepairReimbursement;
import com.sales.service.SCarRepairApplicationService;
import com.sales.service.SCarRepairReimbursementService;

/**
 * @author Leo
 *
 */
public class CarAction extends BaseAction {

	private SCarRepairApplicationService carRepairApplicationService;
	
	private SCarRepairReimbursementService carRepairReimbursementService;
	
	private SCarRepairApplication ca;
	
	private SCarRepairReimbursement cr;
	
    private List<SCarRepairApplication> caList;
	
	private List<SCarRepairReimbursement> crList;
	
	private Integer caId;
	
	private Integer crId;
	
	public String caList() {
		if (this.ca == null) {
			this.ca = new SCarRepairApplication();
		}
		if (this.ca.getPageNo() == null) {
			this.ca.setPageNo(1);
		}
		this.ca.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		this.ca.setUserId(this.getLoginUser().getUserId());
		this.caList = this.carRepairApplicationService.getCarApplicationListPage(ca);
		int totalCount = this.carRepairApplicationService.getCarApplicationListPageCount(ca);
		this.ca.setTotalCount(totalCount);
		return "caList";
	}
	
	public String toEditCa() {
		if (this.caId != null) {
			this.ca = this.carRepairApplicationService.getCarApplication(this.caId);
		}
		return "caEdit";
	}
	
	public String editCa() {
		if(isFromMobile()) {
			ca.setRepairComment(NormalFun.getUTF8(ca.getRepairComment()));
		}
		this.carRepairApplicationService.addCarRepairApplication(this.ca,this.getLoginUser());
		if(isFromMobile()) {
			Result res = new Result(Result.SUCESSED, "车辆维修申请流程提交成功！");
			JSONObject json = JSONObject.fromObject(res);
			writeJsonP(json);
			return null;
		}
		return "toCaList";
	}
	
	public String deleteCa() {
		this.carRepairApplicationService.deleteCa(this.caId);
		return "toCaList";
	}
	
	public String caDetail() {
		this.ca = this.carRepairApplicationService.getCarApplication(caId);
		return "caDetail";
	}
	
	public String toReSubmitCa() {
		this.ca = this.carRepairApplicationService.getCarApplication(caId);
		return "caReSubmit";
	}
	
	public String reSubmitCa() {
		this.ca.setUserId(this.getLoginUser().getUserId());
		this.carRepairApplicationService.updateCarRepairApplication(ca);
		return "toCaList";
	}
	
	public String crList() {
		if (this.cr == null) {
			this.cr = new SCarRepairReimbursement();
		}
		if (this.cr.getPageNo() == null) {
			this.cr.setPageNo(1);
		}
		this.cr.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		this.cr.setUserId(this.getLoginUser().getUserId());
		this.crList = this.carRepairReimbursementService.getCarReimbursementListPage(this.cr);
		int totalCount = this.carRepairReimbursementService.getCarReimbursementListPageCount(this.cr);
		this.cr.setTotalCount(totalCount);
		return "crList";
	}
	
	public String toEditCr() {
		if (this.crId != null) {
			this.cr = this.carRepairReimbursementService.getCarReimbursement(this.crId);
		}
		this.caList = this.carRepairApplicationService.getCarApplicationListForReimbursement(this.getLoginUser().getUserId());
		return "crEdit";
	}
	
	public String editCr() {
		if(isFromMobile()) {
			cr.setReimbursementComment(NormalFun.getUTF8(cr.getReimbursementComment()));
		}
		this.carRepairReimbursementService.addCarRepairReimbursement(this.cr,this.getLoginUser());
		if(isFromMobile()) {
			Result res = new Result(Result.SUCESSED, "车辆维修报销申请流程提交成功！");
			JSONObject json = JSONObject.fromObject(res);
			writeJsonP(json);
			return null;
		}
		return "toCrList";
	}
	
	public String deleteCr() {
		this.carRepairReimbursementService.deleteCr(this.crId);
		return "toCrList";
	}
	
	public String crDetail() {
		this.cr = this.carRepairReimbursementService.getCarReimbursement(crId);
		return "crDetail";
	}
	
	public String toReSubmitCr() {
		this.cr = this.carRepairReimbursementService.getCarReimbursement(crId);
		this.caList = this.carRepairApplicationService.getCarApplicationListForReimbursement(this.getLoginUser().getUserId());
		return "crReSubmit";
	}
	
	public String reSubmitCr() {
		this.cr.setUserId(this.getLoginUser().getUserId());
		this.carRepairReimbursementService.updateCarRepairReimbursement(cr);
		return "toCrList";
	}

	/**
	 * @return the ca
	 */
	public SCarRepairApplication getCa() {
		return ca;
	}

	/**
	 * @param ca the ca to set
	 */
	public void setCa(SCarRepairApplication ca) {
		this.ca = ca;
	}

	/**
	 * @return the cr
	 */
	public SCarRepairReimbursement getCr() {
		return cr;
	}

	/**
	 * @param cr the cr to set
	 */
	public void setCr(SCarRepairReimbursement cr) {
		this.cr = cr;
	}

	/**
	 * @return the caList
	 */
	public List<SCarRepairApplication> getCaList() {
		return caList;
	}

	/**
	 * @param caList the caList to set
	 */
	public void setCaList(List<SCarRepairApplication> caList) {
		this.caList = caList;
	}

	/**
	 * @return the crList
	 */
	public List<SCarRepairReimbursement> getCrList() {
		return crList;
	}

	/**
	 * @param crList the crList to set
	 */
	public void setCrList(List<SCarRepairReimbursement> crList) {
		this.crList = crList;
	}

	/**
	 * @return the caId
	 */
	public Integer getCaId() {
		return caId;
	}

	/**
	 * @param caId the caId to set
	 */
	public void setCaId(Integer caId) {
		this.caId = caId;
	}

	/**
	 * @return the crId
	 */
	public Integer getCrId() {
		return crId;
	}

	/**
	 * @param crId the crId to set
	 */
	public void setCrId(Integer crId) {
		this.crId = crId;
	}

	/**
	 * @param carRepairApplicationService the carRepairApplicationService to set
	 */
	public void setCarRepairApplicationService(SCarRepairApplicationService carRepairApplicationService) {
		this.carRepairApplicationService = carRepairApplicationService;
	}

	/**
	 * @param carRepairReimbursementService the carRepairReimbursementService to set
	 */
	public void setCarRepairReimbursementService(SCarRepairReimbursementService carRepairReimbursementService) {
		this.carRepairReimbursementService = carRepairReimbursementService;
	} 
}
