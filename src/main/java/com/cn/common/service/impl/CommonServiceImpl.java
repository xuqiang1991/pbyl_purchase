/**
 

 *  <P> Copyright 2017 阳光康众</p>

 *  <p> Created on 2017年7月10日</p>
 *  <p> Created by zhoujb</p>
 
 
 */
package com.cn.common.service.impl;

import com.cn.common.dao.CommonDao;
import com.cn.common.service.CommonService;
import com.cn.common.vo.TreeNodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project ChuFangLiuZhuan_PlatForm
 * @Package com.cn.common.service.impl
 * @ClassName CommonServiceImpl.java
 * @Description
 * @JDK version used 1.8
 * @Author zhoujb
 * @Create Date 2017年7月10日
 * @modify By
 * @modify Date
 * @Why&What is modify
 * @Version 1.0
 */
@Service
public class CommonServiceImpl implements CommonService {
	@Autowired
	private CommonDao commonDao;

	@Override
	public List<TreeNodeVo> findResourceByRoleIdAndParentId(String roleId, String parentId) {
		// TODO Auto-generated method stub
		return commonDao.findResourceTreeByRoleIdAndParentId(parentId, roleId);
	}

	@Override
	public List<TreeNodeVo> findResourceTreeVoByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return commonDao.findResourceTreeByRoleId(roleId);
	}

	@Override
	public List<TreeNodeVo> findDeptTreeByUserIdAndParentId(String userId, String parentId) {
		// TODO Auto-generated method stub
		return commonDao.findDeptTreeByUserIdAndParentId(userId, parentId);
	}

	@Override
	public List<TreeNodeVo> findDeptTreeByUserId(String userId) {
		// TODO Auto-generated method stub
		return commonDao.findDeptTreeByUserId(userId);
	}

    @Override
    public List<TreeNodeVo> findMenuResourceTreeByResourceId(String resourceId) {
	    // TODO Auto-generated method stub
	   return commonDao.findMenuResourceTreeByResourceId(resourceId);
    }



}
