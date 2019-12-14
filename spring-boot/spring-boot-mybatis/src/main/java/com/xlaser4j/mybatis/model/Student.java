package com.xlaser4j.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @package: com.xlaser4j.mybatis.config
 * @author: Elijah.D
 * @time: 2019/12/14 14:24
 * @description:
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Long id;

    private Integer age;

    private String name;
}
