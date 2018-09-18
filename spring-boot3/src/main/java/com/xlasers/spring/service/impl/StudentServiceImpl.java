package com.xlasers.spring.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xlasers.spring.mapper.StudentMapper;
import com.xlasers.spring.model.Student;
import com.xlasers.spring.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * The type Student service.
 *
 * @package: com.xlasers.spring.service.impl
 * @author: Elijah.D
 * @time: CreateAt 2018/9/29 && 14:28
 * @description: service实现
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
}
