/**
 * 
 */
package com.sales.common;

import com.sales.model.BaseObject;

/**
 * @author apple
 *
 */
public class LabelStringValueBean extends BaseObject {

	private String label;
	private String value;
	/**
	 * @param label
	 * @param value
	 */
	public LabelStringValueBean(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
