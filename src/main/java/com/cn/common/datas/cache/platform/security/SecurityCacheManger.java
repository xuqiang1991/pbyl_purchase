/**
 

 *  <P> Copyright 2017 阳光康众</p>

 *  <p> Created on 2017年7月1日</p>
 *  <p> Created by zhoujb</p>
 
 
 */
package com.cn.common.datas.cache.platform.security;

import com.cn.common.GlobalConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Project ChuFangLiuZhuan_PlatForm
 * @Package com.cn.cache.platform
 * @ClassName SecurityCacheManger.java
 * @Description
 * @JDK version used 1.8
 * @Author zhoujb
 * @Create Date 2017年7月1日
 * @modify By
 * @modify Date
 * @Why&What is modify
 * @Version 1.0
 */
//@Service
public class SecurityCacheManger {
	private static Logger logger = LoggerFactory.getLogger(SecurityCacheManger.class);
	private static final String CHECK_INIT_CACHE_FILED = "security.init";

	public void initCache() {

	}

	/**
	 * @Description 缓存版本
	 * @return
	 * @date 2017年7月1日
	 */
	private String builCacheVersion() {
		return CHECK_INIT_CACHE_FILED.concat(GlobalConstant.getServerVersion());
	}

}
