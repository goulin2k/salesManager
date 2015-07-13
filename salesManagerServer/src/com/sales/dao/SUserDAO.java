package com.sales.dao;

import java.util.List;
import java.util.Map;

import com.sales.model.SPosition;
import com.sales.model.SUser;

public interface SUserDAO {
	
	public List<SUser> getUserList(Long pageNumber, int pageSize);
	
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_user
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    void insert(SUser user);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_user
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    void updateSUserById(SUser user); 

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_user
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    SUser getSUserById(Integer userId);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_user
     *
     * @abatorgenerated Tue Apr 23 17:36:31 CST 2013
     */
    public void deleteUser(Integer userId);
    
    public SUser getSUserByPassword(SUser user);
    
    public Integer getUserCount();
    
    public SUser getSUserByUserName(String loginName);
    
    public List getSUserByParentId(Integer parentId);
    
    public List<SUser> getPerformanceUserList(Integer postType,Integer postId);
    
    public List<SUser> getUserListByPosition(SPosition position);
    
    public List<SUser> getChildUsersById(Integer userId);
    
    /**
     * 查询指定用户的所有上级用户信息
     * @param userId
     * @return
     */
    public List<SUser> getParentUsersById(Integer userId);
    
    public List<SUser> getUserPageList(Long pageNumber, int pageSize, SUser userInfo);
    public Integer getUserCountByUser(SUser userInfo);
    
    public List<SUser> getOpenUserList(Map queryMap);

	public Integer getOpenUserCount(Map queryMap);
	
	public List<SUser> getUserListByPositionId(Integer positionId);
	public Integer getUserPageCount(SUser userInfo);
	
	/**
	 * 通过K3用户名查询获取用户对象
	 * @param k3UserName
	 * @return
	 */
	public SUser getUserByK3Name(String k3UserName);
	
	/**
	 * 查询获取所有有下属的销售人员信息
	 * @return
	 */
	public List<SUser> selectParentSalers();
	
}