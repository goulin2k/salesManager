package com.sales.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.TProductDAO;
import com.sales.model.TProduct;

public class TProductDAOImpl extends SqlMapClientDaoSupport implements TProductDAO {
	
	public TProductDAOImpl(){
		super();
	}
	
	public List getProductListByParentId(Integer parentId){
		List productList = getSqlMapClientTemplate().queryForList("t_product.getProductListByParentId", parentId);
		return productList;	
	}
	
	public List getProductListById(Integer parentId){
		List productList = getSqlMapClientTemplate().queryForList("t_product.getProductListById", parentId);
		return productList;	
	}
	
	public List getProductListByCondition(Map queryMap){
		List productList = getSqlMapClientTemplate().queryForList("t_product.getProductListByCondition", queryMap);
		return productList;	
	}
	
	public TProduct getProductByFNumber(String fNumber){
		TProduct product = (TProduct) getSqlMapClientTemplate().queryForObject("t_product.getProductByFNumber", fNumber);
		return product;
	}
	
	public List getProductList(Map queryMap){
		List productList = (List) getSqlMapClientTemplate().queryForList("t_product.getProductList", queryMap);
		return productList;
	}
	
	public Integer getProductCount(Map queryMap){
		Integer productCount = (Integer) getSqlMapClientTemplate().queryForObject("t_product.getProductCount", queryMap);
		return productCount;
	}

}
