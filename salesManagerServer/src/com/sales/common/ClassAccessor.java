/**
 * 
 */
package com.sales.common;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sales.model.SSalesProject;

/**
 * @author apple
 *
 */
public class ClassAccessor {
	
	/**
	 * 根据类名获取属性 List<DisplayField>
	 * @param 	cls
	 * @return	List<DisplayField>
	 */
	public static List<DisplayField> getClassProps(String cls) {
		Class<?> obj = null; 
		List<DisplayField> list = new ArrayList<DisplayField>();
		try {
			obj = Class.forName(cls);  
			Field[] field = obj.getDeclaredFields(); 
			for (int i = 0; i < field.length; i++) {
				list.add(new DisplayField(field[i]));				
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
//			throw(new RuntimeException("Class Not Found Exception:\t" + cls));
		}
		return list;
	}
	
	/**
	 * 根据类名获取主键属性字段	{@link DisplayField} 
	 * @param cls
	 * @return
	 */
	public static DisplayField getKeyField(String cls) {
		List<DisplayField> list = getClassProps(cls);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			DisplayField displayField = (DisplayField) iterator.next();
			if(displayField != null ) {
				if(displayField.getIsKey() == true)
					return displayField;
			}
		}
		return null;
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			List<DisplayField> list = ClassAccessor.getClassProps(
					"com.sales.model.SSalesProject");
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				DisplayField displayField = (DisplayField) iterator.next();
				System.out.println(displayField.getProperty() + "|" +
						displayField.getDataType() + "^" + displayField.getDisplayName());
			}
			
			DisplayField key = ClassAccessor.getKeyField("com.sales.model.SSalesProject");
			if (key != null)
				System.out.println(key.toString());
			
			SSalesProject sp = new SSalesProject();
			sp.setTopical("JDBose 大促销！");
			List<LabelStringValueBean> ls = sp.toLabelValues();
			for (Iterator iterator = ls.iterator(); iterator.hasNext();) {
				LabelStringValueBean labelStringValueBean = (LabelStringValueBean) iterator
						.next();
				System.out.println(labelStringValueBean.toString());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static <T> T cast(Class<T> clazz, Object obj) {  
        return clazz.cast(obj);  
    } 
	
	
}
