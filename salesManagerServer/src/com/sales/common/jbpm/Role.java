/**
 * 
 */
package com.sales.common.jbpm;

import org.jbpm.api.identity.Group;

/**
 * @author Administrator
 *
 */
public class Role implements Group{
	private String id;
	private String name;
	private String type;
	
	

	/**
	 * @param id
	 * @param name
	 * @param type
	 */
	public Role(String id, String name, String type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see org.jbpm.api.identity.Group#getId()
	 */
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.jbpm.api.identity.Group#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.jbpm.api.identity.Group#getType()
	 */
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	
	
}
