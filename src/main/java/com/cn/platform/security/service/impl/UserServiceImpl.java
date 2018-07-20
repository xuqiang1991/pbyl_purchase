package com.cn.platform.security.service.impl;

import com.cn.common.GlobalConstant;
import com.cn.framework.mvc.mysql.dao.BaseDao;
import com.cn.framework.mvc.mysql.service.impl.BaseServiceImpl;
import com.cn.framework.utils.PWDEncryptUtil;
import com.cn.platform.security.dao.UserDao;
import com.cn.platform.security.dao.UserRoleDao;
import com.cn.platform.security.entity.User;
import com.cn.platform.security.entity.UserRole;
import com.cn.platform.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "userService")
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	protected BaseDao<User, String> getDao() {
		// TODO Auto-generated method stub
		return userDao;
	}

	@Override
	public String saveUser(User user) {
		// TODO Auto-generated method stub
		String userId = userDao.insert(user);

		// 保存user-role关系表
		if (!CollectionUtils.isEmpty(user.getRoleIdList())) {
			List<UserRole> userRols = new ArrayList<>();
			for (String roleId : user.getRoleIdList()) {
				userRols.add(new UserRole(userId, roleId, GlobalConstant.STATUS_VALID));
			}
			userRoleDao.batchInsert(userRols);
		}
		return userId;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
		// 更新user-role关系表

		// 删除旧的用户与角色关系
		userRoleDao.deleteByUserId(user.getId());

		// 保存新的用户与角色关系
		if (!CollectionUtils.isEmpty(user.getRoleIdList())) {
			List<UserRole> userRoles = new ArrayList<>();
			for (String roleId : user.getRoleIdList()) {
				userRoles.add(new UserRole(user.getId(), roleId, GlobalConstant.STATUS_VALID));
			}
			userRoleDao.batchInsert(userRoles);
		}
	}

	@Override
	public Long deleteByIds(List<String> userIds) {
		List<UserRole> list = userRoleDao.findUserRoleByUserList(userIds);
		List<String> userRoleIdList = new ArrayList<>();
		for (UserRole sur : list) {
			userRoleIdList.add(sur.getId());
		}
		if (userRoleIdList.size() > 0) {
			userRoleDao.deleteByIds(userRoleIdList); // 删除用户角色表数据
		}

		return userDao.deleteByIds(userIds);
	}

	@Override
	public User findUserByAccount(String account) {
		User user = new User();
		if (account != null) {
			user = userDao.findUserByAccount(account);
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cn.platform.security.service.UserService#isUniqueName(com.cn.platform.security.entity.SysUser)
	 */
	@Override
	public boolean isUniqueAccount(User user) {
		// TODO Auto-generated method stub
		String id = user.getId();
		User entity = userDao.findUserByAccount(user.getAccount());
		if (entity == null) {
			return true;
		} else {
			if (entity.getId().equals(id)) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public Map<String, Object> updatePassword(String userId, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		User user = findById(userId);
		Boolean isOk = false;
		String msg = null;
		try {
			isOk = PWDEncryptUtil.verify(oldPassword, user.getPassword());
			if (isOk) {
				user.setPassword(PWDEncryptUtil.encrypt(newPassword));
				update(user);
				msg = "密码修改成功!";
			} else {
				msg = "密码修改失败,原密码不正确!";
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isOk = false;
			msg = "网络错误,请联系系统管理员.";
		}

		Map<String, Object> resMap = new HashMap<>();
		resMap.put(GlobalConstant.MOTHED_INVOKE_RES_IS_SUCCESS, isOk);
		resMap.put(GlobalConstant.MOTHED_INVOKE_RES_MSG, msg);
		return resMap;
	}
}
