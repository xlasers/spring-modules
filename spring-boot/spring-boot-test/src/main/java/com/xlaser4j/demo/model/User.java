package com.xlaser4j.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @package: com.xlaser4j.demo.model
 * @author: Elijah.D
 * @time: 2020/2/1 17:10
 * @description:
 * @modified: Elijah.D
 */
@Data
@NoArgsConstructor
public class User {
    private Integer id;

    private String name;

    private Integer age;

    public User(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
