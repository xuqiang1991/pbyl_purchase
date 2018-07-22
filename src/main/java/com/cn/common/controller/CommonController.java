
package com.cn.common.controller;

import com.cn.common.service.CommonService;
import com.cn.common.vo.TreeNodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Project ChuFangLiuZhuan_PlatForm
 * @Package com.cn.common.controller
 * @ClassName CommonController.java
 * @Description
 * @JDK version used 1.8
 * @Author zhoujb
 * @Create Date 2017年7月10日
 * @modify By
 * @modify Date
 * @Why&What is modify
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/platform/common")
public class CommonController {
	/**
	 * 一次加载全部树节点
	 */
	private static final String TREE_LOAD_TYPE_ALL_AT_ONCE = "1";

	/**
	 * 一次加载一个节点
	 */
	private static final String TREE_LOAD_TYPE_ONE_AT_ONCE = "2";

	@Autowired
	private CommonService commonService;

	/**
	 * @Description 加载权限资源树资源树 roleId对应资源有选中状态
	 * @param request
	 * @param pId
	 * @param roleId
	 * @param loadType
	 * @return
	 * @date 2017年7月10日
	 */
	@ResponseBody
	@RequestMapping(value = "/loadResources", method = RequestMethod.POST)
	public List<TreeNodeVo> loadResources(HttpServletRequest request, String pId, String roleId, String loadType) {
		List<TreeNodeVo> vos = null;
		if (TREE_LOAD_TYPE_ALL_AT_ONCE.equalsIgnoreCase(loadType)) {
			// 一次性加载
			vos = commonService.findResourceTreeVoByRoleId(roleId);
		} else {
			// 分节点加载
			vos = commonService.findResourceByRoleIdAndParentId(pId, roleId);
		}
		return vos;
	}
	
	/**
	 * @Description 加载菜单资源树 roleId对应资源有选中状态
	 * @param roleId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/loadMenuResources", method = RequestMethod.POST)
	public List<TreeNodeVo> loadMenuResources(String resourceId) {
		List<TreeNodeVo> vos = commonService.findMenuResourceTreeByResourceId(resourceId);
		return vos;
	}
	

	/**
	 * 组织结构树
	 * 
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/loadDepts", method = RequestMethod.POST)
	public List<TreeNodeVo> loadDepts(HttpServletRequest request, String pId, String userId, String loadType) {
		List<TreeNodeVo> vos = null;
		if (TREE_LOAD_TYPE_ALL_AT_ONCE.equalsIgnoreCase(loadType)) {
			// 一次性加载
			vos = commonService.findDeptTreeByUserId(userId);
		} else {
			// 分节点加载
			vos = commonService.findDeptTreeByUserIdAndParentId(userId, pId);
		}
		return vos;
	}

}
