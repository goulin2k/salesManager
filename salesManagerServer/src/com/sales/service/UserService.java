package com.sales.service;

import java.util.List;

import com.sales.model.User;

public interface UserService {
	
	public List<User> getUserList();
	
	public Integer addUser(User user);
	
	public User getUser(Integer id);

	public void deleteUser(Integer id);
	
	public List<User> getUserListByConditions(String param);
}
