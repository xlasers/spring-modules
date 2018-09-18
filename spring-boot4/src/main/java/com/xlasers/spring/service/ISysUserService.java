package com.xlasers.spring.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xlasers.spring.model.SysUser;

/**
 * The interface Sys user service.
 *
 * @package: com.xlasers.spring.service
 * @author: Elijah.D
 * @time: CreateAt 2018/10/2 && 22:25
 * @description: 系统用户, 服务类
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
public interface ISysUserService extends IService<SysUser> {
	/**
	 * Gets user by id of first db.
	 *
	 * @param id the id
	 * @return the user by id of first db
	 */
	SysUser getUserByIdOfFirstDb(long id);

	/**
	 * Gets user by id of second db.
	 *
	 * @param id the id
	 * @return the user by id of second db
	 */
	SysUser getUserByIdOfSecondDb(long id);

	/**
	 * Gets user by id of first and second.
	 *
	 * @param id the id
	 * @return the user by id of first and second
	 */
	SysUser getUserByIdOfFirstAndSecond(long id);
}