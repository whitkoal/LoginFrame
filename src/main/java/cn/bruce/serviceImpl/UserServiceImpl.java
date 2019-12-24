package cn.bruce.serviceImpl;

import cn.bruce.dao.UserDao;
import cn.bruce.pojo.User;
import cn.bruce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
