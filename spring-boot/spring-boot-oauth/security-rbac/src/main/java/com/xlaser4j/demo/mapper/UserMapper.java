package com.xlaser4j.demo.mapper;

import java.util.List;

import com.xlaser4j.demo.entity.Role;
import com.xlaser4j.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @package: com.xlaser4j.demo.mapper
 * @author: Elijah.D
 * @time: 2020/2/2 20:03
 * @description:
 * @modified: Elijah.D
 */
@Mapper
public interface UserMapper {
    /**
     * get user
     *
     * @param name
     * @return
     */
    User getUserByName(String name);

    /**
     * get roles
     *
     * @param uId
     * @return
     */
    List<Role> listRolesByUserId(Integer uId);
}
