package com.sales.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.sales.common.NormalFun;
import com.sales.dao.TProductDAO;
import com.sales.model.TProduct;
import com.sales.service.ProductService;

public class ProductServiceImpl implements ProductService {
	
	private TProductDAO productDao;
	
	public String getProductTreeGrid(String parentId){ 
		List productList = productDao.getProductListById(Integer.parseInt(parentId)); 
		JSONArray json = JSONArray.fromObject(productList);
		return json.toString();
	}
	
	public List getProductListByCondition(String fNumber, String fName){
		Map queryMap = new HashMap();
		queryMap.put("fNumber", fNumber);
		queryMap.put("fName", fName);
		List productList = productDao.getProductListByCondition(queryMap);
		return productList;	
	}
	
	public TProduct getProductByFNumber(String fNumber){
		TProduct product = productDao.getProductByFNumber(fNumber);
		return product;
	}

	public TProductDAO getProductDao() {
		return productDao;
	}

	public void setProductDao(TProductDAO productDao) {
		this.productDao = productDao;
	}
	
	public List getProductList(String fNumber, String fName, Integer pageNumber, Integer pageSize){
		Map queryMap = new HashMap();
		Integer startNumber = (pageNumber - 1) * pageSize;
		queryMap.put("startNumber", startNumber);
		queryMap.put("pageSize", pageSize);
		queryMap.put("fNumber", fNumber);
		queryMap.put("fName", fName);
		List prodcutList = (List) productDao.getProductList(queryMap);
		return prodcutList;
	}
	
	public Integer getProductCount(String fNumber, String fName){		
		Map queryMap = new HashMap();
		queryMap.put("fNumber", fNumber); 
		queryMap.put("fName", fName);
		Integer prodcutCount = productDao.getProductCount(queryMap);
		return prodcutCount;
	}

}
