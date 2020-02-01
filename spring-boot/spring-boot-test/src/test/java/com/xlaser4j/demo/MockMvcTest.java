package com.xlaser4j.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xlaser4j.demo.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @package: com.xlaser4j.demo.controller
 * @author: Elijah.D
 * @time: 2020/2/1 17:14
 * @description:
 * @modified: Elijah.D
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MockMvcTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGet() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/a")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "Elijah"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testPost() throws Exception {

        // Json参数利用jackson转换object为json字符串
        User user = new User(1, "Elijah", 18);
        String jsonStr = new ObjectMapper().writeValueAsString(user);
        System.out.println(jsonStr);

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/b")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }
}
