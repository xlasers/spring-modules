package com.xlaser4j.demo;

import com.xlaser4j.demo.model.Person;
import com.xlaser4j.demo.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2020/1/27 21:36
 * @description:
 * @modified: Elijah.D
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonTest {
    @Autowired
    private MongoTemplate template;

    @Autowired
    private PersonRepository repo;

    @Test
    public void testRepo() {
        long arg = System.currentTimeMillis();
        repo.insert(new Person("Martin" + arg, Person.init(arg)));
        repo.insert(new Person("Linus" + arg, Person.init(arg)));
        System.out.println(repo.findAll());
    }

    @Test
    public void testTemplate() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex("^L"));
        System.out.println(template.find(query, Person.class));
    }
}
