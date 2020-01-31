package com.xlaser4j.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * @package: com.xlaser4j.demo.entity
 * @author: Elijah.D
 * @time: 2018/10/6 14:47
 * @description: 实体类
 * @modified: Elijah.D
 */
@Data
@Entity(name = "student")
public class StudentEntity {
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
