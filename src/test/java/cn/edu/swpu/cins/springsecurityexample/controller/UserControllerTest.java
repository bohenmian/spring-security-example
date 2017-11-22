package cn.edu.swpu.cins.springsecurityexample.controller;

import cn.edu.swpu.cins.springsecurityexample.model.persistence.User;
import cn.edu.swpu.cins.springsecurityexample.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @MockBean
    private UserService userService;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void test_when_getUser_success() throws Exception {
        List<User> list = Collections.emptyList();
        when(userService.getUser()).thenReturn(list);
        String url = "/user";
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void test_when_getUserInfo_success() throws Exception {
        User user = new User();
        user.setId(Long.valueOf(1));
        user.setUsername("jackson");
        user.setPassword("password");
        when(userService.getUserInfo(Long.valueOf(1))).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("jackson"));
    }

    @Test
    public void test_when_getUserInfo_fail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

}