package com.tiny.url.app.db.exception;

public class TinySequenceException extends Throwable {
	private static final long serialVersionUID = 1L;

	private String errCode;
	private String errMsg;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public TinySequenceException(String errMsg) {
		this.errMsg = errMsg;
	}
}
