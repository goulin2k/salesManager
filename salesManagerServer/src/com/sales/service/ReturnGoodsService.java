package com.sales.service;
 
import java.util.List; 

import com.sales.model.TReturnGoods;

	public interface ReturnGoodsService {public TReturnGoods getReturnGoodsById(Integer returnGoodsId);

	public List getReturnGoodsList(Integer pageNumber, Integer pageSize, TReturnGoods returnGoods);

	public Integer getReturnGoodsCount(TReturnGoods returnGoods);
	
	public List getReturnGoodsAllList(String billIds);

}
