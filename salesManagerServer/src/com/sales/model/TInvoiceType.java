/**
 * 
 */
package com.sales.model;

/**
 * 销售报价单开票方式
 * @author Administrator
 *
 */
public class TInvoiceType extends BaseObject {
	private int typeId;		//方式id
	private String Name;	//开票方式名
	
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
