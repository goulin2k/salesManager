package com.sales.dao.impl;

import java.util.List; 

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.TReturnGoodsDAO;
import com.sales.model.TReturnGoods;

public class TReturnGoodsDAOImpl extends SqlMapClientDaoSupport implements TReturnGoodsDAO {
	
	public TReturnGoods getReturnGoodsById(Integer returnGoodsId){
		TReturnGoods returnGoods = (TReturnGoods) getSqlMapClientTemplate().queryForObject("t_return_goods.getReturnGoodsById", returnGoodsId);
		return returnGoods;
	}
	
	public List getReturnGoodsList(TReturnGoods returnGoods){
		List returnGoodsList = (List) getSqlMapClientTemplate().queryForList("t_return_goods.getReturnGoodsList", returnGoods);
		return returnGoodsList;
	}
	
	public Integer getReturnGoodsCount(TReturnGoods returnGoods){
		Integer returnGoodsCount = (Integer) getSqlMapClientTemplate().queryForObject("t_return_goods.getReturnGoodsCount", returnGoods);
		return returnGoodsCount;
	}
	
	public List getReturnGoodsAllList(String billIds){
		List returnGoodsList = (List) getSqlMapClientTemplate().queryForList("t_return_goods.getReturnGoodsAllList", billIds);
		return returnGoodsList;
	}

}
