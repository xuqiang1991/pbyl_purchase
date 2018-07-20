package com.cn.platform.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CamboLogoutFilter extends LogoutFilter {
  private static final Logger logger = LoggerFactory.getLogger(CamboLogoutFilter.class);
  
  protected boolean preHandle(ServletRequest request, ServletResponse response)
    throws Exception
  {
    Subject subject = getSubject(request, response);
    Object account = subject.getSession().getAttribute("userAccount");
    String redirectUrl = getRedirectUrl(request, response, subject);
    try
    {
      subject.logout();
    }
    catch (SessionException ise)
    {
      ise.printStackTrace();
      logger.error("event:帐号[{}]安全退出出错", account == null ? "未知帐号" : account.toString());
    }
    issueRedirect(request, response, redirectUrl);
    
    return false;
  }
}
