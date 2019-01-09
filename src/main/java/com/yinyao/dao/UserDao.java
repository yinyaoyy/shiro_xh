package com.yinyao.dao;

import com.yinyao.entity.User;

import java.util.List;

public interface UserDao {
    User getUserInfoByUserName(String username);
    public List<User> getRoleNameByUserName(String username);
}
