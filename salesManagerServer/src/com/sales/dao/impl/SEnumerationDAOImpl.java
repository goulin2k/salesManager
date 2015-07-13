package com.sales.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.SEnumerationDAO;

public class SEnumerationDAOImpl extends SqlMapClientDaoSupport implements SEnumerationDAO {
	
	public SEnumerationDAOImpl() {
        super();
    }
	
	public List selectEnumerationByType(Integer enumerationType){
		List enumerationList = getSqlMapClientTemplate().queryForList("s_enumeration.selectEnumerationByType", enumerationType);
		return enumerationList;
	}

}
