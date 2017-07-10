package com.xiaomai.supershopowner.common;

public class RSResult {

	private String code;
	private String msg;
	private Object result;

	public RSResult() {
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "RSResult [result=" + result + ", message=" + msg +",code="+code+ "]";
	}

}
