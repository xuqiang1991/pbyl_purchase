/**
 

 *  <P> Copyright 2014 . </p>

 *  <p> Created on 2017年11月29日下午2:54:14</p>
 *  <p> Created by zhoujb</p>
 
 
 */
package com.cn.platform;

/**
 * @Project: zhoujb
 * @Package: com.cn.platform
 * @ClassName: RuleConstant
 * @Description: 规则配置常量
 * @JDK version used: 
 * @Author: zhoujb
 * @Create Date: 2017年11月29日下午2:54:14
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class RuleConstant {

	/**
	* 银行余额限制
	*/
	public final static String BANKCARD_BALANCE_LIMIT_RULE = "bankcard_balance_limit_rule";

	/**
	 * 当日消费金额上限设置
	 */
	public final static String CONSUMPTION_AMOUNT_LIMIT_RULE = "consumption_amount_limit_rule";

	/**
	 * 订单金额阀值设置
	 */
	public final static Integer ORDER_THRESHOLD_LIMIT = 100000;

}
