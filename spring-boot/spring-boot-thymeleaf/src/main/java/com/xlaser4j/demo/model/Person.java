package com.xlaser4j.demo.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @package: com.xlaser4j.demo.model
 * @author: Elijah.D
 * @time: 2020/1/26 12:19
 * @description:
 * @modified: Elijah.D
 */
@Data
@AllArgsConstructor
public class Person {
    private String id;

    private String name;

    private Integer age;

    public static List<Person> listPersons() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("1", "Elijah", 20));
        persons.add(new Person("2", "Martin Flower", 40));
        persons.add(new Person("3", "Linus", 50));
        return persons;
    }
}
