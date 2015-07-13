package com.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sales.dao.UserDao;
import com.sales.model.SUser;
import com.sales.model.User;
import com.sales.service.SUserService;
import com.sales.service.UserService;

public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	private SUserService sUserService;
	


	/**
	 * @param sUserService the sUserService to set
	 */
	public void setsUserService(SUserService sUserService) {
		this.sUserService = sUserService;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Integer addUser(User user) {
		Integer id = userDao.addUser(user);
		user.setId(0);
		user.setName(user.getName() + "_t");
		user.setDesc("测试数据(有事务）");
		Integer id2 = sUserService.addUserWithNoTrans(user);
		user.setId(0);
		user.setName(user.getName() + "_nt");
		user.setDesc("测试数据(无事务）");
		Integer id3 = sUserService.addUserWithNoTrans(user);
		if(1==1)
			throw(new RuntimeException("test trans exception！"));
		return id;
	}

	@Override
	public List<User> getUserList() {
		return this.userDao.getUserList();
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getUser(Integer id) {
		return this.userDao.getUser(id);
	}

	@Override
	public void deleteUser(Integer id) {
		this.userDao.deleteUser(id);
	}

	@Override
	public List<User> getUserListByConditions(String param) {
		return this.userDao.getUserListByConditions(param);
	}

}
