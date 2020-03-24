package com.cjl.miaosha.service;

import com.cjl.miaosha.dao.UserDao;
import com.cjl.miaosha.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cjl
 * @date 2020/3/24 10:28
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getById(Integer id){
       return userDao.getById(id);
    }
}
