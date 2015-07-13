package com.sales.dao;

import java.util.List;
import java.util.Map;

import com.sales.model.TProduct;

public interface TProductDAO {
	
	public List getProductListByParentId(Integer parentId);
	
	public List getProductListById(Integer parentId);
	
	public List getProductListByCondition(Map queryMap);
	
	public TProduct getProductByFNumber(String fNumber);
	
	public List getProductList(Map queryMap);
	
	public Integer getProductCount(Map queryMap);

}
