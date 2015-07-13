package com.sales.service;
 
import java.util.List; 

import com.sales.model.TProduct;

public interface ProductService {
	
	public String getProductTreeGrid(String parentId);
	
	public List getProductListByCondition(String fNumber, String fName);
	
	public TProduct getProductByFNumber(String fNumber);
	
	public List getProductList(String fNumber, String fName, Integer pageNumber, Integer pageSize);
	
	public Integer getProductCount(String fNumber, String fName);

}
