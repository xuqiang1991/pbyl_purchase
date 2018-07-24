package com.cn.config;

import com.cn.framework.shiro.matcher.CnCredentialsMatcher;
import com.cn.platform.security.CamboLogoutFilter;
import com.cn.platform.security.realm.CnRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    @Bean(name = "customRealm")
    public CnRealm customRealm() {
        CnRealm cnRealm =  new CnRealm();
        cnRealm.setCredentialsMatcher(new CnCredentialsMatcher());
        return cnRealm;
    }

    @Bean
    protected CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(CnRealm customRealm) {
        DefaultWebSecurityManager  securityManager = new DefaultWebSecurityManager ();
        securityManager.setRealm(customRealm);
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/pf_toLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("logout", new CamboLogoutFilter());

        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
        filterChainDefinitionMap.put("/pf_doLogin", "anon");
        filterChainDefinitionMap.put("/pf_toLogin", "anon");
        filterChainDefinitionMap.put("/pf_building", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/platform/js/**", "anon");
        filterChainDefinitionMap.put("/platform/css/**", "anon");
        filterChainDefinitionMap.put("/platform/images/**", "anon");
        filterChainDefinitionMap.put("/platform/common/**", "anon");
        filterChainDefinitionMap.put("/platform/lib/**", "anon");
        filterChainDefinitionMap.put("/platform/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

}