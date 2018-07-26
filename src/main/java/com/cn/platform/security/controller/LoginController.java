/**
 



 *  <p> Created on 2017年6月28日</p>

 
 
 */
package com.cn.platform.security.controller;

import com.alibaba.fastjson.JSON;
import com.cn.common.GlobalConstant;
import com.cn.framework.mvc.controller.RespBody;
import com.cn.platform.security.entity.Resource;
import com.cn.platform.security.entity.User;
import com.cn.platform.security.service.ResourceService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project ChuFangLiuZhuan_PlatForm
 * @Package com.cn.platform.login
 * @ClassName LoginController.java
 * @Description 后台系统登录
 * @JDK version used 1.8
 * @Author zhoujb
 * @Create Date 2017年6月28日
 * @modify By
 * @modify Date
 * @Why&What is modify
 * @Version 1.0
 */
@Controller
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private ResourceService resorceService;
//	@Autowired
//	private LoginTimesCache loginTimesCache;

	/**
	 * 登录页面
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = {"/", "index", "/pf_toLogin"})
	public String index(User user) {
		if(user != null){
			logger.info("index....");
			return "index.html";
		}else {
			logger.info("Login....");
			return "login.html";
		}
	}


	/**
	 * 登录验证 返回主页
	 * 
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/pf_doLogin")
	@ResponseBody
	public RespBody doLogin(Model model, HttpServletRequest request) throws Exception {
		RespBody respBody = new RespBody();
		String userAccount = request.getParameter("name");
		String password = request.getParameter("password");
		int errorTimes = 0;
		String msg = null;
		Map<String,Object> retMap = new HashMap<>();
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(userAccount, password);
			token.setRememberMe(true);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);

			if (subject.isAuthenticated()) {
				User user = (User) subject.getSession().getAttribute(GlobalConstant.SESSION_PLATFORM_USER_KEY);
				request.getSession().setAttribute(GlobalConstant.SESSION_PLATFORM_USER_KEY, user);
				request.getSession().setAttribute(GlobalConstant.SESSION_LOGIN_TIMES, 0);
				List<Resource> menuList = resorceService.findMenuList();

				/*String systemVersion = SystemConfig.getStringValue("system.publish.version");

				// 0开发，1测试，2正式
				if (GlobalConstant.SYSTEM_PUBLISH_VERSION_DEV.equals(systemVersion)) {
					systemVersion = "开发版";
				} else if (GlobalConstant.SYSTEM_PUBLISH_VERSION_TEST.equals(systemVersion)) {
					systemVersion = "测试版";
				} else if (GlobalConstant.SYSTEM_PUBLISH_VERSION_RC.equals(systemVersion)) {
					systemVersion = "正式版";
				}*/

//				view = new ModelAndView("/platform/index/index");
				retMap.put("menuList", menuList);
				retMap.put("user", user);
				//retMap.put("systemVersion", systemVersion);
				// 清空登录次数缓存
				//loginTimesCache.clearTimes(userAccount);

				model.addAttribute("menuList", menuList);
				model.addAttribute("user", user);
				//model.addAttribute("systemVersion", systemVersion);
				respBody.setStatus(RespBody.StatusEnum.OK);
				respBody.setResult(retMap);
				msg = "登录成功";
			} else {
				respBody.setStatus(RespBody.StatusEnum.FAIL);
				msg = "登录超时，请重新登录";
			}
		} catch (IncorrectCredentialsException e) {
			msg = "登录密码错误";
			//errorTimes = loginTimesCache.loginErrorTimes(userAccount);

		} catch (ExcessiveAttemptsException e) {
			msg = "登录失败次数过多";e.printStackTrace();
		} catch (LockedAccountException e) {
			msg = "帐号已被锁定.";e.printStackTrace();
		} catch (DisabledAccountException e) {
			msg = "帐号已被禁用.";e.printStackTrace();
		} catch (ExpiredCredentialsException e) {
			msg = "帐号已过期.";e.printStackTrace();
		} catch (UnknownAccountException e) {
			msg = "帐号不存在.";e.printStackTrace();
		} catch (UnauthorizedException e) {
			msg = "您没有得到相应的授权！";e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("do login msg:{}", JSON.toJSONString(respBody));
		if(respBody.getStatus() != null){
			if(respBody.getStatus() != RespBody.StatusEnum.OK){
				respBody.setStatus(RespBody.StatusEnum.FAIL);
			}
		}else{
			respBody.setStatus(RespBody.StatusEnum.FAIL);
		}
		respBody.setMessage(msg);
		/*model.addAttribute("message", msg);
		model.addAttribute("loginTims", errorTimes);*/
		return respBody;
	}

	/**
	 * menus
	 *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/menus")
	@ResponseBody
	public RespBody menus(User user){
		RespBody respBody = new RespBody();
		String userAccount = user.getAccount();
		List<Resource> menus = resorceService.findResourceListByUser(userAccount);

		if(!CollectionUtils.isEmpty(menus)){
			Map<String,Object> jsonMenus = new HashMap<>();
			for (Resource resource: menus){
				jsonMenus.put("text", resource.getName());
			}
			respBody.setStatus(RespBody.StatusEnum.OK);
		}else{
			respBody.setStatus(RespBody.StatusEnum.ERROR);
			respBody.setMessage("菜单权限不存在");
		}
		return respBody;
	}

	@RequestMapping("/403")
	public String unauthorizedRole() {
		return "/403";
	}

	@RequestMapping("/pf_building")
	public ModelAndView building() {
		ModelAndView view = new ModelAndView("/platform/common/building");
		return view;
	}

	/*@RequestMapping(value = "/pf_logout")
	public ModelAndView logout(RedirectAttributes redirectAttributes, HttpServletRequest request) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		session.removeAttribute(GlobalConstant.SESSION_PLATFORM_USER_KEY);
		currentUser.logout();
		redirectAttributes.addFlashAttribute("message", "您已安全退出");
		return new ModelAndView("redirect:/pf_toLogin");
	}*/
}
