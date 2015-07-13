package com.sales.dao;

import java.util.List; 

import com.sales.model.TReturnGoods; 

public interface TReturnGoodsDAO { 
	
	public TReturnGoods getReturnGoodsById(Integer returnGoodsId);

	public List getReturnGoodsList(TReturnGoods returnGoods);

	public Integer getReturnGoodsCount(TReturnGoods returnGoods);
	
	public List getReturnGoodsAllList(String billIds);

}
