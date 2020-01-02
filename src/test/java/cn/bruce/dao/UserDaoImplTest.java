package cn.bruce.dao;

import cn.bruce.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void findAll() {
        mongoTemplate.findAll(User.class);
    }

    @Test
    void getUser() {
    }

    @Test
    void update() {
    }

    @Test
    void insert() {
        User user = new User();
        user.setName("bruce");
        user.setPassword("bruce");
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        User u = mongoTemplate.insert(user);
        System.out.println(u.toString());
    }

    @Test
    void insertAll() {
    }

    @Test
    void remove() {
    }

    @Test
    void findByPage() {
    }

    @Test
    void getUserByName() {
    }
}