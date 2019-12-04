package com.xlaser4j.jpa.model;

import javax.persistence.*;

import lombok.Data;

/**
 * @package: com.xlaser4j.jpa.model
 * @author: Elijah.D
 * @time: 2018/10/6 14:47
 * @description: 实体类
 * @copyright: Copyright(c) 2018
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
