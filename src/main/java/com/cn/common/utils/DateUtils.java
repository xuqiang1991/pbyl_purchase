/**
 

 *  <P> Copyright 2014 . </p>

 *  <p> Created on 2017年11月27日下午4:56:16</p>
 *  <p> Created by zhoujb</p>
 
 
 */
package com.cn.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @Project: zhoujb
 * @Package: com.cn.common.utils
 * @ClassName: DateUtils
 * @Description: 日期工具类
 * @JDK version used: 
 * @Author: zhoujb
 * @Create Date: 2017年11月27日下午4:56:16
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class DateUtils {

	/**
	 * 获取单月最后一天和第一天
	 * @author CHANG.CHI.HUNG
	 * @Email zhenzx@sun309.com
	 * @data 2017年11月24日上午9:35:21
	 */
	public static Map<String, String> getCurMonthFirstAndLastDay() {
		Map<String, String> map = new HashMap<>();
		Calendar cale = Calendar.getInstance();
		// 获取当月第一天和最后一天  
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 获取当月的第一天  
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		String firstday = format.format(cale.getTime());
		map.put("firstday", firstday);
		// 获取当月的最后一天  
		cale.add(Calendar.MONTH, 1);
		cale.set(Calendar.DAY_OF_MONTH, 0);
		String lastday = format.format(cale.getTime());
		map.put("lastday", lastday);
		return map;

	}

}
