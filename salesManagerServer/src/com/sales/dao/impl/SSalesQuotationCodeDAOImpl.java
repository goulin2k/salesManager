package com.sales.dao.impl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.SSalesQuotationCodeDAO;
import com.sales.model.SSalesQuotationCode;

public class SSalesQuotationCodeDAOImpl extends SqlMapClientDaoSupport implements SSalesQuotationCodeDAO {
	
	public SSalesQuotationCode getCodeByYear(Integer year){
		SSalesQuotationCode code = (SSalesQuotationCode) getSqlMapClientTemplate().queryForObject("s_sales_quotation_code.getCodeByYear", year);
		return code;
	}
	
	public void insertCode(SSalesQuotationCode code){
		getSqlMapClientTemplate().insert("s_sales_quotation_code.insertCode", code);
	}
	
	public void updateCode(SSalesQuotationCode code){
		getSqlMapClientTemplate().insert("s_sales_quotation_code.updateCode", code);
	}

}
