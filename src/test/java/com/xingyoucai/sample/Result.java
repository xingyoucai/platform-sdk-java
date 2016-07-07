package com.xingyoucai.sample;

public class Result {
	public Result(int errcode) {
		this.errcode = errcode;
	}

	private int errcode;

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
}
