package com.freamwork;

import java.io.Serializable;

public interface Result extends Serializable {

	/**
	 * 请求是否成功。
	 * 
	 * @return 如果成功，则返回<code>true</code>
	 */
	boolean isSuccess();

	/**
	 * 设置请求成功标志。
	 * 
	 * @param success 成功标志
	 */
	void setSuccess(boolean success);

	/**
	 * 取得结果代码。
	 * 
	 * @return 结果代码
	 */
	String getCode();

	/**
	 * 设置结果代码。
	 * 
	 * @param code 结果代码
	 */
	void setCode(String code);

	/**
	 * 取得所有model对象。
	 * 
	 * @return model对象表
	 */
	Object getModel(String key);
	
	
	/**
	 * 设置返回对象
	 * @param key 键
	 * @param value 值
	 */
	void setModel(String key, Object value);
	
	
	/**
	 * 设置错误信息
	 * @param code 错误码
	 * @param errorMsg 错误信息
	 */
	void setError(String code, String errorMsg);
	
	
	/**
	 * 获取错误信息 
	 * @return 返回错误信息
	 */
	String getErrorMsg();

}
