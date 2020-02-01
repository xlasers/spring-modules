package com.xlaser4j.demo;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2020/2/1 16:31
 * @description:
 * @modified: Elijah.D
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApplicationTest {

    /// webEnvironment默认是mock环境,但是TestRestTemplate需要真实servlet环境,可以设置成defined/random

    /// Mockito等单元测试参考:https://github.com/xlaser4j/junit4j
}
