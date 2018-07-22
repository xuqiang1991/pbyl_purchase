
package com.cn.common.invoke.dto;

import java.io.Serializable;

/**
 * 对外接口出参
 * @Project: zhoujb 
 * @Package: com.cn.common.invoke.dto
 * @ClassName: Response
 * @Description: <p> </p>
 * @JDK version used: 
 * @Author: zhoujb
 * @Create Date: 2017年12月21日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class Response implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2526607896162883219L;

	/**
	 * 响应结果代码
	 */
	private String resultCode;

	/**
	 * 响应信息 ,当交易结果代码不成功时，该字段返回错误信息，否则返回空字符串
	 */
	private String resultMessage;

	/**
	 * 返回数据
	 */
	private String result;
	/**
	 * 返回数据
	 */
	private Object resultObj;

	public Response() {
		super();
	}

	/**
	 * @param resultCode
	 */
	public Response(String resultCode) {
		super();
		this.resultCode = resultCode;
	}

	public Response(String resultCode, String resultMessage) {
		super();
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
	}

	public Response(String resultCode, String resultMessage, String result) {
		super();
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
		this.result = result;
	}

	public Response(String resultCode, String resultMessage, Object resultObj) {
		super();
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
		this.resultObj = resultObj;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the resultObj
	 */
	public Object getResultObj() {
		return resultObj;
	}

	/**
	 * @param resultObj the resultObj to set
	 */
	public void setResultObj(Object resultObj) {
		this.resultObj = resultObj;
	}

}
