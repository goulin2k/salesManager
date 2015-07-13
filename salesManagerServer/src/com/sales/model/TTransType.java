/**
 * 
 */
package com.sales.model;

/**
 * 销售报价单运输方式
 * @author Administrator
 *
 */
public class TTransType extends BaseObject {
	private int typeId;		//方式id
	private String Name;	//运输方式名
	
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
}
