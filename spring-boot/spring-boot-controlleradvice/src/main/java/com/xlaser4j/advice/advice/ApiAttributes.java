package com.xlaser4j.advice.advice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @package: com.xlaser4j.advice.advice
 * @author: Elijah.D
 * @time: 2020/1/19 13:50
 * @description: 设置controller全局参数
 * @modified: Elijah.D
 */
@ControllerAdvice
public class ApiAttributes {
    /**
     * set map
     *
     * @return map
     */
    @ModelAttribute(value = "map")
    public Map<String, Integer> setMap() {
        Map<String, Integer> map = new HashMap<>(1);
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 2);
        return map;
    }

    /**
     * set list
     *
     * @return list
     */
    @ModelAttribute(value = "list")
    public List<Integer> setList() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        return list;
    }
}
