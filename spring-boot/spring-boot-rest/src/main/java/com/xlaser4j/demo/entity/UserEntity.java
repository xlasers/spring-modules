package com.xlaser4j.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @package: com.xlaser4j.demo.entity
 * @author: Elijah.D
 * @time: 2020/1/31 20:56
 * @description:
 * @modified: Elijah.D
 */
@Data
@Entity(name = "user")
@NoArgsConstructor
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -7395878524665212384L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private Integer age;

    public UserEntity(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
