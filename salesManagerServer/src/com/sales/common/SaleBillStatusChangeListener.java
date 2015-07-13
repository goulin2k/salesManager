package com.sales.common;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SaleBillStatusChangeListener implements ServletContextListener {

	private Timer timer = null;

	public void contextInitialized(ServletContextEvent event) {
		// 在这里初始化监听器，在tomcat启动的时候监听器启动，可以在这里实现定时器功能
		timer = new Timer(true);
	}

	public void contextDestroyed(ServletContextEvent event) {// 在这里关闭监听器，所以在这里销毁定时器。
		timer.cancel();
		event.getServletContext().log(
				"定时器销毁--------------------------------------------------");
	}

}
