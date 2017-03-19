package com.statestr.core.service;

import com.statestr.core.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by ruantianbo on 2017/3/19.
 */
public class UserServiceImpl implements UserService{
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Override
    public boolean checkUserLogin(String userName, String pwd) {

        return false;
    }
}
