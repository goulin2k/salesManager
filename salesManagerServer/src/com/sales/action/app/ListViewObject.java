/**
 * 
 */
package com.sales.action.app;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sales.common.ClassAccessor;
import com.sales.common.DisplayField;
import com.sales.common.LabelStringValueBean;
import com.sales.model.BaseObject;

/**
 * @author apple
 *
 */
public class ListViewObject extends BaseObject {

	private String title;
	private List<String> labels;
	private List<Object> data;
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
	 * @return the labels
	 */
	public List<String> getLabels() {
		return labels;
	}
	/**
	 * @param labels the labels to set
	 */
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	/**
	 * @return the data
	 */
	public List<Object> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<Object> data) {
		this.data = data;
	}
	
	/**
	 * @return
	 */
	public int getColumnsSize() {
		if(labels == null)
			return 0;
		return labels.size();
	}
	
	/**
	 * @return
	 */
	public int getDataSize() {
		if(data == null)
			return 0;
		return data.size();
	}
	
	/**
	 * @param lo
	 * @param labels
	 * @param listVals
	 * @param dsField
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	public static ListViewObject getListViewObject(Object object) throws NoSuchFieldException,
			IllegalAccessException {
		ListViewObject lo = new ListViewObject();
		List<String> labels = new ArrayList<String>();
		List listVals = new ArrayList();
		
		List<LabelStringValueBean> list = new ArrayList<LabelStringValueBean>();
		List<DisplayField> dslist = ClassAccessor.getClassProps(
				object.getClass().getName());
		
		
		try {
			for (Iterator iterator = dslist.iterator(); iterator.hasNext();) {
				DisplayField dsField = (DisplayField) iterator.next();
				if(dsField.isChildrenList() == false)
					continue;
				//获取List属性的值
				lo.setTitle(dsField.getDisplayName());
				Field listField = object.getClass().getDeclaredField(
						dsField.getProperty());
				listField.setAccessible(true);
				List listData = (List) listField.get(object);
		
				if (listData.size() > 0) {
		
					List<DisplayField> listFields = ClassAccessor
							.getClassProps(listData.get(0).getClass().getName());
					for (Iterator iterator2 = listFields.iterator(); iterator2
							.hasNext();) {
						DisplayField displayField = (DisplayField) iterator2
								.next();
						if (!displayField.getDisplayName().equals(DisplayField.UNDIFINED)) 
							labels.add(displayField.getDisplayName());
					}
					
					for (Iterator it = listData.iterator(); it.hasNext();) {
						int i = 0;
						Object o = it.next();
						String[] vals = new String[labels.size()];
						for (Iterator iterator2 = listFields.iterator(); iterator2
								.hasNext();) {
							DisplayField dField = (DisplayField) iterator2.next();
							if(!dField.getDisplayName().equalsIgnoreCase(DisplayField.UNDIFINED)) {
								Field field = ClassAccessor.cast(o.getClass(), o).getClass().getDeclaredField(
										dField.getProperty());
								
								field.setAccessible(true);
								String val = (field.get(o) == null) ? ""
										: field.get(o).toString();
								vals[i] = val;
								i++;
							}
						}
						listVals.add(vals);
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		lo.setData(listVals);
		lo.setLabels(labels);
		
		return lo;
	}
}
