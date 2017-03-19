package com.statestr.core.test.service;

import com.statestr.core.entity.AdminUserEntity;
import com.statestr.core.entity.UserEntity;
import com.statestr.core.util.SecurityUntil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by ruantianbo on 2017/3/11.
 */
public class UserServiceOOCTest extends BaseTest{

    @Autowired
    @Qualifier(value="sessionFactory")
    private SessionFactory sessionFactory;

    @Test
    public void testSpringTest(){
        System.out.print("done");
    }

    @Test
    @Rollback(false)
    @Transactional
    public void addtestAddUser(){
        Session session = sessionFactory.openSession();
        AdminUserEntity admin = new AdminUserEntity();
        admin.setUserName("xiekakaban");
        admin.setPassword(SecurityUntil.eccryptMD5("123456"));
        admin.setEmail("ruantianbo@163.com");

        UserEntity user1 = new UserEntity();
        user1.setUserName("tianyeling");
        user1.setPassword(SecurityUntil.eccryptMD5("123456"));
        user1.setEmail("357536041");
        user1.setMale(Boolean.TRUE);
        user1.setWeiChat("357536041");

        session.saveOrUpdate(admin);

        session.saveOrUpdate(user1);

        session.flush();

    }

    public void testCheckUserLogin(){

    }


}
