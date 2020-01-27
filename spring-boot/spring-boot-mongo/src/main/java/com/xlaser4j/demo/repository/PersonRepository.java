package com.xlaser4j.demo.repository;

import com.xlaser4j.demo.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @package: com.xlaser4j.demo.repository
 * @author: Elijah.D
 * @time: 2020/1/27 21:32
 * @description:
 * @modified: Elijah.D
 */
@Repository
public interface PersonRepository extends MongoRepository<Person, String> {
}
