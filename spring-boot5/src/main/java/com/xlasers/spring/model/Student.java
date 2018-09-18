package com.xlasers.spring.model;

import javax.persistence.*;

import lombok.Data;

/**
 * The type Student.
 *
 * @package: com.xlasers.spring.model
 * @author: Elijah.D
 * @time: CreateAt 2018/10/6 && 14:47
 * @description: 实体类
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@Data
@Entity
@Table(name = "student")
public class Student {
	/**
	 * The Id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * The Name.
	 */
	private String name;

	/**
	 * The Age.
	 */
	private Integer age;
}
