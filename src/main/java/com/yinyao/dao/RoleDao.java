package com.yinyao.dao;

import com.yinyao.entity.User;

import java.util.List;

public interface RoleDao {

    public List<String> getRoleNameByUserName(String username);
}
