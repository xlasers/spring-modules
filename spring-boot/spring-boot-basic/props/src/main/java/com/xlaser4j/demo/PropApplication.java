package com.xlaser4j.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Console;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2019/12/5 10:20
 * @description: 注入properties
 * @modified: Elijah.D
 */
@SpringBootApplication
public class PropApplication {
    private final Company company;

    private final Logo logo;

    public PropApplication(Company company, Logo logo) {
        this.company = company;
        this.logo = logo;
    }

    public static void main(String[] args) {
        /// SpringApplication.run(PropApplication.class, args);
        new SpringApplicationBuilder(PropApplication.class).build().run(args);
    }

    @PostConstruct
    private void printInfo() {
        // SINGLE
        Console.log("【Single】:{}\n", company.getName() + " " + company.getLocation());

        // ARRAY
        Console.log("【Array】:{}\n", Arrays.toString(company.getWeekday()));

        // LIST
        Console.log("【List】:{}\n", company.getWeekend());

        // MAP
        Console.log("【Map】:{}\n", company.getSex());

        // LIST<MAP> TO BEAN
        Console.log("【List<Map>】:{}\n", company.getEmployees());
        List<Employee> employees = company.getEmployees()
                .stream()
                .map(o -> BeanUtil.mapToBean(o, Employee.class, true))
                .collect(Collectors.toList());
        Console.log("【MapToBean】:{}\n", employees);

        // @VALUE
        Console.log("【@VALUE】:{}\n", logo.getLogo());
    }
}
