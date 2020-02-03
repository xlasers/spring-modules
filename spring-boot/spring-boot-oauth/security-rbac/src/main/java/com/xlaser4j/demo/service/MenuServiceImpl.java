package com.xlaser4j.demo.service;

import java.util.List;

import com.xlaser4j.demo.entity.Menu;
import com.xlaser4j.demo.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
 * @package: com.xlaser4j.demo.service
 * @author: Elijah.D
 * @time: 2020/2/3 14:20
 * @description:
 * @modified: Elijah.D
 */
@Service
public class MenuServiceImpl {
    private final MenuMapper menuMapper;

    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    /**
     * list menu
     *
     * @return
     */
    public List<Menu> listMenus() {
        return menuMapper.listMenus();
    }
}
