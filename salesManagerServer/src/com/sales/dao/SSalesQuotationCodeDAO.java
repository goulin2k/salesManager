package com.sales.dao;

import com.sales.model.SSalesQuotationCode;

public interface SSalesQuotationCodeDAO {
	
	public SSalesQuotationCode getCodeByYear(Integer year);
	
	public void insertCode(SSalesQuotationCode code);
	
	public void updateCode(SSalesQuotationCode code);

}
