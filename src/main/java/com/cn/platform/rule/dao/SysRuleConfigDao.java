
package com.cn.platform.rule.dao;

import com.cn.framework.mvc.mysql.dao.BaseDao;
import com.cn.platform.rule.entity.SysRuleConfig;

/**
 * @Project: zhoujb
 * @Package: com.cn.platform.rule.dao
 * @ClassName: SysRuleConfigDao
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: zhoujb
 * @Create Date: 2017年11月29日上午10:24:14
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public interface SysRuleConfigDao extends BaseDao<SysRuleConfig, String> {

	/**
	 * @author CHANG.CHI.HUNG
	 * @Email zhenzx@sun309.com
	 * @data 2017年11月29日下午2:33:55
	 */
	SysRuleConfig findByRuleCode(String ruleCode);

}
