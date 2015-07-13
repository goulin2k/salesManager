/**
 * 
 */
package com.sales.common;

import java.lang.reflect.Field;

import com.sales.model.BaseObject;

/**
 * JSP页面显示实体类的属性
 * @author apple
 *
 */
public class DisplayField extends BaseObject {
	
	public static final String UNDIFINED = "Undifined";

	private static final long serialVersionUID = -7771034870088197805L;
	
	private String displayName;
	private String property;
	private String dataType;
	private Boolean isKey;
	private boolean isListInTable;
	private Boolean isEditable;
	private Boolean isEditLink;
	private Boolean isNullable;
	private int minLength;
	private int maxLength;
	private boolean isChildrenList;
	private int index;
	
	public DisplayField() {
		super();
		this.displayName = UNDIFINED;
		this.isKey = false;
		this.isListInTable = true;
		this.isEditable = true;
		this.isEditLink = false;
		this.isNullable = true;
		this.minLength = 0;
		this.maxLength = 50;
		this.index = 0;
		this.isChildrenList = false;
	}
	
	public DisplayField(Field field) {
		this();
		this.property = field.getName();
		this.dataType = field.getType().getSimpleName();
		if(field.isAnnotationPresent(DisplayAnnotation.class)) {
			DisplayAnnotation dsa = (DisplayAnnotation)field.getAnnotation(DisplayAnnotation.class);
			this.displayName = dsa.name();
			this.isKey = dsa.isKey();
			this.isListInTable = dsa.isListInTable();
			this.isEditable = dsa.isEditable();
			this.isEditLink = dsa.isEditLink();
			this.isNullable = dsa.isNullable();
			this.minLength = dsa.minLength();
			this.maxLength = dsa.maxLength();
			this.isChildrenList = dsa.isChildrenList();
		}
		
		
	}
	
	public DisplayField(Field field, int index) {
		this(field);
		this.index = index;
	}

	/**
	 * @param display		显示名
	 * @param property		属性名
	 * @param dataType		数据类型	
	 * @param isKey			是否主键字段		
	 * @param isEditable	可编辑？
	 * @param isNullable	可为空？
	 * @param minLength		最小长度
	 * @param maxLength		最大长度
	 */
	public DisplayField(String display, String property, String dataType,
			Boolean isKey, Boolean isEditable, Boolean isNullable, 
			int minLength, int maxLength) {
		this();
		this.displayName = display;
		this.property = property;
		this.dataType = dataType;
		this.isKey = isKey;
		this.isEditable = isEditable;
		this.isEditLink = isEditLink;
		this.isNullable = isNullable;
		this.minLength = minLength;
		this.maxLength = maxLength;
	}
	
	public DisplayField(String display, String property, String dataType,
			Boolean isKey, Boolean isEditable, Boolean isNullable, 
			int minLength, int maxLength, boolean isChildrenList) {
		this(display, property,  dataType,
				 isKey,  isEditable,  isNullable, 
				 minLength,  maxLength);		
		this.isChildrenList = isChildrenList;
	}
	
	public DisplayField(String display, String property, String dataType,
			Boolean isKey, Boolean isEditable, Boolean isNullable, 
			int minLength, int maxLength, boolean isChildrenList, int index) {
		this(display, property,  dataType,
				 isKey,  isEditable,  isNullable, 
				 minLength,  maxLength, isChildrenList);
		this.index = index;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Boolean getIsKey() {
		return isKey;
	}

	public void setIsKey(Boolean isKey) {
		this.isKey = isKey;
	}

	public boolean getIsListInTable() {
		return isListInTable;
	}

	public void setIsListInTable(boolean isListInTable) {
		this.isListInTable = isListInTable;
	}

	public Boolean getIsEditable() {
		return isEditable;
	}

	public void setIsEditable(Boolean isEditable) {
		this.isEditable = isEditable;
	}

	/**
	 * @return the isEditLink
	 */
	public Boolean getIsEditLink() {
		return isEditLink;
	}

	/**
	 * @param isEditLink the isEditLink to set
	 */
	public void setIsEditLink(Boolean isEditLink) {
		this.isEditLink = isEditLink;
	}

	public Boolean getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(Boolean isNullable) {
		this.isNullable = isNullable;
	}

	public int getMinLength() {
		return minLength;
	}

	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the isChildrenList
	 */
	public boolean isChildrenList() {
		return isChildrenList;
	}

	/**
	 * @param isChildrenList the isChildrenList to set
	 */
	public void setChildrenList(boolean isChildrenList) {
		this.isChildrenList = isChildrenList;
	}
	
	
	
}
