/**
 * 
 */
package com.sales.action;

import com.sales.common.Constants;
import com.sales.model.SCustomerPProduct;
import com.sales.model.SUser;
import com.sales.service.CustomerPotentialProductService;

import freemarker.core.ReturnInstruction.Return;

/**
 * 客户潜力产品Action
 * @author apple
 *
 */
public class CustomerPProductionAction extends BaseAction {
	private SCustomerPProduct pproduction;
	private CustomerPotentialProductService pproductionService;
	private int customerId;
	
	
	/**
	 * @return the pproduction
	 */
	public SCustomerPProduct getPproduction() {
		return pproduction;
	}
	/**
	 * @param pproduction the pproduction to set
	 */
	public void setPproduction(SCustomerPProduct pproduction) {
		this.pproduction = pproduction;
	}
	/**
	 * @param pproductionService the pproductionService to set
	 */
	public void setPproductionService(
			CustomerPotentialProductService pproductionService) {
		this.pproductionService = pproductionService;
	}
	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	/**
	 * 获取指定客户的潜力产品信息
	 * @return
	 */
	public String getPotentialProduct() throws Exception{
		String id = request.getParameter("customerId");
		if(id != null && !id.equalsIgnoreCase("")) {
			customerId = Integer.parseInt(id);
			pproduction = pproductionService.getPProduct(customerId);
		}
		if(pproduction==null) {
			customerId = Integer.parseInt(id);
			pproduction = new SCustomerPProduct();
		}
		String name = request.getParameter("customerName");
		String cusName = new String(name.getBytes("ISO-8859-1"), "UTF-8"); 
		pproduction.setCustomerName(cusName);
		pproduction.setCustomerId(customerId);
		return "view";
	}
	
	/**
	 * 修改保存客户潜力产品信息
	 * @return
	 */
	public String save() {
		SUser user = (SUser) this.session.get(Constants.USER_INFO);
		pproduction.setUserName(user.getUserName());
		customerId = pproduction.getCustomerId();
		pproductionService.save(pproduction);
		
		return "saved";
	}

}
