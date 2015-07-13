package com.sales;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
/*
 * Created on 2006-7-25
 *
 * 文件名 :  BaseTestCase.java
 * 作  者:	Brian
 * 公共类:	BaseTestCase……
 * 版权声明： Copyright ? 2000 SiChuan Yinhai Co. Ltd. 
 * 			All right reserved.
 */

/**
 * 作者：Brian
 * 功能描述：提供Spring框架下单元测试基类，初试化成员ctx和log4j对象
 * 创建日期：2006-7-25
 */
public class BaseTestCase extends TestCase {
	private String[] contextFiles;
	
	protected final ApplicationContext ctx;
	
	/**
	 * log4j static变量<code>logger</code>
	 */
	protected static final Logger logger = (Logger) Logger
			.getLogger(BaseTestCase.class);
	
	/**
	 * 
	 */
	public BaseTestCase() {
		this(BaseTestCase.class.toString());
	}
	/**
	 * @param name
	 */
	public BaseTestCase(String name) {
		super(name);
		if (readProperties() == true) {
			try {
				ctx = new FileSystemXmlApplicationContext(contextFiles);
				logger.info("BaseTestCase初试化成功");
			}catch(Exception e) {
				e.printStackTrace();
				throw(new ExceptionInInitializerError("单元测试配置文件读取错误"));
			}
		}else {
			throw(new ExceptionInInitializerError("单元测试配置文件读取错误"));
		}
	}
	
	private boolean readProperties() {
		try{
			 Properties property = new Properties();
			 InputStream inStr = ClassLoader.getSystemResourceAsStream("ut.properties");
			 property.load(inStr);
			 String contextFile = property.getProperty("contextFiles");
			 contextFiles = contextFile.split(",");
			 if(contextFiles == null || contextFile.equalsIgnoreCase(""))
			 	throw(new IllegalArgumentException(
			 			"单元测试配置文件'ut.properties文件未配置'"));
			 return true;
		}catch(IOException e) {
			logger.error(e);
			return false;
		}

	}
}
