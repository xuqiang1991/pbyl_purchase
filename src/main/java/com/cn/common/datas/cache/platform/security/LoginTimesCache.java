package com.cn.common.datas.cache.platform.security;

import com.cn.common.datas.cache.CacheConstants;
import com.cn.framework.cache.redis.RedisService;
import com.cn.framework.common.spring.ext.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 
 * @Project: Prescription_Clinic 
 * @Package: com.cn.cache.platform.security
 * @ClassName: LoginTimesCache
 * @Description: <p>	登录次数记录    《过期时间段为一个小时》</p>
 * @JDK version used: 
 * @Author: 香薷
 * @Create Date: 2017年9月5日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class LoginTimesCache {

	/**
	 * 登录次数缓存
	 */
	public static final String CACHE_LOGIN_ERROR = "LOGINERROR:";

	private RedisService redisSvc = SpringContextHolder.getBean(RedisService.class);

	//设置登录次数缓存
	public int loginErrorTimes(String account) {
		String key = CACHE_LOGIN_ERROR.concat(account);
		String errorTimes = redisSvc.get(key);
		if (StringUtils.isNotBlank(errorTimes) && !errorTimes.equals(CacheConstants.CACHE_KEY_NOT_EXIST)) {
			int times = Integer.parseInt(errorTimes);
			times += 1;
			redisSvc.set(key, times);
			redisSvc.expire(key, 3600);
			return times;
		} else {
			redisSvc.set(key, 1);
			redisSvc.expire(key, 3600);
			return 1;
		}
	}

	/**
	 * 清空对应账户的缓存
	 * @param account
	 */
	public void clearTimes(String account) {
		String key = CACHE_LOGIN_ERROR.concat(account);
		String errorTimes = redisSvc.get(key);
		if (StringUtils.isNotBlank(errorTimes) && !errorTimes.equals(CacheConstants.CACHE_KEY_NOT_EXIST)) {
			redisSvc.del(key);
		}
	}

}
