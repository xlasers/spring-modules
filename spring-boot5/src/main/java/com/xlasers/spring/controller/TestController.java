package com.xlasers.spring.controller;
public class TestController {
<<<<<<< HEAD
=======
	private final StudentRepository repository;

	/**
	 * Instantiates a new Test controller.
	 *
	 * @param repository the repository
	 */
	@Autowired
	public TestController(StudentRepository repository) {
		this.repository = repository;
	}

	/**
	 * Test 1 object.
	 *
	 * @return the object
	 */
	@GetMapping("/1")
	public Object test1() {

		return repository.findByOrderByIdDesc();
	}

	/**
	 * Test 2 object.
	 *
	 * @return the object
	 */
	@GetMapping("/2")
	public Object test2() {
		return repository.findByNameStartingWithAndAgeLessThanEqualOrderByIdDesc("平", 19);
	}

	/**
	 * Test 3 object.
	 *
	 * @return the object
	 */
	@GetMapping("/3")
	public Object test3() {
		return repository.findNameById(1L);
	}

	/**
	 * Test 4.
	 */
	@GetMapping("/4")
	public void test4() {
		repository.deleteByNameAndAge("tom", 11);
	}

	/**
	 * Test 5 object.
	 *
	 * @return the object
	 */
	@GetMapping("/5")
	public Object test5() {

		Pageable pageable = PageRequest.of(0, 2);
		Page<Student> page = repository.findByNameStartingWithOrderByIdDesc(pageable, "tom");

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
	@GetMapping("/6")
	public Object test6() {

		Sort sort = new Sort(Sort.Direction.ASC, "id");

		Pageable pageable = PageRequest.of(0, 5, sort);

		Specification<Student> specification = (root, criteriaQuery, criteriaBuilder) ->
		{
			Path path = root.get("name");
			return criteriaBuilder.equal(path, "平平");
		};

		return repository.findAll(specification, pageable);
	}
>>>>>>> d2aa626... :hankey: write bad code that will improve after
}
