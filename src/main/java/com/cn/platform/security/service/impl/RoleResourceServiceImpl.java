package com.cn.platform.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.framework.mvc.mysql.dao.BaseDao;
import com.cn.framework.mvc.mysql.service.impl.BaseServiceImpl;
import com.cn.platform.security.dao.RoleResourceDao;
import com.cn.platform.security.entity.RoleResource;
import com.cn.platform.security.service.RoleResourceService;

@Service(value = "roleResourceService")
public class RoleResourceServiceImpl extends BaseServiceImpl<RoleResource, String> implements RoleResourceService {

	@Autowired
	private RoleResourceDao roleResourceDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cn.framework.mvc.service.impl.BaseServiceImpl#getDao()
	 */
	@Override
	protected BaseDao<RoleResource, String> getDao() {
		// TODO Auto-generated method stub
		return roleResourceDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cn.platform.security.service.RoleResourceService#findRoleResource(java.lang.String)
	 */
	@Override
	public List<RoleResource> findRoleResource(String roleid) {
		// TODO Auto-generated method stub
		return roleResourceDao.findRoleResource(roleid);
	}

	@Override
	public List<RoleResource> findResourceRole(String resourceId) {
		return roleResourceDao.findResourceRole(resourceId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cn.platform.security.service.RoleResourceService#findResourceRoleList(java.util.List)
	 */
	@Override
	public List<RoleResource> findResourceRoleList(List<String> resourceIds) {
		// TODO Auto-generated method stub
		return roleResourceDao.findResourceRoleList(resourceIds);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cn.platform.security.service.RoleResourceService#findRoleResourceList(java.util.List)
	 */
	@Override
	public List<RoleResource> findRoleResourceList(List<String> roleIds) {
		// TODO Auto-generated method stub
		return roleResourceDao.findRoleResourceList(roleIds);
	}

	@Override
	public void deleteByRoleIds(List<String> roleIds) {
		// TODO Auto-generated method stub

	}

}
