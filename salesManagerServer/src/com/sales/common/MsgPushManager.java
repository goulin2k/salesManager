package com.sales.common;

import java.util.Collection;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;

public class MsgPushManager {

	/**
	 * 发送消息
	 * 
	 * @param sender
	 *            发送者
	 * @param receiverid
	 *            接收者id
	 * @param msg
	 *            消息内容
	 */
	public void send(final String sender, final String receiverid,
			final String msg) {

		Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
			public boolean match(ScriptSession session) {
				if (session.getAttribute(Constants.DWR_USERID) == null)
					return false;
				else
					return (session.getAttribute(Constants.DWR_USERID)).equals(receiverid);
			}
		}, new Runnable() {
			public void run() {
				ScriptBuffer script = new ScriptBuffer();
				script.appendCall("showMsg", msg);
				Collection<ScriptSession> sessions = Browser
						.getTargetSessions();
				for (ScriptSession scriptSession : sessions) {
				    //调用对应用户ID的页面js
					scriptSession.addScript(script);
				}
			}
		});
	}

}
