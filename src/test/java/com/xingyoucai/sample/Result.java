package com.xingyoucai.sample;

public class Result {
	private int errcode;
	private String errmsg;
	private Data data;

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	class Data {

		private String signKey;

		public void setSignKey(String signKey) {
			this.signKey = signKey;
		}

		public String getSignKey() {
			return this.signKey;
		}
	}
}
