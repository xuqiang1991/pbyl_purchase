package com.cn.config;

import com.alibaba.fastjson.JSONObject;
import com.cn.common.utils.CommonUtil;
import com.cn.framework.mvc.controller.RespBody;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.OutputStream;

/**
 * 登陆失效处理
 * Created by xuqiang
 * 2018/7/26.
 */
public class ShiroFormAuthenticationFilter  extends FormAuthenticationFilter {
    private static final Logger log = LoggerFactory.getLogger(ShiroFormAuthenticationFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                return true;
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }
            if(CommonUtil.isAjax(request)){
                RespBody respBody = new RespBody();
                respBody.setResult("登陆失效，请重新登陆！");
                respBody.setStatus(RespBody.StatusEnum.ERROR);

                OutputStream out = response.getOutputStream();
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json; charset=utf-8");
                response.getWriter().write(JSONObject.toJSON(respBody).toString());
                out.flush();
                out.close();
            }else{
                this.saveRequestAndRedirectToLogin(request, response);
            }
            return false;
        }
    }



}
