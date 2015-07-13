package com.sales.model;

public class SEnumeration {
	
	private Integer enumerationId;
	private String enumerationCode;
	private Integer enumerationType;
	private String enumerationName;
	private String isSelect;
	/**
	 * 
	 */
	public SEnumeration() {
		super();
	}
	
	/**
	 * @param enumerationId
	 * @param enumerationName
	 */
	public SEnumeration(Integer enumerationId, String enumerationName) {
		super();
		this.enumerationId = enumerationId;
		this.enumerationName = enumerationName;
	}

	/**
	 * @param enumerationId
	 * @param enumerationCode
	 * @param enumerationType
	 * @param enumerationName
	 */
	public SEnumeration(Integer enumerationId, String enumerationCode,
			Integer enumerationType, String enumerationName) {
		super();
		this.enumerationId = enumerationId;
		this.enumerationCode = enumerationCode;
		this.enumerationType = enumerationType;
		this.enumerationName = enumerationName;
	}
	
	
	public String getIsSelect() {
		return isSelect;
	}
	public void setIsSelect(String isSelect) {
		this.isSelect = isSelect;
	}
	public Integer getEnumerationId() {
		return enumerationId;
	}
	public void setEnumerationId(Integer enumerationId) {
		this.enumerationId = enumerationId;
	}
	public String getEnumerationCode() {
		return enumerationCode;
	}
	public void setEnumerationCode(String enumerationCode) {
		this.enumerationCode = enumerationCode;
	}
	public Integer getEnumerationType() {
		return enumerationType;
	}
	public void setEnumerationType(Integer enumerationType) {
		this.enumerationType = enumerationType;
	}
	public String getEnumerationName() {
		return enumerationName;
	}
	public void setEnumerationName(String enumerationName) {
		this.enumerationName = enumerationName;
	}

}
