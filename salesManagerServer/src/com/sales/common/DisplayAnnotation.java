/**
 * 
 */
package com.sales.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * @author apple
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface DisplayAnnotation {
	String name();
	boolean isKey() default false;
	boolean isEditLink() default false;
	boolean isListInTable() default true;
	boolean isEditable() default true;
	boolean isNullable() default true;
	int minLength() default 0;
	int maxLength() default 50;
	
	boolean isChildrenList() default false;
}
