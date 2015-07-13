/**
 * 
 */
package com.sales.model;

import com.sales.common.NormalFun;

/**
 * 销售收款统计报表实体数据类
 * @author 苟林
 *
 */
public class K3RecieveBillStatistics extends BaseObject{
	private int userId;
	private int year;
	private int month;
	private int week;
	private int day;
	private int empId;
	private double amount;
	private double amountReceive;
	
	private String empName;			//业务员姓名
	private String depName;		//销售部门名
	
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	/**
	 * @return the week
	 */
	public int getWeek() {
		return week;
	}
	/**
	 * @param week the week to set
	 */
	public void setWeek(int week) {
		this.week = week;
	}
	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}
	/**
	 * @return the empId
	 */
	public int getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the amountReceive
	 */
	public double getAmountReceive() {
		return amountReceive;
	}
	/**
	 * @param amountReceive the amountReceive to set
	 */
	public void setAmountReceive(double amountReceive) {
		this.amountReceive = amountReceive;
	}
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * @return the depName
	 */
	public String getDepName() {
		return depName;
	}
	/**
	 * @param depName the depName to set
	 */
	public void setDepName(String depName) {
		this.depName = depName;
	}
	
	
	/**
	 * 格式化金额字段
	 * @return
	 */
	public String getAmountString() {
		return NormalFun.formatCurrency(amount);
	}
}
