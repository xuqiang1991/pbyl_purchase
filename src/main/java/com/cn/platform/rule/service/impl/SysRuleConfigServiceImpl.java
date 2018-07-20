/**
 

 *  <P> Copyright 2014 . </p>

 *  <p> Created on 2017年11月29日</p>
 *  <p> Created by zhoujb</p>
 
 
 */
package com.cn.platform.rule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.framework.mvc.mysql.dao.BaseDao;
import com.cn.framework.mvc.mysql.service.impl.BaseServiceImpl;
import com.cn.platform.rule.dao.SysRuleConfigDao;
import com.cn.platform.rule.entity.SysRuleConfig;
import com.cn.platform.rule.service.SysRuleConfigService;

/**
 * @Project: zhoujb
 * @Package: com.cn.platform.rule.service.impl
 * @ClassName: SysRuleConfigServiceImpl
 * @Description: 规则配置实现类
 * @JDK version used: 
 * @Author: zhoujb
 * @Create Date: 2017年11月29日上午10:25:11
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
@Service
public class SysRuleConfigServiceImpl extends BaseServiceImpl<SysRuleConfig, String> implements SysRuleConfigService {
	@Autowired
	protected SysRuleConfigDao sysRuleConfigDao;

	@Override
	protected BaseDao<SysRuleConfig, String> getDao() {
		return sysRuleConfigDao;
	}

	@Override
	public SysRuleConfig findByRuleCode(String ruleCode) {
		return sysRuleConfigDao.findByRuleCode(ruleCode);
	}

}
