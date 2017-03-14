package com.statestr.core.dao;

import com.statestr.core.entity.UserEntity;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * Created by ruantianbo on 2017/3/12.
 */
@Repository("userDao")
public class UserDao extends GenericHibernateDao<UserEntity,String> implements IUserDao{


}
