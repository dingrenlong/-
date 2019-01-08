package com.drl.entity.util;
/**
 * 封装执行的响应结果
 * @author Administrator
 *
 * @param <T>
 */
public class ResponseResult<T> {
	/*
	 * 执行成功
	 */
	public static final int STATE_OK=1;
	
	/*
	 * 执行失败
	 */
	public static final int STATE_ERR=0;
	
	/*
	 * 执行成功还是失败
	 */
	private Integer state;//成功（1），失败（0）
	
	/*
	 * 错误信息
	 */
	private String message;//仅当失败时封装错误信息
	
	/*
	 * 返回数据类型T，返回值
	 */
	private T data;//数据，返回值
	
	/**
	 * 无参构造
	 */
	public ResponseResult() {
		super();
	}
	
	/**
	 * 设置执行成功或失败
	 * @param state
	 */
	public ResponseResult(Integer state) {
		super();
		this.state = state;
	}
	
	/**
	 * 设置执行成功或失败，及错误信息
	 * @param state
	 * @param message
	 */
	public ResponseResult(Integer state, String message) {
		super();
		this.state = state;
		this.message = message;
	}
	
	/**
	 * 
	 * @param state 执行状态
	 * @param data 返回值
	 */
	public ResponseResult(Integer state, T data) {
		super();
		this.state = state;
		this.data = data;
	}
	
	/**
	 * 设置状态为失败，并设置错误信息
	 * @param throwable 捕获到的异常
	 */
	public ResponseResult(Throwable throwable) {
		super();
		this.state=STATE_ERR;
		message=throwable.getMessage();
	}
	
	/**
	 * 
	 * @param state 执行状态
	 * @param message 错误信息
	 * @param data 返回值
	 */
	public ResponseResult(Integer state, String message, T data) {
		super();
		this.state = state;
		this.message = message;
		this.data = data;
	}
	
	//其他标准做法
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
