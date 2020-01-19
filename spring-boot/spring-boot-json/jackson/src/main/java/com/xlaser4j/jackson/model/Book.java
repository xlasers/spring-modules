package com.xlaser4j.jackson.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @package: com.xlaser4j.advice.model
 * @author: Elijah.D
 * @time: 2020/1/18 17:08
 * @description:
 * @modified: Elijah.D
 */
@Data
@AllArgsConstructor
public class Book {
    private String name;

    private Double price;

    private Date publish;
}
