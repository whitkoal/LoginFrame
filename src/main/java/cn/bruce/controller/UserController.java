package cn.bruce.controller;

import cn.bruce.pojo.User;
import cn.bruce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "insert")
    public String insert() {
        System.out.println("insert-------------------");
        User user = new User();
        user.setName("wang");
        user.setPassword("123456");
        userService.insert(user);
        return "sucess";
    }

    @RequestMapping(value = "findAll")
    public List<User> findAll(){
        return userService.findAll();
    }
}
