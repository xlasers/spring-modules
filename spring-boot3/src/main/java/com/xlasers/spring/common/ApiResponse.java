package com.xlasers.spring.common;

import lombok.Data;

/**
 * The type Api response.
 *
 * @package: com.xlasers.spring.common
 * @author: Elijah.D
 * @time: CreateAt 2018/9/30 && 12:50
 * @description: api通用返回
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@Data
public class ApiResponse {
	/**
	 * The Code.
	 */
	private Integer code;

	/**
	 * The Msg.
	 */
	private String msg;

	/**
	 * The Data.
	 */
	private Object data;

	/**
	 * Instantiates a new Api response.
	 */
	public ApiResponse() {
		this.code = Status.SUCCESS.getCode();
		this.msg = Status.SUCCESS.getMsg();

	}

	/**
	 * Instantiates a new Api response.
	 *
	 * @param code the code
	 * @param msg  the msg
	 * @param data the data
	 */
	public ApiResponse(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * Instantiates a new Of success.
	 *
	 * @return the api response
	 */
	public static ApiResponse  ofSuccess(){

		return new ApiResponse(Status.SUCCESS.getCode(),Status.SUCCESS.getMsg(),null);
	}

	/**
	 * Of success api response.
	 *
	 * @param data the data
	 * @return the api response
	 */
	public static ApiResponse ofSuccess(Object data){

		return new ApiResponse(Status.SUCCESS.getCode(),Status.SUCCESS.getMsg(),data);
	}

	/**
	 * Of status api response.
	 *
	 * @param status the status
	 * @return the api response
	 */
	public static ApiResponse ofStatus(Status status){

		return new ApiResponse(status.getCode(),status.getMsg(),null);
	}

	/**
	 * Of message api response.
	 *
	 * @param msg the msg
	 * @return the api response
	 */
	public static ApiResponse ofMessage(String msg) {
		return new ApiResponse(Status.SUCCESS.getCode(), msg, null);
	}
}
