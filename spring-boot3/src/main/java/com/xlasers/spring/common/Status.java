package com.xlasers.spring.common;

import lombok.Getter;

/**
 * The enum Status.
 *
 * @package: com.xlasers.spring.common
 * @author: Elijah.D
 * @time: CreateAt 2018/9/30 && 12:51
 * @description: 通用状态码
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@Getter
public enum Status {

	/**
	 * 操作成功
	 */
	SUCCESS(200, "操作成功"),
	/**
	 * 请求错误
	 */
	BAD_REQUEST(400, "请求错误");

	private Integer code;

	private String msg;

	Status(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
