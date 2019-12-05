package com.xalser4j.prop;

import com.xlaser4j.prop.Company;
import com.xlaser4j.prop.PropApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @package: com.xalser4j.prop
 * @author: Elijah.D
 * @time: 2019/12/5 10:53
 * @description:
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PropApplication.class)
public class CompanyTest {
    @Autowired
    private Company company;

    @Test
    void testProp() {
        System.out.println(company);
    }
}
