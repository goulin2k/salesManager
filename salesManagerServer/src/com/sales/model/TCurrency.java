package com.sales.model;

import java.text.DecimalFormat;

public class TCurrency {
	
	private Integer fCurrencyID;
	private String fNumber;
	private String fName;
	private String fExchangeRate;
	
	public String getFExchangeRate() {
		DecimalFormat df = new DecimalFormat("####.00");
		String s = df.format(new Double(this.fExchangeRate));
		return s;
	}
	public void setFExchangeRate(String fExchangeRate) {
		this.fExchangeRate = fExchangeRate;
	}
	public Integer getFCurrencyID() {
		return fCurrencyID;
	}
	public void setFCurrencyID(Integer fCurrencyID) {
		this.fCurrencyID = fCurrencyID;
	}
	public String getFNumber() {
		return fNumber;
	}
	public void setFNumber(String fNumber) {
		this.fNumber = fNumber;
	}
	public String getFName() {
		return fName;
	}
	public void setFName(String fName) {
		this.fName = fName;
	}

}
