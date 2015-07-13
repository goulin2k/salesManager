/*
 * Created on 2007-7-9
 *  Copyright ? 2000 SiChuan Yinhai Co. Ltd. 
 * 			All right reserved.
 */
package com.sales.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.sales.action.app.ListViewObject;
import com.sales.common.ClassAccessor;
import com.sales.common.DisplayField;
import com.sales.common.LabelStringValueBean;
import com.sales.common.NormalFun;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Base class for Model objects.  This is basically for the toString method.
 * 实体类基类,覆盖了toString()方法, 打印出该实体对象所有的属性名称和值
 *
 * @author Goulin
 * 引用包
 */
public class BaseObject implements Serializable {
    private static final long serialVersionUID = 3256446889040622647L;

    /** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * 打印Model Object实例对象属性值为字符串显示
	 */
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
	
	/**
	 * 将对象转换为HashMap
	 * @return
	 */
	public Map<String, Object> toMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = this.getClass().getDeclaredFields();
		for(Field field : fields){
			if(Modifier.isStatic(field.getModifiers())) continue;
			try {
				field.setAccessible(true);
				map.put(field.getName(), field.get(this));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	/**
	 * 将对象属性以label显示名和值对的方式封装，只封装@Display字段，提供页面进行展示
	 * @return
	 */
	public List<LabelStringValueBean> toLabelValues() {
		if(this == null)
			return null;
		
		List<LabelStringValueBean> list = new ArrayList<LabelStringValueBean>();
		List<DisplayField> dslist = ClassAccessor.getClassProps(
				this.getClass().getName());
		
		
		try {
			for (Iterator iterator = dslist.iterator(); iterator.hasNext();) {
				DisplayField dsField = (DisplayField) iterator.next();
				// 遍历非子集合属性，转换为LabelValue对象
				if (!dsField.isChildrenList()) {
					if (!dsField.getDisplayName()
							.equals(DisplayField.UNDIFINED)) {
						Field field = this.getClass().getDeclaredField(
								dsField.getProperty());
						field.setAccessible(true);
						
						LabelStringValueBean lvb = new LabelStringValueBean(
								dsField.getDisplayName(),
								getFormatString(field.get(this)));
						list.add(dsField.getIndex(), lvb);
					}
				}
				
				
					
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private String getFormatString(Object o) {
		String strVal = "";
		
		if(o == null)
			return strVal;
		if(o.getClass().getSimpleName().equals("Date")) {
			strVal = NormalFun.formatDateString((Date)o);
		}else {
			strVal = o.toString();
		}
		
		return strVal;
	}
	
}

