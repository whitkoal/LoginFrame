package cn.bruce.service;

import cn.bruce.pojo.User;

import java.util.List;

public interface UserService {
    public void insert(User user);
    List<User> findAll();
}
