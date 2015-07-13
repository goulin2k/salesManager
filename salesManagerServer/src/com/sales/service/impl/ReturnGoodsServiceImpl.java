package com.sales.service.impl;
 
import java.util.List; 

import com.sales.dao.TReturnGoodsDAO;
import com.sales.model.TReturnGoods;
import com.sales.service.ReturnGoodsService;

public class ReturnGoodsServiceImpl implements ReturnGoodsService {
	
	private TReturnGoodsDAO returnGoodsDao;

	public TReturnGoodsDAO getReturnGoodsDao() {
		return returnGoodsDao;
	}

	public void setReturnGoodsDao(TReturnGoodsDAO returnGoodsDao) {
		this.returnGoodsDao = returnGoodsDao;
	}
	
	public TReturnGoods getReturnGoodsById(Integer returnGoodsId){
		TReturnGoods returnGoods = returnGoodsDao.getReturnGoodsById(returnGoodsId);
		return returnGoods;
	}
	
	public List getReturnGoodsList(Integer pageNumber, Integer pageSize, TReturnGoods returnGoods){ 
		Integer startNumber = (pageNumber - 1) * pageSize;
		returnGoods.setStartNumber(startNumber);
		returnGoods.setPageSize(pageSize);
		List returnGoodsList = returnGoodsDao.getReturnGoodsList(returnGoods);
		return returnGoodsList;
	}
	
	public Integer getReturnGoodsCount(TReturnGoods returnGoods){
		Integer returnGoodsCount = returnGoodsDao.getReturnGoodsCount(returnGoods);
		return returnGoodsCount;
	}
	
	public List getReturnGoodsAllList(String billIds){
		List returnGoodsList = returnGoodsDao.getReturnGoodsAllList(("(" + billIds + ")")); 
		return returnGoodsList;
	}

}
