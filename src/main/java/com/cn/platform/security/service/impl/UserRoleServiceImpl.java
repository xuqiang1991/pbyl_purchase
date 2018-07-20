package com.cn.platform.security.service.impl;

import com.cn.framework.mvc.mysql.dao.BaseDao;
import com.cn.framework.mvc.mysql.service.impl.BaseServiceImpl;
import com.cn.platform.security.dao.UserRoleDao;
import com.cn.platform.security.entity.UserRole;
import com.cn.platform.security.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userRoleService")
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole, String> implements UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cn.framework.mvc.service.impl.BaseServiceImpl#getDao()
	 */
	@Override
	protected BaseDao<UserRole, String> getDao() {
		// TODO Auto-generated method stub
		return userRoleDao;
	}

	@Override
	public List<UserRole> findUserRoleByRole(String roleId) {
		return userRoleDao.findUserRoleByRole(roleId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cn.platform.security.dao.RoleDao#findRoleByCode(java.lang.String)
	 */
	@Override
	public List<UserRole> findUserRoleByUser(String userId) {
		return userRoleDao.findUserRoleByUser(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cn.platform.security.service.UserRoleService#findUserRoleByUserList(java.util.List)
	 */
	@Override
	public List<UserRole> findUserRoleByUserList(List<String> userIds) {
		// TODO Auto-generated method stub
		return userRoleDao.findUserRoleByUserList(userIds);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cn.platform.security.service.UserRoleService#findUserRoleByRoleList(java.util.List)
	 */
	@Override
	public List<UserRole> findUserRoleByRoleList(List<String> roleIds) {
		// TODO Auto-generated method stub
		return userRoleDao.findUserRoleByRoleList(roleIds);
	}

}
