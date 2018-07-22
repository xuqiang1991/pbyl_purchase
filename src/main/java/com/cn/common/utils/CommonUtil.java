
package com.cn.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @Project: zhoujb
 * @Package: com.cn.common.utils
 * @ClassName: CommonUtil
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: zhoujb
 * @Create Date: 2017年11月27日上午11:15:32
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class CommonUtil {

	/** 
	* [身份证号] 前六位，后四位，其他用星号隐藏每位1个星号<例子:6222600**********1234> 
	*  
	* @param cardNum 
	* @return 
	*/
	public static String idCardNumFront(String cardNum) {
		if (StringUtils.isBlank(cardNum)) {
			return "";
		}
		return StringUtils.left(cardNum, 6)
				.concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(cardNum, 4), StringUtils.length(cardNum), "*"), "******"));
	}

	public static String formatMedicalCard(String medicalCard) {
		return medicalCard.substring(0, 4) + "*****" + medicalCard.substring(medicalCard.length() - 4, medicalCard.length());
	}

}
