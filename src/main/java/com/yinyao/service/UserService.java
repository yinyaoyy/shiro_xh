package com.yinyao.service;

import com.yinyao.dao.UserDao;
import com.yinyao.dao.classesMapper;
import com.yinyao.entity.Classes;
import com.yinyao.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private classesMapper classesMapper;
    public User getUserInfoByUserName(String username) {
        User user = userDao.getUserInfoByUserName(username);
        return  user;
    }
    public List<User> getRoleByUsername(String username){
        List<User> role = userDao.getRoleNameByUserName(username);
        return  role;
    }
    public Classes getClassesInfoByUserName(String cid) {
        Classes user = classesMapper.getClasses(cid);
        return  user;
    }
}
