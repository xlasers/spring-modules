package com.xlasers.spring.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * The type Student.
 *
 * @package: com.xlasers.spring.model
 * @author: Elijah.D
 * @time: CreateAt 2018/9/29 && 14:20
 * @description: 模型
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@Data
@Accessors(chain = true	)
@ApiModel("Student:学生")
@EqualsAndHashCode(callSuper = true)
public class Student extends Model<Student> {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(hidden = true)
    private Long id;

    /**
     * name
     */
    @ApiModelProperty("name:名字")
    private String name;

    /**
     * age
     */
    @ApiModelProperty("age:年龄")
    private Integer age;

    /**
     * 主键值
     *
     * @return
     */
    @Override
    protected Serializable pkVal() {
        return id;
    }
}
