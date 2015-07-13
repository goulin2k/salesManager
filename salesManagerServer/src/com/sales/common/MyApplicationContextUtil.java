/**
 * 
 */
package com.sales.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Leo
 *
 */
public class MyApplicationContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext context;

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}
	
	public static ApplicationContext getContext(){
		return context;
	}

}
