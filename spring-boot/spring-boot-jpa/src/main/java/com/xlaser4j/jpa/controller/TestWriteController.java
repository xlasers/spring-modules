package com.xlaser4j.jpa.controller;

import com.xlaser4j.jpa.dao.write.StudentWriteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.spring.controller
 * @author: Elijah.D
 * @time: 2018/10/6 15:00
 * @description: controller测试
 * @copyright: Copyright(c) 2018
 * @version: V1.0
 * @modified: Elijah.D
 */
@Slf4j
@RestController
@RequestMapping("/write")
public class TestWriteController {
    @Autowired
    private StudentWriteRepository writeRepo;

    /**
     * find
     *
     * @return
     */
    @GetMapping("/1")
    public Object findAll() {
        return writeRepo.findAll();
    }

    /**
     * update
     */
    @GetMapping("/2")
    public Integer update() {
        return writeRepo.updateNameById("Kent Beck", 4L);
    }

    /**
     * save
     */
    @GetMapping("/3")
    public Integer save() {
        return writeRepo.saveStudent("Kent Beck-" + Math.random(), 5);
    }

    /**
     * delete
     */
    @GetMapping("/4")
    public void delete() {
        writeRepo.deleteByNameAndAge("tom", 11);
    }
}
