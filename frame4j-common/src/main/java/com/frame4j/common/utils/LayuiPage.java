package com.frame4j.common.utils;

import java.util.List;

/**
 * layui 规定page
 * 
 * @author MJ
 *
 */
public class LayuiPage<T> {
	// 状态码
	private Integer code;
	// 消息
	private String msg;
	// 总数量
	private Long count;
	// 数据
	private List<T> data;

	public LayuiPage() {
	}

	public LayuiPage(Integer code, String msg, Long count, List<T> data) {
		super();
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}

	public LayuiPage(List<T> data, Long count) {
		super();
		this.code = 0;
		this.msg = "";
		this.count = count;
		this.data = data;
	}

	public LayuiPage<T> data(List<T> data, Long count) {
		return new LayuiPage<T>(data, count);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
