package com.sales.dao;

import java.util.List;
import java.util.Map;

import com.sales.model.SaleProcess;

public interface SaleProcessDAO {
    public int insert(SaleProcess record);

    public int insertList(List<SaleProcess> listSaleProcess);
    
    public List<SaleProcess> getSaleProList(Map queryMap);
    
    public List<SaleProcess> getSaleProPageList(Map queryMap);
    
    public int getSaleProCount(Map queryMap);
}