package com.sales.common.jbpm;

import org.jbpm.api.identity.User;

public class MyUser implements org.jbpm.api.identity.User{
	private String id;
	private String givenName;
	private String businessEmail;
	private String familyName;
	
	/**
	 * @param id
	 * @param givenName
	 * @param businessEmail
	 * @param familyName
	 */
	public MyUser(String id, String givenName, String businessEmail,
			String familyName) {
		super();
		this.id = id;
		this.givenName = givenName;
		this.businessEmail = businessEmail;
		this.familyName = familyName;
	}

	/* (non-Javadoc)
	 * @see org.jbpm.api.identity.User#getBusinessEmail()
	 */
	@Override
	public String getBusinessEmail() {
		// TODO Auto-generated method stub
		return businessEmail;
	}

	/* (non-Javadoc)
	 * @see org.jbpm.api.identity.User#getFamilyName()
	 */
	@Override
	public String getFamilyName() {
		// TODO Auto-generated method stub
		return familyName;
	}

	/* (non-Javadoc)
	 * @see org.jbpm.api.identity.User#getGivenName()
	 */
	@Override
	public String getGivenName() {
		// TODO Auto-generated method stub
		return givenName;
	}

	/* (non-Javadoc)
	 * @see org.jbpm.api.identity.User#getId()
	 */
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * @param businessEmail the businessEmail to set
	 */
	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
	}

	/**
	 * @param familyName the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	
	
	
}
