package com.eplusing.mall.web.bean;

public class Response {
	//200成功，500失败
	private String code;
	private String msg;
	private Product data;
	
	public Response(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public Response(String code, String msg, Product data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	
	
	@Override
	public String toString() {
		return "Response [code=" + code + ", msg=" + msg + ", data=" + data + "]";
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
	public Object getData() {
		return data;
	}
	public void setData(Product data) {
		this.data = data;
	}
	
	
}
