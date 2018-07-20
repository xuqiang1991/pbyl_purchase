/**
 

 *  <P> Copyright 2014 . </p>

 *  <p> Created on 2017年11月29日上午10:02:39</p>
 *  <p> Created by zhoujb</p>
 
 
 */
package com.cn.platform.rule.service;

import com.cn.framework.mvc.service.BaseService;
import com.cn.platform.rule.entity.SysRuleConfig;

/**
 * @Project: zhoujb
 * @Package: com.cn.platform.rule.service
 * @ClassName: SysRuleConfigService
 * @Description: 规则配置接口类
 * @JDK version used: 
 * @Author: zhoujb
 * @Create Date: 2017年11月29日上午10:02:39
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public interface SysRuleConfigService extends BaseService<SysRuleConfig, String> {

	/**
	 * 根据规则code添加
	 * @author CHANG.CHI.HUNG
	 * @Email zhenzx@sun309.com
	 * @data 2017年11月29日下午2:11:21
	 */
	SysRuleConfig findByRuleCode(String ruleCode);

}
