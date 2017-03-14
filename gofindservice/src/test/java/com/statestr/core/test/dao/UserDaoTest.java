package com.statestr.core.test.dao;

import com.statestr.core.dao.UserDao;
import com.statestr.core.entity.UserEntity;
import com.statestr.core.test.service.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by ruantianbo on 2017/3/12.
 */
public class UserDaoTest extends BaseTest{

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Test
    public void testGetById(){
        String id = "402881ed5ac1e8a2015ac1e8a8030001";
        UserEntity u = userDao.get(id);
        System.out.println(u.getUserName());
        Assert.assertNotNull(u);
    }
}
