package com.xlaser4j.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

/**
 * hibernate.LazyInitializationException: could not initialize proxy [..] - no Session
 * <p>
 * 懒加载机制发生如上异常解决:
 * 1.application.yml中配置,在没有事务的使用懒加载: jpa.properties.hibernate.enable_lazy_load_no_trans=true
 * 2.实体类上加上注解false{@link Proxy#lazy()}
 *
 * @package: com.xlaser4j.demo.entity
 * @author: Elijah.D
 * @time: 2020/2/1 20:56
 * @description:
 * @modified: Elijah.D
 */
@Data
// @Proxy(lazy = false)
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
