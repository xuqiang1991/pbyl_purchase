package com.cn.platform.security.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cn.framework.common.spring.ext.SpringContextHolder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cn.platform.security.entity.Resource;
import com.cn.platform.security.entity.User;
import com.cn.platform.security.service.ResourceService;
import com.cn.platform.security.service.UserService;

public class CnRealm extends AuthorizingRealm {
  private static Logger logger = LoggerFactory.getLogger(CnRealm.class);
  
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
  {
    long startTime = System.currentTimeMillis();
    Subject subject = SecurityUtils.getSubject();
    User user = (User)subject.getSession().getAttribute("platformUser");
    if (user == null) {
      return null;
    }
    if (user.getRoleIdList().size() == 0) {
      return null;
    }
    Set<String> roleIds = new HashSet();
    roleIds.addAll(user.getRoleIdList());
    
    Set<String> permissions = new HashSet();
    ResourceService resourceService = (ResourceService) SpringContextHolder.getBean(ResourceService.class);
    

    List<Resource> resourceList = resourceService.queryResourceByRoleIds(user.getRoleIdList());
    for (Resource resource : resourceList) {
      permissions.add(resource.getCode());
    }
    long costTime = System.currentTimeMillis() - startTime;
    logger.info("doGetAuthorizationInfo cost time:" + costTime);
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleIds);
    info.setStringPermissions(permissions);
    return info;
  }
  
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
    throws AuthenticationException
  {
    String account = (String)token.getPrincipal();
    
    UserService userService = (UserService)SpringContextHolder.getBean(UserService.class);
    User user = userService.findUserByAccount(account);
    if (user == null) {
      throw new UnknownAccountException();
    }
    if (user.getStatus().intValue() != 1) {
      throw new LockedAccountException();
    }
    AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getAccount(), user.getPassword(), getName());
    Subject subject = SecurityUtils.getSubject();
    subject.getSession().setAttribute("platformUser", user);
    subject.getSession().setAttribute("userAccount", user.getAccount());
    return authcInfo;
  }
  
  public void clearCachedAuthorizationInfo(String principal)
  {
    SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
    clearCachedAuthorizationInfo(principals);
  }
  
  public void clearAllCachedAuthorizationInfo()
  {
    Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
    cache.clear();
  }
  
  public boolean supports(AuthenticationToken token)
  {
    return token instanceof UsernamePasswordToken;
  }
}
