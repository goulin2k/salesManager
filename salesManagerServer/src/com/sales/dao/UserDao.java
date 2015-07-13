package com.sales.dao;

import java.util.List;

import com.sales.model.User;

public interface UserDao {
	
    public List<User> getUserList();
	
	public Integer addUser(User user);
	
	public User getUser(Integer id);
	
	public void deleteUser(Integer id);
	
	public List<User> getUserListByConditions(String param);
}
