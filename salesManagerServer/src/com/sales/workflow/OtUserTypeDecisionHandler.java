/**
 * 
 */
package com.sales.workflow;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.springframework.context.ApplicationContext;

import com.sales.common.MyApplicationContextUtil;
import com.sales.model.SUser;
import com.sales.service.SUserService;

/**
 * @author Leo
 *
 */
public class OtUserTypeDecisionHandler implements DecisionHandler {

	/* (non-Javadoc)
	 * @see org.jbpm.api.jpdl.DecisionHandler#decide(org.jbpm.api.model.OpenExecution)
	 */
	@Override
	public String decide(OpenExecution exe) {
		Integer userId = Integer.valueOf((String) exe.getVariable("userId"));
		ApplicationContext context = MyApplicationContextUtil.getContext();
		SUserService userService = (SUserService) context.getBean("sUserService");
		SUser startUser = userService.getUserById(userId);
		SUser parentUser = userService.getUserById(startUser.getParentUserId());
		
		if ("后勤部经理".equals(parentUser.getPositionBsName())) {
			return "后勤部经理";
		} else {
			return "上级主管";
		}
	}

}
