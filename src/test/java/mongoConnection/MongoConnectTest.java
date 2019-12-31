package mongoConnection;

import cn.bruce.pojo.User;
import cn.bruce.service.UserService;
import cn.bruce.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public class MongoConnectTest {
    @Autowired
    private UserService userService;

    @Test
    public String insert() {
        System.out.println("insert-------------------");
        User user = new User();
        user.setName("wang");
        user.setPassword("123456");
        userService.insert(user);
        return "sucess";
    }

    public List<User> findAll(){
        return userService.findAll();
    }

}
