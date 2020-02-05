package com.xlaser4j.demo.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @package: com.xlaser4j.demo.model
 * @author: Elijah.D
 * @time: 2020/2/5 15:34
 * @description:
 * @modified: Elijah.D
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Msg implements Serializable {
    /**
     * MessageConversionException: Cannot convert object of type [model] to JMS message.
     * Supported message payloads are: String, byte array, Map<String,?>, Serializable object.] with root cause
     * <p>
     * 必须实现序列化,否则抛出异常{@link org.springframework.jms.support.converter.MessageConversionException}
     */
    private static final long serialVersionUID = 8576054622042153346L;

    private String primary;

    private Date start;

    private String content;
}
