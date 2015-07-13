package com.sales.service;
 
import java.util.List; 
  
import com.sales.model.SPosition;
import com.sales.model.SUser;
import com.sales.model.User;

public interface SUserService {
	
	public List<SUser> getUserList(Long pageNumber, int pageSize);
	
	public void updateUserById(SUser user);
	
	public void deleteUser(Integer userId);
	
	public void insertUser(SUser user);
	
	public SUser getUserById(Integer userId);
	
	public boolean userLogin(SUser user);
	
	public Integer getUserCount();
	
	public Integer addUserWithNoTrans(User user);
	
	public Integer addUserWithTrans(User user);
	
	public SUser getSUserByUserName(String loginName);
	
	public List getSUserByParentId(Integer parentId, String userName);
	
	/**
	 * 获取所有下属的K3用户员工用户名
	 * @param userId
	 * @return
	 */
	public List<String> getK3EmpNamesByParentId(Integer userId);
	
	/**
	 * 获取所有下属的K3用户员工用户名
	 * @param userId
	 * @return
	 */
	public List<String> getK3EmpNamesByParentName(String k3UserName);
	
	/**
	 * 获取所有下属的K3用户员工Id
	 * @param userId
	 * @return
	 */
	public List<Integer> getK3EmpIdsByParentId(Integer userId);
	
	public List<SUser> getUserListByPosition(SPosition position);
	
	public List<SUser> getChildUsersById(Integer userId);
	
	/**
	 * 查询获取指定用户所有上级用户信息
	 * @param userId
	 * @return
	 */
	public List<SUser> getParentUsersById(Integer userId);
	
	public List<SUser> getUserPageList(Long pageNumber, int pageSize,SUser userInfo);
	public Integer getUserCountByUser(SUser userInfo);
	
	public List<SUser> getOpenUserList(Integer departmentId, String userName, String childUserIds, Long pageNumber, int pageSize);

	public Integer getOpenUserCount(Integer departmentId, String userName, String childUserIds);

	public List<SUser> getUserListByPositionId(Integer positionId);
	
	public Integer getUserPageCount(SUser userInfo); 
	
	/**
	 * 查询获取所有有下属的销售人员信息
	 * @return
	 */
	public List<SUser> selectParentSaleUsers();
	
}
