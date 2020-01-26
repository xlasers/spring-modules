package com.xlaser4j.demo.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

/**
 * @package: com.xlaser4j.demo.config
 * @author: Elijah.D
 * @time: 2020/1/26 12:54
 * @description:
 * @modified: Elijah.D
 */
@Configuration
public class DateConverter implements Converter<String, Date> {
    /**
     * convert sting to date
     *
     * @param s source
     * @return target
     */
    @SneakyThrows
    @Override
    public Date convert(String s) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        return format.parse(s);
    }
}
