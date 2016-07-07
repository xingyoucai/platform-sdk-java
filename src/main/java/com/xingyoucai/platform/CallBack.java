package com.xingyoucai.platform;

import okhttp3.Response;

public interface CallBack {
	
	public void success(Response response);
	
	public void fail();
}
