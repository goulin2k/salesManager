/**
 * 
 */
package com.sales.model;

import java.util.List;

import com.sales.action.app.ListViewObject;
import com.sales.common.LabelStringValueBean;

/**
 * @author apple
 *
 */
public class BuzViewObject extends BaseObject {

	private String title;
	private List<LabelStringValueBean> data;
	
	
	
	private ListViewObject listData;
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the data
	 */
	public List<LabelStringValueBean> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<LabelStringValueBean> data) {
		this.data = data;
	}
	
	/**
	 * @return the listData
	 */
	public ListViewObject getListData() {
		return listData;
	}
	/**
	 * @param listData the listData to set
	 */
	public void setListData(ListViewObject listData) {
		this.listData = listData;
	}
	
	
}
