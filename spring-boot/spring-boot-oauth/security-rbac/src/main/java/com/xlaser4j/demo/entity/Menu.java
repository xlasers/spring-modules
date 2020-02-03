package com.xlaser4j.demo.entity;

import java.util.List;

import lombok.Data;

/**
 * @package: com.xlaser4j.demo.entity
 * @author: Elijah.D
 * @time: 2020/2/3 13:41
 * @description:
 * @modified: Elijah.D
 */
@Data
public class Menu {
    private Integer id;

    private String pattern;

    private List<Role> roles;
}
