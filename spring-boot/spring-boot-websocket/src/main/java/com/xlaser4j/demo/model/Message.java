package com.xlaser4j.demo.model;

import lombok.Data;

/**
 * @package: com.xlaser4j.demo.model
 * @author: Elijah.D
 * @time: 2020/2/5 10:22
 * @description:
 * @modified: Elijah.D
 */
@Data
public class Message {
    private String from;

    private String to;

    private String payload;
}
