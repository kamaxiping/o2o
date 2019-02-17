package com.lianxi.o2o.dto;

public class Result<T> {
	private boolean success;// 是否成功标志
	private T data; // 成功后返回的数据
	private String errorMsg;// 失败的信息
	private int errorCode;

	public Result() {
		super();
	}

	public Result(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}

	public Result(boolean success, String errorMsg, int errorCode) {
		super();
		this.success = success;
		this.errorMsg = errorMsg;
		this.errorCode = errorCode;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
