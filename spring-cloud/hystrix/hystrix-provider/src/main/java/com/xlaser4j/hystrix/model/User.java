package com.xlaser4j.hystrix.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @package: com.xlaser4j.hystrix.model
 * @author: Elijah.D
 * @time: 2020/3/25 15:20
 * @description:
 * @modified: Elijah.D
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;

    private String name;

    private boolean status;
}
