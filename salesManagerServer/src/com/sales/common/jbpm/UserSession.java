/**
 * 
 */
package com.sales.common.jbpm;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.api.identity.Group;
import org.jbpm.api.identity.User;
import org.jbpm.pvm.internal.identity.spi.IdentitySession;



/**
 * @author Administrator
 *
 */
public class UserSession implements IdentitySession{

	/* (non-Javadoc)
	 * @see org.jbpm.pvm.internal.identity.spi.IdentitySession#createGroup(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String createGroup(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.jbpm.pvm.internal.identity.spi.IdentitySession#createMembership(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void createMembership(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.jbpm.pvm.internal.identity.spi.IdentitySession#createUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String createUser(String arg0, String arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.jbpm.pvm.internal.identity.spi.IdentitySession#deleteGroup(java.lang.String)
	 */
	@Override
	public void deleteGroup(String arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.jbpm.pvm.internal.identity.spi.IdentitySession#deleteMembership(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void deleteMembership(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.jbpm.pvm.internal.identity.spi.IdentitySession#deleteUser(java.lang.String)
	 */
	@Override
	public void deleteUser(String arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.jbpm.pvm.internal.identity.spi.IdentitySession#findGroupById(java.lang.String)
	 */
	@Override
	public Group findGroupById(String userId) {
		Role role;
		// TODO Auto-generated method stub
		if(userId.equals("user1") || userId.equals("user2")
				|| userId.equals("user3"))
			role = new Role("user", "用户", "users");
		else if(userId.equals("manager") || userId.equals("mana")
				|| userId.equals("manager1"))
			role = new Role("manager", "经理", "managers");
		else
			role = new Role("boss", "老板", "bosses role");
		
		return role;
	}

	/* (non-Javadoc)
	 * @see org.jbpm.pvm.internal.identity.spi.IdentitySession#findGroupsByUser(java.lang.String)
	 */
	@Override
	public List<Group> findGroupsByUser(String userId) {
		List<Group> groups = new ArrayList<Group>();
		Role role;
		// TODO Auto-generated method stub
		if(userId.equals("user1") || userId.equals("user2")
				|| userId.equals("user3"))
			role = new Role("user", "用户", "users");
		else if(userId.equals("manager") || userId.equals("mana")
				|| userId.equals("manager1"))
			role = new Role("manager", "经理", "managers");
		else
			role = new Role("boss", "老板", "bosses role");
			
		groups.add(role);
		return groups;
	}

	/* (non-Javadoc)
	 * @see org.jbpm.pvm.internal.identity.spi.IdentitySession#findGroupsByUserAndGroupType(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Group> findGroupsByUserAndGroupType(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.jbpm.pvm.internal.identity.spi.IdentitySession#findUserById(java.lang.String)
	 */
	@Override
	public User findUserById(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.jbpm.pvm.internal.identity.spi.IdentitySession#findUsers()
	 */
	@Override
	public List<User> findUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.jbpm.pvm.internal.identity.spi.IdentitySession#findUsersByGroup(java.lang.String)
	 */
	@Override
	public List<User> findUsersByGroup(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.jbpm.pvm.internal.identity.spi.IdentitySession#findUsersById(java.lang.String[])
	 */
	@Override
	public List<User> findUsersById(String... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
