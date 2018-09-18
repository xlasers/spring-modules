package com.xlasers.spring.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xlasers.spring.model.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * The interface Student mapper.
 *
 * @package: com.xlasers.spring.mapper
 * @author: Elijah.D
 * @time: CreateAt 2018/9/29 && 14:19
 * @description: mapper类
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
