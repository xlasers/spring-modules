package com.xlaser4j.demo.controller;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.xlaser4j.demo.entity.StudentEntity;
import com.xlaser4j.demo.repository.read.StudentReadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.spring.controller
 * @author: Elijah.D
 * @time: 2018/10/6 15:00
 * @description: controller测试
 * @modified: Elijah.D
 */
@Slf4j
@RestController
@RequestMapping("/read")
public class TestReadController {
    private final StudentReadRepository readRepo;

    @Autowired
    public TestReadController(StudentReadRepository readRepo) {
        this.readRepo = readRepo;
    }

    /**
     * find
     *
     * @return
     */
    @GetMapping("/1")
    public Object findAll() {

        return readRepo.findAll();
    }

    /**
     * simple
     *
     * @return
     */
    @GetMapping("/2")
    public Object findBySimpleCondition() {
        return readRepo.findNameById(2L);
    }

    /**
     * complex
     *
     * @return
     */
    @GetMapping("/3")
    public Object findByComplexCondition() {
        return readRepo.findByNameStartingWithAndAgeLessThanEqualOrderByIdDesc("L", 19);
    }

    /**
     * 分页
     *
     * @return
     */
    @GetMapping("/4")
    public Object findByPage() {

        Pageable pageable = PageRequest.of(0, 2);
        Page<StudentEntity> page = readRepo.findByNameStartingWithOrderByIdDesc(pageable, "L");

        log.info("【pageable】获取列表总条数, total: {}", page.getTotalElements());
        log.info("【pageable】获取列表总页数, total: {}", page.getTotalPages());
        log.info("【pageable】获取每页大小, total: {}", page.getSize());

        return page;
    }

    /**
     * root: 查询的类型
     * query: 查询条件
     * builder: 构建Predicate
     *
     * @return object
     * @see Specification#toPredicate(Root, CriteriaQuery, CriteriaBuilder)
     */
    @GetMapping("/5")
    public Object findBySpecification() {

        // 构建表查询条件
        Specification<StudentEntity> specification = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), "Linus");

        // 构建排序分页查询条件
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(0, 5, sort);

        // 查询
        return readRepo.findAll(specification, pageable);
    }
}
