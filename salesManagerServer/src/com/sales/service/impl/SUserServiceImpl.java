package com.sales.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import my.com.ibatis.core.dao.support.Page;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sales.common.Constants;
import com.sales.dao.SUserDAO;
import com.sales.dao.UserDao;
import com.sales.model.SPosition;
import com.sales.model.SUser;
import com.sales.model.SUserJson;
import com.sales.model.User;
import com.sales.service.SUserService;

public class SUserServiceImpl implements SUserService {
	
	private SUserDAO sUserDao;
	private UserDao userDao;

	public SUserDAO getsUserDao() {
		return sUserDao;
	}

	public void setsUserDao(SUserDAO sUserDao) {
		this.sUserDao = sUserDao;
	}

	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<SUser> getUserList(Long pageNumber, int pageSize) {
		List userList = sUserDao.getUserList(pageNumber, pageSize);
		return userList;
	}
	
	public void updateUserById(SUser user){
		sUserDao.updateSUserById(user);
	}
	
	public void deleteUser(Integer userId){
		sUserDao.deleteUser(userId);
	}
	
	public void insertUser(SUser user){
		sUserDao.insert(user);
	}
	
	public SUser getUserById(Integer userId){
		SUser user = sUserDao.getSUserById(userId);
		return user;
	}
	
	public boolean userLogin(SUser user){
		SUser rUser = sUserDao.getSUserByPassword(user);
		if(rUser!=null && rUser.getUserId()>0){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public Integer getUserCount() {
		// TODO Auto-generated method stub
		Integer usreCount = sUserDao.getUserCount();
		return usreCount;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Integer addUserWithNoTrans(User user) {
		return userDao.addUser(user);
	}
	
	
	public Integer addUserWithTrans(User user) {
		return userDao.addUser(user);
	}

	@Override
	public SUser getSUserByUserName(String loginName) {
		// TODO Auto-generated method stub
		return sUserDao.getSUserByUserName(loginName);
	}
	
	public List getSUserByParentId(Integer parentId, String userName){
		List userList = new ArrayList();
		SUserJson sUser = new SUserJson();
		sUser.setId(parentId);
		sUser.setText(userName);
		sUser.setChildren(sUserDao.getSUserByParentId(parentId));
		userList.add(sUser);
		return userList;
	}

	@Override
	public List<SUser> getUserListByPosition(SPosition position) {
		return sUserDao.getUserListByPosition(position);
	}

	@Override
	public List<SUser> getChildUsersById(Integer userId) {
		return sUserDao.getChildUsersById(userId);
	}

	@Override
	public List<SUser> getParentUsersById(Integer userId) {
		return sUserDao.getParentUsersById(userId);
	}
	
	@Override
	public List<SUser> getUserPageList(Long pageNumber, int pageSize,
			SUser userInfo) {
		// TODO Auto-generated method stub
		return sUserDao.getUserPageList(pageNumber, pageSize, userInfo);
	}

    public List<SUser> getOpenUserList(Integer departmentId, String userName, String childUserIds, Long pageNumber, int pageSize) {
    	long startRow = Page.getStartOfPage(pageNumber, pageSize);
		long endRow = pageSize;
		Map<String,Comparable> queryMap = new HashMap<String,Comparable>();
		queryMap.put("startRow", startRow);
		queryMap.put("endRow", endRow);
		queryMap.put("departmentId", departmentId); 
		queryMap.put("userName", userName); 
		if(childUserIds==null || childUserIds.trim().equals("")){
			childUserIds = "(-1)";
		}
		if(childUserIds.equalsIgnoreCase(Constants.USER_FILTER_MARK))
			childUserIds = null;
		queryMap.put("childUserIds", childUserIds); 
    	List userList  = sUserDao.getOpenUserList(queryMap);
		return userList;
	}

	public Integer getOpenUserCount(Integer departmentId, String userName, String childUserIds) { 
		Map<String,Comparable> queryMap = new HashMap<String,Comparable>(); 
		queryMap.put("departmentId", departmentId); 
		queryMap.put("userName", userName); 
		if(childUserIds==null || childUserIds.trim().equals("")){
			childUserIds = "(-1)";
		}
		if(childUserIds.equalsIgnoreCase(Constants.USER_FILTER_MARK))
			childUserIds = null;
		queryMap.put("childUserIds", childUserIds); 
		Integer count = sUserDao.getOpenUserCount(queryMap);
		return count;
	}

	@Override
	public Integer getUserCountByUser(SUser userInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<SUser> getUserListByPositionId(Integer positionId) {
		return sUserDao.getUserListByPositionId(positionId);
	}

	@Override
	public Integer getUserPageCount(SUser userInfo) {
		// TODO Auto-generated method stub
		return sUserDao.getUserPageCount(userInfo);
	}

	/* (non-Javadoc)
	 * @see com.sales.service.SUserService#getK3EmpIdsByParentId(java.lang.Integer, java.lang.String)
	 */
	@Override
	public List<String> getK3EmpNamesByParentId(Integer userId) {
		List<SUser> users = getChildUsersById(userId);
		List<String> empIds = new ArrayList<String>();
		for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			SUser sUser = (SUser) iterator.next();
			if(sUser.getK3UserId() != null)
				empIds.add(sUser.getK3UserName());
		}
		return empIds;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.SUserService#getK3EmpNamesByParentId(java.lang.String)
	 */
	@Override
	public List<String> getK3EmpNamesByParentName(String k3UserName) {
		SUser user = sUserDao.getUserByK3Name(k3UserName);
		if (user == null)
			return null;
		
		return getK3EmpNamesByParentId(user.getUserId());
	}
	
	

	/* (non-Javadoc)
	 * @see com.sales.service.SUserService#getK3EmpIdsByParentId(java.lang.Integer)
	 */
	@Override
	public List<Integer> getK3EmpIdsByParentId(Integer userId) {
		List<SUser> users = getChildUsersById(userId);
		List<Integer> empIds = new ArrayList<Integer>();
		for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			SUser sUser = (SUser) iterator.next();
			if(sUser.getK3UserId() != null)
				empIds.add(sUser.getK3UserId());
		}
		return empIds;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.SUserService#selectParentSaleUsers()
	 */
	@Override
	public List<SUser> selectParentSaleUsers() {
		List<SUser> list = sUserDao.selectParentSalers();
		return list;
	}
	
	

}
