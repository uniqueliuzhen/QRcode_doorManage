package com.freamwork;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务层返回对象
 * 
 */
public class ResultSupport implements Result {

	private static final long serialVersionUID = 3976733653567025460L;
	private boolean success = true;
	private String code = "200";
	private String errorMsg = "";
	private final Map<String,Object> models = new HashMap<String,Object>();
	private Map<String,Object> data = new HashMap<String,Object>();

	public ResultSupport() {

	}
	
	public ResultSupport(boolean success, String code, String errorMsg) {
		this.success = success;
		this.code = code;
		this.errorMsg = errorMsg;
	}

	@Override
	public void setModel(String key, Object value) {
		models.put(key, value);
	}


	@Override
	public Object getModel(String key) {
		return models.get(key);
	}

	@Override
	public boolean isSuccess() {
		return success;
	}

	@Override
	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.success = false;
		this.code = code;
	}

	@Override
	public String getErrorMsg() {
		return errorMsg;
	}

	@Override
	public void setError(String code, String errorMsg) {
		this.success = false;
		this.code = code;
		this.errorMsg = errorMsg;
	}

	public Map<String,Object> getData() {
		if(models.size() > 0){
			return models;
		}
		return data;
	}

}
