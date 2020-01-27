package com.xlaser4j.demo.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @package: com.xlaser4j.demo.model
 * @author: Elijah.D
 * @time: 2020/1/27 21:10
 * @description:
 * @modified: Elijah.D
 */
@Data
@NoArgsConstructor
@Document(collection = "person")
public class Person {
    private String id;

    private String name;

    private List<Long> data;

    public Person(String name, List<Long> data) {
        this.name = name;
        this.data = data;
    }

    public static List<Long> init(Long arg) {
        List<Long> data = new ArrayList<>();
        data.add(arg);
        data.add(arg + 1);
        data.add(arg + 2);
        return data;
    }
}
