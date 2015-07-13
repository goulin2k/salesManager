package com.sales.common;

import javax.servlet.http.HttpSession;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.impl.DefaultScriptSessionManager;

import com.sales.model.SUser;

public class DwrScriptSessionManagerUtil extends DefaultScriptSessionManager {

	public DwrScriptSessionManagerUtil() {
		try {
			addScriptSessionListener(new ScriptSessionListener() {

				public void sessionDestroyed(ScriptSessionEvent event) {
				}

				public void sessionCreated(ScriptSessionEvent event) {
					HttpSession session = WebContextFactory.get().getSession();  
	                ScriptSession scriptSession = event.getSession();  
	                if (session.getAttribute(Constants.USER_INFO) == null) 
	                	return;
	                String userId = String.valueOf(((SUser)session.getAttribute(Constants.USER_INFO)).getUserId());  
	                scriptSession.setAttribute(Constants.DWR_USERID, userId);  
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
