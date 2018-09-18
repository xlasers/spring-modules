package com.xlasers.spring.controller;

import java.util.List;

import com.xlasers.spring.common.ApiResponse;
import com.xlasers.spring.model.Student;
import com.xlasers.spring.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * The type Student controller.
 *
 * @package: com.xlasers.spring.controller
 * @author: Elijah.D
 * @time: CreateAt 2018/9/29 && 14:18
 * @description: controller控制
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@RestController
@RequestMapping("/student")
@Api(value = "controller: 控制器")
public class StudentController {
	private final RestTemplate restTemplate;

	private final StudentService studentService;

	@Autowired
	public StudentController(RestTemplate restTemplate, StudentService studentService) {
		this.restTemplate = restTemplate;
		this.studentService = studentService;
	}

	/**
	 * Rest test api response.
	 *
	 * @return the api response
	 */
	@GetMapping("/rest")
	public ApiResponse restTest() {

		Object object = restTemplate.getForObject("http://localhost:8080/student?all", Object.class);
		return ApiResponse.ofSuccess(object);
	}

	/**
	 * Gets students.
	 *
	 * @return the students
	 */
	@ApiOperation(value = "获取学生列表")
	@GetMapping(value = "", params = "all")
	public ApiResponse getStudents() {

		List<Student> students = studentService.selectList(null);
		return ApiResponse.ofSuccess(students);
	}

	/**
	 * Post student api response.
	 *
	 * @param student the student
	 * @return the api response
	 */
	@ApiOperation("新增单个学生")
	@PostMapping("")
	public ApiResponse postStudent(@RequestBody Student student) {

		boolean isSuccess = studentService.insert(student);
		return isSuccess ? ApiResponse.ofMessage("成功新增一个角色!") : ApiResponse.ofMessage("新增失败!");
	}

	/**
	 * Gets student.
	 *
	 * @param id the id
	 * @return the student
	 */
	@ApiOperation("获取学生信息")
	@GetMapping("/{id}")
	public ApiResponse getStudent(@PathVariable("id") Long id) {

		Student student = studentService.selectById(id);
		return ApiResponse.ofSuccess(student);
	}

	/**
	 * Put student api response.
	 *
	 * @param id the id
	 * @param s  the s
	 * @return the api response
	 */
	@ApiOperation("更新学生")
	@PutMapping("/{id}")
	public ApiResponse putStudent(@PathVariable("id") Long id, @RequestBody Student s) {

		Student student = studentService
				.selectById(id)
				.setAge(s.getAge())
				.setName(s.getName());

		boolean isUpdate = studentService.updateById(student);
		return isUpdate ? ApiResponse.ofMessage("成功更新一个角色!") : ApiResponse.ofMessage("更新失败!");
	}

	/**
	 * Delete student api response.
	 *
	 * @param id the id
	 * @return the api response
	 */
	@ApiOperation("删除学生")
	@DeleteMapping("/{id}")
	public ApiResponse deleteStudent(@PathVariable Long id) {

		boolean isDelete = studentService.deleteById(id);
		return isDelete ? ApiResponse.ofMessage("成功删除一个角色!") : ApiResponse.ofMessage("删除失败!");
	}
}