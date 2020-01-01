package cn.bruce.controller;

import cn.bruce.pojo.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import cn.bruce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RestController
@RequestMapping("/user")
class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    // 在测试之前构造模拟mcv环境
    @BeforeAll
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public String insert() {
        // mockMvc.perform(MockMvcRequestBuilders.get("/user/insert"))
                

        System.out.println("insert-------------------");
        User user = new User();
        user.setName("bruce");
        user.setPassword("bruce");
        user.setStore("admin");
        user.setAuthorities(new HashSet<>());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        userService.insert(user);
        return "success";
    }

    @Test
    void findAll() {
    }
}