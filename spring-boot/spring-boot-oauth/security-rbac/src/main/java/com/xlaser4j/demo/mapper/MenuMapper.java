package com.xlaser4j.demo.mapper;

import java.util.List;

import com.xlaser4j.demo.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @package: com.xlaser4j.demo.mapper
 * @author: Elijah.D
 * @time: 2020/2/3 14:04
 * @description:
 * @modified: Elijah.D
 */
@Mapper
public interface MenuMapper {
    /**
     * list
     *
     * @return
     */
    List<Menu> listMenus();
}
