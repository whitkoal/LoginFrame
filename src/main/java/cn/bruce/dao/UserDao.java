package cn.bruce.dao;

import cn.bruce.pojo.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserDao {
    List<User> findAll();

    User getUser(Integer id);

    void update(User user);

    void insert (User user);

    void insertAll(List<User> users);

    void remove(Integer id);

    List<User> findByPage(User user, Pageable pageable);

    User getUserByName(String username);

    User getUserByMobile(String mobile);
}
