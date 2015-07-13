package com.sales.dao.impl;

import com.sales.dao.SSalesQuotationProductDAO;
import com.sales.model.SSalesQuotationProduct;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class SSalesQuotationProductDAOImpl extends SqlMapClientDaoSupport implements SSalesQuotationProductDAO {
 
    public SSalesQuotationProductDAOImpl() {
        super();
    }
 
    public void insertQuotationProduct(SSalesQuotationProduct quotationProduct) {
        getSqlMapClientTemplate().insert("s_sales_quotation_product.insertQuotationProduct", quotationProduct);
    }
 
    public void updateQuotationProduct(SSalesQuotationProduct quotationProduct) {
        getSqlMapClientTemplate().update("s_sales_quotation_product.updateQuotationProduct", quotationProduct); 
    }
    
}