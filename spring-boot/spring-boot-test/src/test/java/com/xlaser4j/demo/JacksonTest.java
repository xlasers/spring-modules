package com.xlaser4j.demo;

import java.io.IOException;

import com.xlaser4j.demo.model.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2020/2/1 17:17
 * @description:
 * @modified: Elijah.D
 */
@JsonTest
@RunWith(SpringRunner.class)
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class JacksonTest {
    @Autowired
    private JacksonTester<User> jacksonTester;

    @Test
    public void testSerialize() throws IOException {

        // Serialization
        User user = new User(1, "Elijah", 20);
        JsonContent<User> content = jacksonTester.write(user);
        System.out.println(content);

        // 注意pom配置将json文件打包编译
        Assertions.assertThat(content).isEqualToJson("json/user.json");

        // Contain Name
        Assertions.assertThat(content).hasJsonPathStringValue("@.name");

        // Contain Name And Value = Elijah
        Assertions.assertThat(content).extractingJsonPathStringValue("@.name").isEqualTo("Elijah");
    }

    @Test
    public void testDeserialize() throws IOException {

        // Deserialization
        String content = "{\"id\": 1,  \"name\": \"Elijah\",  \"age\": 20}";
        User user = jacksonTester.parseObject(content);
        System.out.println(user);

        Assertions.assertThat(user.getAge()).isEqualTo(20);
    }
}
