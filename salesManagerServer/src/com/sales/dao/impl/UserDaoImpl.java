package com.sales.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.UserDao;
import com.sales.model.User;

public class UserDaoImpl extends SqlMapClientDaoSupport implements UserDao {

	@Override
	public Integer addUser(User user) {
		return (Integer) getSqlMapClientTemplate().insert("USER.addUser", user);
	}

	@Override
	public List<User> getUserList() {
		return getSqlMapClientTemplate().queryForList("USER.getUserList");
	}

	@Override
	public User getUser(Integer id) {
		return (User) getSqlMapClientTemplate().queryForObject("USER.getUserById", id);
	}

	@Override
	public void deleteUser(Integer id) {
		getSqlMapClientTemplate().delete("USER.deleteUserById", id);
	}

	@Override
	public List<User> getUserListByConditions(String param) {
		Map map = new HashMap<String, String>();
		map.put("searchtxt", param);
		return getSqlMapClientTemplate().queryForList("USER.getUserListByConditions",map);
	}

}
