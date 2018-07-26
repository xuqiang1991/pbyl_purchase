package com.cn.config;

import com.cn.platform.security.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by xuqiang
 * 2018/7/25.
 */
public class SessionInfoArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> claz = parameter.getParameterType();
        //将user这个对象加入到controller的方法参数中
        if(claz == User.class) {
            return true ;
        }

        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
            Class c = parameter.getParameterType();
            if(c == User.class){
                Subject subject = SecurityUtils.getSubject();
                User user = (User)subject.getSession().getAttribute("platformUser");
                user.setPassword(null);
                return user;
            }
        return null;
    }

}
